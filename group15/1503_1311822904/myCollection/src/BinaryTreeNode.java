import java.util.Objects;

public class BinaryTreeNode {

	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	//TODO
    public BinaryTreeNode insert(Integer o){
      if(data==null){
          data=o;
          return this;
      }

        BinaryTreeNode b=new BinaryTreeNode();
        b.setData(o);

       if(Objects.equals(data, o)){
           return this;
       }
      if(data<o){
          if(left==null){
              left=b;
              return b;
          }
          left.insert(o);
      }
      if(data>o){
          if(right==null){
              right=b;
              return b;
          }
          right.insert(o);
      }
      return b;
    }

    public void showAll(){
	    if(right!=null){
            right.showAll();
        }
        System.out.print(data+" ");
	    if(left!=null){
	        left.showAll();
        }

    }



}
