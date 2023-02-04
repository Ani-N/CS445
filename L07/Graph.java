/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
		
//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E 
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R 
//		      ........R E M O V E   A F T E R   T E S T I N G .......
/*
		for (int node = 0 ; node < G.length ; ++node )
		{
			System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
			System.out.format( "out(%d)=%d  ",node,outDegree(node) );
			System.out.format( "deg(%d)=%d\n",node,degree(node) );
		}
*/
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;

		// WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
		for (int y = 0; y < dimension; y++)
		{
			for (int x = 0; x < dimension; x++)
			{
				if(x==y) G[y][x] = 0;
				else G[y][x] = -1;
			}
		}
	

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			int row = graphFile.nextInt();
			int col = graphFile.nextInt();
			int weight = graphFile.nextInt();

			G[row][col] = weight;
			// read in the row,col,weight // that eat us this line
			// call add edge
		}

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
  private boolean hasEdge(int fromNode, int toNode)
  {
    return (G[fromNode][toNode] != -1); 
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int count = 0;
		for (int row = 0; row < G.length; row++)
		{
			if (G[row][node] >0) count++;
		}
		return count;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int count = 0;
		for (int col = 0; col < G.length; col++)
		{
			if (G[node][col] >0) count++;
		}
		return count;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int max = 0;
		for (int row = 0; row < G.length; row++)
		{
			int currOut = outDegree(row);
			if(currOut > max) max = currOut;
		}
		return max; 
	}

	public int maxInDegree()
	{
		int max = 0;
		for (int col = 0; col < G.length; col++)
		{
			int currOut = inDegree(col);
			if(currOut > max) max = currOut;
		}
		return max; 
	}

	public int minOutDegree()
	{
		int min = G.length;
		for (int row = 0; row < G.length; row++)
		{
			int currOut = outDegree(row);
			if(currOut < min) min = currOut;
		}
		return min; 
	}
	public int minInDegree()
	{
		int min = G.length;
		for (int col = 0; col < G.length; col++)
		{
			int currOut = inDegree(col);
			if(currOut < min) min = currOut;
		}
		return min; 
	}	
	
	public int maxDegree()
	{
		int max = 0;
		for (int node = 0; node < G.length; node++)
		{
			int currDeg = degree(node);
			if(currDeg > max) max = currDeg;
		}
		return max; 
	}

	public int minDegree()
	{
		int min = (G.length)*2;
		for (int node = 0; node < G.length; node++)
		{
			int currDeg = degree(node);
			if(currDeg < min) min = currDeg;
		}
		return min; 
	}
	
	public void removeEdge(int fromNode, int toNode)
	{
		/* if caller passes in a row col pair that 
		   out of bound or has no edge there, you must 
		   throw and catch an exception. See my output.
		
		   if the edge is there then remove it by writing 
		   in a NO_EDGE value at that coordinate in the grid	
		*/
		try
		{
			G[fromNode][toNode] = -1;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println( "java.lang.Exception: Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")" );
		}
	}
	
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF

