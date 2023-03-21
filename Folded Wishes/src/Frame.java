import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
	//Variables
	String[] colorList = {"Blue", "Pink", "Yellow", "Green"};
	boolean startScreen = true;
	boolean chooseScreen = false;
	boolean playScreen = false;
	boolean firstMove;
	boolean isBetween = false;
	int time = 1;
	int alternator = -2147483648;
	boolean tileChosen = false;
	
	
	//player
	int playerTurn = 1;
	static int playerCount = 2;
	int moveNum = 1;
	
	int PN;
	
	
	//x and y//
	//pb (player board)
	int pbx = 290;
	int pby = 150;
	//pn (player number for choose tiles)
	int pnx = 242;
	int pny = 242;
	int pnxO = -70;
	int pnyO = 0;
	//mouse
	int mouseX = 0;
	int mouseY = 0;
	//pnt
	int pntX = 400;
	int pntY = 300;
	//Classes for functions
	Random rnd = new Random();
	Scanner input = new Scanner(System.in);
	JFrame f = new JFrame("Folded Wishes");
	//Classes for effects/minor graphics
	Effects cherry = new Effects(-170, -10);
	Image blankCursor = getImage("Assets/Transparent Pixel.png");
	Cursor blanker = Toolkit.getDefaultToolkit().createCustomCursor(blankCursor, new Point(0, 0), "blank cursor");
	CustomCursor ninja = new CustomCursor(0, 0);
	//Classes for mechanics
	static Blocks[][] wishes = new Blocks[6][6];
	static Board scan = new Board();
	PlayerBoard play = new PlayerBoard(PN);
	static ArrayList<Player> players = new ArrayList<Player>();
	
	//Imager
	static Imager[] choose = new Imager[4];
	Imager pb = new Imager(620, 300);
	Imager chooseText = new Imager(420, 100);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		cherry.paint(g);
		
		if(startScreen) {
			pb.setScalers(.8, .8);
			pb.paint(g);
		}
		
		if(chooseScreen) {
			chooseText.changeColor("Choose A Tile.png");
			chooseText.setScalers(.8, .8);
			chooseText.paint(g);
			
			for(int x = 0; x < 4; x++) {
				choose[x].setX(x*pntX-pnxO);
				choose[x].setY(pntY);
				choose[x].setScalers(.4, .4);
				choose[x].changeColor("PN-" + colorList[x] + "_Tile.png");
				choose[x].paint(g);
			}
		}
		
		if(playScreen) {
			for(int r = 0; r < wishes.length; r++) {
				for(int c = 0; c < wishes[r].length; c++) {
					if(wishes[r][c] != null) {
						wishes[r][c].paint(g);
					}
				}
			}
		}
		
		//change the tile colors
		for(int r = 0; r < scan.main.length; r++) {
			for(int c = 0; c < scan.main[r].length; c++) {
				if(wishes[r][c] != null) {
					if(scan.main[r][c] == 1) {
						wishes[r][c].changeColor("Blue");
					}else if(scan.main[r][c] == 2) {
						wishes[r][c].changeColor("Pink");
					}else if(scan.main[r][c] == 3) {
						wishes[r][c].changeColor("Yellow");
					}else if(scan.main[r][c] == 4) {
						wishes[r][c].changeColor("Green");
					}else if(scan.main[r][c] == 5) {
						wishes[r][c].changeColor("PN-Blue");
					}else if(scan.main[r][c] == 6) {
						wishes[r][c].changeColor("PN-Pink");
					}else if(scan.main[r][c] == 7) {
						wishes[r][c].changeColor("PN-Yellow");
					}else if(scan.main[r][c] == 8) {
						wishes[r][c].changeColor("PN-Green");
					}else if(scan.main[r][c] == 0) {
						wishes[r][c].setTransparent();
					}
				}
			}
		}
		
		if(playScreen == true) {
			int xPos = 140;
			int yPos = 140;
			int xOffset = -320;
			int yOffset = 0;
			int markR = 0;
			int markC = 0;
			for(int r = 0; r < scan.main.length; r++) {
				for(int c = 0; c < scan.main[r].length; c++) {
					if(r == 0 || r == 5 || c == 0 || c == 5) {
						//System.out.println("Current coords: " + (r*xPos + 320) + ", " + (c*yPos-yOffset));
						if(mouseX >= r*xPos-xOffset && mouseX <= r*xPos-xOffset + 127) {
							if(mouseY >= c*yPos-yOffset && mouseX <= c*yPos-yOffset + 127) {
								System.out.println(mouseX + ", " + mouseY);
								isBetween = true;
								markR = r;
								markC = c;
							}else {
								isBetween = false;
							}
						}else {
							isBetween = false;
						}
					}
				}
			}
			
			if(isBetween = true) {
				System.out.println("isBetween is true");
				wishes[markR][markC] = new Blocks(markR*xPos-xOffset, markC*yPos-yOffset, players.get(playerTurn - 1).currentTile);
			}
		}
		
		f.getContentPane().setCursor(blanker);
		ninja.setX(mouseX-25);
		ninja.setY(mouseY-55);
		ninja.paint(g);
	}
	
	public static void main(String[] arg) {
		@SuppressWarnings("unused")
		Frame f = new Frame();
		int xPos = 140;
		int yPos = 140;
		int xOffset = -320;
		int yOffset = 0;
		for(int r = 0; r < scan.main.length; r++) {
			for(int c = 0; c < scan.main[r].length; c++) {
				int z = scan.main[r][c];
				if(z == 1) {
					wishes[r][c] = new Blocks(r*xPos-xOffset, c*yPos-yOffset, "Blue");
				}else if(z == 2) {
					wishes[r][c] = new Blocks(r*xPos-xOffset, c*yPos-yOffset, "Pink");
				}else if(z == 3) {
					wishes[r][c] = new Blocks(r*xPos-xOffset, c*yPos-yOffset, "Yellow");
				}else if(z == 4) {
					wishes[r][c] = new Blocks(r*xPos-xOffset, c*yPos-yOffset, "Green");
				}
			}
		}
		
		for(int x = 0; x < playerCount; x++) {
			players.add(new Player(x+1));
		}
		
		for(int r = 0; r < choose.length; r++) {
			choose[r] = new Imager(0, 0);
		}
		
		/*JButton btn1 = new JButton("Full-Screen");
	    btn1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            device.setFullScreenWindow(f);
	        }
	    });
	    JButton btn2 = new JButton("Normal");
	    btn2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            device.setFullScreenWindow(null);
	        }
	    });

	    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panel.add(btn1);
	    panel.add(btn2);
	    f.add(panel);

	    f.pack();
	    f.setVisible(true);
		*/
	}
	
	public Frame() {
		Image img = getImage("Assets/Folded Wishes Logo.png");
		f.setIconImage(img);
		f.setSize(new Dimension(1920, 1080));
		f.setBackground(Color.blue);
		f.add(this);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setVisible(true);
		f.setResizable(true);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(time, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Blocks.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		
		//Shiny Button
		if(startScreen == true) {
			int offsetY = 15;
			if(arg0.getX() >= pb.getX() && arg0.getX() <= pb.getX() + pbx) {
				if(arg0.getY() >= pb.getY() + offsetY && arg0.getY() <= pb.getY() + pby) {
					pb.changeColor("ShinyPlay_Button.png");
				}else {
					pb.changeColor("Play_Button.png");
				}
			}else {
				pb.changeColor("Play_Button.png");
			}
		}
	}
	
	public void mouseDragged(MouseEvent arg0) {
		//shinyButton
		//int offsetX = 0;
		int offsetY = 15;
		mouseX = arg0.getX();
		mouseY = arg0.getY();
		if(mouseX >= pb.getX() && mouseX <= pb.getX() + pbx) {
			if(mouseY >= pb.getY() + offsetY && mouseY <= pb.getY() + pby) {
				pb.changeColor("ShinyPlay_Button.png");
			}else {
				pb.changeColor("Play_Button.png");
			}
		}else {
			pb.changeColor("Play_Button.png");
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//pb
		//+15 is offset
		if(mouseX >= pb.getX() && mouseX <= pb.getX() + pbx) {
			if(mouseY >= pb.getY()+ 15 && mouseY <= pb.getY() + pby) {
				startScreen = false;
				chooseScreen = true;
			}
		}
		
		//pnt
		//+20 is offset
		for(int x = 0; x < choose.length; x++) {
			if(mouseX >= choose[x].getX() && mouseX <= choose[x].getX() + pnx) {
				if(mouseY >= choose[x].getY() && mouseY <= choose[x].getY() + pny + 40) {
					int tileNum = x;
					for(int i = 0; i < playerTurn; i++) {
						if(players.get(i).PT == tileNum+1 && playerTurn != 1) {
							tileChosen = true;
						}
					}
					if(tileChosen == false) {
						players.get(playerTurn - 1).currentTile = players.get(playerTurn - 1).tileList[x+4];
						players.get(playerTurn - 1).PT = x+1;
						System.out.println("Player "+ (playerTurn) + ": " + players.get(playerTurn-1).PT);
						if(playerTurn != playerCount) {
							playerTurn++;
						}else {
							chooseScreen = false;
							playScreen = true;
						}
					}else {
						tileChosen = false;
						System.out.println("Player number already chosen");
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.print(arg0.getKeyCode() +", ");
		if(arg0.getKeyCode() == 32){ //Spacebar
			if(alternator % 2 == 0) {
				chooseScreen = true;
			}else {
				playScreen = false;
			}
			alternator++;
		}
		
		if(arg0.getKeyCode() == 70) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}