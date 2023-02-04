import java.util.*;
import java.io.*;

public class DrugInteractions
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		TreeMap<String, TreeSet<String>> foodDrug2Category = new TreeMap<String, TreeSet<String>>();
		while(foodDrug2CategoryFile.ready()) //fills the treemap with key (category): value (TreeSet of foodDrugs) pairs
		{
			String [] fileLine = foodDrug2CategoryFile.readLine().split(",");
			TreeSet<String> fdInLine = new TreeSet <String>();
			for(int i = 1; i < fileLine.length; i++) fdInLine.add(fileLine[i]);
			foodDrug2Category.put(fileLine[0], fdInLine);
		}

		printTreeMap(foodDrug2Category);
		System.out.println();


		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		TreeMap<String, TreeSet<String>> patient2FoodDrug = new TreeMap<String, TreeSet<String>>();
		while(patient2FoodDrugFile.ready()) //fills the treemap with key (category): value (TreeSet of foodDrugs) pairs
		{
			String [] fileLine = patient2FoodDrugFile.readLine().split(",");
			TreeSet<String> fdInLine = new TreeSet <String>();
			for(int i = 1; i < fileLine.length; i++) fdInLine.add(fileLine[i]);
			patient2FoodDrug.put(fileLine[0], fdInLine);
		}
		printTreeMap(patient2FoodDrug);
		System.out.println();



		
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );
		ArrayList<TreeSet<String>> dontMixPairs = new ArrayList<TreeSet<String>>();
		while(dontMixFile.ready()) //fills the treeset of treesets with sets of types to avoid mixing
		{
			String [] fileLine = dontMixFile.readLine().split(",");
			TreeSet<String> drugsInLine = new TreeSet <String>();
			for(int i = 0; i < fileLine.length; i++) drugsInLine.add(fileLine[i]);
			dontMixPairs.add(drugsInLine);
		}
		/*
		for(TreeSet<String> item : dontMixPairs) 
		{
			System.out.println(TS2String(item));
		}
		System.out.println();
		*/

		for(Map.Entry<String, TreeSet<String>> pair : patient2FoodDrug.entrySet())
		{
			String patientName = pair.getKey();
			TreeSet<String> patientFDCategories = foodDrugtoType(pair.getValue(), foodDrug2Category);
			for(TreeSet<String> item : dontMixPairs) 
			{
				if(patientFDCategories.containsAll(item)) System.out.println(patientName);
			}

		}
		
		
		
	} // END MAIN
	
	static TreeSet<String> foodDrugtoType (TreeSet<String> foodDrug, TreeMap<String, TreeSet<String>> Drug2CategoryMap)
	{
		TreeSet<String> returnSet = new TreeSet<String>();
		for(Map.Entry<String, TreeSet<String>> pair : Drug2CategoryMap.entrySet())
		{

			for(String item : foodDrug)
			{
				if(pair.getValue().contains(item))
				{
					returnSet.add(pair.getKey());
				}
			}
		}
		return returnSet;
	}

	static String TS2String(TreeSet<String> set )
	{
		String toString = "";
		for(String item : set)
		{
			toString = toString + item + " " ;
		}
		return toString;
	}
	static void printTreeMap(TreeMap<String, TreeSet<String>> map2print)
	{
		for(Map.Entry<String, TreeSet<String>> pair : map2print.entrySet())
		{
			System.out.println(pair.getKey() +" " + TS2String(pair.getValue()));
		}
	}


} // END CLASS