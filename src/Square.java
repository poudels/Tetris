public class Square extends SShape {
	
	@Override
	protected int[][] orientX() {
		int[][] out = {
				{getPosX(), getPosX() + scale, getPosX() + scale, getPosX()}
			};
		return out;
	}
	
	@Override
	protected int[][] orientY() {
		int[][] out = {
				{getPosY(), getPosY(), getPosY() - scale, getPosY() - scale}
			};
		return out;
	}
	
	@Override
	public void rotate(){
		
	}
}
