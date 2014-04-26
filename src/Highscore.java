import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Highscore {
	private String filename = "";
	private String highscore = "0";
	
	public Highscore(String filename){
		this.filename = filename;
		try {
			File file = new File(filename);
			if(file.exists()){
				
			}
			else{
				//create file
				boolean isCreated = file.createNewFile();
				if(isCreated){
					Writer writer = null;
					try {
					writer = new BufferedWriter(new OutputStreamWriter(
					          new FileOutputStream(filename), "utf-8"));
					    writer.write("0");
					}
					catch(IOException ex){
						System.out.println("Something went wrong");
					}
					finally {
					   try {writer.close();} catch (Exception ex) {}
					}
				}
			}
		}
		catch (Exception e){
			System.out.println("IOE exception");
		}
	}
	
	public String getHighscore(){
		try {
			BufferedReader buffr = new BufferedReader(new FileReader(filename));
			highscore = buffr.readLine();
			buffr.close();
			return highscore;
		}
		catch(Exception e){
			System.out.println("Problem loading file.");
			return "-1";
		}
	}
	
	public void setHighscore(String hs){
		Writer writer = null;
		try {
		writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filename), "utf-8"));
		    writer.write(hs);
		}
		catch(IOException ex){
			System.out.println("Something went wrong");
		}
		finally {
			try {writer.close();} catch (Exception ex) {}
		}
	}
}
