public class RLShape extends SShape{
	
	public RLShape(){
		//super();
	}
	
	@Override
	protected int[][] orientX() {
		int[][] out = {
				{getPosX(), getPosX() + scale, getPosX() - scale, getPosX() - scale},
				{getPosX(), getPosX(), getPosX(), getPosX() + scale},
				{getPosX(), getPosX() - scale, getPosX() + scale, getPosX() + scale},
				{getPosX(), getPosX(), getPosX(), getPosX() - scale}
			};
		return out;
	}
	
	@Override
	protected int[][] orientY() {
		int[][] out = {
				{getPosY(), getPosY(), getPosY(), getPosY() - scale},
				{getPosY(), getPosY() + scale, getPosY() - scale, getPosY() - scale},
				{getPosY(), getPosY(), getPosY(), getPosY() + scale},
				{getPosY(), getPosY() - scale, getPosY() + scale, getPosY() + scale}
			};
		return out;
	}
}
