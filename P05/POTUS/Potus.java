import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		while(state2PresidentsFile.ready()) //fills the treemap with key (state): value (TreeSet of POTUSes) pairs
		{
			String [] fileLine = state2PresidentsFile.readLine().split("\\s+");
			TreeSet<String> presInLine = new TreeSet <String>();
			for(int i = 1; i < fileLine.length; i++) presInLine.add(fileLine[i]);
			state2Presidents.put(fileLine[0], presInLine);
		}

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		while(allPresidentsFile.ready())
		{
			allPresidents.add(allPresidentsFile.readLine());
		}




		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		while(allStatesFile.ready())
		{
			allStates.add(allStatesFile.readLine());
		}


		
		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");
		for(Map.Entry<String, TreeSet<String>> pair : state2Presidents.entrySet())
		{
			System.out.println(pair.getKey() + TS2String(pair.getValue()));
		}



		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");

		TreeSet<String> prezNoState = new TreeSet<String>();
		for(String prez : allPresidents)
		{
			boolean hasState = false;
			for(Map.Entry<String, TreeSet<String>> pair : state2Presidents.entrySet())
			{
				if(pair.getValue().contains(prez))
				{
					System.out.println(prez + " " + pair.getKey());
					hasState = true;
				}
			}
			if(!hasState) prezNoState.add(prez);
		}
	
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");

		for(String prez:prezNoState) System.out.println(prez);

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");	

		allStates.removeAll( state2Presidents.keySet() ) ;
		for(String state : allStates) System.out.println(state);
	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
	static String TS2String(TreeSet<String> set )
	{
		String toString = "";
		for(String item : set)
		{
			toString = toString + " " + item;
		}
		return toString;
	}


	
}	// END CLASS