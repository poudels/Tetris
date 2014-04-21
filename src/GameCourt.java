import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream.GetField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameCourt extends JPanel {
	private final int COURT_HEIGHT = 400;
	private final int COURT_WIDTH = 280;
	private final int cellSize = 20;
	private boolean isFalling = true;
	private final int INTERVAL = 250;

	private Shape fallObj;
	private Shape nextFallObj;
	
	private NextObjectDisplay nextObjectDisplay;
	
	private final int gridWidth = COURT_WIDTH / cellSize;
	private final int gridHeight = COURT_HEIGHT / cellSize;

	private boolean[][] cellEmpty = new boolean[gridHeight][gridWidth];
	private Cell[][] gridCell = new Cell[gridHeight][gridWidth];
	private JLabel showLines;
	private int linesCompleted = 0;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isFalling) {
			fallObj.draw(g);
//			Graphics2D gc = (Graphics2D) nextObjectDisplay.getGraphics();
//			nextFallObj.draw(gc);
			
			
		}
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				if (!cellEmpty[i][j]) {
					gridCell[i][j].draw(g);
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	// check if line is completed
	private void checkGame() {
		for (int i = 0; i < gridHeight; i++) {
			boolean isFilled = true;
			for (int j = 0; j < gridWidth; j++) {
				if (cellEmpty[i][j] == true) {
					isFilled = false;
					break;
				}
			}

			if (isFilled) {
				linesCompleted += 1;
				showLines.setText(" Lines = " + linesCompleted);
//				nextObjectDisplay.setObject(nextFallObj);
//				nextObjectDisplay.repaint();
				System.out.println("Lines Completed = " + linesCompleted);
				for (int k = i; k > 0; k--) {
					if (k == 0)
						continue;
					for(int l = 0; l < cellEmpty[k].length; l++){
						cellEmpty[k][l] = cellEmpty[k - 1][l];
					}
				}
			}
		}
	}

	// ticker
	public void tick() {
		if (isFalling) {
			// boolean stepDown = true;
			FilledCell[] ce = fallObj.getCell();

			for (int i = 0; i < 4; i++) {
				int x = ce[i].getI();
				int y = ce[i].getJ();
//				System.out.println("x = " + x);
//				System.out.println("y = " + y);

				int f = y;
				if (f < gridHeight - 1) {
					f = y + 1;
				}
				
				if (f < 0){
					f = 0;
				}
				// check if next bottom grid is empty or not
				System.out.println("f = " + f + "Grid Height = " + gridHeight);
				if (!cellEmpty[f][x] || y >= gridHeight - 1) {
					System.out.println("Object Landed");
					addCellToGrid(ce);
					checkGame();
					fallObj = makeNewObj();
					nextObjectDisplay.setObject(nextFallObj);
					nextObjectDisplay.repaint();
					return;
				}

			}
			fallObj.stepDown();
			repaint();
		}

	}

	private Shape makeNewObj() {
		Shape out;
		Shape s1 = new ZShape();
		Shape s2 = new Square();
		Shape s3 = new LineShape();
		Shape s4 = new TShape();
		Shape s5 = new RLShape();
		Shape s6 = new LLShape();
		Shape[] shapes = {s1, s2, s3, s4, s5, s6};
		
		int a = (int) Math.round(5 * Math.random());
		out = shapes[a];
		
//		nextObjectDisplay.setObject(nextFallObj);
//		nextObjectDisplay.repaint();
		
		if(nextFallObj != null){
			System.out.println("Next Object is not null");
			Shape temp = nextFallObj;
			nextFallObj = out;
			return temp;
		}
		else {
			System.out.println("Next Object null");
			int b = (int) Math.round(5 * Math.random());
			nextFallObj = shapes[b];
			if(nextFallObj != null){
				System.out.println("Next Object created");
			}
			return out;
		}
	}

	
	public Shape getNextObject() {
		return nextFallObj;
	}
	
	public int getLines() {
		return linesCompleted;
	}
	
	private void addCellToGrid(Cell[] cell) {
		for (int i = 0; i < cell.length; i++) {
			int x = cell[i].getI();
			int y = cell[i].getJ();
			cellEmpty[y][x] = false;
			gridCell[y][x] = cell[i];
		}
	}

	private void stepRight() {
		Cell[] ce = fallObj.getCell();

		for (int i = 0; i < 4; i++) {
			int x = ce[i].getI();
			int y = ce[i].getJ();
//			System.out.println("x = " + x);
//			System.out.println("y = " + y);

			int f = x;
			if (f < gridWidth - 1) {
				f = x + 1;
			}
			// check if next bottom grid is empty or not
			if (!cellEmpty[y][f] || x >= gridWidth - 1) {
				return;
			}
		}
		fallObj.stepRight();
	}

	private void stepLeft() {
		Cell[] ce = fallObj.getCell();

		for (int i = 0; i < 4; i++) {
			int x = ce[i].getI();
			int y = ce[i].getJ();

			int f = x;
			if (f > 0) {
				f = x - 1;
			}
			// check if left grid grid is empty or not
			if (!cellEmpty[y][f] || x < 1) {
				return;
			}
		}
		fallObj.stepLeft();
	}
	
	
	public GameCourt(NextObjectDisplay nextObject, JLabel level, JLabel lines, JLabel score) {
		showLines = lines;
		
		fallObj = makeNewObj();
		
		nextObjectDisplay = nextObject;
		nextObjectDisplay.setObject(nextFallObj);
		
		System.out.println("Height = " + gridHeight);
		System.out.println("Width = " + gridWidth);

		setBackground(new Color(200, 200, 200));
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				cellEmpty[i][j] = true;
				gridCell[i][j] = new EmptyCell(i * cellSize, j * cellSize, 20, Color.CYAN);
			}
		}

		setFocusable(true);

		// initiate the timer
		final Timer timer = new Timer(INTERVAL, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tick();

			}
		});
		timer.start();

		// add keyListener to game court
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("Left");
					stepLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("Right");
					stepRight();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("Down");
					timer.setDelay(20);
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("Up");
					fallObj.rotate();
				}
			}

			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					timer.setDelay(INTERVAL);
				}
			}
		});
	}
}
