package ca.mcgill.cs.comp303.rummy.model;

public interface GameSerializer
{
	void saveGame(String pFileName, GameEngine pGameEngine);
	
	GameEngine loadGame(String pFileName);
}
