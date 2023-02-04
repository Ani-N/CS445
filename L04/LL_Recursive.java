// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
 		return (search(key) != null); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST WILL BE IDENTICAL TO OTHER
	// MUST USE RECURSION. THIS MUST BE A DEEP COPY OF THE OTHER LIST INTO THIS LIST
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public LL_Recursive( LL_Recursive<T> other )
	{
		head = null;
		copyHelper( other.head );  // got you started here
	}
	private void copyHelper( Node<T> otherHead )
	{
		if(otherHead.next != null)
		{
			copyHelper(otherHead.next);
		}
		insertAtFront(otherHead.data);
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		if(head == null) insertAtFront(data);
		else
		{
			Node<T> tail = new Node<T>(data);
			tailHelper(head).next = tail;// YOUR CODE HERE. MUST USE insertAtFront() IN BASE CASE
		}
	}
	private Node<T> tailHelper( Node<T> otherHead )
	{
		if(otherHead.next == null) return(otherHead);
		return (tailHelper(otherHead.next));
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public int size()
	{
		return sizeHelper(head); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
	private int sizeHelper( Node<T> otherHead )
	{
		if(otherHead == null) return 0;
		return (1+sizeHelper(otherHead.next));
	}

	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		
		if(head == null) return "";
		String retString = "";


		return (stringHelper(head) + "\n");
	}
	private String stringHelper(Node<T> otherHead)
	{
		String toString = "";
		if(otherHead.next == null) toString = (otherHead.data).toString();
		else
		{
			toString = (otherHead.data).toString() + " -> " + stringHelper(otherHead.next);
			
		}
		return toString;
	}

	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		if(head == null) return null;
		return searchHelper(head, key); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
	private Node<T> searchHelper( Node<T> otherHead, T key )
	{
		if(otherHead.next == null) return null;
		if(otherHead.data.equals(key)) return otherHead;
		return searchHelper(otherHead.next, key);
	}
} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS