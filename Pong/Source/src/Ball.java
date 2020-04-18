import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x;
	public double y;
	public double dx;
	public double dy;
	
	private double angle;
	
	private final double SPEED = 0.7;
	private final int WIDTH = 5;
	private final int HEIGHT = 5;
	
	Ball(){
		this.x = Game.WIDTH/2;
		this.y = 40;
		
		angle = new Random().nextInt(120 - 60) + 60 + 1;
		
		while(angle<110 && angle>70) {
			angle = new Random().nextInt(120 - 60) + 61;
		}
		
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick(){
		
		x +=  dx * SPEED;
		y += dy * SPEED;
		
		// Ball Outside	
		if(y >= Game.HEIGHT)
		{
			//Ponto do inimigo.
			System.out.println("Enemy's point!");
			new Game().start();
			return;
		}else if(y <= 0) {
			//Ponto do jogador.
			System.out.println("Player's point");
			new Game().start();
			return;
		}
		
		if(x<=0 || x>=Game.WIDTH - WIDTH)
			dx*= -1;
		
		// Colisions
		Rectangle bounds = new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.WIDTH, Game.player.HEIGHT);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, Game.enemy.y, Game.player.WIDTH, Game.player.HEIGHT);
		
		if(bounds.intersects(boundsPlayer)) {
			angle = new Random().nextInt(120 - 60) + 61;
			
			while(angle<110 && angle>70) {
				angle = new Random().nextInt(120 - 60) + 61;
			}
			
			this.dx = Math.sin(Math.toRadians(angle));	
			this.dy = Math.cos(Math.toRadians(angle));	
			
			if(dy>0)
				dy *= -1; 
		}else if(bounds.intersects(boundsEnemy)) {
			angle = new Random().nextInt(120 - 60) + 61;
			
			while(angle<110 && angle>70) {
				angle = new Random().nextInt(120 - 60) + 61;
			}
			
			this.dx = Math.sin(Math.toRadians(angle));	
			this.dy = Math.cos(Math.toRadians(angle));	
			
			if(dy<0)
				dy *= -1;
		}
		
	}
	
	public void render(Graphics g){
		tick();
		g.setColor(new Color(255, 255, 255));
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
	
}
