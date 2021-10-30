package tp.p1.characters;

import tp.p1.game.Game;

public abstract class EnemyShip extends Ship {

	protected int points;
	
	public EnemyShip(Game game, int x, int y, int live, int puntos) {
		super(game, x, y, live);
		this.points = puntos;
	}
	
	public EnemyShip() {
		super();
	}
	
	public void move() {
		
	}
	
	public void onDelete() {
		this.game.receivePoints(this.points);
	}

	public boolean receiveMissileAttack(int damage){
		this.getDamage(damage);
		return true;
	}

	public boolean receiveShockWaveAttack(int damage) {
		this.getDamage(damage);
		return true;
	}
	
}