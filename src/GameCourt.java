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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameCourt extends JPanel {
	private final int COURT_HEIGHT = 440;
	private final int COURT_WIDTH = 280;
	private final int cellSize = 20;
	private boolean playing = true;
	private int[] INTERVAL = {0, 250, 200, 150, 100, 50};
	private Timer timer;

	private Shape fallObj;
	private Shape nextFallObj;

	private NextObjectDisplay nextObjectDisplay;

	private final int gridWidth = COURT_WIDTH / cellSize;
	private final int gridHeight = COURT_HEIGHT / cellSize;

	private boolean[][] cellEmpty = new boolean[gridHeight][gridWidth];
	private Cell[][] gridCell = new Cell[gridHeight][gridWidth];
	private Dashboard dashboard;
	private int level;
	private int score = 0;
	private int linesCompleted = 0;
	private JPanel gameOver;
	private JLayeredPane lPane;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				try {
					gridCell[i][j].draw(g);
					if (!cellEmpty[i][j]) {
						Cell d = new FilledCell(i * 20, j * 20, 20, Color.RED);
						gridCell[i][j].draw(g);
					}
					//if empty draw empty squares
					else{
//						gridCell[i][j].draw(g);
					}
				}
				catch(Exception e){
					System.out.println("Value of i = "+ i);
					System.out.println("Value of j = " + j);
				}
			}
		}
		// draw falling object
		if (playing) {
			fallObj.draw(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
	
	private void updateScore(int lines){
		int out = 0;
		if(lines == 1){
			out = 40 * (level + 1);
		}
		else if(lines == 2){
			out = 100 * (level + 1);
		}
		else if(lines == 3){
			out = 300 * (level + 1);
		}
		else if(lines == 4){
			out = 1200 * (level + 1);
		}
		score += out;
		dashboard.setScore(score);
	}
	
	// check if line is completed
	private void checkGame() {
		int totalLines = 0;
		for (int i = 0; i < gridHeight; i++) {
			boolean isFilled = true;
			// if single cell is empty, line is not completed
			for (int j = 0; j < gridWidth; j++) {
				if (cellEmpty[i][j] == true) {
					isFilled = false;
					break;
				}
			}

			// if line is completed, remove them from grid
			if (isFilled) {
				totalLines += 1;
				linesCompleted += 1;
				dashboard.setLine(linesCompleted);
				System.out.println("Lines Completed = " + linesCompleted);
				for (int k = i; k >= 0; k--) {
					//if any line is completed, set first row to empty
					if (k == 0){
						for (int l = 0; l < cellEmpty[k].length; l++) {
							cellEmpty[k][l] = false;
							gridCell[0][l] = new EmptyCell(0 * cellSize, l * cellSize, 20,
									Color.BLUE);
						}
						continue;
					}
//						
					for (int l = 0; l < cellEmpty[k].length; l++) {
						cellEmpty[k][l] = cellEmpty[k - 1][l];
//						gridCell[k][l] = gridCell[k - 1][l];
					}
				}
			}
		}
		
		updateScore(totalLines);
		System.out.println("Total lines completed this time = " + totalLines);
		//level up is line completed is equal to certain amount
		if (linesCompleted > 10){
			newLevel(level + 1);
			return;
		}
	}

	// ticker
	public void tick() {
		if (playing) {
			FilledCell[] ce = fallObj.getCell();

			for (int i = 0; i < 4; i++) {
				int x = ce[i].getI();
				int y = ce[i].getJ();
				int y1 = y;
				if (y1 < gridHeight - 1) {
					y1 = y + 1;
				}

				//if index is less than 0, change it to 0- a minimum value
				if (y1 < 0) {
					y1 = 0;
				}
				// check if next bottom grid is empty or not
				// if not empty add the object to it top and continue game
				if (!cellEmpty[y1][x] || y >= gridHeight - 1) {
					System.out.println("Object Landed");
					addCellToGrid(ce);
					checkGame();
					fallObj = makeNewObj();
					nextObjectDisplay.setObject(nextFallObj);
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
		Shape[] shapes = { s1, s2, s3, s4, s5, s6 };

		int a = (int) Math.round(5 * Math.random());
		out = shapes[a];

		if (nextFallObj != null) {
			Shape temp = nextFallObj;
			nextFallObj = out;
			return temp;
		} else {
			int b = (int) Math.round(5 * Math.random());
			nextFallObj = shapes[b];
			return out;
		}
	}

	public Shape getNextObject() {
		return nextFallObj;
	}

	public int getLines() {
		return linesCompleted;
	}

	//Add cells to grid and check if game is over
	private void addCellToGrid(Cell[] cell) {
		for (int i = 0; i < cell.length; i++) {
			int x = cell[i].getI();
			int y = cell[i].getJ();
			if (x < 0 || y < 0) {
				System.err.println("Array ouf bound exception");
				System.err.println("Game over");
				gameOver.setVisible(true);
				lPane.setLayer(gameOver, 10000);
				playing = false;
				return;
			}
			cellEmpty[y][x] = false;
			gridCell[y][x] = cell[i];
		}
	}

	//if object can move right, move one step right
	private void stepRight() {
		Cell[] ce = fallObj.getCell();

		for (int i = 0; i < 4; i++) {
			int x = ce[i].getI();
			int y = ce[i].getJ();

			int x1 = x;
			if (x1 < gridWidth - 1) {
				x1 = x + 1;
			}
			// check if right grid is empty or not
			if (!cellEmpty[y][x1] || x >= gridWidth - 1) {
				return;
			}
		}
		fallObj.stepRight();
	}

	//if object can move left, move one step left
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

	//if object can rotate, rotate clockwise
	private void rotate() {
		Cell[] ce = fallObj.getRotationCell();

		for (int i = 0; i < 4; i++) {
			int x = ce[i].getI();
			int y = ce[i].getJ();

			System.out.println("i = " + x);
			System.out.println("j = " + y);

			// check if left grid grid is empty or not
			if (!cellEmpty[y][x] || x < 0 || y < 0) {
				return;
			}
		}
		fallObj.rotate();
	}

	// control play and pause
	public void pauseOrPlay() {
		playing = !playing;
	}

	public void reset() {
		playing = true;
		fallObj = makeNewObj();
		nextObjectDisplay.setObject(nextFallObj);
		
		timer.setDelay(INTERVAL[level]);
		linesCompleted = 0;
		score = 0;
		
		dashboard.setLevel(this.level);
		dashboard.setLine(linesCompleted);
		dashboard.setScore(0);
		
		gameOver.setVisible(false);
		lPane.setLayer(gameOver, 0);

		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				cellEmpty[i][j] = true;
				gridCell[i][j] = new EmptyCell(i * cellSize, j * cellSize, 20,
						Color.GRAY);
			}
		}
	}

	public void newLevel(int level) {
		//increase the level and reset
		this.level = level;
		reset();
	}

	public GameCourt(Dashboard dashboard, JPanel gameOver, JLayeredPane lPane) {
		this.level = 1;
		fallObj = makeNewObj();
		this.dashboard = dashboard;
		this.gameOver = gameOver;
		this.lPane = lPane;

		nextObjectDisplay = dashboard.getNextObjectDisplay();
		nextObjectDisplay.setObject(nextFallObj);

		System.out.println("Height = " + gridHeight);
		System.out.println("Width = " + gridWidth);

		setBackground(new Color(200, 200, 200));

		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				cellEmpty[i][j] = true;
				gridCell[i][j] = new EmptyCell(i * cellSize, j * cellSize, 20,
						Color.GRAY);
			}
		}

		setFocusable(true);

		// initiate the timer
		timer = new Timer(INTERVAL[level], new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		// add keyListener to game court
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (playing) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						System.out.println("Left");
						stepLeft();
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						System.out.println("Right");
						stepRight();
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						System.out.println("Down");
						// stepDown();
						timer.setDelay(20);
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						System.out.println("Up");
						rotate();
					}

					else if (e.getKeyCode() == 80) {
						System.out.println("Pause");
						playing = !playing;
					}
				} else {
					if (e.getKeyCode() == 80) {
						System.out.println("Play");
						playing = !playing;
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					timer.setDelay(INTERVAL[level]);
				}
			}
		});
	}
}
