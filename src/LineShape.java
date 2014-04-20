import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class LineShape implements Shape {
	//
	// LinkedList<Shape> allItems = new LinkedList<Shape>();
	private int i = 4;
	private int j = -1;
	private int scale = 20;

	private int POS_X = i * scale;
	private int POS_Y = j * scale;

	private CellColor color;

	public LineShape() {
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);
	}

	public void draw(Graphics gc) {
		gc.setColor(color);
		gc.fill3DRect(POS_X, POS_Y, scale, scale, true);
		gc.fill3DRect(POS_X, POS_Y + scale, scale, scale, true);
		gc.fill3DRect(POS_X, POS_Y + 2 * scale, scale, scale, true);
		gc.fill3DRect(POS_X, POS_Y + 3 * scale, scale, scale, true);
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

	public int getPosX() {
		return POS_X;
	}

	public int getPosY() {
		return POS_Y;
	}

	public FilledCell[] getCell() {

		FilledCell[] c = { new FilledCell(POS_X, POS_Y, scale, color),
				new FilledCell(POS_X, POS_Y + scale, scale, color),
				new FilledCell(POS_X, POS_Y + 2 * scale, scale, color),
				new FilledCell(POS_X, POS_Y + 3 * scale, scale, color) };
		return c;
	}

}
