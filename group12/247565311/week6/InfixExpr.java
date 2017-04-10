package week6;

public class InfixExpr {
	String expr = null;
    Node node = new Node();
	Element getElem = null;
	public InfixExpr(String expr) {
		this.expr = expr;
        getElem = new Element(expr);
	}
	public float evaluate() {
        Node node = new Node('\0',getElem.getNextNum());
        Node root = createNode(node);
		return getValue(root);
	}
    class Element{
        private int index;
        private String str
        public Element(String _str){
            index = 0;
            str = _str;
        }
        public double getNextNum(){
            double resl = 0,resr;
            int fbits = 0;
            char ch = str.charAt(index);
            boolean hasp = false;
            while(ch=='.' || (ch<='9' && '0'<=ch)){
                index += 1;
                if(ch == '.') hasp = true;
                else{
                    if(hasp){
                        fbits -= 1;
                        resr += (double)(ch-'0') * Math.pow(10,fbits)
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
        Node node = new Node(getElem.getNextOper,0);
        node.left = root;
        node.right = 
    }
    private double getValue(Node root){
        if(root == null) throw new Exception("inlegal operator found");
        switch(root.op){
          case '+':
            return getValue(root.left)+getValue(root.right);
            break;
          case '-':
            return getValue(root.left)-getValue(root.right);
            break;
          case '*':
            return getValue(root.left)*getValue(root.right);
            break;
          case '/':
            return getValue(root.left)/getValue(root.right);
            break;
          default:
            return root.val;
            break;
        }
    }
}
