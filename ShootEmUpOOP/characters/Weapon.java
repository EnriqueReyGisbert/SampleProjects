package tp.p1.characters;

import tp.p1.game.Game;

public abstract class Weapon extends GameObject {

	public Weapon(Game game, int x, int y, int live, int damage) {
		super(game, x, y, live);
		this.setDamage(damage);
	}
	
	public Weapon() {
		super();
	}

	private int damage;
	
	public int getMyDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public String toSerializer() {
		return super.toSerializer();
	}
	
}
