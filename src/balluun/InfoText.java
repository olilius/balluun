package balluun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class InfoText {

	String text;
	int color;
	int colorDelta;
	
	public void setPaused(boolean isPaused) {
		if (isPaused) {
			text = "Paused";
			color = 255;
			colorDelta = -1;
		} else {
			text = null;
		}
	}

	public void setExploded(boolean isExploded) {
		if (isExploded) {
			text = "Game Over";
			color = 255;
			colorDelta = -1;
		} else {
			text = null;
		}
	}

	public void move() {
		if (text != null) {
			color = color + colorDelta;
			if (color == 255 || color == 150) {
				colorDelta = -colorDelta;
			}
		}
	}

	public void paintComponent(Graphics g) {
		if (text == null) {
			return;
		}
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Chinese Font", Font.BOLD, 100));
		g2d.setColor(new Color(0, color, 0));
		
		int width = g.getFontMetrics().stringWidth(text);
		
		g2d.drawString(text, 512 - width/2, 768/2 - 50);
	}

}
