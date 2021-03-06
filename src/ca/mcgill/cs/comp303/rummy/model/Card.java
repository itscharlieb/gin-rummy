package ca.mcgill.cs.comp303.rummy.model;
import java.io.Serializable;
/**
 * An immutable description of a playing card.
 */

@SuppressWarnings("serial")
public final class Card implements Comparable<Card>, Serializable
{
	/**
	 * Represents the rank of the card.
	 */
	public enum Rank 
	{ ACE, TWO, THREE, FOUR, FIVE, SIX,
		SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }
	
	/**
	 * Represents the suit of the card.
	 */
	public enum Suit 
	{ CLUBS, DIAMONDS, HEARTS, SPADES }
	
	private final Rank aRank;
	
	private final Suit aSuit;
	
	/**
	 * Create a new card object. 
	 * @param pRank The rank of the card.
	 * @param pSuit The suit of the card.
	 */
	public Card(Rank pRank, Suit pSuit )
	{
		aRank = pRank;
		aSuit = pSuit;
	}
	
	/**
	 * Obtain the rank of the card.
	 * @return An object representing the rank of the card.
	 */
	public Rank getRank()
	{
		return aRank;
	}
	
	/**
	 * Obtain the suit of the card.
	 * @return An object representing the suit of the card 
	 */
	public Suit getSuit()
	{
		return aSuit;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @return See above.
	 */
	@Override
	public String toString()
	{
		return aRank + " of " + aSuit;
	}

	/**
	 * Compares two cards according to gin rules (ace is low, suits 
	 * run as Spades, Hearts, Diamonds, Clubs (high to low)).
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param pCard The card to compare to
	 * @return Returns a negative integer, zero, or a positive integer 
	 * as this object is less than, equal to, or greater than pCard
	 */
	@Override
	public int compareTo(Card pCard)
	{
		//if the ranks are not equal
		if(!getRank().equals(pCard.getRank()))
		{
			//positive if this rank is greater than pCard rank
			return getRank().ordinal() - pCard.getRank().ordinal();
		}
		else if(!getSuit().equals(pCard.getSuit()))
		{
			//positive if this suit is greater than pCard's suit
			return getSuit().ordinal() - pCard.getSuit().ordinal();
		}
		else
		{
			//they are the same card
			return 0;
		}
	}

	/**
	 * Two cards are equal if they have the same suit and rank.
	 * @param pCard The card to test.
	 * @return true if the two cards are equal
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object pCard ) 
	{
		Card lCard = (Card) pCard;
		return (getRank().equals(lCard.getRank()) && getSuit().equals(lCard.getSuit()));
	}

	/** 
	 * The hashcode for a card is the suit*13 + that of the rank (perfect hash).
	 * @return the hashcode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		int lHashMultiplier = 13;
		return (getSuit().ordinal() * lHashMultiplier) + getRank().ordinal();
	}
}
