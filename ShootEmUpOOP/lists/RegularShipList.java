package tp.p1.lists;
import tp.p1.characters.*;

public class RegularShipList {
	
	public RegularShipList(int numRegular) {									// Constructor RegularShipList.
		this.list[0] = new RegularShip(1, 3);
		this.list[1] = new RegularShip(1, 4);
		this.list[2] = new RegularShip(1, 5);
		this.list[3] = new RegularShip(1, 6);
		this.cont = 4;
		if(numRegular == 8) {
			this.list[4] = new RegularShip(2, 3);
			this.list[5] = new RegularShip(2, 4);
			this.list[6] = new RegularShip(2, 5);
			this.list[7] = new RegularShip(2, 6);
			this.cont = 8;
		}
		this.contOriginal = this.cont;
	}
	
	private RegularShip list[] = new RegularShip[8];
	private int cont;
	private int contOriginal;
	
	public int getOriginalCounter() {											// Devuelve el contador inicial de la
		return this.contOriginal;												// RegularShipList.
	}
	
	public void looseLife(int x, int y) {										// Pierde una vida el RegularShip de la lista
		int i = 0;																// en la posición (x,y) si existe.
		while(this.list[i].comparePosition(x, y))
			i++;
		if(!this.list[i].loseLife()) {  
			this.list[i].copy(this.list[this.cont - 1]);
			this.cont--;
		}
	}
	
	public int minY() {															// Devuelve la coordenada Y mínima de entre
		int min = 8, i = 0;														// todos los RegularShip de la lista.
		while((min != 0) && (i < this.cont)) {
			if(min > this.list[i].getY());
				min = this.list[i].getY();
			i++;
		}
		return min;
	}
	
	public int maxY() {															// Devuelve la coordenada Y máxima de entre
		int max = 0, i = 0;														// todos los RegularShip de la lista.
		while((max != 8) && (i < this.cont)) {
			if(max < this.list[i].getY())
				max = this.list[i].getY();
			i++;
		}
		return max;
	}
	
	public void move(int x, int y) {											// Mueve todos los RegularShip de la lista.
		for(int i = 0; i < this.cont; i++)
			this.list[i].move(x,y);  
	}
	
	public boolean comparePosition(int x, int y, boolean quieroDestruir) {		// Compara la posición de todos los RegularShip
		boolean encontrado = false;												// de la lista con una dada, y si una coincide
		int i = 0;																// resta una vida a ese RegularShip, si 
		while((i < this.cont) && !encontrado) {									// quieroDestruir esta puesto a True.
			encontrado = this.list[i].comparePosition(x, y);
			if(!encontrado)
				i++;
		}
		if(quieroDestruir) {
			boolean destruido = false;
			if(encontrado) {
				destruido = this.list[i].loseLife();
				if(!destruido) {
					this.list[i].copy(this.list[this.cont - 1]);
					this.cont--;
				}
			}
		}
		return encontrado;
	}
	
	public int getLife(int x, int y) {											// Devuelve la vida de un RegularShip de la
		boolean encontrado = false;												// lista en la posición (x,y) si existe.
		int i = 0;
		while((i < this.cont) && !encontrado) {
			encontrado = this.list[i].comparePosition(x, y);
			if(!encontrado)
				i++;
		}
		return this.list[i].getLife();
	}
	
	public void shockWave() {													// Genera los efectos de usar un Shockwave
		boolean[] arrayDestruidos = new boolean[this.cont];						// en la RegularShipList.
		for(int i = 0; i < this.cont; i++) {
			arrayDestruidos[i] = !this.list[i].loseLife();
		}
		for(int i = 0; i < this.cont; i++) {
			while(arrayDestruidos[i] && (this.cont > 0)) {
				this.list[i].copy(this.list[this.cont - 1]);
				this.cont--;
			}
		}
	}
	
	public int getCounter() {													// Devuelve el contador de la RegularShipList.
		return this.cont;
	}
}
