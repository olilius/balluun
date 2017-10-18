package balluun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {

	private int xDelta;
	Color color;
	boolean exploded;

	public Block(int x, int y, int width, int height, int xDelta, Color color) {
		super(x, y, width, height);
		this.xDelta = xDelta;
		this.color = color;
	}
	
	public void move() {
		if (exploded) {
			return;
		}
		x = x + xDelta;
		if (x > 900 || x <= 0) {
			xDelta = -xDelta;
		}
	}

	public void paintComponent(Graphics g) {
	   Graphics2D g2d = (Graphics2D)g;
	   g2d.setColor(color);
	   g2d.fill(this);
	}

	public void setExploded(boolean isExploded) {
		exploded = isExploded;
	}
}
