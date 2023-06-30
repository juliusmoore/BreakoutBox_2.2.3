import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class DrawLoop implements Runnable {

Map map = null;
MainWindow disp = null;
Player player = null;


int[] q, w, a, s;

private int delayer = 1;

	DrawLoop (Map e, MainWindow r, Player t) {
		map = e;
		disp = r;
		player = t;
	}

	public void run () {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
				
		}
		while (true) {
			
			if (delayer == 5) {
				if (Input.sup && !Input.sdown) {
					if (Input.sleft && !Input.sright) {
						player.move("nw");
					} else if (Input.sright && !Input.sleft) {
						player.move("ne");
					} else {
						player.move("n");
					}
				} else if (Input.sdown && !Input.sup) {
					if (Input.sleft && !Input.sright) {
						player.move("sw");
					} else if (Input.sright && !Input.sleft) {
						player.move("se");
					} else {
						player.move("s");
					}
				} else if (Input.sright && !Input.sleft) {
					player.move("e");
				} else if (Input.sleft && !Input.sright) {
					player.move("w");
				} else {
					//no move...
				}
				delayer = 0;
			} else {
				delayer++;
			}
			
			if (player.dead == true) {
				System.out.println("Looks like you died...");
				System.exit(0);
			}
			
			
			disp.world.repaint();
			disp.bar.repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				
			}
		}
	}

}