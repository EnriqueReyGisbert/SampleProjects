package tp.p1.game;
import Exceptions.*;

public interface IPlayerController {
	// PLAYER ACTIONS
	public void move (int numCells) throws OutOfWorldException;
	public void shootLaser(boolean big) throws MissilInFlightException;
	public void shockWave() throws NoShockwaveException;
	// CALLBACKS
	public void receivePoints(int points);
	public void enableShockWave();
	public void enableMissile();
}
