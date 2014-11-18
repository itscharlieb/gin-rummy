package ca.mcgill.cs.comp303.rummy.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinarySerializer implements GameSerializer
{
	public BinarySerializer()
	{
		
	}
	
	@Override
	public void saveGame(String pFileName, GameEngine pGameEngine)
	{
		ObjectOutputStream lOS = null;
		
		try
		{
			lOS = new ObjectOutputStream(new FileOutputStream(pFileName));
			lOS.writeObject(pGameEngine);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(lOS != null)
				{
					lOS.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	@Override
	public GameEngine loadGame(String pFileName) 
	{
		GameEngine lGameEngine = null;
		ObjectInputStream lIS = null;
		
		try
		{
			lIS = new ObjectInputStream(new FileInputStream(pFileName));
			lGameEngine = (GameEngine) lIS.readObject();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				if(lIS != null)
				{
					lIS.close();
				}	
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return lGameEngine;
	}
}
