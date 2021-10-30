package tp.p1.game;

import tp.p1.util.MyStringUtils;

public class FormattedPrinter extends GamePrinter {
	String[][] board;
	final String space = " ";
	
	
	public FormattedPrinter () {  
		
	}
	
	private void encodeGame() {   
		board = new String[this.game.getDIM_X()][this.game.getDIM_Y()];
		for(int i = 0; i < this.game.getDIM_X(); i++) {
			for(int j = 0; j < this.game.getDIM_Y(); j++) {
				board[i][j] =  this.game.characterAtToString(i, j);
			}
		}
	}
	
	public String toString() {
		
		this.encodeGame();
		
		int cellSize = 7;
		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";
		
		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (this.game.getDIM_Y() * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
		
		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);
		
		for(int i=0; i<this.game.getDIM_X(); i++) {
				str.append(margin).append(vDelimiter);
				for (int j=0; j<this.game.getDIM_Y(); j++) {
					str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
				}
				str.append(lineDelimiter);   
		}
		return str.toString();
	}
}
