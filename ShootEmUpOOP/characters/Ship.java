package tp.p1.characters;

import tp.p1.game.Game;

public abstract class Ship extends GameObject {

	public Ship(Game game, int x, int y, int live) {
		super(game, x, y, live);
	}
	
	public Ship() {
		super();
	}
	
	public String toSerializer() {
		return super.toSerializer() + ";" + this.live; 
	}
}
