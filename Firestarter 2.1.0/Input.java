import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {



public void KeyAdapter() {
	System.out.println ("Input Manager 'Input' initialised!");
}

public void keyTyped (KeyEvent key) {
//won't use. Supposedly unreliable...
}

public void keyReleased (KeyEvent key) {
	switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			Part.player.up(false);
			break;
		case KeyEvent.VK_DOWN:
			Part.player.down(false);
			break;
		case KeyEvent.VK_LEFT:
			Part.player.left(false);
			break;
		case KeyEvent.VK_RIGHT:
			Part.player.right(false);
			break;
	}
}

public void keyPressed (KeyEvent key) {
	switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			Part.player.up(true);
			break;
		case KeyEvent.VK_DOWN:
			Part.player.down(true);
			break;
		case KeyEvent.VK_LEFT:
			Part.player.left(true);
			break;
		case KeyEvent.VK_RIGHT:
			Part.player.right(true);
			break;
	}
}

}