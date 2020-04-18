import java.awt.Color;
import java.awt.Graphics;

public class Player {
	protected final int WIDTH = 40;
	protected final int HEIGHT = 5;
	
	protected int x;
	protected int y;
	
	Player(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void rightPressed() {
		this.x++;
		tick();
	}
	
	public void leftPressed() {
		this.x--;
		tick();
	}
	
	public void tick() {
		if(x + WIDTH > Game.WIDTH)
			x--;
		else if(x < 0) 
			x++;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	

}
