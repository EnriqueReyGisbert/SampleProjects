package tp.p1.characters;

import tp.p1.game.*;

public class BoardInitializer {
	
	private Level level;
	private GameObjectBoard board;
	private Game game;
	
	public GameObjectBoard initialize(Game game, Level level) {
		int row = 1;
		this.level = level;
		this.game = game;
		board = new GameObjectBoard(Game.DIM_X, Game.DIM_Y);
		initializeOvni();
		row = initializeRegularAliens(row);
		initializeDestroyerAliens(row);
		return board;
	}
	
	private void initializeOvni () {
		board.add(new Ovni(game));
	}
	
	private int initializeRegularAliens (int currentRow) {
		int num = this.level.getRN(), maxPorFila = 4, currentCol = (this.game.getDIM_Y() / 2) - (maxPorFila / 2);
		for(int i = 0; i < num / maxPorFila;i++) {
			for(int j = 0; j < maxPorFila; j++) {
				board.add(new RegularShip(game, currentRow, currentCol));
				currentCol++;
			}
			currentCol = (this.game.getDIM_Y() / 2) - (maxPorFila / 2);
			currentRow++;
		}
		int row = currentRow;
		return row;
	}
	
	private void initializeDestroyerAliens (int currentRow) {
		int num = this.level.getDN(), currentCol = this.game.getDIM_Y() / 2 - 1, n = 1;
			for(int j = 0; j < num; j++) {
				board.add(new DestroyerShip(game, currentRow, currentCol, j + 1));
				if(n % 2 == 0)
					currentCol -= n;
				else
					currentCol += n;
				n++;
			}
			currentCol = this.game.getDIM_Y() / 2 + 1;
			currentRow++;
		}
}