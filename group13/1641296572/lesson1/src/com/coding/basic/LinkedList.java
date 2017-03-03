package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List
{

	private Node head;
	private int size = 0;

	public void add(Object o)
	{
		if (0 == size)
		{
			head = new Node();
			head.data = o;
			head.next = null;
		} else
		{
			Node pNode = head;
			while (pNode.next != null)
			{
				pNode = pNode.next;
			}
			Node node = new Node();
			node.data = o;
			pNode.next = node;
			node.next = null;
		}
		size++;
	}

	public void add(int index, Object o)
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node node = new Node();
		node.data = o;

		if (index != 0)
		{
			Node nowNode = head;
			Node preNode = head;
			for (int i = 0; i < index; i++)
			{
				preNode = nowNode;
				nowNode = nowNode.next;
			}
			preNode.next = node;
			node.next = nowNode;
		} else
		{
			node.next = head;
			head = node;

		}
		size++;
	}

	public Object get(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i = 0; i < index; i++)
		{
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Object rt = null;
		if (index == 0)
		{
			rt = head.data;
			Node node = head;
			head = head.next;
			node.next = null;
			node.data = null;
		} else
		{
			Node preNode = head;
			Node nowNode = head;
			for (int i = 0; i < index; i++)
			{
				preNode = nowNode;
				nowNode = nowNode.next;
			}
			rt = nowNode.data;
			preNode.next = nowNode.next;
			nowNode.next = null;
			nowNode.data = null;
		}
		size--;
		return rt;
	}

	public int size()
	{
		return size;
	}

	public void addFirst(Object o)
	{
		Node node = new Node();
		node.data = o;
		node.next = head;
		head = node;

		size++;
	}

	public void addLast(Object o)
	{
		Node node = new Node();
		node.data = o;
		node.next = null;

		Node nowNode = head;

		if (size == 0)
		{
			head = node;
			size++;
			return;
		}

		while (nowNode != null && nowNode.next != null)
		{
			nowNode = nowNode.next;
		}

		nowNode.next = node;
		size++;
	}

	public Object removeFirst()
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}
		Node node = head;
		head = head.next;
		Object rt = node.data;
		node.next = null;
		node.data = null;
		size--;
		return rt;
	}

	public Object removeLast()
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		}

		Node nowNode = head;
		Node preNode = head;
		while (nowNode.next != null)
		{
			preNode = nowNode;
			nowNode = nowNode.next;
		}

		preNode.next = null;
		Object rt = nowNode.data;
		nowNode.data = null;
		size--;
		return rt;
	}

	public Iterator iterator()
	{
		return new Iterator()
		{
			Node node = head;

			@Override
			public boolean hasNext()
			{
				if (null != node)
				{
					return true;
				}
				return false;
			}

			@Override
			public Object next()
			{
				if (null == node)
				{
					throw new NoSuchElementException();
				}
				Object o = node.data;
				node = node.next;
				return o;
			}
		};
	}

	private static class Node
	{
		Object data;
		Node next;

	}
}