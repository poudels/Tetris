import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
		
		
		//Dahboard
		Dashboard dashboard = new Dashboard();

		game.add(dashboard, BorderLayout.LINE_END);

		// Add menu bar
		JMenuBar menuBar = new JMenuBar();

		// Add Menu
		JMenu menu1 = new JMenu("Game");

		JMenu m1Item1 = new JMenu("New Game");
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
				gameCourt.reset();
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
				System.exit(0);
			}
		});

		menu1.add(m1Item3);
		menu1.addSeparator();
		
		// Add menu1 to menuBar
		menuBar.add(menu1);

		// Add another menu(help menu)
		final JMenu help = new JMenu("Help");
		help.addMouseListener(new ListenMouse() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				JFrame he = new HelpWindow();
				he.setLocation(300, 250);
				he.setPreferredSize(new Dimension(500, 300));
				he.pack();
				he.setVisible(true);
				
			}
		});

		menuBar.add(help);
		// set Menu bar
		frame.setJMenuBar(menuBar);
		
		
		//Game over window
		gameOver = new JPanel();
		gameOver.setLayout(new BoxLayout(gameOver, BoxLayout.PAGE_AXIS));
		gameOver.setPreferredSize(new Dimension(500,440));
		
		JLabel gOverText = new JLabel("Game over");
		gOverText.setFont(new Font("Hobo Std", 1, 33));
		gOverText.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameOver.add(gOverText);
		
		JLabel newGameText = new JLabel("New Game");
		newGameText.setFont(new Font("Hobo Std", 1, 22));
		newGameText.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameText.addMouseListener(new ListenMouse(){
			public void mousePressed(MouseEvent e) {
				gameCourt.reset();
			}
		});
		gameOver.add(newGameText);
		
		
		gameOver.setBounds(0, 0, 500, 440);
		gameOver.setBackground(new Color(150, 150,100, 150));
		gameOver.setVisible(false);
		layeredPane.add(gameOver, 0);
		
		// Add Game Court to main window
		gameCourt = new GameCourt(dashboard, gameOver, layeredPane);
		game.add(gameCourt, BorderLayout.CENTER);
		gameCourt.setFocusable(true);
		layeredPane.add(game, 1);
		
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
