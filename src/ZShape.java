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
	private int orient = 0;
	
	FilledCell[] c = {new FilledCell(POS_X + scale, POS_Y, scale, color),
			new FilledCell(POS_X + 2 * scale, POS_Y, scale, color),
			new FilledCell(POS_X, POS_Y + scale, scale, color),
			new FilledCell(POS_X + scale, POS_Y + scale, scale, color)};
	
	private int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX() - scale, getPosX(), getPosX() + scale},
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() + scale},
				{ getPosX(), getPosX()  + scale, getPosX(), getPosX() - scale },
				{ getPosX(), getPosX(), getPosX() - scale, getPosX() - scale } };
		return out;
	}

	private int[][] orientY() {
		int[][] out = {
				{ getPosY(), getPosY(), getPosY() - scale, getPosY() - scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() + scale },
				{ getPosY(), getPosY(), getPosY() + scale, getPosY() + scale},
				{ getPosY(), getPosY() + scale, getPosY(), getPosY() - scale} };
		return out;
	}
	
	public ZShape(){
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);
	}
	
	public void draw(Graphics gc) {
		gc.setColor(color);
		for(int i = 0; i < 4; i++){
			gc.fill3DRect(orientX()[orient][i], orientY()[orient][i], scale, scale, true);
		}
//		gc.fill3DRect(POS_X + scale, POS_Y, scale, scale, true);
//		gc.fill3DRect(POS_X + 2 * scale, POS_Y, scale, scale, true);
//		gc.fill3DRect(POS_X, POS_Y + scale, scale, scale, true);
//		gc.fill3DRect(POS_X + scale, POS_Y + scale, scale, scale, true);
	}
	
	public void stepRight() {
		POS_X += scale;
	}
	
	public void stepLeft() {
		POS_X = POS_X - scale;
	}
	
	public void stepDown() {
		POS_Y = POS_Y + scale;
	}
	
	public void rotate(){
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
//		
//		FilledCell[] c = {new FilledCell(POS_X + scale, POS_Y, scale, color),
//				new FilledCell(POS_X + 2 * scale, POS_Y, scale, color),
//				new FilledCell(POS_X, POS_Y + scale, scale, color),
//				new FilledCell(POS_X + scale, POS_Y + scale, scale, color)};
//		return c;
	}
}
