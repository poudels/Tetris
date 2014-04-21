import java.awt.Graphics;
import javax.swing.JPanel;


public class NextObjectDisplay extends JPanel{
	private Shape shape;
	
	public NextObjectDisplay(){
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(0, 80);
		if(shape != null)
			shape.draw(g);
	}
	
	public void setObject(Shape s){
		shape = s;
	}
}
