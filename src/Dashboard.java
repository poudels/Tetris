import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JPanel {
	private JPanel dashboard;
	final NextObjectDisplay nextObject;
	final JPanel levelDisp;
//	final JLabel levelLabel;
	final JLabel level;
	final JPanel lineDisp;
	final JLabel lineLabel;
	final JLabel lines;
	final JPanel scoreDisp;
	final JLabel scoreLabel;
	final JLabel score;
	final JLabel highScore;
	
	
	private JLabel styledLabel(String text, int width, int height, JPanel parent){
		JLabel jl = new JLabel(text);
		jl.setFont(new Font("Hobo Std", 1, 25));
		jl.setPreferredSize(new Dimension(width, height));
		return jl;
	}
	
	public Dashboard() {
		// Dashboard
		dashboard = new JPanel();
		dashboard.setPreferredSize(new Dimension(180, 200));

		// Show next Object
		nextObject = new NextObjectDisplay();
		nextObject.setPreferredSize(new Dimension(180, 150));
		dashboard.add(nextObject);

		// Add level label
		levelDisp = new JPanel();
		
//		levelLabel = styledLabel("Level = ", 100, 30, levelDisp);
		levelDisp.add(styledLabel("Level = ", 100, 30, levelDisp));

		level = styledLabel("1", 50, 30, levelDisp);
		levelDisp.add(level);

		dashboard.add(levelDisp);

		// Add Lines label
		lineDisp = new JPanel();

		lineLabel = styledLabel("Line = ", 100, 30, levelDisp);
		lineDisp.add(lineLabel);
		
		lines = styledLabel("0", 100, 30, levelDisp);
		lineDisp.add(lines);

		dashboard.add(lineDisp);

		scoreDisp = new JPanel();
		
		scoreLabel = styledLabel("Score = ", 100, 30, levelDisp);
		scoreDisp.add(scoreLabel);

		score = styledLabel("0", 100, 30, levelDisp);
		scoreDisp.add(score);

		dashboard.add(scoreDisp);

		// Add highScore label
		highScore = new JLabel("Highscore = 0 ");
		highScore.setFont(new Font("Hobo Std", 1, 25));
		dashboard.add(highScore);
	}
	
	public void setLevel(int x){
		level.setText("" + x);
	}
	
	public void setScore(int x){
		score.setText("" + x);
	}
	
	public void setLine(int x){
		lines.setText("" + x);
	}
	
	public void setHighscore(int x){
		highScore.setText("" + x);
	}
}
