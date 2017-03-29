package week1_0306;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import week1_0306.BinaryTree.BinaryTreeNode;


public class Test_BTree 
{

	@Test
	public void test() 
	{
		BinaryTree btree=new BinaryTree();
		ScoreRecord s1=new ScoreRecord(8,"Tom",98,99,88);
		ScoreRecord s2=new ScoreRecord(2,"Jack",90,99,80);
		ScoreRecord s3=new ScoreRecord(5,"Merry",93,90,90);
		ScoreRecord s4=new ScoreRecord(9,"Mike",88,95,80);
		ScoreRecord s5=new ScoreRecord(3,"Jerry",92,80,78);
		ScoreRecord s6=new ScoreRecord(7,"Jason",91,87,83);
		
		Comparator c= new ScoreRecord();
		
		btree.insert(s1,c);
		btree.insert(s2,c);
		btree.insert(s3,c);
		btree.insert(s4,c);
		btree.insert(s5,c);
		btree.insert(s6,c);
		
		
		btree.printTree();
	}
	
	

}
