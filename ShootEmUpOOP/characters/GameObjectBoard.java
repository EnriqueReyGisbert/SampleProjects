package tp.p1.characters;

import tp.p1.game.*;

public class GameObjectBoard {
	
	private GameObject[] objects;
	private int currentObjects;
	
	public GameObjectBoard (int width, int height) {
		this.currentObjects = 0;
		this.objects = new GameObject[width * height];
	}
	
	public void add (GameObject object) {
		this.objects[this.currentObjects] = object;
		this.currentObjects++;
	}
	
	private GameObject getObjectInPosition (int x, int y) {
		int i = getIndex(x,y);
		if (i != this.currentObjects) {
			return this.objects[i];
		}
		else {
			return null;
		}
	}
	
	private int getIndex(int x, int y) {
		boolean encontrado = false;
		int i = 0;
		while((i < this.currentObjects) && !encontrado) {
			if(this.objects[i].isOnPosition(x, y)) {
				encontrado = true;
			}
			else {
				i++;
			}
		}
		return i;
	}
	
	private void remove(GameObject object) {
		object.setX(-2);
		int i = getIndex(object.getX(), object.getY());
		for(int j = i; j < this.currentObjects - 1; j++) {
			this.objects[j] = this.objects[j + 1];
		}
		this.currentObjects--;
		this.objects[this.currentObjects] = null;
	}
	
	public void update(Game game) {
		AlienShip.cyclesToMove--;
		for(int i = 0; i < this.currentObjects;i++) {
			this.objects[i].move();
			checkAttacks(this.objects[i]);
			while(i < this.currentObjects && this.objects[i].isOut()) {
				this.objects[i].onDelete();
				this.remove(this.objects[i]);
				if(i < this.currentObjects) {
					this.objects[i].move();
					checkAttacks(this.objects[i]);
				}
			}
		}
		for(int i = 0; i < this.currentObjects; i++) {
			this.objects[i].updateMovement();
		}
		if(AlienShip.cyclesToMove == 0)
			AlienShip.cyclesToMove = game.getLevel().getSp();
		if(AlienShip.dir == 'D')
			AlienShip.cyclesToMove = 1;
	}
	
	private void checkAttacks(GameObject object) {
		int x = object.getX(), y = object.getY();
		object.setX(-2);
		GameObject other = getObjectInPosition(x, y);
		object.setX(x);
		if(other != null) {
			object.performAttack(other);
			other.performAttack(object);
			this.removeDead();
		}
	}
	
	public void useShockWave(Game game, UCMShip ucm) {
		for(int i = 0; i< this.currentObjects; i++) {
			ucm.getSuperPower().performAttack(this.objects[i]);
		}
		this.removeDead();
	}
	
	public void computerAction() {
		for(int i = 0; i < this.currentObjects; i++) {
			this.objects[i].computerAction();
		}
	}
	
	private void removeDead() {
		for(int i = 0; i < this.currentObjects; i++) {
			while(i < this.currentObjects && !this.objects[i].isAlive()) {
				this.objects[i].onDelete();
				if(!this.objects[i].isAlive())
					this.remove(this.objects[i]);
				this.removeDead();
			}
		}
	}
	
	public String toString (int x, int y) {
		if(this.getObjectInPosition(x,y) != null) {
			return this.getObjectInPosition(x,y).toString();
		}
		else {
			return " ";
		}
	}
	
	public GameObject getDestroyer(int label) {
		for(int i = 0; i < currentObjects;i++) {
			if(this.objects[i].checkLabel(label))
				return this.objects[i];
		}
		return null;
	}
	
	public void explode(int x, int y) {
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i != 0 || j != 0) {
					GameObject object = this.getObjectInPosition(x + i, y + j);
					if(object != null && (j != 0 || i != 0))
						object.getDamage(1);
				}
			}
		}
	}
	
	public void createExplosive(int x, int y, Game game) {
		int i = this.getIndex(x, y);
		ExplosiveShip ship = new ExplosiveShip(game, x, y, this.objects[i].getLive());
		this.objects[i] = ship;
	}
	
	public String serialize() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < this.currentObjects; i++) {
			String aux = this.objects[i].toSerializer();
			if(aux != null)
				str.append(aux);
		}
		return str.toString();
	}
}
