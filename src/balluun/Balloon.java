package balluun;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Balloon {

	int radius;
	int height; 

	int x = 512;
	int y = 768;
	
	int xDelta = 0;
	int yDelta = -1;
	int yClick = 0;

	private BufferedImage image;
	private BufferedImage boomImage;
	
	boolean exploded;

	public Balloon(int radius) {
		this.radius = radius;
		try { 
			image = ImageIO.read(new File("balluun.png"));
		} catch (IOException ex) {
            // handle exception...
		}
		try { 
			boomImage = ImageIO.read(new File("boom.png"));
		} catch (IOException ex) {
            // handle exception...
		}
	}

	public void move() {
		if (exploded) {
			return;
		}
		x = x + xDelta;
		y = y + yDelta;
		if (yClick > 0) {
			yClick--;
			if (yClick > 40) {
				y = y + 4;
			} else if (yClick > 30) {
					y = y + 3;
			} else if (yClick < 8) {
				y = y + 1;
			} else {
				y = y + 2;
			}
		}
	}

	public void setExploded(boolean isExploded) {
		exploded = isExploded;
	}
	
	public void mouseClicked() {
		yClick = 50;
	}

	public boolean overlaps(Block b) {
		float halfWidth = ((float)b.width / 2f);
		float halfHeight = ((float)b.height / 2f);
		
	    float cx = Math.abs(x - b.x - halfWidth);
	    float xDist =  + radius;
	    if (cx > xDist)
	        return false;
	    float cy = Math.abs(y - b.y - halfHeight);
	    float yDist = halfHeight + radius;
	    if (cy > yDist)
	        return false;
	    if (cx <= halfWidth || cy <= halfHeight)
	        return true;
	    float xCornerDist = cx - halfWidth;
	    float yCornerDist = cy - halfHeight;
	    float xCornerDistSq = xCornerDist * xCornerDist;
	    float yCornerDistSq = yCornerDist * yCornerDist;
	    float maxCornerDistSq = radius * radius;
	    return xCornerDistSq + yCornerDistSq <= maxCornerDistSq;
	}

	public void paintComponent(Graphics g) {
		if (exploded) {
			g.drawImage(boomImage, x - boomImage.getWidth()/2, y - boomImage.getHeight()/2, null);
		} else {
			g.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
		}
	}
}
