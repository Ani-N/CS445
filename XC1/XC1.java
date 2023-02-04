/*
	THE STARTER FILE FOR 445 XC1  MISSING NUMBER PROBLEm
*/

import java.util.*;
import java.io.*;

public class XC1 // YOU DONT NEED A LINKED LIST CLASS. YOUR MAIN METHOD IS YOUR 'LINKEDLIST' CLASS
{
	public static void main(String[] args) throws Exception
	{	
		if ( args.length < 1 ) 
		{
			System.out.println("MISSING INPUT FILE ON CMD LINE\n");
			System.exit(0);
		}
		Scanner infile = new Scanner( new File( args[0] ) );
		
		// DO THE WHOLE PROGRAM INSIDE THIS LOOP
		Node<Integer> head = null;
		while ( infile.hasNextLine() ) // assume line ->  "1 9 2 8 3 10 7 4 6"  (i.e. its missing 5)
		{

			int sum = 0;

			String[]tokens = infile.nextLine().split("\\s+"); // tokens -> ["1"]["9"]["2"]["8"]["3"]["10"]["7"]["4"]["6"]
			//head = new Node<Integer> (Integer.parseInt(tokens[tokens.length-1]), head);
			String toPrint = "";//Integer.toString(head.data);

			Integer max = Integer.MIN_VALUE; //reset the max/min
			Integer min = Integer.MAX_VALUE;

			for( int i= (tokens.length-1) ; i >= 0 ; i--)
			{
				Integer number = Integer.parseInt( tokens[i] ); // convert. "7" to 7
				toPrint = number + " " + toPrint;
				if(number>max) max = number;
				if(number<min) min = number;
				head = new Node<Integer> (number, head);
				sum = sum+number;

				/*
				print the token +  " "
				insert onto front of list 
				keep running track of min max total or whatever how YOU do it 
				*/

			}
			// once outside the above for loop you can now do a few arithmetic operations and detemine the missing number 
			// you should not need any more loops
			int expected = (((tokens.length+1)*(min + max))/2);
			System.out.print(toPrint); //+ "expected: " + expected + "sum: " + sum);
			

			System.out.println("missing " + (expected-sum));//print out "missing 5" // or whatever the actual missing number is 
			
			head.next = null; //clear the list 
		} // END WHILE THERE IS A ANOTHER LINE IN THE FILE 
	} // END MAIN
} // END CLASS XC1

// YOU DO NEED A Node<Integer> CLASS DEFINITION DOWN HERE
class Node<Integer>
{ Integer data;
  Node<Integer> next;
  Node() { this( null, null ); }
  Node(Integer data){this( data, null ); }
  Node(Integer data, Node<Integer> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS	
		

