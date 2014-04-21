import java.awt.Graphics;

public class TShape extends SShape {

	protected int[][] orientX() {
		int[][] out = {
				{ getPosX(), getPosX() - scale, getPosX() + scale, getPosX() },
				{ getPosX(), getPosX(), getPosX(), getPosX() - scale },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() - scale },
				{ getPosX(), getPosX(), getPosX() + scale, getPosX() }
				
			};
		return out;
	}

	protected int[][] orientY() {
		int[][] out = { { getPosY(), getPosY(), getPosY(), getPosY() + scale },
				{ getPosY(), getPosY() - scale, getPosY() + scale, getPosY() },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() },
				{ getPosY(), getPosY() - scale, getPosY(), getPosY() + scale }
			};
		return out;
	}
}
