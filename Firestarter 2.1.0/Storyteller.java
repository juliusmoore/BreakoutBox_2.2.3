import java.io.*;
import java.util.Scanner;

public class Storyteller implements Runnable {

private String link = "default.txt";
private int pauseTime = 50;

private String lc = "Storyteller Reincarnate 1.0";
private int lcLength = 0;
private int reading = 0;
private char sym = '0';

private BufferedReader in = null;

private boolean noSleep = false;

public void Storyteller () {

}

//Allows one to change which file the story will be read from. MUST USE
public void setStory (String e) {
link = e;
}

public void run () {

	try {
		in = new BufferedReader(new FileReader(link));
		
		while (lc != null) {
			lcLength = lc.length() - 1;
			for (int i = 0; i <= lcLength; i++) {
				System.out.print(lc.charAt(i));
				try {Thread.sleep(pauseTime);} catch (InterruptedException ee) {noSleep = true;}
			}
			System.out.format("%n");
			lc = in.readLine();
		}
	}
	catch (IOException e) {
	System.out.println("File not found?");
	}
	if (in != null) {try{in.close();} catch (IOException e) {System.out.format("%n RESOURCE LEAK: Could not close file?");} }
	if (noSleep) {
		System.out.format("%n");
		System.out.println("Gradual readout not possible: Storyteller Can't Sleep.");
	}

	return;
}

//Allows one to change the delay between characters displayed
public void setPause(int e) {
	pauseTime = e;
}

};