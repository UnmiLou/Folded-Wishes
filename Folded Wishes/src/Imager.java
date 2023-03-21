import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Imager {
	// add location attributes
	private Image img;
	private AffineTransform tx;
	private int x, y;
	public double scaleX, scaleY;
	
	public Imager(int x, int y) {
		this.x = x;
		this.y = y;
		scaleX = 1;
		scaleY = 1;
		img = getImage("/Assets/Transparent Pixel.png");
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
	}
	
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}

	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(scaleX, scaleY);
	}

	public void changeColor(String File) {
		img = getImage("/Assets/"+ File);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}
	
	public void setScalers(double setX, double setY) {
		scaleX = setX;
		scaleY = setY;
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
			URL imageURL = Imager.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
