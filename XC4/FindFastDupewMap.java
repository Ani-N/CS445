// STARTER FILE FOR 445 X/CREDIT #4
// GIVEN CODE MAKE SURE YOU USE BUFFEREDREADER

import java.io.*;
import java.util.*;

public class FindFastDupewMap
{	public static void main (String[] args) throws Exception
	{
		BufferedReader stringFile = new BufferedReader( new FileReader( args[0] ) );
		String[] firstLine = stringFile.readLine().split("\\s+");
		if(firstLine.length == 1) oneColumnFastDupe(stringFile, firstLine);
		else twoColumnFastDupe(stringFile, firstLine);

	} // END MAIN
	static void oneColumnFastDupe(BufferedReader stringFile, String[] firstLine) throws Exception
	{
		HashMap<String, Integer> inList = new HashMap<String, Integer>();
		inList.put(firstLine[0], 1);
		Integer count = 2;
		while(stringFile.ready())
		{
			String curLine = stringFile.readLine();
			if(inList.putIfAbsent(curLine, count) != null)
			{
				System.out.println("FIRST DUPE '" + curLine + "' FOUND AT LINE " + count);
				return;
			}
			count++;
		}
		System.out.println("NO DUPES FOUND");
	}

	static void twoColumnFastDupe(BufferedReader stringFile, String[] firstLine) throws Exception
	{
		HashMap<String, Integer> inList = new HashMap<String, Integer>();
		inList.put(firstLine[2], 1);
		Integer count = 2;
		while(stringFile.ready())
		{
			String curLine = stringFile.readLine();
			if(inList.putIfAbsent(curLine, count) != null)
			{
				System.out.println("FIRST DUPE '" + curLine + "' FOUND AT LINE " + count);
				return;
			}
			count++;
		}
		System.out.println("NO DUPES FOUND");
	}

} // END XC4