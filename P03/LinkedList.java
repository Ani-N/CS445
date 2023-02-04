import java.io.*;
import java.util.*;

	// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		int count = 1;
		Node<T> curr = head;
		if (head == null){return 0;}
		while(curr.next != null)
		{
			curr = curr.next;
			count++;
		}
		return count; // YOUR CODE HERE
	}

	public boolean empty()
	{
		return(size() == 0);
	}
	public boolean contains( T key )
	{
		return (search(key) != null);  // YOUR CODE HERE
	}

	public Node<T> search( T key )
	{
		if(head == null){return null;}
		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			if(curr.data.equals(key))
			{
				return curr;
			}
		}
		return null;  // YOUR CODE HERE
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		if(head == null) {insertAtFront(data);}
		else
		{
			Node<T> curr = head;
			Node<T>	tail = new Node<T>(data);
			while(curr.next != null)
			{
				curr = curr.next;
			}
			curr.next = tail;
		}
		
		// YOUR CODE HERE
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		if(empty()) //inserting to empty list
		{
			insertAtFront(data);
			return;
		}
		if(head.data.compareTo(data)>0) //inserting if something belongs at front of list
		{
			insertAtFront(data);
			return;
		}
		Node<T> curr = head;
		Node<T> newNode = new Node<T>(data);
		while(curr.next != null) //loop thru list, making sure to stop at last element and not past it
		{
			if(data.compareTo(curr.next.data) < 0)
			{
				newNode.next = curr.next;
				break;
			}
			curr = curr.next;
		}		
		curr.next = newNode;
		// YOUR CODE HERE
	}

	public boolean remove(T key)
	{
		if(!contains(key)){return false;}
		if(size()==1)
		{
			head = null;
			return true;
		}
		if(head.data.equals(key))
		{
			head = head.next;
			return true;
		}
		if(search(key).next == null)
		{
			removeAtTail();
			return true;
		}

		Node<T> curr = head;

		while (curr.next != null)
		{
			if (curr.next.equals(search(key)))
			{
				curr.next = search(key).next;
				return true;
			}
			curr = curr.next;
		}
		return true;
		 //  REPLACE WITH YOUR CODE 
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(empty()){return false;}
		if(size() == 1)
		{
			head = null;
			return true;
		}
		Node<T> curr = head;
		Node<T> nextNode = curr.next;
		while (nextNode.next != null)
		{
			curr = curr.next;
			nextNode = curr.next;
		}
		curr.next = null; // YOUR CODE HERE
		return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(empty()){return false;}
		if(size() == 1)
		{
			head = null;
			return true;
		}

		head = head.next;
		return true; // YOUR CODE HERE
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();
		for(Node<T> curr = this.head; curr != null; curr = curr.next)
		{
			union.insertInOrder(curr.data);
		}
		
		for(Node<T> curr = other.head; curr != null; curr = curr.next)
		{
			if(!union.contains(curr.data))
			{union.insertInOrder(curr.data);
			}
		}

		// YOUR CODE HERE

		return union;
	}
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		for(Node<T> curr = this.head; curr != null; curr = curr.next)
		{
			if (other.contains(curr.data))
			{
				inter.insertInOrder(curr.data);
			}
		}

		// YOUR CODE HERE

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();
		for(Node<T> curr = this.head; curr != null; curr = curr.next)
		{
			if (other.contains(curr.data))
			{
				continue;
			}
			diff.insertInOrder(curr.data);
		}

		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  (this.union(other).diff(this.inter(other)));
		// REPLACE WITH YOUR CODE 

	}

} //END LINKEDLIST CLASS 

class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
  T data;
  Node<T> next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node<T> next)
  {
    this.data = data;
    this.next = next;
  }

  public String toString()
  {
	  return ""+this.data;
  } 
	 
} //EOF
// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C