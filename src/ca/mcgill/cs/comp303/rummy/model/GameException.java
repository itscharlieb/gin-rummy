package ca.mcgill.cs.comp303.rummy.model;

@SuppressWarnings("serial")
public class GameException extends RuntimeException
{
	/**
	 * @param pMessage The exception message.
	 * @param pException The wrapped exception.
	 */
	public GameException( String pMessage, Throwable pException ) 
	{
		super( pMessage, pException );
	}

	/**
	 * @param pMessage The exception message.
	 */
	public GameException( String pMessage ) 
	{
		super( pMessage );
	}

	/**
	 * @param pException The wrapped exception
	 */
	public GameException( Throwable pException )
	{
		super( pException );
	}
}
