package game.parser.entities;

import java.util.Map;

public class Game {

	private String name;

	private Integer totalKillsByGame;

	private Map<String, Player> killsByPlayers;

	public Game() {

	}

	public Game( String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Integer getTotalKillsByGame() {
		return totalKillsByGame;
	}

	public void setTotalKillsByGame( Integer totalKillsByGame ) {
		this.totalKillsByGame = totalKillsByGame;
	}

	public Map<String, Player> getKillsByPlayers() {
		return killsByPlayers;
	}

	public void setKillsByPlayers( Map<String, Player> killsByPlayers ) {
		this.killsByPlayers = killsByPlayers;
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", totalKillsByGame=" + totalKillsByGame + ", killsByPlayers=" + killsByPlayers + "]";
	}
}