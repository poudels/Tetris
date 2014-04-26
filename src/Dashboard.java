import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JPanel {
	NextObjectDisplay nextObject;
	JPanel levelDisp;
	JLabel level;
	JPanel lineDisp;
	JLabel lines;
	JPanel scoreDisp;
	JLabel scoreLabel;
	JLabel score;
	JLabel hsLabel;
	JLabel hScore;
	Highscore hs;
	int highscore;
	

	private JLabel styledLabel(String text, int font, int width, int height,
			JPanel parent) {
		JLabel jl = new JLabel(text);
		jl.setFont(new Font("Hobo Std", 1, font));
		jl.setPreferredSize(new Dimension(width, height));
		return jl;
	}

	public Dashboard() {
		// Dashboard
		super();
		setPreferredSize(new Dimension(180, 400));

		// Show next Object
		nextObject = new NextObjectDisplay();
		nextObject.setPreferredSize(new Dimension(260, 150));
		super.add(nextObject);

		// Add level label
		levelDisp = new JPanel();

		// levelLabel = styledLabel("Level = ", 100, 30, levelDisp);
		levelDisp.add(styledLabel("Level : ", 25, 100, 30, levelDisp));

		level = styledLabel("1", 25, 50, 30, levelDisp);
		levelDisp.add(level);

		super.add(levelDisp);

		// Add Lines label
		lineDisp = new JPanel();

		// lineLabel = styledLabel("Line = ", 100, 30, levelDisp);
		lineDisp.add(styledLabel("Line : ", 25, 100, 30, levelDisp));

		lines = styledLabel("0", 25, 50, 30, lineDisp);
		lineDisp.add(lines);

		super.add(lineDisp);

		scoreDisp = new JPanel(new GridLayout(2, 1));

		scoreLabel = styledLabel("Score : ", 25, 100, 30, scoreDisp);
		scoreDisp.add(scoreLabel);

		score = styledLabel("0", 20, 50, 30, scoreDisp);
		scoreDisp.add(score);

		super.add(scoreDisp);

		// Add highScore label
		JPanel highScoreDisp = new JPanel();
		// get highscore from file
		hs = new Highscore("hsScore.txt");
		highscore = Integer.parseInt(hs.getHighscore());

		hsLabel = styledLabel("Highscore : ", 25, 140, 30, scoreDisp);
		super.add(hsLabel);
		hScore = styledLabel(("" + highscore), 20, 100, 30, scoreDisp);
		super.add(highScoreDisp);
		super.add(hScore);

		// super.add(highScoreDisp);
	}

	public NextObjectDisplay getNextObjectDisplay() {
		return nextObject;
	}

	public void setLevel(int x) {
		level.setText("" + x);
	}

	public void setScore(int x) {
		score.setText("" + x);
		if(x > highscore){
			highscore = x;
			hScore.setText("" + highscore);
			hs.setHighscore("" + highscore);
		}
	}

	public void setLine(int x) {
		lines.setText("" + x);
	}

	public void setHighscore(int x) {
		hsLabel.setText("" + x);
	}
}
