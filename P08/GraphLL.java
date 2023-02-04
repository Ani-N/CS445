import java.io.*;
import java.util.*;

public class GraphLL
{
	private final int NO_EDGE = -1; // all real edges are positive
	private  Edge[] G;              // every G[i] is the head of a linked list, i.e ref to an Edge 

	private int numEdges;
	public GraphLL( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int numNodes = graphFile.nextInt();   
		G = new Edge[numNodes];
		numEdges=0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head 

		while ( graphFile.hasNextInt() )
		{	// read in the src, dest, weight
			// call addEdge
			addEdge(graphFile.nextInt(), graphFile.nextInt(), graphFile.nextInt());
		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge( int src, int dest, int weight )
	{
		if(G[src] == null) G[src] = new Edge(dest, weight);
		else if(!hasEdge(src, dest))	G[src] = new Edge(dest, weight, G[src]);
	}
	
	private boolean hasEdge(int src, int dest)
	{
		if(G[src] == null) return false;

		Edge curr = G[src];
		while(curr != null && curr.next != null)
		{
			if(curr.dest == dest) return true;
			curr = curr.next;
		}
		return false; // CHANGE/REMOVE/MOVE AS NEEDED
		// GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
	}

	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{	// THE HARDER ONE
		int inDeg = 0;
		for(Edge curr : G)
		{
			while (curr != null)
			{
				if(curr.dest == dest) inDeg++;
				curr = curr.next;
			}
		}
		// WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
		return inDeg;
	}

	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{	// THE EASIER ONE
		int outDeg=0;
		// JUST RETURN THE LENGTH OF THIS LIST AT G[src]
		for(Edge curr = G[src]; curr != null; curr = curr.next) outDeg++;
		return outDeg;	
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3 DEGREE FUNCTIONS
	
	public int maxOutDegree()
	{
		int maxOutDegree = outDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// LOOP OVER ALL NODES CALLING THE OUTDEG OF THAT NODE- RMEMBER THE LARGEST OUTDEG
		for(int i = 0; i < G.length; i++) if(outDegree(i) > maxOutDegree) maxOutDegree = outDegree(i);
		return maxOutDegree;
	}

	public int maxInDegree()
	{
		int maxInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME AS ABOVE BUT CALLING ALL INS AND REMEMBERING  LARGEST INDEG
		for(int i = 0; i < G.length; i++) if(inDegree(i) > maxInDegree) maxInDegree = inDegree(i);
		return maxInDegree;
	}

	public int minOutDegree()
	{
		int minOutDegree = outDegree(0); // ASSUM 1STNODE HAS SMALLES OUTDEG
		// SAME PATTERN AS ABOVE
		for(int i = 0; i < G.length; i++) if(outDegree(i) < minOutDegree) minOutDegree = outDegree(i); 
		return minOutDegree;
	}

	public int minInDegree()
	{
		int minInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME PATTERN 
		for(int i = 0; i < G.length; i++) if(inDegree(i) < minInDegree) minInDegree = inDegree(i);
		return minInDegree;
	}	
	
	public int maxDegree()
	{
		int maxDegree = degree(0);
		// SAME PATTERN
		for(int i = 0; i < G.length; i++) if(degree(i) > maxDegree) maxDegree = degree(i);
		return maxDegree;
	}

	public int minDegree()
	{
		int minDegree = degree(0);
		// SAME PATTERN
				for(int i = 0; i < G.length; i++) if(degree(i) < minDegree) minDegree = degree(i); 
		return minDegree;
	}
	
	public void removeEdge(int src, int dest)
	{	// ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
		// IF THE LIST AT G[src] IS NULL -OR-  SRC OR DEST OUT OF BOUNDS
		// 		THROW AND CATCH AN EXCEPTION - SEE OUTPUT
		
		// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE
		// WALK A CURR UP TO THE PRED OF THE DEADNODE 
		// REMOVE THE NODE (IF ITS THERE)
		
		// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)
		try
		{
			Edge curr = G[src];
			if (curr.dest == dest)
			{
				G[src] = curr.next;
				return;
			}
			while(curr.next != null)
			{
				if(curr.next.dest == dest)
				curr.next = curr.next.next;
			}
		}
		catch(NullPointerException e)
		{ System.out.format("java.lang.Exception: Non Existent Edge Exception: removeEdge(%d,%d)", src, dest); }
		catch(ArrayIndexOutOfBoundsException e)
		{ System.out.format("java.lang.Exception: Non Existent Edge Exception: removeEdge(%d,%d)", src, dest); }
		
	} // E N D  R E M O V E D G E  
	
	// TOSTRING
	public String toString()
	{
		String the2String = "";	
		for(int i = 0; i<G.length; i++)
		{
			the2String += String.format("%d:", i);
			Edge curr = G[i];
			while(curr != null)
			{
				the2String += String.format(" -> [%d,%d]", curr.dest, curr.weight);
				curr = curr.next;
			}

			the2String += "\n";
		}
		// SEE OUTPUT
		return the2String;
	} // END TOSTRING
} // E N D    G R A P H L L    C L A S S

// - - - - Y O U   M U S T   F I L L   I N   T H E   E D G E  (think 'Node')  C L A S S  - - - -
// NOTHING PUBLIC. LET IF DEFAULT TO PACKAGE/PRIVATE LIKE WE DID IN OTHER LL ASSIGNMENTS
// SO THAT YOU DONT HAVE TO CALL SETTERS AND GETTERS IN YOUR GRAPHLL CODE.

class Edge 
{
	// DEFINE dest,weight,nextInt
	// DEFINE FULL CONSTRUCTOR
	int dest;
	int weight;
	Edge next;
	// a Constructor that takes dest,weight, next
	Edge(int dest, int weight)
	{
		this(dest, weight, null);
	}
	Edge(int dest, int weight, Edge next)
	{
		this.dest = dest;
		this.weight = weight;
		this.next = next;
	}

}


