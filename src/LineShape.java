import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class LineShape implements Shape {

	
	
	private int i = 4;
	private int j = -1;
	private int scale = 20;

	private int POS_X = i * scale;
	private int POS_Y = j * scale;
	private int orient = 0;
	
	private int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX(), getPosX(), getPosX()},
				{ getPosX(), getPosX() + scale, getPosX() - scale, getPosX() - 2 * scale},
				{ getPosX(), getPosX(), getPosX(), getPosX()},
				{ getPosX(), getPosX() - scale, getPosX() + scale, getPosX() + 2 * scale } };
		return out;
	}

	private int[][] orientY() {
		int[][] out = {
				{ getPosY(), getPosY() - scale, getPosY() + scale, getPosY() + 2 * scale },
				{ getPosY(), getPosY(), getPosY(), getPosY()},
				{ getPosY(), getPosY() + scale, getPosY() - scale, getPosY() - 2 * scale},
				{ getPosY(), getPosY(), getPosY(), getPosY()} };
		return out;
	}

	private CellColor color;

	public LineShape() {
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);
	}

	public void draw(Graphics gc) {
		gc.setColor(color);
		gc.setColor(color);
		for(int i = 0; i < 4; i++){
			gc.fill3DRect(orientX()[orient][i], orientY()[orient][i], scale, scale, true);
		}
//		gc.fill3DRect(POS_X, POS_Y, scale, scale, true);
//		gc.fill3DRect(POS_X, POS_Y + scale, scale, scale, true);
//		gc.fill3DRect(POS_X, POS_Y + 2 * scale, scale, scale, true);
//		gc.fill3DRect(POS_X, POS_Y + 3 * scale, scale, scale, true);
	}

	public void stepRight() {
		// if(POS_X >= COURT_WIDTH - 2 * scale)
		// return;
		POS_X += scale;
	}

	public void stepLeft() {
		// if (POS_X <= 0)
		// return;
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
//		FilledCell[] c = { new FilledCell(POS_X, POS_Y, scale, color),
//				new FilledCell(POS_X, POS_Y + scale, scale, color),
//				new FilledCell(POS_X, POS_Y + 2 * scale, scale, color),
//				new FilledCell(POS_X, POS_Y + 3 * scale, scale, color) };
//		return c;
	}

}
