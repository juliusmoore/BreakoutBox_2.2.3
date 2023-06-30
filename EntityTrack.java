public class EntityTrack implements Runnable {

private String name = "EntityTrack 1.1";

//Not designed to handle Entities that may need to stick around after they've died, such as the player.

Entity[] entity = new Entity[1000];
boolean[] full = new boolean[1000];
short pointer = 0;
short pointstart = 0;
boolean overflow = false;
Map map;

	public EntityTrack (Map e) {
		System.out.println (name + " Started.");
		map = e;
	}


	public void readyTracker() {
		for (int i = 0; i <= map.rows(); i++) {
			for (int u = map.rowBegins(i); u <= map.rowEnds(i); u++) {
				switch (map.space(u, i)) {
					case 0:
						//ignore
					break;
					case 1:
						//here a Bouncer is created and referenced:
						this.store(new BounceEntity(map, u, i));
					break;
					default:
						System.out.println("An entity that has not yet been proggrammed was found stored in the map array.");
					break;
				}
			}
		}
	}

	//This program just handles references so would use the following as EntityTrack.store(new Entity(args));
	public short store(Entity e) {
		//set the overflow variable to false (used in second loop)
		overflow = false;
		
		//clean up dead entities first to increase the chance of a slot being free
		pointstart = pointer;
		do {
			if (pointer < 999) {
				pointer++;
			} else {
				pointer = 0;
			}
			if (full[pointer]) {
				if (entity[pointer].dead) {
					System.out.println(name + ": deleted entity stored on " + pointer);
					entity[pointer] = null;
				}
			}
		} while (pointer != pointstart);
		
		
		//find an empty slot
		//pointer is already equal to pointstart by the nature of the last loop (important)
		while (full[pointer]) {
			if (pointer < 999) {
				pointer++;
			} else {
				pointer = 0;
			}
			if (pointer == pointstart) {
				overflow = true;
				break;
			}
		}
		
		//check for overflow and safely store the entity
		if (overflow) {
			System.out.println(name + ": had no space left to handle the new " + e.name);
		} else {
			System.out.println(name + ": stored a " + e.name + " in position " + pointer);
			entity[pointer] = e;
			full[pointer] = true;
		}
		
		return pointer;
	}
	
	//runs updateable entities
	public void run() {
		
		do {
			for (int i = 0; i <= 999; i++) {
				if (entity[i] != null) {
					if (entity[i].canupdate) {
						entity[i].update();
					}
				}
			}
			try {
				Thread.sleep(850);
//(17 * 50) above
			} catch (InterruptedException ee) {
				System.out.println("EntityTrack could not rest");
			}
		} while (true);
	}

}