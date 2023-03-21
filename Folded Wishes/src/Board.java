import java.util.Random;

public class Board {
	Random r = new Random();
	boolean firstMove;
	public int[][] main = new int[6][6];
	public int[] blockBoard = {1, 2, 3, 4, 4, 1, 2, 3, 3, 4, 1, 2, 2, 3, 4, 1};
	
	int x = 1;
	
	/*int[] randomize(int[] arr, int n) {
		int[] returnArr = new int[16];
		for(int row = 0; row < arr.length; row++) {
			
		}
		
		return arr;
	}*/
	
	Board() {
		firstMove = true;
		int x = 0;
		 for(int r = 1; r < 5; r++) {
			 for(int c = 1; c < 5; c++) {
				 main[r][c] = blockBoard[x];
				 x++;
			 }
		 }
	}
	
	public void displayBoard() {
		System.out.println("Current Board:");
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 6; c++) {
				System.out.print(main[r][c] + ", ");
			}
			System.out.println();
		}
	}
	
	public void push(int r, int c, int block) {
		boolean error = false;
		switch(r) {
		case 0:
			if(c != 0 && c != 5) {
				for(int row = 5; row > 0; row--) {
					main[row][c] = main[row-1][c];
				}
				main[1][c] = block;
				main[5][c] = 0;
			}else if(error == false){
				error("Code Overlap");
				error = true;
			}
			break;
		case 5:
			if(c != 0 && c != 5) {
				for(int row = 0; row < 5; row++) {
					main[row][c] = main[row+1][c];
				}
				main[4][c] = block;
				main[0][c] = 0;
			}else if(error == false){
				error("Code Overlap");
				error = true;
			}
			break;
		}
		switch(c) {
		case 0:
			if(r != 0 && r!= 5) {
				for(int col = 5; col > 0; col--) {
					main[r][col] = main[r][col-1];
				}
				main[r][1] = block;
				main[r][5] = 0;
			}else if(error == false){
				error("Code Overlap");
				error = true;
			}
			break;
		case 5:
			if(r != 0 && r!= 5) {
				for(int col = 0; col < 5; col++) {
					main[r][col] = main[r][col+1];
				}	
				main[r][4] = block;
				main[r][0] = 0;
			}else if(error == false){
				error("Code Overlap");
				error = true;
			}
			break;
		}
		if(error == false) {
			System.out.println("");
			displayBoard();
		}
		
	}
	
	public void error(String reason) {
		System.out.println();
		System.out.println("Error: " + reason);
	}
	
	public void setfirst(boolean move) {
		firstMove = move;
	}
}
