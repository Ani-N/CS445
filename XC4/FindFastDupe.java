// STARTER FILE FOR 445 X/CREDIT #4
// GIVEN CODE MAKE SURE YOU USE BUFFEREDREADER

import java.io.*;
import java.util.*;

public class FindFastDupe
{	public static void main (String[] args) throws Exception
	{
		BufferedReader stringFile = new BufferedReader( new FileReader( args[0] ) );
        fastDupeFinder(stringFile);

	} // END MAIN
	static void fastDupeFinder(BufferedReader stringFile) throws Exception
	{
		HashSet<String> inList = new HashSet<String>();
		Integer count = 1;
		while(stringFile.ready())
		{
			String curLine = stringFile.readLine();
			if(inList.contains(curLine))
			{
				System.out.println("FIRST DUPE '" + curLine + "' FOUND AT LINE " + count);
				return;
			}
            inList.add(curLine);
			count++;
		}
		System.out.println("NO DUPES FOUND");
	}
} // END XC4