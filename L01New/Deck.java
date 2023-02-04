/*
	Deck class
*/

//import java.util.*;
//import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		// YOU DO THIS => init deck to be exactly numCards long
		deck = new int[numCards];
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
		for (int i = 0; i < numCards; i++)
		{
			deck[i]=i;
		}
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		// YOUR CODE HERE DELETE LINE
		int[] tempDeck = new int[deck.length];
		int deckMid = (deck.length/2);
		int deckIndex = 0;
		int tempIndex = 0;
		while((tempIndex+1) < deck.length)
		{
			tempDeck[tempIndex] = deck[deckMid]; //these are the 0/2/4... indexes. for the outshuffle you would make the 1/3/5.. index 
												 //equal to deckmid (top half of deck) while these would point to deckindex (lower half of deck)
			deckMid++;
			tempDeck[tempIndex+1] = deck[deckIndex]; //same goes here. In the outshuffle, you just have to sqitch it so that tempindex+1 is pointing to deckmid
			deckIndex++;
			tempIndex += 2;
		}
		deck = tempDeck;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK

	//my original in and outshuffle were both like this and didn't really make much sense, it was a lot of trial and error 
	//and it was not very understandable from the outside, I think the other way (inshuffle way) is better.
	public void outShuffle()
	{
		// YOUR CODE HERE DELETE LINE	
		int[] tempDeck = new int[deck.length];
		for(int i = 0; i < (deck.length/2); i++)
		{
			tempDeck[2*i + 1] = deck[i+deck.length/2];
			tempDeck[2*i] = deck[i];
		}
		deck = tempDeck;
	}

	/* optional (not much better):
	if you look at the patterns in the output file, the out-shuffle is basically the same as an inshuffle with 2 less cards in deck
	this means we can use our already created inshuffle to do an outshuffle.
	We can make a deck containing only the middle numbers of the deck,
	then, use the inshuffle code on that middle portion of the deck.
	finally, plug the middle portion of the deck back into the larger deck
	*/
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		// YOUR CODE HERE DELETE LINE
		for(int i = 0; i < deck.length; i++)
		{
			if(i != deck[i])
			{
				return false;
			}
		}
		return true;

		//return false; // JUST HERE TO COMPILE
	}
}	// END DECK CLASS