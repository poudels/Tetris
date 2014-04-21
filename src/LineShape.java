public class LineShape extends SShape {
	
	protected int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX(), getPosX(), getPosX()},
				{ getPosX(), getPosX() + scale, getPosX() - scale, getPosX() - 2 * scale},
				{ getPosX(), getPosX(), getPosX(), getPosX()},
				{ getPosX(), getPosX() - scale, getPosX() + scale, getPosX() + 2 * scale } };
		return out;
	}

	protected int[][] orientY() {
		int[][] out = {
				{ getPosY(), getPosY() - scale, getPosY() + scale, getPosY() + 2 * scale },
				{ getPosY(), getPosY(), getPosY(), getPosY()},
				{ getPosY(), getPosY() + scale, getPosY() - scale, getPosY() - 2 * scale},
				{ getPosY(), getPosY(), getPosY(), getPosY()} };
		return out;
	}
}
