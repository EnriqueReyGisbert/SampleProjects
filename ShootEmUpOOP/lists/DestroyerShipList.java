package tp.p1.lists;
import tp.p1.characters.*;
import java.util.*;

public class DestroyerShipList {
	
	public DestroyerShipList(int numDestroyer, int numRegular) { 				// Constructor DestroyerShipList.
		if(numRegular == 4) {
			this.list[0] = new DestroyerShip(2, 4);
			this.list[1] = new DestroyerShip(2, 5);
			this.cont = 2;
		}
		else {
			this.list[0] = new DestroyerShip(3, 4);
			this.list[1] = new DestroyerShip(3, 5);
			this.cont = 2;
			if(numDestroyer == 4) {
				this.list[2] = new DestroyerShip(3, 3);
				this.list[3] = new DestroyerShip(3, 6);
				this.cont = 4;
			}
		}
		this.contOriginal = this.cont;
	}
	
	private DestroyerShip list[] = new DestroyerShip[4];
	private int cont;
	private int contOriginal;
	
	public int getOriginalCounter() {											// Devuelve el contador inicial
		return this.contOriginal;												// de la DestoyerShipList.
	}
	
	public int[][] shoot(double probabilidad, Random rnd) {						// Disparan los DestroyerShip
		int posiciones[][] = new int[this.cont][2];
		for(int i = 0; i < this.cont; i++) {
			double random = rnd.nextDouble();
			if((random < probabilidad) && !this.list[i].getHasShot()) {
				posiciones[i][0] = this.list[i].getX() + 1;
				posiciones[i][1] = this.list[i].getY();
				this.list[i].setShoot(true);
			}
			else {
				posiciones[i][0] = -1;
				posiciones[i][1] = -1;
			}
		}
		return posiciones;
	}
	
	public int minY() {															// Devuelve la coordenada Y mínima de entre
		int min = 8, i = 0;														// todos los DestroyerShip de la lista.
		while((min != 0) && (i < this.cont)) {
			if(min > this.list[i].getY())
				min = this.list[i].getY();
			i++;
		}
		return min;
	}
	
	public int maxY() {															// Devuelve la coordenada Y máxima de entre						
		int max = 0, i = 0;														// todos los DestroyerShip de la lista.
		while((max != 8) && (i < this.cont)) {
			if(max < this.list[i].getY())
				max = this.list[i].getY();
			i++;
		}
		return max;
	}
	
	public void move(int x, int y) {											// Mueve todos los DestroyerShip de la lista.
		for(int i = 0; i < this.cont; i++)
			this.list[i].move(x,y);  
	}
	
	public boolean comparePosition(int x, int y, boolean quieroDestruir) {		// Compara la posición de todos los DestroyerShip
		boolean destruido = false;												// de la lista con una dada, y si una coincide
		int i = 0;																// destruye ese DestroyerShip si quieroDestruir
		while((i < this.cont) && !destruido) {									// esta puesto a True.
			destruido = this.list[i].comparePosition(x, y);
			if(!destruido) {
				i++;
			}
		}
		if(quieroDestruir) {
			if(destruido) {
				this.list[i].copy(this.list[this.cont - 1]);
				this.cont--;
				
			}
		}
		return destruido;
	}
	
	public void shockWave() {													// Genera los efectos de usar un Shockwave
		this.cont = 0;															// en la DestroyerShipList.
	}
	
	public int getCounter() {													// Devuelve el contador de la DestroyerShipList.
		return this.cont;
	}
	
	public void setCanShoot(int i) {											// Pone el parámetro setShoot a falso.
		this.list[i].setShoot(false);
	}
}
