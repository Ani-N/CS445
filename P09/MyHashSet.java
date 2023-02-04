import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	static int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // **DO NOT CHANGE THIS NUMBER**

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	public boolean add( String key )
	{
		// your code here to add the key to the table and ++ your size variable
		//if(contains(key)) return false;

		int h = hashOf(key, numBuckets);
		Node curr = bucketArray[h];
		while(curr != null)
		{
			if (key.equals(curr.data)) return false;
			curr = curr.next;
		}
		bucketArray[h] = new Node(key, bucketArray[h]);

		++size; // you just added a key to one of the lists
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}

	public boolean remove( String key ) // if not found return false else remove & return true
	{
		int h = hashOf(key, numBuckets);
		Node curr = bucketArray[h];
		
		if(curr == null) return false;
		
		if (key.equals(curr.data))
		{
			bucketArray[h] = bucketArray[h].next;
			size--;
			//System.out.println("SIZE: " + size);
			return true;
		}

		while(curr.next != null)
		{
			String nextWord = curr.next.data;
			if (nextWord.equals(key))
			{
				curr.next = curr.next.next;
				size--;
				//System.out.println(size);
				return true;
			}
			curr = curr.next;
		}
		//System.out.println("remove failed");
		return false;  // just to make it compile.
		// You hash this key. goto that list. look for this key in that list
	}

	public boolean contains( String key )
	{	
		int h = hashOf(key, numBuckets);
		Node curr = bucketArray[h];
		while(curr != null)
		{
			if (key.equals(curr.data)) return true;
			curr = curr.next;
		}
		return false;  // just to make it compile.
		// You hash this key. goto that list. look for this key in that list
	}

	public boolean isEmpty() // use the call to size
	{
		return (size == 0);
	}

	public int size()
	{
		return this.size;
	}

	public void clear()
	{
		size=0;
		bucketArray = new Node[numBuckets];
	}

	private void upSize_ReHash_AllKeys()
	{	
		System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;
		
		for(Node curr : bucketArray)
		{
			while(curr != null)
			{
				int h = hashOf( curr.data, biggerArray.length );
				biggerArray[h] = new Node(curr.data, biggerArray[h]);
				curr = curr.next;
			}
		}
		/*
		FOR EACH LIST IN THE ARRAY
			FOR EACH NODE IN THAT LIST
				HASH THAT KEY INTO THE BIGGER TABLE
				BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
		*/

		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH
	
	
	private int hashOf( String key, int numBuckets ) // h MUST BE IN [0..numBuckets-1]
	{
		int returnVal = 0;
		for (char c : key.toCharArray())
		{
			returnVal += (c-'a' + 1);
			returnVal = (returnVal * primes[c-'a']);
		}
		return Math.abs(returnVal) % numBuckets;
	}
	
} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	int hRaw;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
}



