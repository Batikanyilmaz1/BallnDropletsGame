//Batıkan Yılmaz
//120200036

public class Droplet {
	private int clickHeal = 3;
	private int dmgFloor = 3;
	private int dmgCollision = 5;
	private int x, y, yVelocity;

	//droplet constructor
	public Droplet(int x, int y, int yVelocity) {	
		this.x = x;
		this.y = y;
		this.yVelocity = yVelocity;
	}

	//setters and getters
	public int getClickHeal() {
		return clickHeal;
	}

	public void setClickHeal(int clickHeal) {
		this.clickHeal = clickHeal;
	}

	public int getDmgFloor() {
		return dmgFloor;
	}

	public void setDmgFloor(int dmgFloor) {
		this.dmgFloor = dmgFloor;
	}

	public int getDmgCollision() {
		return dmgCollision;
	}

	public void setDmgCollision(int dmgCollision) {
		this.dmgCollision = dmgCollision;
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

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}


	
	
}
