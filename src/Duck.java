import java.awt.*;

public class Duck implements Constants {
	private int x;
	private int y;
	private int wsize;
	private int hsize;
	private int xSpeed;
	private int ySpeed;
	private static RandomGenerator duckx = new RandomGenerator();
	private static RandomGenerator ducky = new RandomGenerator();
	private static RandomGenerator speedRandom = new RandomGenerator();
	Image image = FrameUtil.getImage("duck.png");
	Image image2 = FrameUtil.getImage("chiefduck.png");

	public void drawDuck(Graphics g) {
		g.drawImage(image, x, y, wsize,hsize,null);
	}
	public void drawDuck2(Graphics g) {
		g.drawImage(image2, x, y, wsize,hsize,null);
	}

	public void move(Duck duck) {
		x+=xSpeed;
		y+=ySpeed;
		if(x>=GWIDTH-duck.getWsize()) {
			xSpeed = -Math.abs(xSpeed);
		}
		else if(x<=0) {
			xSpeed = Math.abs(xSpeed);
		}
		else if(y>=GHEIGHT-duck.getHsize()) {
			ySpeed = -Math.abs(ySpeed);
		}
		else if(y<=0) {
			ySpeed = Math.abs(ySpeed);
		}
	}
/*	public void move() {
		x+=xSpeed;
		y+=ySpeed;
	}*/
	public Duck() {
		this.x =   duckx.getRandom2(50,GWIDTH);
		this.y = ducky.getRandom2(50,GHEIGHT);
		this.xSpeed = speedRandom.getRandom(MIN_SPEED,MAX_SPEED);
		this.ySpeed = speedRandom.getRandom(MIN_SPEED,MAX_SPEED);
		this.wsize = 40;
		this.hsize = 40;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public int getWsize() {
		return wsize;
	}

	public void setWsize(int wsize) {
		this.wsize = wsize;
	}

	public int getHsize() {
		return hsize;
	}

	public void setHsize(int hsize) {
		this.hsize = hsize;
	}
	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}








