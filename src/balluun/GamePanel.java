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
	InfoText infoText;
	boolean paused;
	
	public GamePanel() {
		balloon = new Balloon(50);
		infoText = new InfoText();
		setPreferredSize(new Dimension(1024, 768));
		blocks = new ArrayList<>();
		blocks.add(new Block(100, 100, 180, 20, 1, new Color(30, 30, 30)));
		blocks.add(new Block(350, 250, 180, 20, 2, new Color(30, 30, 30)));
		blocks.add(new Block(400, 400, 180, 20, -1, new Color(30, 30, 30)));
		blocks.add(new Block(400, 550, 180, 20, -2, new Color(30, 30, 30)));
	}

	public void move() {
		infoText.move();
		if (paused) {
			return;
		}
		balloon.move();
		for (Block b : blocks) {
			b.move();
		}
		for (Block b : blocks) {
			if (balloon.overlaps(b)) {
				balloon.setExploded(true);
				System.out.println("HIT!!! "+System.currentTimeMillis());
				for (Block bl : blocks) {
					bl.setExploded(true);
				}
				infoText.setExploded(true);
			}
		}
	}
	
	public void setPaused(boolean isPaused) {
		paused = isPaused;
		infoText.setPaused(isPaused);
	}

	public void mouseClicked() {
		balloon.mouseClicked();
	}

	@Override
    public void paintComponent(Graphics g){
	   super.paintComponent(g);
	   balloon.paintComponent(g);

		for (Block b : blocks) {
			b.paintComponent(g);
		}

		infoText.paintComponent(g);
	   Toolkit.getDefaultToolkit().sync();
     }

 }