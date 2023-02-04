import java.io.*;
import java.util.*;

public class Jumbles
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader dictFile = new BufferedReader(new FileReader(args[0]));
        BufferedReader jumblesFile = new BufferedReader(new FileReader(args[1]));

        TreeMap<String, TreeSet<String>> dict = new TreeMap<String, TreeSet<String>>();
        TreeMap<String, TreeSet<String>> jumb2words = new TreeMap<String, TreeSet<String>>();
        
        while (dictFile.ready())
        {  
            String curLine = dictFile.readLine();
            String curCanon = toCanonical(curLine);

            if (dict.containsKey(curCanon)) dict.get(curCanon).add(curLine);
            else
            {
                TreeSet<String> words = new TreeSet<String>();
                words.add(curLine);
                dict.put(curCanon, words);
            }
        }

        while (jumblesFile.ready())
        {  
            TreeSet<String> words = new TreeSet<String>();
            String curLine = jumblesFile.readLine();
            
            //System.out.println("CURLINE IS " + curLine);
            
            String curCanon = toCanonical(curLine);

            words = dict.get(curCanon);
            jumb2words.put(curLine, words);
        }

        for(Map.Entry<String, TreeSet<String>> pair : jumb2words.entrySet())
		{
			System.out.println(pair.getKey() + TS2String(pair.getValue()));
		}

        //tree map- key = treeset of word letters, val =  strings of dict words
        //jumbles- do the same    
    }

    static String toCanonical( String s ) // assume s = "zebra"
    {
        char[] letters = s.toCharArray(); // letters -> [z][e][b][r][a]
        Arrays.sort( letters ); // now letters -gt; [a][b][e][r][z]
        return new String( letters ); // read String API it has a constructor that accepts a char array and does the obvious with it
    }
    
    static String TS2String(TreeSet<String> set )
	{
        String toString = "";
		if(set == null) return "";
        for(String item : set)
		{
			toString = toString + " " + item;
		}
		return toString;
	}
}