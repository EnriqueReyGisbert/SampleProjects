package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class RegularShip extends AlienShip implements IExecuteRandomActions {
	
	public RegularShip(Game game, int x, int y) { 	
		super(game, x, y, 2, 5);
	}
	
	public RegularShip() {
		super();
	}

	public void move(int x, int y) {								
		this.x = this.x + x;
		this.y = this.y + y;
	}

	public void computerAction() {
		if(canConvertIntoExplosive(game))
			this.game.createExplosive(this.x, this.y);
	}
	
	static boolean canConvertIntoExplosive(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getEF();
	}

	@Override
	public String toString() {
		return "R[" + Integer.toString(this.live) + "]";
	}
	
	public String toSerializer() {
		return "R" + super.toSerializer() + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'R' || !verifier.verifyAlienShipString(stringFromFile, game, 2))
			return null;
		RegularShip alien = new RegularShip(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)));
		alien.live = Character.getNumericValue(stringFromFile.charAt(6));
		cyclesToMove = Character.getNumericValue(stringFromFile.charAt(8));
		dir = stringFromFile.charAt(10);
		return alien;
	}
}