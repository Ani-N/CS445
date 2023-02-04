import java.io.*;
import java.util.*;

public class Tower<T>
{
	private Disk<T> base;  // pointer to first disk at BASE of the tower (i.e. the old head pointer)
	private Disk<T> top;   // pointer to last disk at TOP of the tower   (i.e. the old tail pointer)

	public Tower()
	{	base = null; // compiler does this anyway. just for emphasis
	}

	public boolean empty()
	{
		return (base==null);
	}

	// i.e. the old insertAtTail or now insertAtTop we call it a PUSH
	public void push(T label)
	{
		if(empty())
		{
			base = new Disk<T>(label);
			top = base;
			return; 
		}
		top.next = new Disk<T>(label);// YOU WRITE THIS METHOD
		top = top.next;
		//System.out.println("top: " + top.label);
	}

	// i.e. the old removeAtTail or now removeAtTop we call it a POP
	public Disk<T> pop() throws Exception
	{
		// YOU WRITE THIS METHOD
		// MUST THROW FATAL EXCEPTION IF TOWER IS EMPTY
		try
		{
			if (base.next == null) base = null;
			Disk<T> curr = base;
			while(curr.next.next != null)
			{
				curr = curr.next;
			}
			top = curr;
			curr = curr.next;
			top.next = null;
			return curr;
		}
		catch(NullPointerException e){return null;}
		// JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// prints the tower base to top, left to right,  respectively //
	public String toString()
	{	if (base == null ) 	return "EMPTY\t";
		String toString = "";
		for ( Disk<T> curr = base; curr != null ; curr=curr.next )
			toString += curr.label + " ";

		return toString;
	}
} // END TOWER CLASS

/*###############################################################################*/

class Disk<T>
{
	T label;
	Disk<T> next;

	Disk(T data)
	{	this( data, null );
	}

	Disk(T label, Disk<T> next)
	{	this.label = label;
		this.next = next;
	}

} // END DISK CLASS