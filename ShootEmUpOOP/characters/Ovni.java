package tp.p1.characters;

import Exceptions.FileContentsException;
import tp.p1.game.FileContentsVerifier;
import tp.p1.game.Game;

public class Ovni extends EnemyShip implements IExecuteRandomActions {
	
	private static boolean existsOvni;
	
	public Ovni(Game game) { 				// Constructor Ovni.
		super(game, 0, -1, 1, 25);
		this.live = 1;
		setExistsOvni(true);
	}
	
	public Ovni() {
		setExistsOvni(true);
	}

	public void move() {											// Mueve un Ovni una posición hacia la izquierda si es posible.
		if(this.y != -1)
			this.y = this.y - 1;
	}
	
	@Override
	public void computerAction() {
		if(canGenerateRandomOvni(this.game) && (this.y == -1)) {
			this.live = 1;
			this.y = this.game.getDIM_Y();
		}
	}
	
	private boolean canGenerateRandomOvni(Game game) {
		return game.getRandom().nextDouble() < game.getLevel().getOF();
	}

	public void onDelete() {
		this.game.enableShockWave();
		if(this.live == 0) {
			this.y = -1;
			this.live = 1;
		}
		super.onDelete();
	}
	
	@Override
	public String toString() {
		return "O[" + Integer.toString(this.live) + "]";
	}
	
	public String toSerializer() {
		if(this.y != -1)
			return "O" + super.toSerializer() + ";" + this.live + '\n';
		return null;
	}
	
	public GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		if(stringFromFile.charAt(0) != 'O' || !verifier.verifyOvniString(stringFromFile, game, 1))
			return null;
		Ovni alien = new Ovni(game);
		alien.setX(Character.getNumericValue(stringFromFile.charAt(2)));
		alien.setY(Character.getNumericValue(stringFromFile.charAt(4)));
		return alien;
	}

	public static boolean getExistsOvni() {
		return existsOvni;
	}

	public static void setExistsOvni(boolean existsOvni) {
		Ovni.existsOvni = existsOvni;
	}
}