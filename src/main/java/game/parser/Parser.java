package game.parser;

import game.parser.entities.Game;
import game.parser.entities.Player;
import game.parser.entities.Ranking;
import game.parser.enums.PlayerMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

	static final String STARTED_GAME = "started";
	static final String ENDED_GAME = "ended";
	static final String WORLD = "<WORLD>";
	static final String KILLED = "killed";
	static final String USING = "using";
	static final String BY = "by";

	/**
	 * @description Executa a leitura do arquivo para que possa percorrer cada
	 *              linha e analisar os assassinatos e mortes de cada jogador
	 * 
	 * @param BufferedReader
	 *            bufferedReader
	 * 
	 * @return List<Game> retorna a lista dos games com as informações de
	 *         assassinatos e mortes de cada jogador para cada game
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	public static Ranking executeParseByFile( BufferedReader bufferedReader ) throws IOException {

		int countGame = 0;
		int totalKillsByGame = 0;
		int position = 0;
		String line = null;
		String nameOfPlayer = null;
		Game game = null;
		List<Game> games = new ArrayList<Game>();
		Map<String, Player> mapPlayers = null;

		// Fazendo a leitura de cada linha do arquivo
		while ( ( line = bufferedReader.readLine() ) != null ) {

			// Inicializando um novo game
			if ( line.contains( STARTED_GAME ) ) {

				++countGame;
				totalKillsByGame = 0;

				mapPlayers = new TreeMap<String, Player>( comparator() );
				game = new Game( String.format( "GAME_%s", countGame ) );
				game.setKillsByPlayers( mapPlayers );

				// Preenchendo informações do jogador
			} else if ( !line.contains( ENDED_GAME ) ) {

				position = 0;
				line = line.substring( 22 );// Desconsiderando as informações
											// até o primeiro nome do jogador
				String[] informations = line.split( "\\s" );

				nameOfPlayer = informations[0];

				if ( !nameOfPlayer.equalsIgnoreCase( WORLD ) ) {

					// Verificando se o 2º nome é mesmo "killed", caso
					// contrário, o nome do 1º jogador é composto
					if ( !informations[1].equalsIgnoreCase( KILLED ) ) {
						nameOfPlayer = String.format( "%s %s", nameOfPlayer, informations[++position] );
					}

					// Somando um assassinato para o jogador
					addKilled( nameOfPlayer, mapPlayers );
					nameOfPlayer = informations[position + 2];

					// Verificando se o 4º nome é mesmo "using", caso
					// contrário, o nome do 2º jogador é composto
					if ( !informations[3].equalsIgnoreCase( USING ) ) {
						if ( position == 0 ) {
							++position;
						}
						nameOfPlayer = String.format( "%s %s %s", informations[3 - position], informations[3], informations[3 + position] ).replace( KILLED, "" ).replace( USING, "" ).trim();
					}

					// Somando uma morte para o jogador
					addDeath( nameOfPlayer, mapPlayers );
					++totalKillsByGame;

				} else {
					++position;
					// Substituindo o nome do jogador caso o mesmo seja composto
					nameOfPlayer = String.format( "%s %s", informations[3 - position], informations[3] ).replace( BY, "" ).trim();
					addDeath( nameOfPlayer, mapPlayers );
				}
			} else {
				// Contabilizando o tital de mortes por game
				game.setTotalKillsByGame( totalKillsByGame );
				games.add( game );
			}
		}
		return new Ranking( games );
	}

	/**
	 * @description Soma um assassinato para o jogador
	 * 
	 * @param String
	 *            nameOfPlayer nome do jogador, Map<String, Player> mapPlayers
	 *            mapa com nome do jogador e suas informações
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	private static void addKilled( String nameOfPlayer, Map<String, Player> mapPlayers ) {
		updateKilledOrDeathOfPlayer( nameOfPlayer, PlayerMode.KILLED, mapPlayers );
	}

	/**
	 * @description Soma uma morte para o jogador
	 * 
	 * @param String
	 *            nameOfPlayer nome do jogador, Map<String, Player> mapPlayers
	 *            mapa com nome do jogador e suas informações
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	private static void addDeath( String nameOfPlayer, Map<String, Player> mapPlayers ) {
		updateKilledOrDeathOfPlayer( nameOfPlayer, PlayerMode.DEATH, mapPlayers );
	}

	/**
	 * @description Atualiza um assassinato ou morte de um jogador a partir do
	 *              tipo( KILLED ou DEATH )
	 * 
	 * @param String
	 *            nameOfPlayer nome do jogador, Map<String, Player> mapPlayers
	 *            mapa com nome do jogador e suas informações
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	private static void updateKilledOrDeathOfPlayer( String nameOfPlayer, PlayerMode playerMode, Map<String, Player> mapPlayers ) {

		Player player = null;

		if ( playerMode == PlayerMode.KILLED ) {
			if ( mapPlayers.containsKey( nameOfPlayer ) ) {
				player = mapPlayers.get( nameOfPlayer );
				mapPlayers.get( player.getName() ).setKilled( player.getKilled() + 1 );
			} else {
				player = new Player( nameOfPlayer );
				player.setKilled( player.getKilled() + 1 );
				mapPlayers.put( player.getName(), player );
			}
		} else {
			if ( mapPlayers.containsKey( nameOfPlayer ) ) {
				player = mapPlayers.get( nameOfPlayer );
				mapPlayers.get( player.getName() ).setDied( player.getDied() + 1 );
			} else {
				player = new Player( nameOfPlayer );
				player.setDied( player.getDied() + 1 );
				mapPlayers.put( player.getName(), player );
			}
		}
	}

	/**
	 * @description Comparator utilizado para ordenar os nomes do jogadores
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	private static Comparator<String> comparator() {
		return new Comparator<String>() {
			@Override
			public int compare( String o1, String o2 ) {
				return o1.compareTo( o2 );
			}
		};
	}
}