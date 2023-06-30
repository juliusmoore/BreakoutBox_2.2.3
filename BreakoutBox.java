import java.io.File;

public class BreakoutBox {

	public static void main(String[] arg) {
		//testvars
		String temporaryString;
		
		System.out.println("Welcome to BreakoutBox 2.2.3");
		
		Storyteller disp = new Storyteller();
		disp.setPause(20);
		
		//commands
		String cominfo = null;
		String command = null;
		int temp;
		//Line is based on static methods so its interference is less complex.
		//Handle is based on static methods so it does not need to be set up, although its methods will
		//	be initialized as we go along.
		
		//declare world related arrays and variables
		int x, y, xm, ym, xi, yi, px, py;
		
		//Get the name of the map from the player
		String mappy;
		File memap;
		disp.setStory("maps/Legend.txt");
		disp.run();
		mappies: do {
			mappy = Line.ask("Please select one of the above maps:");
			memap = new File("maps/" + mappy + ".mox");
			if (memap.isFile()) {
				break mappies;
			} else {
				System.out.println("Sorry. That was not a choice...");
				System.out.println(" ");
				disp.run();
			}
		} while (true);
		
		//Set up alternative output. Note that it will not display until the MainWindow is set up.
		Output debug = new Output(310, 5);
		DebugWindow debug_window = new DebugWindow(debug, "Debugging Window");
		new Thread(debug).start();
		Output messages = new Output(310, 2);
		DebugWindow messages_window = new DebugWindow(messages, "Messages");
		new Thread(messages).start();
		
		//Tie Handle's println function to the debug panel
		Handle.initPrintln(debug);
		Handle.initMes(messages);
		
		Handle.println("BreakoutBox Main: If you see this line, Handle's println function is initialized to this frame.");
		Handle.mes("BreakoutBox Main: If you see this line, Handle's mes function is initialized to this frame.");
		
		//map generation
		Map map = new Map ("maps");
		map.load(mappy);
		
		//debug map gen (display the generated array) (for new formats)
		map.dumpMap();
		
		//set up the entity tracker
		EntityTrack tracker = new EntityTrack(map);
		tracker.readyTracker();
		new Thread(tracker).start();
		
		//initialization
		//Take note: xm and xy values are purposefully one more than the array's maximum reference.
		xm = map.largestRowLength();
		ym = map.rows();
		
		//get the player's spawning point
		int[] spawn = new int[2];
		spawn = map.spawn();
		if (spawn[0] == 0) {
			System.out.println("No spawning point!");
			spawn[1] = (xm + 1) / 2 - 1;
			spawn[2] = (ym + 1) / 2 - 1;
		}
		
		//add the player
		Player player = new Player (map, spawn[1], spawn[2]);
		
		//add the GUI and display
		MainWindow window = new MainWindow(map, player);
		new Thread(new DrawLoop(map, window, player)).start();
		
		//simple gameloop for debugging
		boolean rungame = true;
		while (rungame && !player.dead) {
			
			commandloop:
			while (true) {
				command = Line.ask("You step through the darkness towards the");
				//interpret the command
				switch (command) {
					case "n":
					player.move("n");
					break commandloop;
					
					case "nw":
					player.move("nw");
					break commandloop;
					
					case "w":
					player.move("w");
					break commandloop;
					
					case "sw":
					player.move("sw");
					break commandloop;
					
					case "s":
					player.move("s");
					break commandloop;
				
					case "se":
					player.move("se");
					break commandloop;
					
					case "e":
					player.move("e");
					break commandloop;
					
					case "ne":
					player.move("ne");
					break commandloop;
					
					case "help":
					cominfo = "_help";
					break;
					
					case "about":
					cominfo = "_about";
					break;
					
					case "quit":
					System.exit(0);
					break;
					
					case "exit":
					System.exit(0);
					break;
					
					case "new game":
					cominfo = "No new game function was created.";
					break;
					
					case "release info":
					cominfo = "_updateinfo";
					break;
					
					case "textd":
					cominfo = "*d";
					break;
					
					case "textm":
					cominfo = "*m";
					break;
					
					case "stacksd":
					cominfo = "**d";
					break;
					
					case "stacksm":
					cominfo = "**m";
					break;
					
					default:
					cominfo = "Invalid action/command. Type help for further information.";
					break;
				}
				switch (cominfo.charAt(0)) {
					case '_':
					disp.setStory ("message/" + cominfo.substring(1) + ".txt");
					disp.run();
					break;
					
					case '*':
					switch (cominfo.charAt(1)) {
						case '*':
						switch (cominfo.charAt(2)) {
							case 'd':
							debug.dumpStacks();
							break;
							
							case 'm':
							messages.dumpStacks();
							break;
							
							default:
						}
						break;
						
						case 'd':
						debug.text(Line.ask("Send this to messages: "));
						break;
						
						case 'm':
						messages.text(Line.ask("Send this to messages: "));
						break;
						
						default:
					}
					break;
					
					default:
					System.out.println (cominfo);
					break;
				}
			}
			
		}
	}

}