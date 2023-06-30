import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.BorderFactory;

//Stacksize must always be greater than the buffer size....otherwise the dumpStacks method should be modified

//Note that as of Output 1.1, about 47.5 characters could on a line of a width of 310 pixels when these
//	characters were digits (12345678901234567890).

//Apparently this is not allowed to be static.
public class Output extends JPanel implements Runnable {
	
	final int lineSpacing = 15;
	
	public int width;
	public int height;
	
	public String name = "Ouput 1.1";
	
	final String EXTRACT = "_/*-+";
	
	final int stackSize = 30;
	String[] stack = new String[stackSize];
	int atStack = 0;
	
	
	int bufferSize; //used to always be 15 lines
	String[] buffer;
	
	private void initializeBuffer(int lines) {
		bufferSize = lines;
		buffer = new String[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			buffer[i] = " ";
		}
	}
	
	private void initializeStack() {
		for (int i = 0; i < stackSize; i++) {
			stack[i] = " ";
		}
	}

/*
Sizing:
lines = number of lines (also det. height)
w = width (in pixels)
*/
	Output (int w, int lines) {
		width = w; //this variable is looked at by the window
		height = (lines + 1) * lineSpacing; //this variable is looked at by the window
		
		Dimension sizing = new Dimension(width, height);
		setPreferredSize (sizing);
		setLayout(null);
		setBorder (BorderFactory.createLineBorder (new Color(0x33, 0x66, 0xff)));
		setBackground (Color.black);
		setOpaque(true);
		
		this.setBounds (0, 0, (int) sizing.getWidth(), (int) sizing.getHeight());
		
		this.initializeStack();
		this.initializeBuffer(lines);
		
		System.out.println("Output 1.0 initialized. Ready for customization.");
	}
	
	public synchronized void text (String input) {
		//System.out.println("text was called: " + input);
		if (input != EXTRACT) {
			stack[atStack] = input;
			atStack++;
		} else {
			for (int i = bufferSize - 1; i > 0; i--) {
				buffer[i] = buffer[i-1];
			}
			//System.out.println("place in buffer: " + stack[0]);
			buffer[0] = stack[0];
			//this.dumpStacks();
			for (int i = 1; i < stackSize; i++) {
				stack[i-1] = stack[i];
			}
			atStack--;
		}
	}
	
	public void run() {
		//System.out.println(name + " Running");
		int i = 1;
		do {
			//safely extract from the stack, adds to buffer
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException eee) {}
			
			if (atStack != 0 && (i == 2 || atStack == 20)) {
				i = 1;
				//System.out.println(name + " Extracting text");
				this.text(EXTRACT);
			} else {
				i = 1;
			}
			
			this.repaint();
			
			i++;
		} while(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int temp = 0;
		
		g.setColor(Color.white); //sets the text colour, can be reset during operation
		
		try {
			//buffer contains what to paint, so we can just rewrite
			
			//reset the background
			super.paintComponent(g);
			
			//render the buffer
			for (int i = 0; i < bufferSize; i++) {
				//-i+bufferSize reverses the string order
				try {
					temp = -i + bufferSize - 1;
					//System.out.println(name + " Drew: " + buffer[temp]);
					g.drawString(buffer[temp], 5, (i+1)*lineSpacing); //Draw the text
				} catch (Exception eee) {
					System.out.println(name + " Localized Exception while Painting: " + temp);
					System.out.println(eee);
				}
			}
		
		} catch (Exception e) {
			System.out.println(name + " Exception while painting!");
		}
	}
	
	public void dumpStacks() {
		for (int i = 0; i < stackSize; i++) {
			System.out.print(name + " stack[" + i + "] = " + stack[i]);
			if (i < bufferSize) {
				System.out.println(" | buffer[" + i + "] = " + buffer[i]);
			} else {
				System.out.println("");
			}
		}
	}
}