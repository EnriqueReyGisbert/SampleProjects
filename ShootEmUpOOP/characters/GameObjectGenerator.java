package tp.p1.characters;

import tp.p1.game.*;
import Exceptions.*;

public class GameObjectGenerator {
	private static GameObject[] availableGameObjects = {
			new UCMShip(),
			new Ovni(),
			new RegularShip(),
			new DestroyerShip(),
			new ExplosiveShip(),
			new Shockwave(),
			new Bomb(),
			new UCMShipLaser(),
			new SuperUCMShipLaser()
	};
	
	public static GameObject parse(String stringFromFile, Game game, FileContentsVerifier verifier)	throws FileContentsException {
		GameObject gameObject = null;
		for (GameObject go: availableGameObjects) {
				gameObject = go.parse(stringFromFile, game, verifier);
				if (gameObject != null) break;
		}
		return gameObject;
	}
}   
