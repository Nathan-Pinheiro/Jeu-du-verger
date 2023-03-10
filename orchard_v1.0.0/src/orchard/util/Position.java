package orchard.util;

public class Position {

	private int x;
	private int y;
	
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int y() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Position add(Position p) {
		return new Position(x + p.x(), y + p.y());
	}
	
	
}
