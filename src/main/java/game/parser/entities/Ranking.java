package game.parser.entities;

import java.util.List;

public class Ranking {

	private List<Game> games;

	public Ranking( List<Game> games ) {
		this.games = games;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames( List<Game> games ) {
		this.games = games;
	}
}