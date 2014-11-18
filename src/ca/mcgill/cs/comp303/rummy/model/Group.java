package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Group implements ICardSet, Serializable
{
	private final int MIN_GROUP_SIZE = 3;
	private final int MAX_GROUP_SIZE = 4;
	private ArrayList<Card> aGroup;
	
	public Group(Collection<Card> pCards) throws HandException
	{
		if(!isValidGroup(pCards))
		{
			throw new HandException("Input cards do not represent a valid group.");
		}
		
		aGroup.addAll(pCards);
	}

	@Override
	public Iterator<Card> iterator()
	{
		return aGroup.iterator();
	}

	@Override
	public boolean contains(Card pCard)
	{
		return aGroup.contains(pCard);
	}

	@Override
	public int size()
	{
		return aGroup.size();
	}

	@Override
	public boolean isGroup()
	{
		return true;
	}

	@Override
	public boolean isRun()
	{
		return false;
	}
	
	private boolean isValidGroup(Collection<Card> pCards)
	{
		if(pCards.size() < MIN_GROUP_SIZE || pCards.size() > MAX_GROUP_SIZE) return false;
		
		Iterator<Card> lCardIterator = pCards.iterator();
		
		Card lCurrentCard = lCardIterator.next();
		while(lCardIterator.hasNext())
		{
			Card lNextCard = lCardIterator.next();
			
			//if the two compared cards do not have the same rank
			if(!lCurrentCard.getRank().equals(lNextCard.getRank()))
			{
				return false;
			}
			
			lCurrentCard = lNextCard;
			lNextCard = lCardIterator.next();
		}
		
		return true;
		
	}

}
