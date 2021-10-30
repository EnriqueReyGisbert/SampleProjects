package tp.p1.game;

public abstract class GamePrinter {
	
	protected Game game;
	
	public abstract String toString();
	
	public void setGame(Game game) {
		this.game = game;
	}
}