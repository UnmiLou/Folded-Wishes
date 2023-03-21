import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Blocks {
	// add location attributes
	private Image img;
	private AffineTransform tx;
	private int x, y;
	public ArrayList<String> folds = new ArrayList<String>();
	Random rnd = new Random(1234);

	public Blocks(int x, int y, String color) {
		
		if(color.equals("Blue")) {
			folds.add("Corner");
		}else if(color.equals("Pink")) {
			folds.add("Crumple");
			folds.add("Crumple");
		}else if(color.equals("Yellow")) {
			folds.add("Mountain");
		}else if(color.equals("Green")) {
			folds.add("Swap");
		}
		this.x = x;
		this.y = y;
		img = getImage("/Assets/" + color + "_Tile.png"); // load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		// initialize the location of the image
		// use your variables
	}
	
	
	
	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		// call update to update the actually picture location
		update();

		g2.drawImage(img, tx, null);

	}

	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.21, .21);
	}

	public void changeColor(String color) {
		img = getImage("/Assets/" + color + "_Tile.png");
	}
	
	public void setTransparent() {
		img = getImage("/Assets/Transparent Pixel.png");
	}
	
	public static void nextMoves(int i, Blocks[][] data) {
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.8, .8);
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
}
