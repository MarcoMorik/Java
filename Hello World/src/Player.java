
public class Player {
	private double x,y;
	private String name;
	public Player(double pX, double pY, String pName) {
		x = pX;
		y = pY;
		name = pName;
	}
	public void move(double pX, double pY) {
		x += pX;
		y = y + pY;
	}
	public void printPosition() {
		System.out.println("Meine Position: " + x + ", " + y);
	}
}
