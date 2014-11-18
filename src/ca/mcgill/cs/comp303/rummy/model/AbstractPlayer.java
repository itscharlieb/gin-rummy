package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@SuppressWarnings("serial")
public abstract class AbstractPlayer implements Serializable
{
	private Hand aHand;
	private String aName;
	private int aGameScore;
	
	public String getName()
	{
		return aName;
	}
	
	public void add(Card pCard)
	{
		aHand.add(pCard);
	}
	
	public void remove(Card pCard)
	{
		aHand.remove(pCard);
	}
	
	public void clearHand()
	{
		aHand.clear();
	}
	
	public int gameScore()
	{
		return aGameScore;
	}
	
	public void incrementGameScore(int pRoundScore)
	{
		aGameScore += pRoundScore;
	}
	
	public int handScore()
	{
		return aHand.score();
	}
	
	public abstract Set<Card> layoff(Collection<ICardSet> pCards);
	
	/**
	 * @param pCard
	 * @return true if the player wants the card from the discard pile
	 */
	public abstract boolean wantsDiscardCard(Card pCard);
	
	/**
	 * @return the card that the player wants to discard
	 */
	public abstract Card discard();
	
	/**
	 * @return true if the player wants to knock
	 */
	public abstract boolean isKnocking();
}
