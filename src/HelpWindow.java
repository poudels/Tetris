import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpWindow extends JFrame {

	public HelpWindow() {
		super("Help");
		
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

		final JTextArea textArea = new JTextArea(200, 20);
		textArea.setText(d);

		JScrollPane scroll = new JScrollPane(textArea);
		super.add(scroll);
	}

}
