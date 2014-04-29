import java.awt.Color;
import java.awt.Graphics;

public interface Cell {
	public void draw(Graphics gc);
	public int getI();
	public int getJ();
	public Color getColor();
}
