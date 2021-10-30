package tp.p1.game;

public class Stringifier extends GamePrinter {
	
	public Stringifier() {
		
	}
	
	public String toString() {
		return this.game.serialize();
	}
}
