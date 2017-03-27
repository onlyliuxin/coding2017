package org.wsc.coding.basic.tree_node;

import org.wsc.coding.basic.exception.NullElementException;
import org.wsc.coding.basic.exception.RepeatingElementException;

/**
 * BinaryTreeNode 二叉树结构
 *
 *
 * @author Administrator
 * @date 2017年2月26日下午5:47:32
 * @version v1.0
 *
 * @param <E>
 *            必须实现Comparable接口
 */
@SuppressWarnings("rawtypes")
public class BinaryTreeNode<E extends Comparable> {

	/** 左节点 */
	private BinaryTreeNode<E> left;
	/** 数据区 */
	private E data;
	/** 右节点 */
	private BinaryTreeNode<E> right;

	/**
	 * 插入
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BinaryTreeNode<E> insert(E data) {
		if (data == null)
			throw new NullElementException("Do not insert a null");
		// 当前数据区为空,则将data放入数据区
		if (this.data == null) {
			this.data = data;
			return this;
		}
		// 对比当前数据区数据和data大小
		int result = this.data.compareTo(data);
		// 如果相等，则抛出异常
		if (result == 0)
			throw new RepeatingElementException("Do not insert duplicate element");
		// 当前数据区数据大于data，将data递归放入左节点
		if (result > 0) {
			// 左节点为空，则将数据置入左节点
			if (left == null)
				left = new BinaryTreeNode<E>(data);
			else// 左节点不为空，则将数据递归置入左节点
				left.insert(data);
		} else {
			// 右节点为空，则将数据置入右节点
			if (right == null)
				right = new BinaryTreeNode<E>(data);
			else// 右节点不为空，则将数据递归置入右节点
				right.insert(data);
		}
		return this;
	}

	/**
	 * 查询
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BinaryTreeNode<E> seek(E data) {
		checkCurrElement();
		if (data == null)
			return null;
		// 对比当前数据区数据和data大小
		int result = this.data.compareTo(data);
		if (result == 0) {
			return this;
		} else if (result > 0) {// 当前数据区数据大于data，递归对比左节点
			return left == null ? null : left.seek(data);
		} else {// 当前数据区数据小于data，递归对比右节点
			return right == null ? null : right.seek(data);
		}

	}

	/**
	 * 删除
	 * 
	 * @param data
	 * @return
	 */
	public BinaryTreeNode<E> remove(E data) {
		return removeChild(null, data);
	}

	@SuppressWarnings("unchecked")
	public BinaryTreeNode<E> removeChild(BinaryTreeNode<E> supNode, E data) {
		checkCurrElement();
		if (data == null)
			return null;
		// 对比当前数据区数据和data大小
		int result = this.data.compareTo(data);
		// 如果相同，将通过父节点将子节点引用置为null
		if (supNode != null && result == 0) {
			if (supNode.left == this)
				supNode.left = null;
			else
				supNode.right = null;
		} else if (result > 0) {// 当前数据区数据大于data，递归对比左节点
			return left == null ? null : left.removeChild(this, data);
		} else {// 当前数据区数据小于data，递归对比右节点
			return right == null ? null : right.removeChild(this, data);
		}
		return this;
	}

	/**
	 * 检查当前节点元素是否有效
	 */
	private void checkCurrElement() {
		if (this.data == null)
			throw new NullElementException("The current node element is null");
	}

	public BinaryTreeNode() {
		super();
	}

	public BinaryTreeNode(E data) {
		super();
		this.data = data;
	}

	public BinaryTreeNode(BinaryTreeNode<E> left, E data, BinaryTreeNode<E> right) {
		super();
		this.left = left;
		this.data = data;
		this.right = right;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}

}
