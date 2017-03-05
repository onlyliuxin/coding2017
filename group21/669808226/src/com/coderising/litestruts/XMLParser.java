package com.coderising.litestruts;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	private Document doc;
	private NodeList nodes;
	private Node currentNode;
	
	public Node getNode()
	{
		return currentNode;
	}
	
	public NodeList getNodeList()
	{
		return nodes;
	}
	
	public Document getDocument()
	{
		return doc;
	}
	
	public XMLParser(){}
	
	public void Parse(String uri) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			this.doc = db.parse(uri);
			nodes = this.doc.getChildNodes();
			if(nodes.getLength() < 1)
			{
				throw new Exception("Target is empty!");
			}
			this.currentNode = nodes.item(0);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw(e);
		}
	}
	
	public XMLParser GetNodesByName(String name)
	{
		this.nodes = this.doc.getElementsByTagName(name);
		return this;
	}
	
	public XMLParser FindNodeByAttribute(String attrName, String attrValue) throws Exception
	{
		for(int i = 0; i < this.nodes.getLength(); ++i)
		{
			Node n = this.nodes.item(i);
			if(getAttributeByName(n, attrName).equals(attrValue))
			{
				this.currentNode =  n;
				return this;
			}
		}
		throw new Exception("Target node not found!");
	}
	
	public String getAttributeValue(String name)
	{
		return getAttributeByName(this.currentNode, name);
	}
	
	public XMLParser getChildNodes()
	{
		this.nodes = this.currentNode.getChildNodes();
		return this;
	}
	
	private String getAttributeByName(Node node, String name)
	{
		String result = "";
		NamedNodeMap attributes = node.getAttributes();
		if(attributes != null)
		{
			for (int j = 0; j < attributes.getLength(); j++) 
			{
				Node attrNode = attributes.item(j);
				if(attrNode.getNodeName().equals(name))
				{
					result = attrNode.getNodeValue();
				}
			}
		}
		return result;
	}
}
