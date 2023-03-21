import java.util.Scanner;

public class PlayerBoard {
	int[][] tp = new int[6][6];
	int[][] op = new int[6][7];
	int[][] main;
	int pn;
	Scanner input = new Scanner(System.in);
	
	PlayerBoard(int numPlayers){
		if(numPlayers == 2) {
			main = tp;
		}else {
			main = op;
		}
		//main[2][1] = 1;//placeholders
	}
	
	public void setPiece(int PN) {
		System.out.print("Row: ");
		int r = input.nextInt();
		System.out.print("Col: ");
		int c = input.nextInt();
		if(r != 0 && r != 5 && c != 0 && c != 5) {
			if(main[r][c] == 0) {
				main[r][c] = PN;
			}else {
				error("A piece is already on this slot");
			}
		}else {
			error("Out of bounds");
		}
	}
	
	public void push(int r, int c) {
		if(r == 0) {
			if(c != 0 && c != 5) {
				for(int row = 5; row > 0; row--) {
					main[row][c] = main[row-1][c];
				}
			}
			if(main[5][c] != 0) {
				chooseEdge(r, c, main[5][c]);
			}
		}else if(r == 5) {
			if(c != 0 && c != 5) {
				for(int row = 0; row < 5; row++) {
					main[row][c] = main[row+1][c];
				}
			}
			if(main[0][c] != 0) {
				chooseEdge(r, c, main[0][c]);
			}
		}else if(c == 0) {
			if(r != 0 && r!= 5) {
				for(int col = 5; col > 0; col--) {
					main[r][col] = main[r][col-1];
				}
			}
			if(main[r][5] != 0) {
				chooseEdge(r, c, main[r][5]);
			}
		}else if(c == 5) {
			if(r != 0 && r!= 5) {
				for(int col = 0; col < 5; col++) {
					main[r][col] = main[r][col+1];
				}
			}
			if(main[r][0] != 0) {
				chooseEdge(r, c, main[r][0]);
			}
		}
	}
	
	public void chooseEdge(int r, int c, int PN) {
		int choose = 0;
		boolean top = false;
		boolean middle = false;
		boolean bottom = false;
		if(r == 1 && c == 5) { //If top left corner
			System.out.print("This is R1C5");
			if(main[1][1] != 0 || main[2][1] != 0) {
				if(main[1][1] == 0) {
					top = true;
				}
				if(main[2][1] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {	
					main[r][0] = 0;
					main[1][1] = PN;
				}else if(top == false && bottom == true) {
					main[r][0] = 0;
					main[2][1] = PN;
				}else if(top == false && bottom == false) {
					main[1][0] = 0;
				}
			}else {
				System.out.print("Enter 1(R1 C1) or 2(R2 C1)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till correct tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][1] == 0) {
						main[1][0] = 0;
						main[1][1] = PN;
					}else if(choose == 2 && main[2][1] == 0) {
						main[1][0] = 0;
						main[2][1] = PN;
					}
				}
			}
		}else if(r == 4 && c == 5) { //If bottom left corner
			System.out.print("This is R4C5");
			if(main[4][1] != 0 || main[3][1] != 0) {
				if(main[3][1] == 0) {
					top = true;
				}
				if(main[4][1] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					
					main[r][0] = 0;
					main[3][1] = PN;
				}else if(top == false && bottom == true) {
					main[r][0] = 0;
					main[4][1] = PN;
				}else if(top == false && bottom == false){
					main[4][0] = 0;
				}
			}else {
				System.out.print("Enter 1(R3 C1) or 2(R4 C1)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[4][1] == 0) {
						main[4][0] = 0;
						main[3][1] = PN;
					}else if(choose == 2 && main[3][1] == 0) {
						main[4][0] = 0;
						main[4][1] = PN;
					}
				}
			}
		}else if(r == 1 && c == 0) { //If top right corner PUSHING RIGHT
			System.out.print("This is R1C0");
			if(main[1][4] != 0 || main[2][4] != 0) {
				if(main[1][4] == 0) {
					top = true;
				}
				if(main[2][4] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					
					main[r][5] = 0;
					main[1][4] = PN;
				}else if(top == false && bottom == true) {
					main[r][5] = 0;
					main[2][4] = PN;
				}else if(top == false && bottom == false){
					main[1][5] = 0;
				}
			}else {
				System.out.print("Enter 1(R1 C4) or 2(R2 C4)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till correct tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][4] == 0) {
						main[1][5] = 0;
						main[1][4] = PN;
					}else if(choose == 2 && main[2][4] == 0) {
						main[1][5] = 0;
						main[2][4] = PN;
					}
				}
			}
		}else if(r == 4 && c == 0) { //If bottom right corner PUSHING RIGHT
			System.out.print("This is R4C5");
			if(main[4][4] != 0 || main[3][4] != 0) {
				if(main[3][4] == 0) {
					top = true;
				}
				if(main[4][4] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					main[r][5] = 0;
					main[3][4] = PN;
				}else if(top == false && bottom == true) {
					main[r][5] = 0;
					main[4][4] = PN;
				}else if(top == false && bottom == false){
					main[4][5] = 0;
				}
			}else {
				System.out.print("Enter 1(R3 C4) or 2(R4 C4)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[4][4] == 0) {
						main[4][5] = 0;
						main[3][4] = PN;
					}else if(choose == 2 && main[3][4] == 0) {
						main[4][5] = 0;
						main[4][4] = PN;
					}
				}
			}
		}else if(r>1 && r<4 && c == 5) { // L E F T   SIDE   N O N - C O R N E R
			System.out.print("This is non corner");
			if(main[r][1] != 0 || main[r+1][1] != 0 || main[r-1][1] != 0) {
				if(main[r-1][1] == 0) {
					top = true;
				}
				if(main[r][1] == 0) {
					middle = true;
				}
				if(main[r+1][1] == 0) {
					bottom = true;
				}
				if(top == true && middle == false && bottom == false) {
					PN = main[r-1][1];
				}else if(top == false && middle == true && bottom == false) {
					PN = main[r][1];
				}else if(top == false && middle == false && bottom == true) {
					PN = main[r+1][1];
				}else if(top == false && middle == false && bottom == false) {
					main[r][0] = 0;
				}else if(top == true && middle == true && bottom == false) { //	TOP AND MIDDLE
					System.out.print("Enter 1(R"+ (r-1) +" C1), 2(R"+ r +" C1)"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 2) {
						System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[r-1][1] == 0) { //TOP
							main[r][0] = 0;
							main[r-1][1] = PN;
						}else if(choose == 2 && main[r][1] == 0) { //MIDDLE
							main[r][0] = 0;
							main[r][1] = PN;
						}
					}
				}else if(top == true && middle == false && bottom == true) { // TOP AND BOTTOM
					System.out.print("Enter 1(R"+ (r-1) +" C1), 3(R"+ (r+1) +" C1)"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 3) {
						System.out.print("Invalid Input. Choose 1 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[r-1][1] == 0) { //TOP
							main[r][0] = 0;
							main[r-1][1] = PN;
						}else if(choose == 3 && main[r+1][1] == 0) { //BOTTOM
							main[r][0] = 0;
							main[r+1][1] = PN;
						}
					}
				}else if(top == false && middle == true && bottom == true) { // MIDDLE AND BOTTOM
					System.out.print("Enter 2(R"+ r +" C1), 3(R"+ (r+1) +" C1)"); //Click tile
					choose = input.nextInt();
					if(choose != 2 && choose != 3) {
						System.out.print("Invalid Input. Choose 2 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 2 && main[r][1] == 0) { //MIDDLE
							main[r][0] = 0;
							main[r][1] = PN;
						}else if(choose == 3 && main[r+1][1] == 0) { //BOTTOM
							main[r][0] = 0;
							main[r+1][1] = PN;
						}
					}
				}
			}else {
				System.out.print("Enter 1(R"+ (r-1) +" C1), 2(R"+ r +" C1), or 3(R"+(r+1)+" C1)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2 && choose != 3) {
					System.out.print("Invalid Input. Choose 1 or 2 or 3."); //Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[r-1][1] == 0) {
						main[r][0] = 0;
						main[r-1][1] = PN;
					}else if(choose == 2 && main[r][1] == 0) {
						main[r][0] = 0;
						main[r][1] = PN;
					}else if(choose == 3 && main[r+1][1] == 0) {
						main[r][0] = 0;
						main[r+1][1] = PN;
					}
				}
			}
		}else if(r>1 && r<4 && c == 0) { //right side non-corner PUSHING RIGHT
			System.out.print("This is non corner");
			if(main[r][4] != 0 || main[r+1][4] != 0 || main[r-1][4] != 0) {
				if(main[r-1][4] == 0) {
					top = true;
				}
				if(main[r][4] == 0) {
					middle = true;
				}
				if(main[r+1][4] == 0) {
					bottom = true;
				}
				if(top == true && middle == false && bottom == false) {
					PN = main[r-1][4];
				}else if(top == false && middle == true && bottom == false) {
					PN = main[r][4];
				}else if(top == false && middle == false && bottom == true) {
					PN = main[r+1][4];
				}else if(top == false && middle == false && bottom == false) {
					main[r][5] = 0;
				}else if(top == true && middle == true && bottom == false) { //	TOP AND MIDDLE
					System.out.print("Enter 1(R"+ (r-1) +" C4), 2(R"+ r +" C4)"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 2) {
						System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[r-1][4] == 0) { //TOP
							main[r][5] = 0;
							main[r-1][4] = PN;
						}else if(choose == 2 && main[r][1] == 0) { //MIDDLE
							main[r][5] = 0;
							main[r][4] = PN;
						}
					}
				}else if(top == true && middle == false && bottom == true) { // TOP AND BOTTOM
					System.out.print("Enter 1(R"+ (r-1) +" C4), 3(R"+ (r+1) +" C4)"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 3) {
						System.out.print("Invalid Input. Choose 1 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[r-1][4] == 0) { //TOP
							main[r][5] = 0;
							main[r-1][4] = PN;
						}else if(choose == 3 && main[r+1][4] == 0) { //BOTTOM
							main[r][5] = 0;
							main[r+1][4] = PN;
						}
					}
				}else if(top == false && middle == true && bottom == true) { // MIDDLE AND BOTTOM
					System.out.print("Enter 2(R"+ r +" C1), 3(R"+ (r+1) +" C1)"); //Click tile
					choose = input.nextInt();
					if(choose != 2 && choose != 3) {
						System.out.print("Invalid Input. Choose 2 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 2 && main[r][4] == 0) { //MIDDLE
							main[r][5] = 0;
							main[r][4] = PN;
						}else if(choose == 3 && main[r+1][5] == 0) { //BOTTOM
							main[r][5] = 0;
							main[r+1][4] = PN;
						}
					}
				}
			}else {
				System.out.print("Enter 1(R"+ (r-1) +" C4), 2(R"+ r +" C4), or 3(R"+(r+1)+" C4)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2 && choose != 3) {
					System.out.print("Invalid Input. Choose 1 or 2 or 3."); //Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[r-1][4] == 0) {
						main[r][5] = 0;
						main[r-1][4] = PN;
					}else if(choose == 2 && main[r][4] == 0) {
						main[r][5] = 0;
						main[r][4] = PN;
					}else if(choose == 3 && main[r+1][4] == 0) {
						main[r][5] = 0;
						main[r+1][4] = PN;
					}
				}
			}
		}else if(r == 5 && c == 1) { //If top left corner PUSHING UP
			if(main[1][1] != 0 || main[1][2] != 0) {
				if(main[1][1] == 0) {
					top = true;
				}
				if(main[1][2] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {	
					main[0][c] = 0;
					main[1][1] = PN;
				}else if(top == false && bottom == true) {
					main[0][c] = 0;
					main[1][2] = PN;
				}else if(top == false && bottom == false) {
					main[0][1] = 0;
				}
			}else {
				System.out.print("Enter 1(R1 C1) or 2(R1 C2)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till correct tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][1] == 0) {
						main[0][1] = 0;
						main[1][1] = PN;
					}else if(choose == 2 && main[2][1] == 0) {
						main[0][1] = 0;
						main[1][2] = PN;
					}
				}
			}
		}else if(r == 5 && c == 4) { //If top right corner PUSHING UP
			//System.out.print("This is R4C4");
			if(main[1][4] != 0 || main[1][3] != 0) {
				if(main[1][3] == 0) {
					top = true;
				}
				if(main[1][4] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					
					main[0][c] = 0;
					main[1][3] = PN;
				}else if(top == false && bottom == true) {
					main[0][c] = 0;
					main[1][4] = PN;
				}else if(top == false && bottom == false){
					main[0][4] = 0;
				}
			}else {
				System.out.print("Enter 1(R1 C3) or 2(R1 C4)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][4] == 0) {
						main[0][4] = 0;
						main[1][3] = PN;
					}else if(choose == 2 && main[1][3] == 0) {
						main[0][4] = 0;
						main[1][4] = PN;
					}
				}
			}
		}else if(r == 0 && c == 1) { //If bottom left corner PUSHING DOWN
			System.out.print("This is R0C1");
			if(main[4][1] != 0 || main[4][2] != 0) {
				if(main[4][1] == 0) {
					top = true;
				}
				if(main[4][2] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					
					main[5][c] = 0;
					main[4][1] = PN;
				}else if(top == false && bottom == true) {
					main[5][c] = 0;
					main[4][2] = PN;
				}else if(top == false && bottom == false){
					main[5][1] = 0;
				}
			}else {
				System.out.print("Enter 1(R4 C1) or 2(R4 C2)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till correct tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][4] == 0) {
						main[5][1] = 0;
						main[4][1] = PN;
					}else if(choose == 2 && main[2][4] == 0) {
						main[5][1] = 0;
						main[4][2] = PN;
					}
				}
			}
		}else if(r == 5 && c == 4) { //If bottom right corner PUSHING DOWN
			System.out.print("This is R5C4");
			if(main[4][4] != 0 || main[4][3] != 0) {
				if(main[4][3] == 0) {
					top = true;
				}
				if(main[4][4] == 0) {
					bottom = true;
				}
				if(top == true && bottom == false) {
					main[5][c] = 0;
					main[4][3] = PN;
				}else if(top == false && bottom == true) {
					main[5][c] = 0;
					main[4][4] = PN;
				}else if(top == false && bottom == false){
					main[5][4] = 0;
				}
			}else {
				System.out.print("Enter 1(R4 C3) or 2(R4 C4)"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2) {
					System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[4][4] == 0) {
						main[5][4] = 0;
						main[4][3] = PN;
					}else if(choose == 2 && main[4][3] == 0) {
						main[5][4] = 0;
						main[4][4] = PN;
					}
				}
			}
		}else if(c>1 && c<4 && r == 5) { //top side non-corner PUSHING UP
			System.out.print("This is non corner");
			if(main[1][c] != 0 || main[1][c+1] != 0 || main[1][c-1] != 0) {
				if(main[1][c-1] == 0) {
					top = true;
				}
				if(main[1][c] == 0) {
					middle = true;
				}
				if(main[1][c+1] == 0) {
					bottom = true;
				}
				if(top == true && middle == false && bottom == false) {
					PN = main[1][c-1];
				}else if(top == false && middle == true && bottom == false) {
					PN = main[1][c];
				}else if(top == false && middle == false && bottom == true) {
					PN = main[1][c+1];
				}else if(top == false && middle == false && bottom == false) {
					main[0][c] = 0;
				}else if(top == true && middle == true && bottom == false) { //	TOP AND MIDDLE
					System.out.print("Enter 1(R1 C"+ (c-1) +"), 2(R1 C"+ c +")"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 2) {
						System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[1][c-1] == 0) { //TOP
							main[0][c] = 0;
							main[1][c-1] = PN;
						}else if(choose == 2 && main[1][c] == 0) { //MIDDLE
							main[0][c] = 0;
							main[1][c] = PN;
						}
					}
				}else if(top == true && middle == false && bottom == true) { // TOP AND BOTTOM
					System.out.print("Enter 1(R1 C"+ (c-1) +"), 3(R1 C"+ (c+1) +")"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 3) {
						System.out.print("Invalid Input. Choose 1 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[1][c-1] == 0) { //TOP
							main[0][c] = 0;
							main[1][c-1] = PN;
						}else if(choose == 3 && main[1][c+1] == 0) { //BOTTOM
							main[0][c] = 0;
							main[1][c+1] = PN;
						}
					}
				}else if(top == false && middle == true && bottom == true) { // MIDDLE AND BOTTOM
					System.out.print("Enter 2(R1 C"+ c +"), 3(R1 C"+ (c+1) +")"); //Click tile
					choose = input.nextInt();
					if(choose != 2 && choose != 3) {
						System.out.print("Invalid Input. Choose 2 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 2 && main[1][c] == 0) { //MIDDLE
							main[0][c] = 0;
							main[1][c] = PN;
						}else if(choose == 3 && main[1][c+1] == 0) { //BOTTOM
							main[0][c] = 0;
							main[1][c+1] = PN;
						}
					}
				}
			}else {
				System.out.print("Enter 1(R1 C"+ (c-1) +"), 2(R1 C"+ c +"), or 3(R1 C"+ (c+1) +")"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2 && choose != 3) {
					System.out.print("Invalid Input. Choose 1 or 2 or 3."); //Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[1][c-1] == 0) {
						main[0][c] = 0;
						main[1][c-1] = PN;
					}else if(choose == 2 && main[1][c] == 0) {
						main[0][c] = 0;
						main[1][c] = PN;
					}else if(choose == 3 && main[1][c+1] == 0) {
						main[0][c] = 0;
						main[1][c+1] = PN;
					}
				}
			}
		}else if(c>1 && c<4 && r == 0) { //bottom side non-corner PUSHING DOWN
			System.out.print("This is non corner");
			if(main[4][c] != 0 || main[4][c+1] != 0 || main[4][c-1] != 0) {
				if(main[4][c-1] == 0) {
					top = true;
				}
				if(main[4][c] == 0) {
					middle = true;
				}
				if(main[4][c+1] == 0) {
					bottom = true;
				}
				if(top == true && middle == false && bottom == false) {
					PN = main[4][c-1];
				}else if(top == false && middle == true && bottom == false) {
					PN = main[4][c];
				}else if(top == false && middle == false && bottom == true) {
					PN = main[4][c+1];
				}else if(top == false && middle == false && bottom == false) {
					main[5][c] = 0;
				}else if(top == true && middle == true && bottom == false) { //	TOP AND MIDDLE
					System.out.print("Enter 1(R4 C"+ (c-1) +"), 2(R4 C"+ c +")"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 2) {
						System.out.print("Invalid Input. Choose 1 or 2.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[4][c-1] == 0) { //TOP
							main[5][c] = 0;
							main[4][c-1] = PN;
						}else if(choose == 2 && main[4][c] == 0) { //MIDDLE
							main[5][c] = 0;
							main[4][c] = PN;
						}
					}
				}else if(top == true && middle == false && bottom == true) { // TOP AND BOTTOM
					System.out.print("Enter 1(R4 C"+ (c-1) +"), 3(R4 C"+ (c+1) +")"); //Click tile
					choose = input.nextInt();
					if(choose != 1 && choose != 3) {
						System.out.print("Invalid Input. Choose 1 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 1 && main[4][c-1] == 0) { //TOP
							main[5][c] = 0;
							main[4][c-1] = PN;
						}else if(choose == 3 && main[4][c+1] == 0) { //BOTTOM
							main[5][c] = 0;
							main[4][c+1] = PN;
						}
					}
				}else if(top == false && middle == true && bottom == true) { // MIDDLE AND BOTTOM
					System.out.print("Enter 2(R4 C"+ c +"), 3(R4 C"+ (c+1) +")"); //Click tile
					choose = input.nextInt();
					if(choose != 2 && choose != 3) {
						System.out.print("Invalid Input. Choose 2 or 3.");//Ignore other clicks till available tile
						chooseEdge(r, c, PN);
					}else {
						if(choose == 2 && main[4][c] == 0) { //MIDDLE
							main[5][c] = 0;
							main[4][c] = PN;
						}else if(choose == 3 && main[4][c+1] == 0) { //BOTTOM
							main[5][c] = 0;
							main[4][c+1] = PN;
						}
					}
				}
			}else {
				System.out.print("Enter 1(R4 C"+ (c-1) +"), 2(R4 C"+ c +"), or 3(R4 C"+ (c+1) +")"); //Click tile
				choose = input.nextInt();
				if(choose != 1 && choose != 2 && choose != 3) {
					System.out.print("Invalid Input. Choose 1 or 2 or 3."); //Ignore other clicks till available tile
					chooseEdge(r, c, PN);
				}else {
					if(choose == 1 && main[4][c-1] == 0) {
						main[5][c] = 0;
						main[4][c-1] = PN;
					}else if(choose == 2 && main[4][c] == 0) {
						main[5][c] = 0;
						main[4][c] = PN;
					}else if(choose == 3 && main[4][c+1] == 0) {
						main[5][c] = 0;
						main[4][c+1] = PN;
					}
				}
			}
		}
	}
	
	public void displayBoard() {
		System.out.println("Current Board:");
		for(int r = 0; r < main.length; r++) {
			for(int c = 0; c < main[r].length; c++) {
				System.out.print(main[r][c] + ", ");
			}
			System.out.println(); 
		}
	}
	
	public void error(String reason) {
		System.out.println();
		System.out.println("Error: " + reason);
	}
}
