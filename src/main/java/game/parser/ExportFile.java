package game.parser;

import game.parser.entities.Game;
import game.parser.entities.Player;
import game.parser.entities.Ranking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportFile {

	private Ranking ranking;

	public ExportFile( Ranking ranking ) {
		this.ranking = ranking;
	}

	/**
	 * @description Exporta um arquivo com as informações dos games, com os
	 *              respectivos assassinatos e mortes de cada jogador para cada
	 *              jogo
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	public void exportFileWithResults() throws IOException {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append( adicionarTabs( "Jogador", 4 ) );
		stringBuilder.append( adicionarTabs( "Assassinato", 4 ) );
		stringBuilder.append( adicionarTabs( "Morte", 3 ) );
		stringBuilder.append( "\n" );

		Player player = null;

		for ( Game game : getRanking().getGames() ) {

			stringBuilder.append( game.getName() );
			stringBuilder.append( "\t{\n" );

			for ( String nameOfPlayer : game.getKillsByPlayers().keySet() ) {
				player = game.getKillsByPlayers().get( nameOfPlayer );
				stringBuilder.append( adicionarTabs( nameOfPlayer, 4 ) );
				stringBuilder.append( adicionarTabs( String.valueOf( player.getKilled() ), nameOfPlayer.split( "\\s" ).length > 1 ? 3 : 4 ) );
				stringBuilder.append( adicionarTabs( String.valueOf( player.getDied() ), 5 ) );
				stringBuilder.append( "\n" );
			}
			stringBuilder.append( "\n" );
			stringBuilder.append( "Total de Assassinatos" );
			stringBuilder.append( adicionarTabs( String.valueOf( game.getTotalKillsByGame() ), 4 ) );
			stringBuilder.append( "\n" );
			stringBuilder.append( "}" );
			stringBuilder.append( "\n\n" );
		}

		FileWriter finalFile = new FileWriter( new File( "RANKING.txt" ), true );
		finalFile.write( stringBuilder.toString() );
		finalFile.close();
	}

	/**
	 * @description Adiciona tabs ao valor informado, com base na quantidade
	 *              informada
	 * 
	 * @param String
	 *            valor, int quantidade
	 * 
	 * @return String com o valor acrescido das tabs
	 * 
	 * @author Ederson Patrício
	 * 
	 * */
	private String adicionarTabs( String valor, int quantidade ) {
		String valorFinal = "";
		String tab = "\t";
		for ( int i = 0; i < quantidade; i++ ) {
			valorFinal += tab;
		}
		return valorFinal.concat( valor );
	}

	// Método Get
	public Ranking getRanking() {
		return ranking;
	}

	// Método Set
	public void setRanking( Ranking ranking ) {
		this.ranking = ranking;
	}
}