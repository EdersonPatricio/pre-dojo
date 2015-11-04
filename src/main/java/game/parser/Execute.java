package game.parser;

import game.parser.entities.ExportFile;
import game.parser.entities.Ranking;

import java.io.BufferedReader;
import java.io.FileReader;

public class Execute {

	public static void main( String[] args ) {
		try {
			// Ler o arquivo de log
			BufferedReader readerFile = new BufferedReader( new FileReader( "Ranking.log" ) );

			// Executa a leitura do arquivo para que possa percorrer cada linha
			// e analisar os assassinatos e mortes de cada jogador
			Ranking ranking = Parser.executeParseByFile( readerFile );

			// Instanciando a classe ExportFile com a lista de games
			ExportFile exportFile = new ExportFile( ranking );

			// A partir da lista de games com sa devidas informações de cada
			// jogador, será exportado um arquivo com as informações referentes
			// à cada jogo
			exportFile.exportFileWithResults();

			System.out.println( "*******************|<-Finalizado!!->|*******************" );
		} catch ( Exception e ) {
			System.out.println( "*******************|<-Arquivo não localizado!!->|*******************" );
		}
	}
}