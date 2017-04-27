package structure.week7;
import structure.week1.Stack;
// 使用表达式树来完成这个运算
public class InfixExpr {
	String expr = null;
	Element getElem = null;
	public InfixExpr(String expr) {
		this.expr = "0+"+expr; // 由于遇到优先级变化会生成单独节点，必须以最低优先级开头
        getElem = new Element(expr);
	}
	public float evaluate() throws Exception{
        Node root = createNode(null);
		return (float) getValue(root);
	}
	public double evaluate_stack(){
		return 0.0;
	}
    class Element{
        private int index;
        private String str;
        public Element(String _str){
            index = 0;
            str = _str;
        }
        public double peekNextNum(){
            int lastindex = index;
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
            index = lastindex;
            return resl+resr;
        }
        public double getNextNum() throws Exception{
            if(!hasNext()) {
            	int a = index -2;
            	throw new Exception("表达式格式错误，在位置"+a+"处缺少操作数。");
            }
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
        public char peekNextOper(){
            int lastindex = index;
            char ch = '\0';
            while(hasNext()){
                ch = str.charAt(index);
                index += 1;
                if(ch=='+'||ch=='-'||ch=='*'||ch=='/') break;
                ch = '\0';
            }
            index = lastindex;
            return ch;
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
    private Node createNode(Node node) throws Exception{
        if(getElem.peekNextOper() == '\0') return node;
        if(node == null){
            node = new Node('\0',getElem.getNextNum());
        }
        char ch = getElem.getNextOper();
        Node root = new Node(ch,0);
        root.left = node;
        char chn = getElem.peekNextOper();
        if(operUpDowm(ch,chn)<0){ // 操作符优先级提升
            root.right = createNode(null);
        }else if(operUpDowm(ch,chn)>0){ // 操作符优先级下降
            root.right = new Node('\0',getElem.getNextNum());
            return root;    // 这里是遍历完一个连续乘除法，需要返回节点，考虑5-2*3-4，不返回会导致5-2*3+4
        }else{
            root.right = new Node('\0',getElem.getNextNum());
        }
        return createNode(root);
    }
    private int operUpDowm(char c1,char c2){
        int temp1 = 0,temp2=0;
        if(c1=='+'||c1=='-')temp1 = 0;
        else if(c1=='*'||c1=='/')temp1 = 1;
        if(c2=='+'||c2=='-')temp2 = 0;
        else if(c2=='*'||c2=='/')temp2 = 1;
        return temp1-temp2;
    }
    private double getValue(Node root) throws Exception{
        if(root == null) throw new Exception("解析表达式出现异常");
        switch(root.op){
        case '+':
            return getValue(root.left)+getValue(root.right);
		case '-':
            return getValue(root.left)-getValue(root.right);
		case '*':
            return getValue(root.left)*getValue(root.right);
		case '/':
            return getValue(root.left)/getValue(root.right);
        case '\0':
            return root.val;
		default:
            throw new Exception("目前还不支持 "+new StringBuilder().append(root.op).toString()+" 运算符。");
        }
    }
}
