import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Game implements Runnable {
	GameCourt gameCourt = null;
	JPanel gameOver = null;
	
	private class ListenMouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private void addLevel(final JMenu menu,final int level) {
		JMenuItem x = new JMenuItem("Level " + level);
		x.addMouseListener(new ListenMouse() {			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				gameCourt.newLevel(level);
			}
		});
		menu.add(x);
	}
	
	public void run() {
		final JFrame frame = new JFrame("Tetris Battle");
		frame.setLayout(new BorderLayout());
//		frame.setResizable(false);
		frame.setLocation(300, 100);
		
		final JPanel game = new JPanel(new BorderLayout());
		game.setPreferredSize(new Dimension(460, 440));
		game.setBounds(0,0,460,440);

		//add a new layered pane
		final JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(500, 440));
		
		
		
		final JPanel dashboard = new Dashboard();
//		// Dashboard
//		final JPanel dashboard = new JPanel();
//		dashboard.setPreferredSize(new Dimension(180, 200));
//
//		// Show next Object
//		final NextObjectDisplay nextObject = new NextObjectDisplay();
//		nextObject.setPreferredSize(new Dimension(180, 150));
//		dashboard.add(nextObject);
//
//		// Add level label
//		final JPanel levelDisp = new JPanel();
//		final JLabel levelLabel = new JLabel("Level = ");
//		levelLabel.setFont(new Font("Hobo Std", 1, 25));
//		levelLabel.setPreferredSize(new Dimension(100,30));
//		levelDisp.add(levelLabel);
//		
//		final JLabel level = new JLabel("1");
//		level.setFont(new Font("Hobo Std", 1, 25));
//		level.setPreferredSize(new Dimension(50,30));
//		levelDisp.add(level);
//		
//		dashboard.add(levelDisp);
//
//		// Add Lines label
//		final JPanel lineDisp = new JPanel();
//		
//		final JLabel lineLabel = new JLabel("Line = ");
//		lineLabel.setFont(new Font("Hobo Std", 1, 25));
//		lineLabel.setPreferredSize(new Dimension(100,30));
//		lineDisp.add(lineLabel);
//		final JLabel lines = new JLabel("0");
//		lines.setFont(new Font("Hobo Std", 1, 25));
//		lines.setPreferredSize(new Dimension(50,30));
//		lineDisp.add(lines);
//		
//		dashboard.add(lineDisp);
//		
//		final JPanel scoreDisp = new JPanel();
//		final JLabel scoreLabel = new JLabel("Score = ");
//		scoreLabel.setFont(new Font("Hobo Std", 1, 25));
//		scoreLabel.setPreferredSize(new Dimension(100,30));
//		scoreDisp.add(scoreLabel);
//		
//		final JLabel score = new JLabel("0");
//		score.setFont(new Font("Hobo Std", 1, 25));
//		score.setPreferredSize(new Dimension(50,30));
//		scoreDisp.add(score);
//		
//		dashboard.add(scoreDisp);
//		
//		// Add highScore label
//		final JLabel highScore = new JLabel("Highscore = 0 ");
//		highScore.setFont(new Font("Hobo Std", 1, 25));
//		dashboard.add(highScore);

		// Graphics g = nextObj.getGraphics();
		// gameCourt.getNextObject().draw(g);

		game.add(dashboard, BorderLayout.LINE_END);

		// Add menu bar
		final JMenuBar menuBar = new JMenuBar();

		// Add Menu
		final JMenu menu1 = new JMenu("Game");
		// menu1.setPreferredSize(new Dimension(100, 25));

		final JMenu m1Item1 = new JMenu("New Game");
		addLevel(m1Item1, 1);
		m1Item1.addSeparator();
		addLevel(m1Item1, 2);
		m1Item1.addSeparator();
		addLevel(m1Item1, 3);
		m1Item1.addSeparator();
		addLevel(m1Item1, 4);
		m1Item1.addSeparator();
		addLevel(m1Item1, 5);
		
		menu1.add(m1Item1);
		menu1.addSeparator();
		
		final JMenuItem m1Item2 = new JMenuItem("Pause");
		m1Item2.addMouseListener(new ListenMouse() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				gameCourt.pauseOrPlay();
			}
		});

		menu1.add(m1Item2);
		menu1.addSeparator();
		
		final JMenuItem m1Item5 = new JMenuItem("Reset");
		m1Item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				gameCourt.reset();
				game.remove(gameCourt);
				game.add(new GameCourt(nextObject, level, lines, highScore, gameOver, layeredPane),
						 BorderLayout.CENTER);
			}
		});
		
		menu1.add(m1Item5);
		menu1.addSeparator();
		
		
		
		final JMenuItem m1Item4 = new JMenuItem("Preferences");
		menu1.add(m1Item4);
		menu1.addSeparator();
		
		final JMenuItem m1Item3 = new JMenuItem("Exit");
		m1Item3.addMouseListener(new ListenMouse() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		menu1.add(m1Item3);
		menu1.addSeparator();
		
		// Add menu1 to menuBar
		menuBar.add(menu1);

		// Add another menu(help menu)
		final JMenu help = new JMenu("Help");
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame he = new JFrame("Help");

				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader("Canvas.js"));
				} catch (Exception excep) {
					System.err.println("Error Opening File");
				}

				String d = "";
				boolean hasNext = true;
				while (hasNext && reader != null) {
					String line = null;
					try {
						line = reader.readLine();
					} catch (IOException e1) {
						System.err.println("Error reading file");
					}

					if (line == null)
						break;
					d += line + "\n";
				}

				final JTextArea textArea = new JTextArea(20, 20);
				textArea.setText(d);

				JScrollPane scroll = new JScrollPane(textArea);
				he.add(scroll);

				he.setLocation(400, 250);
				he.setPreferredSize(new Dimension(300, 300));
				he.pack();
				he.setVisible(true);
				
			}
		});

		menuBar.add(help);
		// set Menu bar
		frame.setJMenuBar(menuBar);
		
		
		//Game over window
		gameOver = new JPanel(new FlowLayout());
		gameOver.setPreferredSize(new Dimension(500,440));
		JLabel gOverText = new JLabel("Game over");
		gOverText.setFont(new Font("Hobo Std", 1, 30));
		gOverText.setPreferredSize(new Dimension(150,440));
//		gOverText.setBounds(100, 0, 100, 10);
//		gOverText.setLocation(100, 100);
//		gOverText.set
		gameOver.add(gOverText);
		
		gameOver.setBounds(0, 0, 500, 440);
		gameOver.setBackground(new Color(150, 150,100, 150));
		gameOver.setVisible(false);
		layeredPane.add(gameOver, 0);
		
		// Add Game Court to main window
		gameCourt = new GameCourt(nextObject, level, lines,
				score, gameOver, layeredPane);
		game.add(gameCourt, BorderLayout.CENTER);
		layeredPane.add(game, 1);
//		layeredPane.setLayer(gameOver, 10000);
		
//		frame.add(game);
		frame.add(layeredPane);
		// pack Jframe and set visible to true
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
