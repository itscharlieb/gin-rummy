package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Models a hand of 10 cards. The hand is not sorted. Not threadsafe.
 * The hand is a set: adding the same card twice will not add duplicates
 * of the card.
 * @inv size() > 0
 * @inv size() <= HAND_SIZE
 */

@SuppressWarnings("serial")
public class Hand implements Serializable
{
	private ArrayList<Card> aUnmatchedCards;
	private Set<ICardSet> aMatchedSets;
	private Card aTemporaryCard;
	
	/**
	 * Creates a new, empty hand.
	 */
	public Hand()
	{
		aUnmatchedCards = new ArrayList<Card>();
		aMatchedSets = new HashSet<ICardSet>();
		aTemporaryCard = null;
	}
	
	/**
	 * Adds pCard to the list of unmatched cards.
	 * If the card is already in the hand, it is not added.
	 * @param pCard The card to add.
	 * @throws HandException if the hand is complete.
	 * @throws HandException if the card is already in the hand.
	 * @pre pCard != null
	 */
	public void add( Card pCard ) throws HandException
	{
		if(contains(pCard))
		{
			throw new HandException("Hand contains parameter card.");
		}
		
		if(isComplete())
		{
			throw new HandException("Hand is complete.");
		}
		
		aUnmatchedCards.add(pCard);
	}
	
	/**
	 * Remove pCard from the hand and break any matched set
	 * that the card is part of. Does nothing if
	 * pCard is not in the hand.
	 * @param pCard The card to remove.
	 * @pre pCard != null
	 */
	public void remove( Card pCard )
	{
		//look in unmatched cards
		if(aUnmatchedCards.contains(pCard))
		{
			aUnmatchedCards.remove(pCard);
		}
		
		//look in matched cards
		else
		{
			//loop through all of the card sets
			for(ICardSet lICardSet : aMatchedSets)
			{
				//if this set contains pCard
				if(lICardSet.contains(pCard))
				{
					destroySet(lICardSet, pCard);
					break;
				}
			}
		}
	}
	
	/**
	 * removes pCard from hand, moves the rest of the cards in pICardSet
	 * to the set of unmatched cards.
	 * @param pICardSet
	 * @param pCard
	 */
	private void destroySet(ICardSet pICardSet, Card pCard)
	{
		//copy all cards that aren't pCard to the unmatched cards
		for(Card lCard : pICardSet)
		{
			if(!lCard.equals(pCard))
			{
				aUnmatchedCards.add(lCard);
			}
		}
		
		//remove the containing set from the matched sets
		aMatchedSets.remove(pICardSet);
	}
	
	/**
	 * @return True if the hand is complete.
	 */
	public boolean isComplete()
	{
		return size() > 11;
	}
	
	/**
	 * Removes all the cards from the hand.
	 */
	public void clear()
	{
		aUnmatchedCards.clear();
		aMatchedSets.clear();
	}
	
	/**
	 * @return A copy of the set of matched sets
	 */
	public Set<ICardSet> getMatchedSets()
	{
		//TODO copy aMatchedSets before returning it
		return aMatchedSets;
	}
	
	/**
	 * @return A copy of the set of unmatched cards.
	 */
	public Set<Card> getUnmatchedCards()
	{
		return (Set<Card>) aUnmatchedCards.clone();
	}
	
	/**
	 * @return The number of cards in the hand.
	 */
	public int size()
	{
		int lNumMatchedCards = 0;
		for(ICardSet lICardSet : aMatchedSets)
		{
			lNumMatchedCards += lICardSet.size();
		}
		
		return lNumMatchedCards + aUnmatchedCards.size();
	}
	
	/**
	 * Determines if pCard is already in the hand, either as an
	 * unmatched card or as part of a set.
	 * @param pCard The card to check.
	 * @return true if the card is already in the hand.
	 * @pre pCard != null
	 */
	public boolean contains( Card pCard )
	{
		for(ICardSet lICardSet : aMatchedSets)
		{
			if(lICardSet.contains(pCard))
			{
				return true;
			}
		}
		
		return aUnmatchedCards.contains(pCard);
	}
	
	/**
	 * @return The total point value of the unmatched cards in this hand.
	 */
	public int score()
	{
		int lScore = 0;
		for(Card lCard : aUnmatchedCards)
		{
			int lCardValue = lCard.getSuit().ordinal() + 1;
			if(lCardValue >= 10)
			{
				lScore += 10;
			}
			else
			{
				lScore += lCardValue;
			}
		}
		
		return lScore;
	}
	
	/**
	 * Creates a group of cards of the same rank.
	 * @param pCards The cards to groups
	 * @pre pCards != null
	 * @throws HandException If the cards in pCard are not all unmatched
	 * cards of the hand or if the group is not a valid group.
	 */
	public void createGroup( Set<Card> pCards )
	{
		for(Card lCard : pCards)
		{
			if(!aUnmatchedCards.contains(lCard))
			{
				throw new HandException("Not all input cards are unmatched cards in the hand.");
			}
		}
		
		try
		{
			 aMatchedSets.add(new Group(pCards));
		}
		catch(HandException e)
		{
			throw e;
		}
	}
	
	/**
	 * Creates a run of cards of the same suit.
	 * @param pCards The cards to group in a run
	 * @pre pCards != null
	 * @throws HandException If the cards in pCard are not all unmatched
	 * cards of the hand or if the group is not a valid group.
	 */
	public void createRun( Set<Card> pCards )
	{
		for(Card lCard : pCards)
		{
			if(!aUnmatchedCards.contains(lCard))
			{
				throw new HandException("Not all input cards are unmatched cards in the hand.");
			}
		}
		
		try
		{
			 aMatchedSets.add(new Run(pCards));
		}
		catch(HandException e)
		{
			throw e;
		}
	}
	
	/**
	 * Calculates the matching of cards into groups and runs that
	 * results in the lowest amount of points for unmatched cards.
	 */
	public void autoMatch()
	{
	}
}
