package structure.week7;
// 姹傝В琛ㄨ揪寮忓瓧绗︿覆锛岄鍏堝皢琛ㄨ揪寮忓垱寤烘垚涓�５琛ㄨ揪寮忔爲锛岄渶瑕佹牴鎹繍绠楃鐨勪紭鍏堢骇鏉ュ垱寤�
public class InfixExpr {
	String expr = null;
	Element getElem = null;
	public InfixExpr(String expr) {
		this.expr = expr;
        getElem = new Element(expr);
	}
	public float evaluate() throws Exception{
        Node node = new Node('\0',getElem.getNextNum());
        Node root = createNode(node);
		return (float) getValue(root);
	}
    class Element{
        private int index;
        private String str;
        public Element(String _str){
            index = 0;
            str = _str;
        }
        public double getNextNum(){
            double resl = 0,resr=0;
            int fbits = 0;
            char ch = str.charAt(index);
            boolean hasp = false;
            while(ch=='.' || (ch<='9' && '0'<=ch)){
                index += 1;
                if(ch == '.') hasp = true;
                else{
                    if(hasp){
                        fbits -= 1;
                        resr += (double)(ch-'0') * Math.pow(10,fbits);
                    }else{
                        resl *= 10;
                        resl += (ch-'0');
                    }
                }
                if(hasNext()) ch = str.charAt(index);
                else break;
            }
            return resl+resr;
        }
        public char getNextOper(){
            if(hasNext()){
                char ch = str.charAt(index);
                if((ch>='0' && '9'>=ch) || ch=='.') return '\0';
                else{
                    index += 1;
                    return ch;
                }
            }else{
                return '\0';
            }
        }
        public boolean hasNext(){
            return index<str.length();
        }
    }
    class Node{
        char op;
        double val;
        Node left,right;
        Node(char o,double v){
            left = null;
            right = null;
            op = o;
            val =v;
        }
    }
    private Node createNode(Node root){
        char ch = getElem.getNextOper();
        if(ch == '\0'){
            
        }else{
            
        }
        Node node = new Node(getElem.getNextOper(),0);
        node.left = root;
        node.right = null;
        // todo 
        return node;
    }
    private double getValue(Node root) throws Exception{
        if(root == null) throw new Exception("表达式非法");
        switch(root.op){
          case '+':
            return getValue(root.left)+getValue(root.right);
		case '-':
            return getValue(root.left)-getValue(root.right);
		case '*':
            return getValue(root.left)*getValue(root.right);
		case '/':
            return getValue(root.left)/getValue(root.right);
		default:
            return root.val;
        }
    }
}
