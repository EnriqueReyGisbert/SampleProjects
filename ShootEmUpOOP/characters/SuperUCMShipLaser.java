package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class SuperUCMShipLaser extends UCMShipLaser {

	public SuperUCMShipLaser(Game game, int x, int y, UCMShip ship) {
		super(game, x, y);
		this.setDamage(2);
	}
	
	public SuperUCMShipLaser() {
		super();
	}

	@Override
	public String toString() {
		return "OO";
	}
	
	public String toSerializer() {
		return "X" + ";" + this.x + "," + this.y + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'X' || !verifier.verifyWeaponString(stringFromFile, game))
			return null;
		SuperUCMShipLaser weapon = new SuperUCMShipLaser(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)), null);
		return weapon;
	}

}
