//The purpose of this class is to not have to pass everything around all the time.
//Yet it will not be dynamic as the buffering system in Output or the entity handling system
//which is in EntityTracker, it is meant to be simple static methods which can act as handles
//to objects which may need to be referenced throughout the program.

public class Handle {
	public static final String name = "Handle 1.0";
	
	
	private static Output debug;
	
	public static void initPrintln(Output d) {
		debug = d;
	}
	
	public static synchronized void println(String message) {
		debug.text(message);
	}

	
	private static Output messenger;

	public static synchronized void initMes(Output d) {
		messenger = d;
	}
	
	public static synchronized void mes(String message) {
		messenger.text(message);
	}

}