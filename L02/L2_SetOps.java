import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	static String[] union( String[] set1, String[] set2 )
	{
		String[] unionSet = new String[(set1.length + set2.length)];
		int count = 0;
		for(String set1Item : set1)
		{
			unionSet[count] = set1Item;
			count++;
		}
		for(String set2Item : set2)
		{
			if (!setContains(unionSet, set2Item))
			{
				unionSet[count] = set2Item;
				count++;
			}
		}

		return trimArray(unionSet, count); // change this to return a trimmed full array
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		String[] interSet = new String[set1.length];
		int count = 0;
		for(String set1Item : set1)
		{
			if (setContains(set2, set1Item))
			{
				interSet[count] = set1Item;
				count++;
			}
		}
		return trimArray(interSet, count); // change this to return a trimmed full array
	}

	static String[] difference( String[] set1, String[] set2 )
	{
		String[] diffSet = new String[set1.length];
		int count = 0;
		for(String set1Item : set1)
		{
			if (setContains(set2, set1Item))
			{
				continue;
			}
			diffSet[count] = set1Item;
			count++;
		}		
		return trimArray(diffSet, count); // change this to return a trimmed full array
	}

	static String[] xor( String[] set1, String[] set2 )
	{
		String[] xorSet = new String[(set1.length + set2.length)];
		int count = 0;
		for(String set1Item : set1)
		{
			if(setContains(set2, set1Item))
			{
				continue;
			}
			xorSet[count] = set1Item;
			count++;
		}
		for(String set2Item : set2)
		{
			if (!setContains(xorSet, set2Item) && !setContains(set1, set2Item))
			{
				xorSet[count] = set2Item;
				count++;
			}
		}
		return trimArray(xorSet, count); // change this to return a trimmed full array
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
	{
		String[] doubleArr = new String[(2*old.length)];
		for(int i = 0; i<old.length; i++)
		{
			doubleArr[i] = old[i];
		}
		return doubleArr; // you change accordingly
	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		String[] trimmedArr = new String[count];
		for(int i = 0; i<count; i++)
		{
			trimmedArr[i] = old[i];
		}
		
		return trimmedArr; // you change accordingly
	}

	static boolean setContains (String[] set, String s)
	{
		//use enhanced for loop, use .equals
		for(String setItem : set)
		{
			if((setItem != null) && (setItem.equals(s)))
			{
			return true;
			}
		}
		return false;
	}

} // END CLASS