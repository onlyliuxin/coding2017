/**   
* @Title: BinaryTree.java 
* @Description: 二叉排序树的实现
* @author glorychou
* @date 2017年2月25日 下午10:22:03 
*/
package per.zyf.bds;

/**
 * @author glorychou
 *
 */
public class BinaryTree<E extends Comparable<E>> {
	// 根节点
	private Node<E> root;
	// 树大小
	private int size;
	
	/** 
	* @Description: 在树中插入元素
	* @param e    节点数据
	* @return boolean    处理情况
	*/
	public boolean add(E e) {
		
		// 创建新节点
		final Node<E> newNode = new Node<>(null, e, null);
		
		// 按照二叉排序方式插入
		if (root != null) {
			Node<E> parentNode = null;
			Node<E> compareNode = root;
			
			while(compareNode != null) {
				// 新节点大于比较节点则插入右子树中
				if(e.compareTo(compareNode.item) > 0) {
					parentNode = compareNode;
					compareNode = compareNode.rightChild;
					
					if(compareNode == null)
						parentNode.rightChild = newNode;
				} else {// 新节点小于或等于比较节点则插入左子树中
					parentNode = compareNode;
					compareNode = compareNode.leftChild;
					
					if(compareNode == null)
						parentNode.rightChild = newNode;
				}
			}
		} else 
			root = newNode;
		
		size++;
		return true;
	}
	
	/** 
	* @Description: 中序遍历输出 
	* @return void    返回类型 
	*/
	public void inorderPrint(Node<E> e) {
		if(e == null) return;
		inorderPrint(e.leftChild);
		System.out.print(e.item.toString() + " ");
		inorderPrint(e.rightChild);
	}
	
	/** 
	* @Description: 判断树是否为空 
	* @return boolean    是否为空 
	*/
	public boolean isEmpty() {
		return size > 0 ? false : true;
	}
	
	/** 
	* @Description: 获取树的节点数 
	* @return int    树节点数
	*/
	public int size() {
		return size;
	}
	
	// 树节点
	private static class Node<E> {
		E item;
		Node<E> leftChild;
		Node<E> rightChild;
		
		Node(Node<E> l, E e, Node<E> r) {
			leftChild = l;
			item = e;
			rightChild = r;
		}
	}
}
