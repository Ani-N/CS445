// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE ONTO THE END (CABOOSE) OF THE LIST
	public void insertAtTail(T data)
	{
		if(this.head == null)
		{
			insertAtFront(data);
		}
		else
		{
			Node<T> tail = new Node<T>(data);
		
			Node<T> curr = head;
			while(curr.getNext() != null)
			{
				curr = curr.getNext();
			}
			curr.setNext(tail);
		}


		// YOUR CODE HERE. MUST USE insertAtFront() IN BASE CASE
	}

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT WE MAKE YOU 
	// COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	public int size()
	{
		int count = 1;
		Node<T> curr = head;
		while(curr.getNext() != null)
		{
			curr = curr.getNext();
			count++;
		}
		return count; //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
	
	// MUST USE search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
	// NO LOOPS ALLOWED 
	public boolean contains( T key )
	{
		return(search(key) != null);
	}

	// TRAVERSE LIST FRONT TO BACK LOOKING FOR THIS DATA VALUE.
	// RETURN REF TO THE FIRST NODE THAT CONTAINS THIS KEY. 
	// DO NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	public Node<T> search( T key )
	{
		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			if(curr.getData().equals(key))
			{
				return curr;
			}
		}
		return null; //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}
} //END OF LINKEDLIST CLASS DEFINITION 