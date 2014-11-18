package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("serial")
public class Run implements ICardSet, Serializable
{
	private ArrayList<Card> aRun;
	private final int MIN_RUN_SIZE = 3;
	
	/**
	 * constructs a run and sorts the cards by rank.
	 * @param pCards
	 * @throws HandException
	 */
	public Run(Collection<Card> pCards) throws HandException
	{
		if(!isValidRun(pCards))
		{
			throw new HandException("Input cards do not represent a valid run.");
		}
		
		aRun.addAll(pCards);
		Collections.sort(aRun);
	}

	@Override
	public Iterator<Card> iterator()
	{
		return aRun.iterator();
	}

	@Override
	public boolean contains(Card pCard)
	{
		return aRun.contains(pCard);
	}

	@Override
	public int size()
	{
		return aRun.size();
	}

	@Override
	public boolean isGroup()
	{
		return false;
	}

	@Override
	public boolean isRun()
	{
		return true;
	}
	
	private boolean isValidRun(Collection<Card> pCards)
	{
		return true;
	}
}
