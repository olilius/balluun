package balluun;

import java.awt.Color;
import java.awt.Rectangle;

public class Block extends Rectangle {

	private int xDelta;
	Color color;

	public Block(int x, int y, int width, int height, int xDelta, Color color) {
		super(x, y, width, height);
		this.xDelta = xDelta;
		this.color = color;
	}
	
	public void move() {
		x = x + xDelta;
		if (x > 900 || x <= 0) {
			xDelta = -xDelta;
		}
	}
}
