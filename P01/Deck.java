/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
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
		// YOUR CODE HERE
		int[] tempDeck = new int[deck.length];
		for(int i = 0; i < (deck.length/2); i++)
		{
			tempDeck[2*i] = deck[i+deck.length/2];
			tempDeck[2*i + 1] = deck[i];
		}
		deck = tempDeck;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		// YOUR CODE HERE
		int[] tempDeck = new int[deck.length];
		for(int i = 0; i < (deck.length/2); i++)
		{
			tempDeck[2*i + 1] = deck[i+deck.length/2];
			tempDeck[2*i] = deck[i];
		}
		deck = tempDeck;
	}
	
	public String toBitString( int n ) 
	{
		String bitString = "";
		while (n != 0)
		{
			if ((n % 2) == 0)
			{
				bitString = "0" + bitString;
			}
			else
			{
				bitString = "1" + bitString;
				n = n-1;
			}
			n = n/2;
		}
		
		return bitString;
		//return ""; // REPLACE WITH YOUR CODE
	}
	
}	// END DECK CLASS