package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class UCMShipLaser extends Weapon {
	
	public UCMShipLaser(Game game, int x, int y) {
		super(game, x, y, 1, 1);
		this.ship = game.getPlayer();
		UCMShip.canShoot = false;
	}
	
	public UCMShipLaser() {
		super();
	}
	
	@SuppressWarnings("unused")
	private UCMShip ship;
	
	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		this.game.enableMissile();
	}

	@Override
	public String toString() {
		return "oo";
	}

	@Override
	public void move() {
		this.x--;
	}
	
	public boolean receiveBombAttack() {
		this.getDamage(1);
		return true;
	}
	
	public boolean performAttack(GameObject other) {
		if(other.receiveMissileAttack(this.getMyDamage())) {
			this.getDamage(this.live);
			return true;
		}
		return false;
	}
	
	public String toSerializer() {
		return "M" + super.toSerializer() + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'M' || !verifier.verifyWeaponString(stringFromFile, game))
			return null;
		UCMShipLaser weapon = new UCMShipLaser(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)));
		return weapon;
	}

}
