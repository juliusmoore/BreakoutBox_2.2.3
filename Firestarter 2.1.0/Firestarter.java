public class Firestarter {
	
	public static void main (String[] arg) {
	System.out.println("Welcome to Firestarter 2.0.");
	System.out.println("Ideas similar to Firestarter, but more ambitious.");
	
	MainLoop Flame = new MainLoop();
	new Thread(Flame).start();
	
	try {Thread.sleep(5000);} catch (InterruptedException e) {System.out.println("Can't sleep for initialization");}
	
	Storyteller about = new Storyteller();
	about.setStory("README.txt");
	new Thread(about).start();
	}

}