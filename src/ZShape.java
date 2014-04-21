public class ZShape extends SShape{
	
	protected int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX() - scale, getPosX(), getPosX() + scale},
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() + scale},
				{ getPosX(), getPosX()  + scale, getPosX(), getPosX() - scale },
				{ getPosX(), getPosX(), getPosX() - scale, getPosX() - scale } };
		return out;
	}

	protected int[][] orientY() {
		int[][] out = {
				{ getPosY(), getPosY(), getPosY() - scale, getPosY() - scale },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() + scale },
				{ getPosY(), getPosY(), getPosY() + scale, getPosY() + scale},
				{ getPosY(), getPosY() + scale, getPosY(), getPosY() - scale} };
		return out;
	}
}
