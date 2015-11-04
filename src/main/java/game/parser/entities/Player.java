package game.parser.entities;

public class Player {

	private String name;

	private int killed;

	private int died;

	public Player() {

	}

	public Player( String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getKilled() {
		return killed;
	}

	public void setKilled( int killed ) {
		this.killed = killed;
	}

	public int getDied() {
		return died;
	}

	public void setDied( int died ) {
		this.died = died;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", killed=" + killed + ", died=" + died + "]";
	}
}