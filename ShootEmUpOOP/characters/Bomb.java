package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class Bomb extends Weapon{
	
	public Bomb(Game game, int x, int y, DestroyerShip ship) {			
		super(game, x, y, 1, 1);
		if(ship != null)
			this.label = ship.getLabel();
		else
			this.label = 0;
	}
	
	public Bomb() {
		super();
	}
	
	private int label;
	
	public void copy(Bomb original) {      					
		this.x = original.x;
		this.y = original.y;
	}
	
	public void move() {           								
		this.x++;
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		DestroyerShip ship = this.game.getDestroyer(this.label);
		if(ship != null)
			ship.setCanShoot(true);
	}

	@Override
	public String toString() {
		return ".";
	}
	
	public boolean performAttack(GameObject other) {
		if(other.receiveBombAttack(this.getMyDamage())) {
			this.getDamage(this.live);
			return true;
		}
		return false;
	}
	
	public boolean receiveMissileAttack(int damage){
		this.getDamage(damage);
		return true;
	}

	public String toSerializer() {
		return "B" + super.toSerializer() + " - " + this.label + '\n';
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier) throws FileContentsException {
		if(stringFromFile.charAt(0) != 'B' || !verifier.verifyWeaponString(stringFromFile, game))
			return null;
		int aux = Character.getNumericValue(stringFromFile.charAt(8));
		DestroyerShip destroyer = game.getDestroyer(aux);
		Bomb weapon = new Bomb(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)),
				destroyer);
		return weapon;
	}
}
