import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class CustomCursor {
	// add location attributes
	private Image img;
	private AffineTransform tx;
	private int x, y;
	private int width, height;

	public CustomCursor(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/Assets/Ninja Star Cursor.gif"); // load the image for Tree
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
		tx.scale(1.6, 1.6);
	}

	public void changeClick() {
		img = getImage("/Assets/Ninja Star Cursor.gif");
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = CustomCursor.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}