package tp.p1.lists;
import tp.p1.characters.*;

public class BombList {
	
	public BombList(int num) {												// Constructor BombList.							
		this.cont = num;
		this.list = new Bomb[num];
		for(int i = 0; i < this.cont; i++)
			this.list[i] = new Bomb();
	}
	
	private Bomb list[];
	private int cont;
	
	public boolean exists(int i) {											// Devuelve si existe la bomba en la posición i.
		return this.list[i].exists();
	}
	
	public void reduceCont() {
		this.cont--;
	}
	
	public int getCont() {
		return this.cont;
	}
	
	public int comparePosition(int x, int y, boolean quieroDestruir) {		// Compara las posiciones de las bombas de la
		int encontrado = -1;												// lista con la dada y si quieroDestruir esta puesto
		int i = 0;															// a True, destruye la bomba de esa posicion.
		while((i < this.cont) && (encontrado == -1)) {
			if(this.list[i].comparePosition(x, y))
				encontrado = i;
			if(encontrado == -1)
				i++;
		}
		if(quieroDestruir) {
			if(encontrado != -1) {
				this.list[i].destroy();
			}
		}
		return encontrado;
	}
	
	public void move() {													// Mueve las bombas de la lista.
		for(int i = 0; i < this.cont; i++) {
			this.list[i].move();
		}
	}
	
	public void shoot(int posiciones[][]) {									// Crea una bomba disparada por un DestroyerShip.
		for(int i = 0; i < this.cont; i++) {
			if(!this.list[i].exists() && (posiciones[i][0] != -1)) {
				this.list[i].create(posiciones[i][0], posiciones[i][1]);
			}
		}
	}
}
