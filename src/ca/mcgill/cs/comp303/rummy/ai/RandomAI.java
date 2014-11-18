package ca.mcgill.cs.comp303.rummy.ai;

import ca.mcgill.cs.comp303.rummy.model.AbstractPlayer;
import ca.mcgill.cs.comp303.rummy.model.Card;
import ca.mcgill.cs.comp303.rummy.model.ICardSet;
import java.util.Collection;
import java.util.Set;

public class RandomAI extends AbstractPlayer
{

	@Override
	public Set<Card> layoff(Collection<ICardSet> pCards)
	{
		//TODO
		return null;
	}
	
	@Override
	public boolean wantsDiscardCard(Card pCard)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Card discard()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isKnocking()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
