package tp.p1.characters;

import tp.p1.game.Game;

public abstract class AlienShip extends EnemyShip {	
	
	public AlienShip(Game game, int x, int y, int live, int puntos) {
		super(game, x, y, live, puntos);
		cyclesToMove = 1;
		if(y == 0)
			prevDir = 'L';
		if(y == game.getDIM_Y() - 1)
			prevDir = 'R';
	}
	
	public AlienShip() {
		super();
	}
	
	public static int cyclesToMove;

	public static boolean haveLanded;
	
	public static char dir;
	
	public static char prevDir;

	public void updateMovement() {
		if (this.y == 0) {
			if(dir == 'L') {
				dir = 'D';
				prevDir = 'L';
			}
			else if(dir == 'D' && prevDir == 'L') {
				prevDir = dir;
				dir = 'R';
			}
		}
		else if(this.y == (this.game.getDIM_Y() - 1)) {
			if(dir == 'R') {
				dir = 'D';
				prevDir = 'R';
				
			}
			else if(dir == 'D' && prevDir == 'R') {
				prevDir = dir;
				dir = 'L';
			}
		}
	}
	
	public int getAlienY() {
		return this.y;
	}
	
	public void move() {
		if(dir == 'D') {
			this.x++;
		}
		else if(cyclesToMove == 0) {
			if(dir == 'L')
				this.y--;
			else
				this.y++;
		}
		if(this.x == this.game.getDIM_X() - 1)
			AlienShip.haveLanded = true;
	}
	
	public void onDelete() {
		this.game.destroyAlien();
		super.onDelete();
	}
	
	public String toSerializer() {
		return super.toSerializer() + ";" + cyclesToMove + ";" + dir;
	}
}
