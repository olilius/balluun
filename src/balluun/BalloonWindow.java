package balluun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BalloonWindow extends JFrame implements ActionListener {

	Timer timer;
	GamePanel gamePanel;
	long lastClicked;
	boolean paused;
	
	public BalloonWindow() {
		setTitle("Balloon game");
		gamePanel = new GamePanel();
		setContentPane(gamePanel);
        setResizable(false);
        pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(10, this);
		timer.start();
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				long now = System.currentTimeMillis();
				if (now - lastClicked > 500) {
					lastClicked = now;
					gamePanel.mouseClicked();
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					paused = !paused;
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (paused) {
			return;
		}
		gamePanel.move();
//		balloon.repaint(new Rectangle(balloon.x - 5, balloon.y - 5, 110, 110));
		gamePanel.repaint();
		
		if (gamePanel.balloon.y <= 0) {
			System.out.println("Completed");
		}
		if (gamePanel.balloon.y >= 669) {
			System.out.println("Game over");
		}
	}

	public static void main(String[] args) {
		BalloonWindow b = new BalloonWindow();
		b.setVisible(true);
	}

}