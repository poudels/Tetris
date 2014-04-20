import java.awt.Color;
import java.awt.color.ColorSpace;

public class CellColor extends Color{
	
	private static final int[][] co = {
			{100, 90, 90},
			{90, 100, 90},
			{90, 100, 0},
			{124, 93,43},
			{112, 44, 44}
	};
	
	public CellColor(int x) {
		super(co[x][0], co[x][1], co[x][2]);
	}
}
