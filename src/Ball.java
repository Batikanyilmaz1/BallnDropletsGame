//Batıkan Yılmaz
//120200036


public class Ball {
	private int clickHeal = 1;
	private int defHeal = 10;
	private int x, y, xVelocity, yVelocity;
	
	//ball constructor
	public Ball(int x, int y, int xVelocity, int yVelocity) {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		
	}

	//setters and getters
	public int getClickHeal() {
		return clickHeal;
	}

	public void setClickHeal(int clickHeal) {
		this.clickHeal = clickHeal;
	}

	public int getDefHeal() {
		return defHeal;
	}

	public void setDefHeal(int defHeal) {
		this.defHeal = defHeal;
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


	public int getxVelocity() {
		return xVelocity;
	}


	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}


	public int getyVelocity() {
		return yVelocity;
	}


	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	
}
