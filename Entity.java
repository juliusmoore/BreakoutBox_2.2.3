import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Entity {

BufferedImage sprite;
int x, y, xi, yi, px, py;
Map map;
String direction = null;
//used for helping to identify errors:
String name = "entity 1.3";
//The following are used to help EntityTracker handle the numerous Enities.
String id = "entity";
boolean dead = false;
boolean pass = true;
boolean canupdate = false;
boolean canrun = false;

	//for statements to run immediately
	Entity (Map iem, int ie0, int ie1) {
		this.setMap(iem, ie0, ie1);
		this.initialize();
	}
	
	//for statements that must run after the name or id has been set
	//could set the name and display a specific initalization method
	public void initialize() {}
	
	public void move (String e) {
		px = x;
		py = y;
		switch (e) {
					case "n":
					direction = "North";
					y--;
					break;
					
					case "nw":
					direction = "Northwest";
					y--;
					x--;
					break;
					
					case "w":
					direction = "West";
					x--;
					break;
					
					case "sw":
					direction = "Southwest";
					y++;
					x--;
					break;
					
					case "s":
					direction = "South";
					y++;
					break;
				
					case "se":
					direction = "Southeast";
					y++;
					x++;
					break;
					
					case "e":
					direction = "East";
					x++;
					break;
					
					case "ne":
					direction = "Northeast";
					y--;
					x++;
					break;
					
					default:
					System.out.println("A(n) " + name + " had an error moving");
					break;
		}
		this.checkTile();
	}
	
	public void jump(int e0, int e1) {
		px = x;
		py = y;
		x = e0;
		y = e1;
	}
	
	public void jumpHome() {
		this.jump(xi, yi);
	}
	
	public void setName(String e) {
		name = e;
	}
	
	public void setId (String e) {
		id = e;
	}
	
	public void setSprite(String e) {
		try {
			sprite = ImageIO.read(new File("sprite/" + e));
		}  catch (IOException ee) {System.out.println("Could not load image of entity: " +name);}
	}
	
	public void setMap (Map em, int e0, int e1) {
	xi = e0;
	yi = e1;
	x = xi;
	y = yi;
	px = x;
	py = y;
	map = em;
	}
	
	//This is to have a consistent method for reacting to the map
	public void checkTile() {}
	
	//This will allow some enities to update from a shared thread. Other entities will use their own threads.
	public void update() {}
	
	//The following two methods are specialized initialization that can be triggered for runnable and updatable entities
	public void canUpdate() {
		canupdate = true;
	}
	
	//this functionality has not been developed yet
	public void canRun() {
		canrun = true;
		
	}

}