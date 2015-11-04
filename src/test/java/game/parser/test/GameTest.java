package game.parser.test;

import game.parser.Parser;
import game.parser.entities.Player;
import game.parser.entities.Ranking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

	/**
	 * @description Testar ler arquivo existente
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws FileNotFoundException
	 * 
	 * */
	@Test
	public void readFileExistent() throws FileNotFoundException {
		FileReader file = new FileReader( "game.log" );
		Assert.assertNotNull( file );
	}

	/**
	 * @description Testar ler arquivo inexistente
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws FileNotFoundException
	 * 
	 * */
	@Test( expected = FileNotFoundException.class )
	public void readFileInexistent() throws FileNotFoundException {
		FileReader file = new FileReader( "games.log" );
		Assert.assertNull( file );
	}

	/**
	 * @description Testar executar o parse de um arquivo com apenas 1 jogo
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFile() throws IOException {
		FileReader file = new FileReader( "src/test/resources/Game_Test_1_game.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );
	}

	/**
	 * @description Testar executar o parse de um arquivo com apenas 1 jogo e
	 *              contendo 3 jogadores
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFileWithThreePlayers() throws IOException {
		FileReader file = new FileReader( "src/test/resources/Game_Test_1_game_3_players.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );

		Map<String, Player> killsByPlayers = ranking.getGames().get( 0 ).getKillsByPlayers();

		Assert.assertEquals( killsByPlayers.size(), 3 );
	}
}