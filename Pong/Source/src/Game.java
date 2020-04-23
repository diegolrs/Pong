import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	boolean isRunning = false;
	
	private BufferStrategy bs;
	private BufferedImage image;
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 4;
	
	private final int SPEED = 4;
	private final int FPS = 60;
	private final long ns = 1000000000/FPS;
	private long lastTime = System.nanoTime();
	
	private boolean rightPressedPlayer = false;
	private boolean leftPressedPlayer = false;
	
	public static void main(String[] args) {
		Game game = new Game();
		Thread thread = new Thread(game);
		JFrame frame = new JFrame();
		
		game.start();
		game.startFrame(frame);
		thread.start();
	}
	
	Game(){
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	public synchronized void start() {
		player = new Player(100, 155);
		enemy = new Enemy(100, 0);
		ball = new Ball();
		isRunning = true;
	}
	
	public void startFrame(JFrame frame) {
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void tick() {
		if(rightPressedPlayer) {		
			player.rightPressed();	
		} else if (leftPressedPlayer) {
			player.leftPressed();
		}
		
	}
	
	public void render() {
		bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		// Background
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		ball.render(g);
		enemy.render(g);
		
		g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();
	}
	
	
	public void startBufferStrategy(BufferStrategy bs) {
		if(bs == null) {
			this.createBufferStrategy(3);	
			return;
		}
		
		bs.show();
	}
	
	public void run() {
		while(isRunning) {
			this.requestFocus();
			long now = System.nanoTime();
			
			if(now - lastTime >= ns/SPEED) {
				tick();
				render();				
				lastTime = now;
			}			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressedPlayer = true;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressedPlayer = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressedPlayer = false;
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressedPlayer = false;
		
	}
	
}
