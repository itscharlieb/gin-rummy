package ca.mcgill.cs.comp303.rummy.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

@SuppressWarnings("serial")
public class GameEngine extends Observable implements Serializable
{
	private final int MAX_GAME_SCORE;
	private Deck aDeck;
	private DiscardPile aDiscardPile;
	private AbstractPlayer aDealer;
	private AbstractPlayer aOtherPlayer;
	private boolean aIsDealersTurn;
	
	public GameEngine(AbstractPlayer pDealer, AbstractPlayer pOtherPlayer)
	{
		//defualt max game score is 100.
		this(pDealer, pOtherPlayer, 100);
	}
	
	public GameEngine(AbstractPlayer pDealer, AbstractPlayer pOtherPlayer, int pMaxGameScore)
	{
		aDealer = pDealer;
		aOtherPlayer = pOtherPlayer;
		aDeck = new Deck();
		aDiscardPile = new DiscardPile();
		aIsDealersTurn = true;
		MAX_GAME_SCORE = pMaxGameScore;
	}
	
	/**
	 * implements all logic necessary to play a game of GinRummy.
	 * Delegates tasks to other methods
	 */
	public void playGame()
	{
		newRound();
		while(!gameIsOver())
		{
			playRound();
			calculateRoundScore();
			newRound();
		}
	}
	
	/**
	 * implements all logic necessary to resume a saved game of GinRummy
	 */
	public void resumeGame()
	{
		
	}
	
	/**
	 * resets GameEngine state so a new round can be played
	 */
	private void newRound()
	{
		//reset player hands
		aDealer.clearHand();
		aOtherPlayer.clearHand();
		
		//reset and shuffle deck, clear discard pile
		aDeck.shuffle();
		aDiscardPile.clear();
		aIsDealersTurn = true;
		
		dealCards();
	}
	
	/**
	 * deal ten cards to each player and then add a card to the top of
	 * the discard pile.
	 */
	private void dealCards()
	{
		for(int i = 0; i < 10; i++)
		{
			aDealer.add(aDeck.draw());
			aOtherPlayer.add(aDeck.draw());
		}
		
		aDiscardPile.add(aDeck.draw());
	}
	
	/**
	 * all logic for playing a round of GinRummy
	 */
	private void playRound()
	{
		AbstractPlayer lCurrentPlayer = aDealer;
		while(!roundIsOver())
		{
			//if the current player wants the card from the top of the discard pile
			if(lCurrentPlayer.wantsDiscardCard(aDiscardPile.showTop()))
			{
				//add card from top of the discard pile to player's hand
				lCurrentPlayer.add(aDiscardPile.draw());
			}
			else
			{
				//add card from top of the deck to the player's hand
				lCurrentPlayer.add(aDeck.draw());
			}
			
			//card that the player wants to discard, remove from her hand, add to discard pile
			Card lDiscardCard = lCurrentPlayer.discard();
			lCurrentPlayer.remove(lDiscardCard);
			aDiscardPile.add(lDiscardCard);
			
			//does the current player want to knock?
			if(lCurrentPlayer.isKnocking())
			{
				knock();
				break;
			}
			
			lCurrentPlayer = getNextPlayer();
		}
	}
	
	private AbstractPlayer getNextPlayer()
	{
		
		if(aIsDealersTurn) 
		{
			aIsDealersTurn = !aIsDealersTurn;
			return aDealer;
		}
		else
		{
			aIsDealersTurn = !aIsDealersTurn;
			return aOtherPlayer;
		}
	}
	
	private boolean roundIsOver()
	{
		return aDiscardPile.size() < 3;
	}
	
	/**
	 * @return true if either player has over MAX_GAME_SCORE points
	 */
	private boolean gameIsOver()
	{
		return(aDealer.gameScore() > MAX_GAME_SCORE || aOtherPlayer.gameScore() > MAX_GAME_SCORE);
	}
	
	/**
	 * @return the winner of the game, 
	 * @throws GameException if neither player has won the game yet
	 */
	private AbstractPlayer winner()
	{
		if(aDealer.gameScore() > MAX_GAME_SCORE) return aDealer;
		if(aOtherPlayer.gameScore() > MAX_GAME_SCORE) return aOtherPlayer;
		
		throw new GameException("Neither player has one the game.");
	}
	
	private void knock()
	{
		
	}
	
	private void calculateRoundScore()
	{
		
	}
}
