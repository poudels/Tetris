import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class ZShape implements Shape{
	
	LinkedList<Shape> allItems = new LinkedList<Shape>();
	private int i = 9;
	private int j = -1;
	private int scale = 20;
	private CellColor color;
	private int POS_X = i * scale;
	private int POS_Y = j * scale;
	
	
	private int[][] getRotateX() {
		int[][] out = {
				{ getPosX(), getPosX() - scale, getPosX() + scale, getPosX() },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() - scale },
				{ getPosX(), getPosX(), getPosX(), getPosX() - scale } };
		return out;
	}

	private int[][] getRotateY() {
		int[][] out = { { getPosY(), getPosY(), getPosY(), getPosY() + scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() + scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() },
				{ getPosY(), getPosY() - scale, getPosY() + scale, getPosY() } };
		return out;
	}
	
	public ZShape(){
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);
	}
	
	public void draw(Graphics gc) {
		gc.setColor(color);
		gc.fill3DRect(POS_X + scale, POS_Y, scale, scale, true);
		gc.fill3DRect(POS_X + 2 * scale, POS_Y, scale, scale, true);
		gc.fill3DRect(POS_X, POS_Y + scale, scale, scale, true);
		gc.fill3DRect(POS_X + scale, POS_Y + scale, scale, scale, true);
	}
	
	public void stepRight() {
		POS_X += scale;
	}
	
	public void stepLeft() {
//		if (POS_X < scale)
//			return;
		POS_X = POS_X - scale;
	}
	
	public void stepDown() {
		POS_Y = POS_Y + scale;
	}
	
	public void rotate(){
		
	}
	
	public int getPosX() {
		return POS_X;
	}
	
	public int getPosY() {
		return POS_Y;
	}
	
	public FilledCell[] getCell() {
		
		FilledCell[] c = {new FilledCell(POS_X + scale, POS_Y, scale, color),
				new FilledCell(POS_X + 2 * scale, POS_Y, scale, color),
				new FilledCell(POS_X, POS_Y + scale, scale, color),
				new FilledCell(POS_X + scale, POS_Y + scale, scale, color)};
		return c;
	}
}
