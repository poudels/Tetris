import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.AttributedCharacterIterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Game implements Runnable {

	public void run() {
		JFrame game = new JFrame("Tetris Battle");
		game.setLayout(new BorderLayout());
		game.setResizable(false);
		game.setLocation(300, 100);



		// Dashboard
		 JPanel dashboard = new JPanel();
		 dashboard.setPreferredSize(new Dimension(180, 200));
		 
		 //Show next Object
		 NextObjectDisplay nextObject = new NextObjectDisplay();
		 nextObject.setPreferredSize(new Dimension(180,150));
		 dashboard.add(nextObject);
		 
		 //Add level label
		 JLabel level = new JLabel(" Level = 1 ");
		 level.setFont(new Font("Hobo Std", 1, 25));
		 dashboard.add(level);
		 
		 
		 //Add Lines label
		 JLabel lines = new JLabel(" Lines = 0");
		 lines.setFont(new Font("Hobo Std", 1, 25));
		 dashboard.add(lines);
		 
		//Add score label
		 JLabel score = new JLabel(" Score = 0 ");
		 score.setFont(new Font("Hobo Std", 1, 25));
		 dashboard.add(score);
		 
		 
		//Add highScore label
		 JLabel highScore = new JLabel("Highscore = 0 ");
		 highScore.setFont(new Font("Hobo Std", 1, 25));
		 dashboard.add(highScore);
		 

		 
//		 Graphics g = nextObj.getGraphics();
//		 gameCourt.getNextObject().draw(g);

		 game.add(dashboard, BorderLayout.LINE_END);
		 

		
		// Add menu bar
		JMenuBar menuBar = new JMenuBar();
				
		// Add Menu
		JMenu menu1 = new JMenu("Game");
//		menu1.setPreferredSize(new Dimension(100, 25));
		
		JMenuItem m1Item1 = new JMenuItem("New Game");
		menu1.add(m1Item1);
		
		JMenuItem m1Item2 = new JMenuItem("Pause");
		menu1.add(m1Item2);
		
		JMenuItem m1Item4 = new JMenuItem("Preferences");
		menu1.add(m1Item4);
		
		JMenuItem m1Item3 = new JMenuItem("Exit");
		m1Item3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		menu1.add(m1Item3);
		
		
		//Add menu1 to menuBar
		menuBar.add(menu1);

		//Add another menu(help menu)
		JMenu help = new JMenu("Help");
		help.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JFrame he = new JFrame("Help");
				
				
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader("./Config2.txt"));
				}
				catch(Exception excep){
					System.err.println("Error Opening File");
				}
				
				String d = "";
				boolean hasNext = true;
				while(hasNext && reader != null){
					String line = null;
					try {
						line = reader.readLine();
					} catch (IOException e1) {
						System.err.println("Error reading file");
					}
					
					if(line == null)
						break;
					d += line;
				}
				
				JTextArea textArea = new JTextArea();
				textArea.setText(d);;
				
				
				he.add(textArea);
				
				he.setLocation(400,250);
				he.setPreferredSize(new Dimension(300, 300));
				he.pack();
				he.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				he.setVisible(true);
			}
		});
		
		
		menuBar.add(help);
		//set Menu bar
		game.setJMenuBar(menuBar);
		
		
		// Add Game Court to main window
		GameCourt gameCourt = new GameCourt(nextObject, level, lines, score);
		game.add(gameCourt, BorderLayout.CENTER);
		
		// pack Jframe and set visible to true
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
