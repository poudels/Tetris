import java.awt.Graphics;

public class TShape implements Shape {
	private int i = 4;
	private int j = -1;
	private int scale = 20;
	private int orient = 0;
	
	private int POS_X = i * scale;
	private int POS_Y = j * scale;

	private int[] Pos_x = { POS_X, POS_X - scale, POS_X + scale, POS_X };
	private int[] Pos_y = { POS_Y, POS_Y, POS_Y, POS_Y + scale };

	private int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX() - scale, getPosX() + scale, getPosX() },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() - scale },
				{ getPosX(), getPosX(), getPosX(), getPosX() - scale } };
		return out;
	}

	private int[][] orientY() {
		int[][] out = { { getPosY(), getPosY(), getPosY(), getPosY() + scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() + scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() },
				{ getPosY(), getPosY() - scale, getPosY() + scale, getPosY() } };
		return out;
	}

	private CellColor color;

	public TShape() {
		int x = (int) Math.round(Math.random() * 4);
		color = new CellColor(x);

	}

	public void draw(Graphics gc) {
		gc.setColor(color);
		for (int i = 0; i < 4; i++) {
			gc.fill3DRect(orientX()[orient][i], orientY()[orient][i], scale, scale, true);
		}
		// gc.fill3DRect(POS_X, POS_Y, scale, scale, true);
		// gc.fill3DRect(POS_X - scale, POS_Y, scale, scale, true);
		// gc.fill3DRect(POS_X + scale, POS_Y, scale, scale, true);
		// gc.fill3DRect(POS_X, POS_Y + scale, scale, scale, true);
	}

	public void stepRight() {
		// if(POS_X >= COURT_WIDTH - 2 * scale)
		// return;
		POS_X += scale;
//		for (int i = 0; i < 4; i++) {
//			Pos_x[i] = Pos_x[i] + scale;
//		}
	}

	public void stepLeft() {
		// if (POS_X <= 0)
		// return;
		POS_X -= scale;
//		for (int i = 0; i < 4; i++) {
//			Pos_x[i] = Pos_x[i] - scale;
//		}
	}

	public void stepDown() {
		POS_Y += scale;
//		for (int i = 0; i < 4; i++) {
//			Pos_y[i] = Pos_y[i] + scale;
//		}
	}

	public void rotate() {
		orient = (orient + 1) % 4;
//		for (int i = 0; i < 4; i++) {
//			boolean isSame = true;
//			for (int j = 0; j < 4; j++) {
//				if (Pos_y[j] != orientY()[i][j]
//						|| Pos_x[j] != orientX()[i][j]) {
//					isSame = false;
//					continue;
//				}
//			}
//
//			if (isSame) {
////				System.out.println("Orientation matched");
//				for (int k = 0; k < 4; k++) {
//					int l = (i + 1) % 4;
//					System.out.println("i = " + i);
//					System.out.println("l = " + l);
//					Pos_x[k] = orientX()[l][k];
//					Pos_y[k] = orientY()[l][k];				
//				}
////				System.out.println("Rotated");
//				break;
//			}
//		}
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
}
