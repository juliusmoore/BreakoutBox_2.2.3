import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Player extends Entity {
//manually access the following two plus the boolean pass as output from checkTile:
String info = null;
int candy = 0;
	
	public Player (Map iem, int ie0, int ie1) {
		super(iem, ie0, ie1);
	}
	
	@Override
	public void initialize() {
		this.setName("player 1.1");
		this.setSprite("player/content.png");
		
		System.out.println("A new entity, " + name + " was created.");
	}
	
	public void checkTile() {
		switch (map.value(x, y)) {
			case 0:
			pass = true;
			dead = true;
			info = "You jumped off a cliff!";
			break;
				
			case 1:
			pass = true;
			dead = false;
			info = null;
			break;
				
			case 2:
			pass = false;
			dead = false;
			x = px;
			y = py;
			info = "A wall blocks your path.";
			break;
				
			case 3:
			pass = true;
			dead = false;
			info = "You safely travelled and found some delicious candy!";
			candy++;
			map.setValue(x, y, 1);
			break;
				
			case 4:
			pass = true;
			if (candy >= map.candy()) {
				dead = true;
				info = "You safely escape with the candy!";
			}
			else {
				dead = false;
				info = "You found the exit but can still smell candy...";
			}
			break;
			
			default:
			pass = true;
			dead = false;
			info = "Despite your best abilities, you can't make out what is here.";
			break;
		}
		if (info != null) {System.out.println(info);}
	}	
}