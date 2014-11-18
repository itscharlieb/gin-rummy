package ca.mcgill.cs.comp303.rummy.model;

import java.io.Serializable;
import java.util.Stack;

@SuppressWarnings("serial")
public class DiscardPile implements Serializable
{
	private Stack<Card> aStack;
	
	public DiscardPile()
	{
		aStack = new Stack<Card>();
	}
	
	/**
	 * adds pCard to the top of the discard pile
	 * @param pCard
	 */
	public void add(Card pCard)
	{
		aStack.add(pCard);
	}
	
	/**
	 * @return the card on top of the discard pile without removing it
	 */
	public Card showTop()
	{
		return aStack.peek();
	}
	
	/**
	 * @return the card on top of the discard pile and remove it from the pile
	 */
	public Card draw()
	{
		return aStack.pop();
	}
	/**
	 * @return the number of cards in the discard pile
	 */
	public int size()
	{
		return aStack.size();
	}
	
	/**
	 * clears all the cards from the discard pile
	 */
	public void clear()
	{
		aStack.clear();
	}
		
}
