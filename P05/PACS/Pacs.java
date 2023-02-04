import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());			
		memberToPacsFile.mark(200);
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED
		for( String curPac : allPacs)
		{
			TreeSet<String> memsInPac = new TreeSet<String>();
			//System.out.println("CUR PAC: " + curPac);
			while(memberToPacsFile.ready())
			{
				String[] splitLine = memberToPacsFile.readLine().split("\\s+"); //reads the line & converts each line to array
				//System.out.println(splitLine[1]);
				if(Arrays.asList(splitLine).contains(curPac))
				{
					//System.out.println(splitLine[0] + " IS IN " + curPac);
					memsInPac.add(splitLine[0]); //convert array to arraylist
				}
			}
			memberToPacsFile.reset();
			pacToMembers.put(curPac, memsInPac);
			//System.out.println(TS2String(memsInPac));
		}


		/*
		POPULATE THE TREE MAP ABOVE

		NOw PRINT THAT MAP (see output)*/
		
		for(Map.Entry<String, TreeSet<String>> pair : pacToMembers.entrySet())
		{
			System.out.println(pair.getKey() + TS2String(pair.getValue()));
		}
		

	} // END MAIN
	static String TS2String(TreeSet<String> set )
	{
		String toString = "";
		for(String item : set)
		{
			toString = toString + " " + item;
		}
		return toString;
	}

} // CLASS
