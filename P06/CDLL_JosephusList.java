import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	public void insertAtTail(T data)
	{
		if(head == null) 
		{
			head = new CDLL_Node<T>(data); // SEE THE LECTURE VIDEO HOW TO WRITe THIS FUNCTION 
		}
		else if(head.next == null)
		{
			CDLL_Node<T> newNode = new CDLL_Node<T>(data, head, head);
			head.next = newNode;
			head.prev = newNode;
		}
		else
		{
			CDLL_Node<T> tail = head.prev;
			CDLL_Node<T> newNode = new CDLL_Node<T>(data, tail, head);
			tail.next = newNode;
			head.prev = newNode;
		}
	}
	
	public int size()
	{
		if(head == null) return 0;
		else if (head.next == null) return 1;
		else
		{
			int count = 1;
			CDLL_Node<T> curr = head;
			while(!curr.next.equals(head))
			{
				count++;
				curr = curr.next;
			}
			return count;
		}
		
		//return 0;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	@SuppressWarnings("unchecked")
	public CDLL_Node<T> search( T key )
	{
		if(head == null) return null;
		if(head.data.equals(key)) return head;
		CDLL_Node curr = head.next;
		while(!curr.equals(head))
		{
			//System.out.println("line104");
			if(curr.data.equals(key)) return curr;
			curr = curr.next;
		}
		return null;
	}

	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL

	public String toString()
	{
		String toString = "";
		if(head == null) return toString;
		toString += head.toString();

		{
			CDLL_Node<T> curr = head;
			while((!curr.next.equals(head))&& curr.next != null)
			{
				curr = curr.next;
				toString += "<=>"+curr.toString();
			}
		}
		return toString;
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		CDLL_Node<T> curr = search(deadNode.data);
		CDLL_Node<T> beforeCurr = curr.prev;
		CDLL_Node<T> afterCurr = curr.next;

		afterCurr.prev = beforeCurr;
		beforeCurr.next = afterCurr;
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		
		String direction = "";
		if(skipCount > 0) direction = "CLOCKWISE";
		else direction = "COUNTER_CLOCKWISE";

		while (size() > 1 )
		{

			// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
			//do

			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();
			
			System.out.println( "stopping on " + curr.data + " to delete " + curr.data);
			
			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS 
			if(skipCount > 0) curr = curr.next;
			else curr = curr.prev;

			if(head == deadNode) head = curr;
			// 1: you gotta move that curr off of the deadNode. 
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
			// 2: check to see if HEAD is pointing to the deadnode. 
			//    If so make head=curr 

			removeNode(deadNode);			
			// NOW DELETE THE DEADNODE

			System.out.println("deleted. list now: " + toString() ); // toString prints the
			
			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
	
			System.out.println("resuming at "+curr.data+" , skipping " + curr.data + " " + (Math.abs(skipCount)-1)+ " nodes " +direction+ " after");
			
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
			for(int i = 0; i < Math.abs(skipCount); i++)
			{
				//System.out.println("line 161");
				if(skipCount > 0) curr = curr.next;
				else curr = curr.prev;
			}

			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			//Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();   
			
		}
		//while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}
	public class CDLL_Node<T>
	{
		private T data;
		private CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

		public CDLL_Node()
		{
			this( null, null, null );  // 3 FIELDS TO INIT
		}

		public CDLL_Node(T data)
		{
			this( data, null, null);
		}

		public CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
		{
			setData( data );
			setPrev( prev );
			setNext( next );
		}

		public T getData()
		{
			return data;
		}

		public CDLL_Node<T> getPrev()
		{
			return prev;
		}
		public CDLL_Node<T> getNext()
		{
			return next;
		}

		public void setData(T data)
		{
			this.data = data;
		}

		public void setNext(CDLL_Node<T> next)
		{
			this.next = next;
		}
		
		public void setPrev(CDLL_Node<T> prev)
		{
			this.prev = prev;
		}
		
		public String toString()
		{
			return ""+getData();
		} 
			
	} //EOF
	
} // END CDLL_LIST CLASS

// COPY THE NODE CLASS INTO HERE THEN DELETE YOUR NODE.JAVA file 
// REMOVE ALL PUBLIC AND REMOVE SETTERS GETTERS
// LEAVE PUBLIC ONLY ON TOSTRING