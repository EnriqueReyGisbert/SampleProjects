package tp.p1.characters;

import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;
import Exceptions.*;

public class UCMShip extends Ship {
	
	public UCMShip(Game game, int x, int y, int live) {
		super(game, x, y, live);
		canShoot = true;
		this.points = 0;
		this.setSuperPower(new Shockwave(game));
		alive = true;
	}
	
	public UCMShip() {
		super();
	}

	public static boolean canShoot;
	private int points;
	private Shockwave superPower;
	private String player = "^__^";
	public static boolean alive;
	
	public void shoot(boolean big) throws MissilInFlightException {
		if(!canShoot)
			throw new MissilInFlightException();
		if(!big) {
				UCMShipLaser laser = new UCMShipLaser(game, this.x, this.y);
				this.game.addObject(laser);
			}
			else {
				SuperUCMShipLaser laser = new SuperUCMShipLaser(game, this.x, this.y, this);
				this.game.addObject(laser);
			}
			canShoot = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void move(int x) throws OutOfWorldException {								
		this.y += x;
		if((this.y < 0) || (this.y >= this.game.getDIM_Y())) {
			this.y -= x;
			throw new OutOfWorldException();
		}
	}
	
	public void tryUseSuperpower() throws NoShockwaveException {								
		if(!this.getSuperPower().getCanShock()) {
			throw new NoShockwaveException();
		}
	}
	
	public boolean returnSuperpower() {								
		return this.getSuperPower().getCanShock();
	}
	
	public void setShoot() {
		canShoot = true;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return this.points;
	}

	@Override
	public void computerAction() {
		
	}

	@Override
	public void onDelete() {
		this.live = 1;
		this.player = "!xx!";
		alive = false;
	}

	@Override
	public void move() {
		
	}
	
	public String state() {
		return "Puntos: " + this.points + "\n" + "Superpower: " + this.superPower.getCanShock() + "\n" + "Lives: " + this.live;
	}

	@Override
	public String toString() {
		return this.player;
	}

	public boolean receiveBombAttack(int damage) {
		this.getDamage(damage);
		return true;
	}

	public Shockwave getSuperPower() {
		return superPower;
	}
	
	public void setSuperPower(Shockwave shockwave) {
		this.superPower = shockwave;
	}

	public void setSuperPower(boolean b) {
		this.superPower.setCanShock(b);
	}
	
	public String toSerializer() {
		String s = "P" + super.toSerializer() + ";" + this.points + ";";
		if(canShoot)
			s += "T";
		else
			s += "F";
		s += ";" + this.game.getMissiles() + '\n';
		return s;
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'P' || !verifier.verifyPlayerString(stringFromFile, game, 3))
			return null;
		UCMShip alien = new UCMShip(game, Character.getNumericValue(stringFromFile.charAt(2)), 
				Character.getNumericValue(stringFromFile.charAt(4)), Character.getNumericValue(stringFromFile.charAt(6)));
		char aux = stringFromFile.charAt(8);
		int i = 0;
		while(aux != ';') {
			i++;
			alien.points = alien.points * 10 + Character.getNumericValue(aux);
			aux = stringFromFile.charAt(8 + i);
		}
		i--;
		if(Character.getNumericValue(stringFromFile.charAt(10 + i)) == 'F')
			canShoot = false;
		else
			canShoot = true;
		alien.game.setMissiles(Character.getNumericValue(stringFromFile.charAt(12 + i)));
		game.setPlayer(alien);
		return alien;
	}
}
