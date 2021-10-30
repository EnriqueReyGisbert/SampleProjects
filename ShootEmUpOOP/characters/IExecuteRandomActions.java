package tp.p1.characters;

import tp.p1.game.*;

public interface IExecuteRandomActions {
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getOF();
	}
	
	static boolean canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getDF();
	}
	
	static boolean canConvertIntoExplosive(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getEF();
	}
}