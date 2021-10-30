package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class DestroyerShip extends AlienShip {
	
	public DestroyerShip(Game game, int x, int y, int label) { 		
		super(game, x, y, 1, 10);
		this.canShoot = true;
		this.label = label;
	}
	
	public DestroyerShip() {
		super();
	}
	
	private boolean canShoot;
	private int label;

	public void move(int x, int y) {								
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public void setCanShoot(boolean bool) {							
		this.canShoot = bool;
	}
	
	public boolean getCanShoot() {								
		return this.canShoot;
	}
	
	public int getLabel() {
		return this.label;
	}
	
	public boolean checkLabel(int label) {
		return this.label == label;
	}

	public void computerAction() {
		if(this.canShoot) {
			if(canGenerateRandomBomb(this.game)) {
				this.setCanShoot(false);
				Bomb bomba = new Bomb(this.game, this.x + 1, this.y, this);
				this.game.addObject(bomba);
			}
		}
	}

	private boolean canGenerateRandomBomb(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getDF();
	}

	public String toString() {
		return "D[" + Integer.toString(this.live) + "]";
	}
	
	public String toSerializer() {
		return "D" + super.toSerializer() + " - " + this.label + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'D' || !verifier.verifyAlienShipString(stringFromFile, game, 1))
			return null;
		DestroyerShip alien = new DestroyerShip(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)), Character.getNumericValue(stringFromFile.charAt(14)));
		alien.live = Character.getNumericValue(stringFromFile.charAt(6));
		cyclesToMove = Character.getNumericValue(stringFromFile.charAt(8));
		dir = stringFromFile.charAt(10);
		return alien;
	}
}
