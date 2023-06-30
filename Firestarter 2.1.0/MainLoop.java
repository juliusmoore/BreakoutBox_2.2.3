

public class MainLoop implements Runnable {

Draw core = new Draw();

public void run() {
	while(true) {
		Part.player.update();
		core.render();
		core.world.requestFocus();
		try {Thread.sleep (10);} catch (InterruptedException e) {e.printStackTrace();}
	}
}



}