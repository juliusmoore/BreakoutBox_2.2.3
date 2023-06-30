//imported from Firestarter 2.1.0; version numbering system counts from this value.

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import javax.swing.JPanel;

public class World extends JPanel {
	String title = "World 3.0.1";
	
	//w648, h504
	int width = 648;
	int height = 504;
	//or in 24x24squares 27 squares wide and 21 squares high.
	
	BufferedImage land = null;
	BufferedImage wall = null;
	BufferedImage fall = null;
	BufferedImage candy = null;
	BufferedImage exit = null;
	
	BufferedImage deaf = null;
	
	Map map;
	Player player;
	
	private int dxmin, dymin, dxmax, dymax;
	private int t1, t2, t3, t4;
	
World (Map e1, Player e2) {
	map = e1;
	player = e2;
	
	setPreferredSize (new Dimension(width, height));
	setLayout(null);
	setBorder (BorderFactory.createLineBorder (new Color(0x33, 0x66, 0xff)));
	setBackground(Color.black);
	setOpaque(true);
	
	this.setBounds (0, 0, width, height);
	
		try {
			wall = ImageIO.read(new File("sprite/tile/wall.png"));
			land = ImageIO.read(new File("sprite/tile/land.png"));
			fall = ImageIO.read(new File("sprite/tile/drop.png"));
			candy = ImageIO.read(new File("sprite/tile/candy.png"));
			exit = ImageIO.read(new File("sprite/tile/exit.png"));
			
			deaf = ImageIO.read(new File("sprite/entity/default.png"));
		} catch (IOException ee) {System.out.println ("Could not load map graphics.");}
	
	System.out.println(title + " output initialized");
}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent (g);
		
		renderLayer(g, 0);
		renderLayer(g, 1);
		
		//render the player:
		g.drawImage(player.sprite, 13*24, 10*24, null);
		
		//g.setColor(Color.white);
		//g.drawRect(50, 50, 10, 10);
		//g.fillRect(50, 50, 10, 10);
		//g.setColor(Color.blue);
		
		//Draw Text
		//g.drawString("blahblach", 10, 20);
		//g.drawString("Test line 2", 10, 40);
		
	}

	public void renderLayer(Graphics back, int layer) {
		//u is x, u is width, u has to stay that way. i is y.
		//I am using true values here, not array values...
		//t1 is rendering spot in x on the map; t2 is y.
		back.setColor(Color.black);
		for (int u = 0; u <= 27; u++) {
				t1 = player.x - 13 + u;
			for (int i = 0; i<=21; i++) {
				t2 = player.y - 10 + i;
				if (t2 >= 0 && t2 <= map.rows()) {				
					if (t1 <= map.rowEnds(t2) && t1 >= map.rowBegins(t2)) {
						t3 = (u) * 24;
						t4 = (i) * 24;
						switch (layer) {
							case 0:
								switch (map.value(t1, t2)) {
									case 0:
									back.drawImage(fall, t3, t4, null);
									break;
									
									case 1:
									back.drawImage(land, t3, t4, null);
									break;
									
									case 2:
									back.drawImage(wall, t3, t4, null);
									break;
									
									case 3:
									back.drawImage(candy, t3, t4, null);
									break;
									
									case 4:
									back.drawImage(exit, t3, t4, null);
									break;
									
									default:
									System.out.println("Firestarter: Map read/render error.");
									break;
								}
							break;
							case 1:
								switch (map.space(t1, t2)) {
									case 1:
									back.drawImage(deaf, t3, t4, null);
									break;
									default:
									//do nothing
									break;
								}
							break;
						}
						
						back.drawRect(t3, t4, 24, 24);
					} else {
						//back.drawRect(t3, t4, 24, 24);
						//back.fillRect(t3, t4, 24, 24);
					}
				} else {
					//back.drawRect(t3, t4, 24, 24);
					//back.fillRect(t3, t4, 24, 24);
				}
			}
		}
	}

}