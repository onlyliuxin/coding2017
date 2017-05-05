package com.coderising.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class XmlParser {
	
	private File targetFile = null;
	private eNode root = null;
	
	public XmlParser(String path){
		setTargetFile(path);
		try {
			String content = loadFile(targetFile);
			parseString(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setTargetFile(String path){
		targetFile = new File(path);
		if(! targetFile.isFile() || ! targetFile.exists()){
			throw new IllegalArgumentException("Path:  " + path + " is NOT valid.");
		}
	}
	
	public File getFile(){
		return targetFile;
	}
	
	protected String loadFile(File f) throws FileNotFoundException{
		StringBuilder sb = new StringBuilder();
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		try{
		while((line = br.readLine()) != null){
			line = line.trim();
			if(!line.startsWith("#") && !line.startsWith("<?")){
				sb.append(line);
			}
		}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		return sb.toString();
	}
	
	protected eNode parseString(String str){
		Queue<String> cache = new LinkedList<>();
		int bracketStart=0;
		int contentStart=0;
		for(int i=0; i< str.length(); i++){
			if(str.charAt(i) == '<'){
				bracketStart = i;
				if(i>contentStart){
					String tagVal = str.substring(contentStart, i).trim();
					if(tagVal.length() > 0){
						//System.out.println("content: " + tagVal);
						cache.add(tagVal);
					}
				}
			}
			else if(str.charAt(i) == '>'){
				String elementTag = str.substring(bracketStart, i+1);
				//System.out.println("pushing: " + elementTag);
				cache.add(elementTag);
				contentStart=i+1;
				
			}
		}
		
		//debug information
		//dumpStack(cache);

		root = processElementStack(cache);
		//System.out.println();
		//System.out.println("Dump node tree");
		//dumpNodePreOrder(root);
		return root;
	}
	
	private eNode processElementStack(Queue<String> stk){

		if(!stk.isEmpty()){
			String token = stk.remove();
			if(token.startsWith("</")){
				System.out.println("should not reach here");
			}
			else if(token.startsWith("<")){
				eNode newNode = createBranchNode(token);
				while(!stk.peek().startsWith("</")){
					newNode.subNodes.add(processElementStack(stk));
				}
				stk.remove();
				return newNode;
			}
			else{
				return createLeafNode(token);
			}
		}
		return null;
	}
	
	private eNode createLeafNode(String str){
		eNode newNode = new eNode();
		newNode.element_raw_content = str;
		newNode.isLeaf = true;
		return newNode;
	}
	
	private eNode createBranchNode(String str){
		eNode newNode = parseRawData(str);
		return newNode;
	}
	
	private eNode parseRawData(String str){
		boolean foundAttributeName = false;
		String attr_name= null;
		eNode branchNode = new eNode();
		//boolean foundAttributeValue = false;
		for(int i = 0; i<str.length(); i++){
			char c = str.charAt(i);
			if(c == '<'){
				for(int j = i; j<str.length();j++){
					if(str.charAt(j) == ' ' || str.charAt(j) == '>'){
						String elementName = str.substring(i+1, j);
						branchNode.element_name = elementName.trim();
						//System.out.println("element name: "  + elementName);
						i = j;
						break;
					}
				}
			}
			else if(Character.isAlphabetic(c) || c == '"'){
				
				if(!foundAttributeName){
					for(int j=i; j<str.length(); j++){
						if(str.charAt(j) == '='){
							attr_name = str.substring(i, j).trim();
							//System.out.println("attribute name: "  + attr_name);
							foundAttributeName = true;
							i = j;
							break;
						}
					}
				}
				else{
					for(int k = i; k<str.length(); k++){
						if(str.charAt(k) == ' ' || str.charAt(k) == '>'){
							String attr_val = str.substring(i, k).trim();
							if(attr_name != null && attr_val != null){
								//System.out.println("Element: " + branchNode.element_name + " put in: "  + attr_name + " " + attr_val);
								branchNode.attributeMap.put(attr_name, attr_val.replaceAll("\"", ""));
							}
							foundAttributeName = false;
							i = k;
							break;
						}
					}
				}

			}
		}
		//System.out.println();
		return branchNode;
	}

	public List<eNode> getActionNodeList(){
		return root.subNodes;
	}

	private void dumpNodeBFS(eNode root){
		Stack<eNode> stk = new Stack<eNode>();
		stk.push(root);
		while(!stk.isEmpty()){
			eNode cur = stk.pop();
			dump(cur, stk);
		}
		
	}
	
	public static void dumpNodePreOrder(eNode root){
		if(root.isLeaf){
			System.out.println("Leaf: "+root.element_raw_content);
		}
		else{
			System.out.println("Branch: " + root.element_name);
			Iterator<String> itr = root.attributeMap.keySet().iterator();
			while(itr.hasNext()){
				String key = itr.next();
				System.out.println("["+ key + "]=[" + root.attributeMap.get(key)+"]");
			}
		}
		for(eNode i : root.subNodes){
			dumpNodePreOrder(i);
		}
	}
	
	private void dump(eNode node, Stack<eNode> stk){
		System.out.println(node.element_raw_content);
		for(int i = 0 ; i< node.subNodes.size(); i++){
			stk.push(node.subNodes.get(i));
		}
	}
	
	private void dumpStack(Collection<String> cache){
		Iterator<String> itr = cache.iterator();
		while(itr.hasNext()){
			String item = itr.next();
			System.out.println(""+item+"");
		}
	}
	
	public void dump(){
		dumpNodePreOrder(root);
	}
	
//	private String parseOpenElement(String str){
//		
//	}
//	
//	private String parseCloseElment(String str){
//		
//	}
	
	
	
	public class eNode{
		public boolean isLeaf = false;
		public String element_raw_content = null;
		public HashMap<String, String> attributeMap = null;
		public String element_name = null;
		public List<eNode> subNodes = null;
		
		public eNode(){
			_init();
		}
		
		private void _init(){
			attributeMap = new HashMap<>();
			subNodes = new LinkedList<>();
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("element_name: ").append(element_name).append("\n");
			sb.append("isLeaf: ").append(isLeaf).append("\n");
			sb.append("name attr: ").append(attributeMap.get("name")).append("\n");
			if(attributeMap.get("class") != null)
			sb.append("class attr: ").append(attributeMap.get("class")).append("\n");
			return sb.toString();
		}
	}
	
//	protected class eGraph{
//		public eNode root = null;
//		
//		public eGraph(eNode root){
//			this.root = root;
//		}
//	}

}
