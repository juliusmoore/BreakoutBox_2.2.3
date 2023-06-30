import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw {

	JFrame window;
	Canvas world;
	BufferStrategy worldBuffer;
	
	private int width = 640;
	private int height = 480;
	
Draw () {
	window = new JFrame("Firestarter");
	JPanel mainpanel = (JPanel) window.getContentPane();
	mainpanel.setPreferredSize (new Dimension(width, height));
	mainpanel.setLayout(null);
	
	world = new Canvas();
	world.setBounds (0, 0, width, height);
	world.setIgnoreRepaint (true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.pack();
	
	window.setResizable(false);
	window.setVisible(true);
	
	mainpanel.add(world);
	world.createBufferStrategy(2);
	worldBuffer = world.getBufferStrategy();
	
	world.requestFocus();
	world.setBackground(Color.black);
	world.addKeyListener (new Input());
}

void render() {
Graphics2D g = (Graphics2D) worldBuffer.getDrawGraphics();
g.clearRect (0, 0, width, height);
render(g);
g.dispose();
worldBuffer.show();
}

protected void render(Graphics2D g) {
//Can render everything here.
g.setColor(Color.white);
g.fillRect (Part.player.x(), Part.player.y(), 10, 10);
}

}