package balluun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class GamePanel extends JPanel {

	Balloon balloon;
	List<Block> blocks;
	
	public GamePanel() {
		balloon = new Balloon(50);
		setPreferredSize(new Dimension(1024, 768));
		blocks = new ArrayList<>();
		blocks.add(new Block(100, 100, 180, 20, 1, new Color(30, 30, 30)));
		blocks.add(new Block(350, 250, 180, 20, 2, new Color(30, 30, 30)));
		blocks.add(new Block(400, 400, 180, 20, -1, new Color(30, 30, 30)));
		blocks.add(new Block(400, 550, 180, 20, -2, new Color(30, 30, 30)));
	}

	public void move() {
		balloon.move();
		for (Block b : blocks) {
			b.move();
		}
		for (Block b : blocks) {
			if (balloon.overlaps(b)) {
				System.out.println("HIT!!! "+System.currentTimeMillis());
			}
		}
	}

	public void mouseClicked() {
		balloon.mouseClicked();
	}

	@Override
    public void paintComponent(Graphics g){
	   super.paintComponent(g);
	   Graphics2D g2d = (Graphics2D)g;
	   // Assume x, y, and diameter are instance variables.
	   Ellipse2D.Double circle = new Ellipse2D.Double(balloon.x, balloon.y, balloon.radius * 2, balloon.radius * 2);

	   g.setColor(new Color(50, 200, 50));

	   g2d.fill(circle);
	   
		for (Block b : blocks) {
			g2d.setColor(b.color);
			g2d.fill(b);
		}

	   Toolkit.getDefaultToolkit().sync();
     }

 }