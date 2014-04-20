import java.awt.Graphics;
import java.awt.Graphics2D;


public interface Shape {
	public void draw(Graphics gc);
	public void stepDown();
	public void stepRight();
	public void stepLeft();
	public void rotate();
	public FilledCell[] getCell();
}
