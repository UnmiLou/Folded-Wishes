import java.util.ArrayList;

public class Player {
	ArrayList<String> moves = new ArrayList<String>();
	String[] tileList = {"Blue", "Pink", "Yellow", "Green", "PN-Blue", "PN-Pink", "PN-Yellow", "PN-Green"};
	boolean firstMove = true;
	String playerName = "";
	String currentTile;
	int PN; //player number
	int PT; //player tile
	
	Player(int playerNumber){
		PN = playerNumber;
		switch(playerNumber) {
		case 1:
			moves.add("Crumple");
		case 2:
			moves.add("Corner");
		case 3:
			moves.add("Mountain");
		case 4: 
			moves.add("Swap");
		}
	}
	
	public int[] getCount() {
		int[] arr = new int[5];
		int Corner = 0;
		int Crumple = 0;
		int Mountain = 0;
		int Swap = 0;
		int Switch = 0;
		
		
		for(int x = 0; x < moves.size(); x++) {
			if(moves.get(x) == "Corner") {
				Corner++;
			}else if(moves.get(x) == "Crumple") {
				Crumple++;
			}else if(moves.get(x) == "Mountain") {
				Mountain++;
			}else if(moves.get(x) == "Swap") {
				Swap++;
			}else if(moves.get(x) == "Switch") {
				Switch++;
			}
		}
		
		for(int x = 0; x < arr.length; x++) {
			if(x == 0) {
				arr[x] = Corner;
			}else if(x == 1) {
				arr[x] = Crumple;
			}else if(x == 2) {
				arr[x] = Mountain;
			}else if(x == 3) {
				arr[x] = Swap;
			}else if(x == 4) {
				arr[x] = Switch;
			}
		}
		return arr;
	}
	
	public void setMove(boolean move) {
		firstMove = move;
	}
	
	public String getName() {
		return playerName;
	}
}
