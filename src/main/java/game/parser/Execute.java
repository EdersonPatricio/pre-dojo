package game.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Execute {

	public static void main( String[] args ) throws IOException {

		try {
			BufferedReader readerFile = new BufferedReader( new FileReader( "game.log" ) );

		} catch ( Exception e ) {
			System.out.println( "*******************|<-Arquivo não localizado!!->|*******************" );
		}
	}
}