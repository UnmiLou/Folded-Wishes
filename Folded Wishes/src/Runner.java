
public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board tiles = new Board();
		PlayerBoard pieces = new PlayerBoard(2);
		
		pieces.displayBoard();
		pieces.setPiece(1);
		pieces.setPiece(2);
		pieces.displayBoard();
		
		//tiles.push(3, 5, 1);
		pieces.push(5, 3);
		pieces.displayBoard();
		Board x = new Board();
		int test = x.main[1][1];
	}
}
