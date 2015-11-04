package game.parser.test;

import game.parser.Parser;
import game.parser.entities.ExportFile;
import game.parser.entities.Player;
import game.parser.entities.Ranking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class RankingTest {

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
		FileReader file = new FileReader( "src/test/resources/Ranking.log" );
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
		FileReader file = new FileReader( "src/test/resources/RankingFake.log" );
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
		FileReader file = new FileReader( "src/test/resources/Ranking_1_game.log" );
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
		FileReader file = new FileReader( "src/test/resources/Ranking_1_game_3_players.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );

		Map<String, Player> killsByPlayers = ranking.getGames().get( 0 ).getKillsByPlayers();

		Assert.assertEquals( killsByPlayers.size(), 3 );
	}

	/**
	 * @description Testar executar o parse de um arquivo com apenas 1 jogo onde
	 *              o jogador Nick possui 1 assassinato e 2 mortes
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFileWithNick1Killed2Death() throws IOException {
		FileReader file = new FileReader( "src/test/resources/Ranking_1_game_Nick_1_Killed_2_Death.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );

		Map<String, Player> killsByPlayers = ranking.getGames().get( 0 ).getKillsByPlayers();

		for ( String nameOfPlayer : killsByPlayers.keySet() ) {
			if ( nameOfPlayer.equals( "Nick" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 1 );
				Assert.assertEquals( player.getDied(), 2 );
			}
		}
	}

	/**
	 * @description Testar executar o parse de um arquivo com apenas 1 jogo e
	 *              contabilizar o total de mortes
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFileAccountingTotalKillsByGame() throws IOException {
		FileReader file = new FileReader( "src/test/resources/Ranking.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );

		Map<String, Player> killsByPlayers = ranking.getGames().get( 0 ).getKillsByPlayers();

		for ( String nameOfPlayer : killsByPlayers.keySet() ) {
			if ( nameOfPlayer.equals( "Brian" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 3 );
				Assert.assertEquals( player.getDied(), 4 );
			} else if ( nameOfPlayer.equals( "Nick" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 1 );
				Assert.assertEquals( player.getDied(), 2 );
			} else if ( nameOfPlayer.equals( "Roman" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 1 );
				Assert.assertEquals( player.getDied(), 2 );
			}
		}
		Assert.assertEquals( ranking.getGames().get( 0 ).getTotalKillsByGame().intValue(), 5 );
	}

	/**
	 * @description Testar executar o parse de um arquivo com apenas 1 jogo e
	 *              com os jogadores com os nomes compostos
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFilePlayerWithCompoundNouns() throws IOException {
		FileReader file = new FileReader( "src/test/resources/Ranking_1_game_Players_With_Compound_Nouns.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 1 );

		Map<String, Player> killsByPlayers = ranking.getGames().get( 0 ).getKillsByPlayers();

		for ( String nameOfPlayer : killsByPlayers.keySet() ) {
			if ( nameOfPlayer.equals( "Barak Obama" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 1 );
				Assert.assertEquals( player.getDied(), 0 );
			} else if ( nameOfPlayer.equals( "Pablo Escobar" ) ) {
				Player player = killsByPlayers.get( nameOfPlayer );
				Assert.assertEquals( player.getKilled(), 0 );
				Assert.assertEquals( player.getDied(), 1 );
			}
		}
		Assert.assertEquals( ranking.getGames().get( 0 ).getTotalKillsByGame().intValue(), 1 );
	}

	/**
	 * @description Testar executar o parse de um arquivo com 3 jogos e logo
	 *              após gerar o arquivo final com as devidas informações
	 * 
	 * @author Ederson Patrício
	 * 
	 * @throws IOException
	 * 
	 * */
	@Test
	public void executeParseByFileAfterExportFinalFile() throws IOException {
		FileReader file = new FileReader( "src/test/resources/GeneralRanking.log" );
		Assert.assertNotNull( file );

		BufferedReader readerFile = new BufferedReader( file );

		Ranking ranking = Parser.executeParseByFile( readerFile );

		Assert.assertEquals( ranking.getGames().size(), 3 );

		ExportFile exportFile = new ExportFile( ranking );

		boolean hasError = false;

		try {
			exportFile.exportFileWithResults();
		} catch ( Exception e ) {
			hasError = true;
		}

		Assert.assertFalse( hasError );
	}
}