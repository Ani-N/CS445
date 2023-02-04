import java.io.*;


public class Knapsack
{
	public static void main( String[] args ) throws Exception
	{	
        int[] set = new int[16]; // 5 elements produces 2^5 subsets ( 32 subsets )
		int numSets = (int) Math.pow(2,set.length); //number of sets calculation

        BufferedReader infile = new BufferedReader( new FileReader( args[0] ) );

        String strNums = infile.readLine();
		int target = Integer.parseInt(infile.readLine()); //reading the nums from file by line

		String [] strNumsArr = strNums.split(" "); //splitting the str. into an array of strings



		for(int i = 0; i<set.length; i++)
		{
			set[i] = Integer.parseInt(strNumsArr[i]); //converting strs in arrray to ints in set
		}

		/*
        System.out.print( "original set: { " );
		for ( int i=0 ; i<set.length ; ++i )
			System.out.print( set[i] + " " );
		System.out.println("}" );
		*/

		for ( int i=0 ; i<numSets; ++i )
		{	
            String bitmap = toBitString( i, set.length );
            int sum = 0;
            for ( int bit =0 ; bit <set.length ; ++bit )
            	if ( bitmap.charAt(bit)=='1' )
					sum += set[bit];
			if ( sum != target) continue;

            //bitmap -> setSum
            //if bitmap -> set, sum of that set != target, 
            //use continue;            


			//System.out.format("bitmap %s  { ", bitmap );
			for ( int bindx=0 ; bindx<set.length ; ++bindx )
            	if ( bitmap.charAt(bindx)=='1' )
					System.out.print( set[bindx] + " " );
				else
					System.out.print( "" );
			System.out.println("");
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
	//static boolean equalsTarget(int target, )
} // END CLASS