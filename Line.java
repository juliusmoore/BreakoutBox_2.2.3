import java.io.*;

public class Line {

	public Line() {
		System.out.println("Line 1.1 initialized");
	}

	public static String ask (String prompt) {
		
		System.out.print(prompt + " ");
		
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		
		String input = null;
		
		try {input = br.readLine();} catch (IOException e) {System.out.println("Error taking input in Line 1.0");}
		
		return input;
	}

}