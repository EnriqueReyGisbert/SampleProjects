package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class Shockwave extends Weapon {

	public Shockwave(Game game) {
		super(game, 0, -3, 1, 1);
		this.canShock = false;
	}
	
	public Shockwave() {
		super();
	}
	
	private boolean canShock;
	
	public boolean getCanShock() {
		return this.canShock;
	}
	
	public void setCanShock(boolean puede) {
		this.canShock = puede;
	}
	
	public void computerAction() {
		
	}

	public void onDelete() {
		
	}

	public void move() {
		
	}

	public String toString() {
		return null;
	}

	public boolean performAttack(GameObject other) {
		if(other.receiveShockWaveAttack(this.getMyDamage())) {
			this.getDamage(1);
			return true;
		}
		return false;
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		return null;
	}
}
