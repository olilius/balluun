package balluun;

public class Balloon {

	int radius;
	int height; 

	int x = 462;
	int y = 668;
	
	int xDelta = 0;
	int yDelta = -1;
	int yClick = 0;
	
	public Balloon(int radius) {
		this.radius = radius;
	}

	public void move() {
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
}
