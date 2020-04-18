import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Player{
	
	public double x;
	public int y;
	private final double DIFFICULTY = 0.7;
	
	Enemy(int x, int y) {
		super(x, y);
	}
	
	public void tick() {
		x = Game.ball.x * DIFFICULTY;
		
		while(x > Game.WIDTH - super.WIDTH)
			x--;
		
		while(x < 0) 
			x++;
		
	}
	
	public void render(Graphics g) {
		tick();
		g.setColor(new Color(255, 255, 255));
		g.fillRect((int)x, super.y, super.WIDTH, super.HEIGHT);
	}

}
