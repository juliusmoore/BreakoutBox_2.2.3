import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {

private int kup = KeyEvent.VK_UP;
private int kdown = KeyEvent.VK_DOWN;
private int kleft = KeyEvent.VK_LEFT;
private int kright = KeyEvent.VK_RIGHT;
private int t = -1;

static Boolean sup = false;
static Boolean sdown = false;
static Boolean sleft = false;
static Boolean sright = false;

static Boolean initialized = true;

public void KeyAdapter() {
	System.out.println ("Input Manager 'Input 1.0' initialised!");
}

public void keyTyped (KeyEvent key) {
//won't use. Supposedly unreliable...
}

public void keyReleased (KeyEvent key) {
	t = key.getKeyCode();
	if (t == kup) {
			sup = false;
	}
	if (t == kdown) {
			sdown = false;
	}
	if (t == kleft) {
			sleft = false;
	}
	if (t == kright) {
			sright = false;
	}
}

public void keyPressed (KeyEvent key) {
	t = key.getKeyCode();
	if (t == kup) {
			sup = true;
	}
	if (t == kdown) {
			sdown = true;
	}
	if (t == kleft) {
			sleft = true;
	}
	if (t == kright) {
			sright = true;
	}
}

}