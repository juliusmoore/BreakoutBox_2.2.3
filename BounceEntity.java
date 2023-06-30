import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class BounceEntity extends Entity {

String directionA = "w";
String directionB = "e";
boolean direction = true;
	
	private String currentDirection() {
		if (direction)
			return directionA;
		else
			return directionB;
	}

	public BounceEntity (Map iem, int ie0, int ie1) {
		super(iem, ie0, ie1);
	}
	
	@Override
	public void initialize() {
		this.setName("bounce 1.3");
		this.setSprite("entity/default.png");
		
		this.canUpdate();
		
		System.out.println("A new entity, " + name + " was created.");
	}
	
	@Override
	public void checkTile() {
		switch (map.value(x, y)) {
			case 0:
			dead = true;
			pass = true;
			break;
			case 2:
			dead = false;
			pass = false;
			break;
			default:
			dead = false;
			pass = true;
			break;
		}
	}
	
	@Override
	public void update() {
		//System.out.println("BOUNCIN");
		
		px = x;
		py = y;
		
		this.move(currentDirection());
		this.checkTile();
		if (!pass) {
			this.jump(px, py);
			direction = !direction;
			this.move(currentDirection());
		}
		map.setSpace(px, py, 0);
		map.setSpace(x, y, 1);
	}
}