package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class ExplosiveShip extends RegularShip {

	public ExplosiveShip(Game game, int x, int y, int life) {
		super(game, x, y);
		this.live = life;
	}
	
	public ExplosiveShip() {
		super();
	}
	
	public void onDelete() {
		this.game.explode(this.x, this.y);
		super.onDelete();
	}
	
	public void computerAction() {
		
	}
	
	public String toString() {
		return "E[" + Integer.toString(this.live) + "]";
	}
	
	public String toSerializer() {
		return "E" + ";" + this.x + "," + this.y + ";" + this.live + ";" + cyclesToMove + ";" + dir + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'E' || !verifier.verifyExplosiveString(stringFromFile, game, 2))
			return null;
		cyclesToMove = Character.getNumericValue(stringFromFile.charAt(8));
		dir = stringFromFile.charAt(10);
		return new ExplosiveShip(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)), Character.getNumericValue(stringFromFile.charAt(6)));
	}
}
