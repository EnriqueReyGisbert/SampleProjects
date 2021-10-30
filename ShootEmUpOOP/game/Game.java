package tp.p1.game;
import tp.p1.characters.*;
import java.util.Random;
import tp.p1.game.Level;
import Exceptions.*;
import java.io.*;

public class Game implements IPlayerController{

	public final static int DIM_X = 9;
	public final static int DIM_Y = 8;

	private int currentCycle;
	private Random rand;
	private Level level;
	GameObjectBoard board;
	private UCMShip player;
	private boolean doExit;
	private BoardInitializer initializer;
	private int numAliens;
	private int numSuperLasers;
	
	public Game (Level level, Random random) {
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		this.numAliens = this.level.getDN() + this.level.getRN();
		this.board = new GameObjectBoard(DIM_X, DIM_Y);
	}
	
	public void initGame () {
		currentCycle = 0;
		this.numSuperLasers = 0;
		board = initializer.initialize(this,level);
		player = new UCMShip(this, DIM_X - 1, DIM_Y / 2, 3);
		board.add(player);
		Ovni.setExistsOvni(false);
		AlienShip.haveLanded = false;
		AlienShip.dir = 'L';
	}
	
	public void destroyAlien() {
		this.numAliens--;
	}
	
	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void reset() {
		initGame();
	}
	
	public void addObject(GameObject object) {
		board.add(object);
	}
	
	public String positionToString(int x, int y) {
		return board.toString(x, y);
	}
	
	public boolean isFinished() {
		return playerWin() || aliensWin() || doExit;
	}
	
	public boolean aliensWin() {
		boolean aux = !UCMShip.alive || AlienShip.haveLanded;
		if(aux)
			System.out.println("Aliens win.");
		return aux;
	}
	
	private boolean playerWin () {
		boolean aux = this.numAliens == 0;
		if(aux)
			System.out.println("You win.");
		return aux;
	}
	
	public void update() {
		board.computerAction();
		board.update(this);
		currentCycle += 1;
		System.out.println(this.infoToString());
	}
	
	public boolean isOnBoard(int x, int y) {
		return x >= 0 && x < DIM_X;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public String infoToString() {
		return "Cycles: " + currentCycle + "\n" +
			player.state() +
			"\n" + "Remaining aliens: " + this.numAliens + "\n" + "Number of SuperMissiles: " + this.numSuperLasers + "\n";
	}
	
	public String getWinnerMessage () {
		if (this.playerWin()) return "Player win!";
		else if (this. aliensWin()) return "Aliens win!";
		else return "Player exits the game";
	}
	
	public void move (int numCells) throws OutOfWorldException {
		this.player.move(numCells);
	}
	
	public void shootLaser(boolean big) throws MissilInFlightException{
		if(big) {
			if(this.numSuperLasers <= 0) {
				throw new MissilInFlightException();
			}
			this.player.shoot(big);
		}
		else {
			this.player.shoot(big);
		}
	}
	
	public void shockWave() throws NoShockwaveException {
		this.player.tryUseSuperpower();
		this.board.useShockWave(this, this.player);
		this.player.setSuperPower(false);
	}

	public void receivePoints(int points) {
		this.player.addPoints(points);
	}
	
	public void enableShockWave() {
		this.player.setSuperPower(true);
	}
	
	public void enableMissile() {
		this.player.setShoot();
	}

	public String characterAtToString(int x, int y) {
		if(this.player.isOnPosition(x, y))
			return this.player.toString();
		else 
			return this.board.toString(x, y);
	}
	
	public int getDIM_Y() {
		return DIM_Y;
	}
	
	public int getDIM_X() {
		return DIM_X;
	}
	
	public void explode(int x, int y) {
		this.board.explode(x, y);
	}
	
	public void createExplosive(int x, int y) {
		this.board.createExplosive(x, y, this);
	}
	
	public void addSuperLaser(boolean add){
		if(add)
			this.numSuperLasers++;
		else {
			if(this.numSuperLasers > 0) {
				this.numSuperLasers--;
			}
		}
	}
	
	public int getPoints() {
		return this.player.getPoints();
	}

	
	public int getMissiles() {
		return this.numSuperLasers;
	}
	
	public UCMShip getPlayer() {
		return this.player;
	}
	
	public String serialize() {
		StringBuilder str = new StringBuilder();
		str.append(" — Space Invaders v2.0 — ");
		str.append('\n');
		str.append('\n');
		str.append("G;").append(this.currentCycle);
		str.append('\n');
		str.append("L;").append(this.level);
		str.append('\n');
		str.append(this.board.serialize());
		return str.toString();
	}
	
	public void load(BufferedReader inChars) throws FileContentsException, IOException {
		FileContentsVerifier verifier = new FileContentsVerifier();
		boolean exito = true;
		char aux1, aux2;
		aux1 = (char) inChars.read();
		aux2 = (char) inChars.read();
		String line = inChars.readLine();
		if(!verifier.verifyCycleString(Character.toString(aux1) + Character.toString(aux2) + line))
			throw new FileContentsException();
		this.currentCycle = Integer.parseInt(line);
		aux1 = (char) inChars.read();
		aux2 = (char) inChars.read();
		line = inChars.readLine();
		if(!verifier.verifyLevelString(Character.toString(aux1) + Character.toString(aux2) + line))
			throw new FileContentsException();
		if(line.toUpperCase().equals("EASY")) {
			level = Level.EASY;
		}
		else if(line.toUpperCase().equals("HARD")) {
			level = Level.HARD;
		}
		else if(line.toUpperCase().equals("INSANE")) {
			level = Level.INSANE;
		}
		else {
			exito = false;
			System.out.println("First argument is incorrect.");
		}
		if(exito) {
			line = inChars.readLine().trim();
			while(line != null && !line.isEmpty() ) {
				GameObject gameObject = GameObjectGenerator.parse(line, this, verifier);
				if (gameObject == null) {
					throw new FileContentsException("invalid file, " + "unrecognised line prefix");
				}
				board.add(gameObject);
				line = inChars.readLine().trim();
			}
			if(!Ovni.getExistsOvni()) {
				board.add(new Ovni(this));
			}
		}
	}
	
	public DestroyerShip getDestroyer(int label) {
		return (DestroyerShip) this.board.getDestroyer(label);
	}

	public void setMissiles(int x) {
		this.numSuperLasers = x;
	}
	
	public GameObjectBoard getGOB() {
		return this.board;
	}
	
	public int getNumAliens() {
		return this.numAliens;
	}
	
	public int getCurrentCycle() {
		return this.currentCycle;
	}
	
	public void setPlayer(UCMShip player) {
		this.player = player;
	}
	
	public void setGame(Game game) { 
		this.rand = game.getRandom();
		this.level = game.getLevel();
		this.numAliens = game.getNumAliens();
		this.board = game.getGOB();
		this.numSuperLasers = game.getMissiles();
		this.player = game.getPlayer();
		this.doExit = false;
		this.currentCycle = game.getCurrentCycle();
	}
}