import java.awt.Graphics;


public class SShape implements Shape{
	
	private int i = 7;
	private int j = -1;
	protected int scale = 20;

	private int POS_X = i * scale;
	private int POS_Y = j * scale;
	private int orient = 0;
	
	protected int[][] orientX() {
		int[][] out = {{}};
		return out;
	}

	protected int[][] orientY() {
		int[][] out = null;
		return out;
	}

	private CellColor color;

	public SShape() {
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);
	}

	public void draw(Graphics gc) {
		gc.setColor(color);
		for(int i = 0; i < 4; i++){
			gc.fill3DRect(orientX()[orient][i], orientY()[orient][i], scale, scale, true);
		}
	}

	public void stepRight() {
		POS_X += scale;
	}

	public void stepLeft() {
		POS_X -= scale;
	}

	public void stepDown() {
		POS_Y += scale;
	}

	public void rotate() {
		orient = (orient + 1) % 4;
	}
	
	public int getPosX() {
		return POS_X;
	}

	public int getPosY() {
		return POS_Y;
	}

	public FilledCell[] getCell() {
		FilledCell[] c = new FilledCell[4];
		for (int i = 0; i < 4; i++) {
			c[i] = new FilledCell(orientX()[orient][i], orientY()[orient][i], scale, color);
		}
		return c;
	}
	
	public FilledCell[] getRotationCell(){
		FilledCell[] c = new FilledCell[4];
		int o = (orient + 1) % 4;
		for (int i = 0; i < 4; i++) {
			c[i] = new FilledCell(orientX()[o][i], orientY()[o][i], scale, color);
		}
		return c;
	}
}
