package game.parser.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

	@Test
	public void readFileExistent() throws FileNotFoundException {

		FileReader file = new FileReader( "game.log" );
		Assert.assertNotNull( file );
	}

	@Test( expected = FileNotFoundException.class )
	public void readFileInexistent() throws FileNotFoundException {
		FileReader file = new FileReader( "games.log" );
		Assert.assertNull( file );
	}
}