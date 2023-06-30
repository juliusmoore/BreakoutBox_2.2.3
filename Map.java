import java.io.*;

public class Map {

//The following lists combine with variable used solely in the retrival of info
//Stores the folder which contains the maps
private String path;

//Map array
private int[][][] map = new int[2][999][999];

//Map dimensional info
private int[] widtha = new int[999];
private boolean astart = true;
private int[] widthb = new int[999];
private int height;
private int widestRow;
private int widestRowLength;

//Spawnpoint
private int spawnx = 0;
private int spawny = 0;
private boolean foundSpawn = false;

//Candy
private int candyCounter = 0;

//Metadata
private String versione = null;
private String title = "N/A";

//Interpreting Variables
//bmaper 1.0
private BufferedReader file = null;
private String line = null;
private int lLength;
private char sym = '0';
private int isym = 0;
private int temp;
//bmaper 2.0
private int xpointer;
private String strung = " ";
private int etype = 0;

	public Map(String e) {
		System.out.println("Map Handler 2.1 initialized");
		path = e + "/";
	}

	public void load (String e) {
		try {
			file = new BufferedReader (new FileReader(path + e + ".mox"));
			versione = file.readLine();
			switch (versione) {
				case "bmaper 1.0":
				title = file.readLine();
				height = -1;
				candyCounter = 0;
				foundSpawn = false;
				line = file.readLine();
				do {
					height++;
					lLength = line.length() - 1;
					widthb[height] = lLength;
					astart = true;
					for (int i = 0; i <= lLength; i++) {
						sym = line.charAt(i);
						isym = sym - 48;
						if (isym == 3) {
							candyCounter++;
						}
						if (isym >= 0 && isym <= 9) {
							map[0][i][height] = isym;
							if (astart && sym != '0') {
								astart = false;
								if (i != 0) {widtha[height] = i-1;}
								else {widtha[height] = i;}
							}
						} else {
							if (astart) {
								i--;
							}
							switch (sym) {
								case 's':
								map[0][i][height] = 1;
								foundSpawn = true;
								spawnx = i;
								spawny = height;
								break;
								
								default:
								System.out.println("MapGen1.0: Unrecognized Symbol: " + sym + " - Replaced with 2.");
								map[0][i][height] = 2;
								break;
							}
						}
						if (i == lLength && sym != '0') {
							widthb[height] = i + 1;
							map[0][i+1][height] = 0;
						}
					}
					line = file.readLine();
				} while (line != null);
				break;
				
				case "bmaper 2.0":
				bm20();
				break;
				
				default:
				System.out.println("MapGen1.0 could not understand the map's formatting.");
				break;
			}
		}
		catch (IOException ee) {
			System.out.println("Map Handler 2.1: IO error loading the map.");
		}
		if (file != null) {
			try {
				file.close();
			}
			catch (IOException ee) {
				System.out.println("Map Handler 2.1: RESOURCE LEAK");
			}
		}
		findTheWidestRow();
	}

	public String name () {
		return title;
	}

	public String version() {
		return versione;
	}
	
	public String folder() {
		return path;
	}
	
	public int rows() {
		return height;
	}
	
	public int rowBegins(int y) {
		return widtha[y];
	}
	
	public int rowEnds(int y) {
		return widthb[y];
	}
	
	public int[] spawn () {
		int[] spawnMessager = new int[] {0, 0, 0};
		spawnMessager[0] = foundSpawn? 1:0;
		if (foundSpawn) {
			spawnMessager[1] = spawnx;
			spawnMessager[2] = spawny;
			return spawnMessager;
		}
		else {
			spawnMessager[1] = 0;
			spawnMessager[2] = 0;
			return spawnMessager;
		}
	}
	
	public int value (int x, int y) {
		return map[0][x][y];
	}
	
	public int space (int x, int y) {
		return map[1][x][y];
	}
	
	public void setValue (int x, int y, int e) {
		map[0][x][y] = e;
	}
	
	public void setSpace (int x, int y, int e) {
		map[1][x][y] = e;
	}
	
	public int largestRow() {
		return widestRow;
	}
	
	public int largestRowLength() {
		return widestRowLength;
	}
	
	public int candy() {
		return candyCounter;
	}
	
	
	private void bm20() {
	try {	
		title = file.readLine();
		height = -1;
		foundSpawn = false;
		candyCounter = 0;
		etype = 0;
		line = file.readLine();
		do {
			astart = true;
			height++;
			widtha[height] = -1;
			widthb[height] = -1;
			astart = true;
			xpointer = 0;
			lLength = line.length() - 1;
			for (int i = 0; i <= lLength; i++) {
				sym = line.charAt(i);
				isym = sym - 48;
				if (isym <= 9 && isym >=0) {
					if (strung == " ") {
						strung = String.valueOf(sym);
					} else {
						strung += String.valueOf(sym);
					}
				} else {
					switch (sym) {
						//e for empty tile
						case 'e':
							map[0][xpointer][height] = Integer.parseInt(strung);
							etype = 0;
							strung = " ";
						break;
						//s for (player's) spawn
						case 's':
							map[0][xpointer][height] = Integer.parseInt(strung);
							spawnx = xpointer;
							spawny = height;
							foundSpawn = true;
							etype = 0;
							strung = " ";
						break;
						//u for updatable entity
						case 'u':
							map[0][xpointer][height] = Integer.parseInt(strung);
							etype = 1;
							strung = " ";
						break;
						//r for runnable entity
						case 'r':
							map[0][xpointer][height] = Integer.parseInt(strung);
							etype = 2;
							strung = " ";
						break;
						case '.':
							if (strung != " ") {
								temp = Integer.parseInt(strung);
							} else {
								temp = 0;
							}
							switch (etype) {
								//updatable entities will be set to positive values
								case 1:
									map[1][xpointer][height] = temp;
								break;
								//runnable entities will be set to negative values
								case 2:
									map[1][xpointer][height] = -temp;
								break;
								//for when nothing need be created using this proccess.
								case 0:
									map[1][xpointer][height] = temp;
								break;
								//illegal symbols
								default:
									System.out.println("MapGen 2.1: Syntax Error in map near " + i + " " + height + ". Cannot compensate. Will now exit.");
									System.exit(0);
								break;
							}
							if (astart) {
								if (map[0][xpointer][height] != 0) {
									astart = false;
									widtha[height]--;
								}
								widtha[height]++;
							}
							widthb[height] = xpointer;
							xpointer++;
							strung = " ";
						break;
						default:
						System.out.println("MapGen 2.1: Illegal symbol used in map near " + i + " " + height + ". Cannot compensate. Will now exit.");
						System.exit(0);
						break;
					}
				}
			}
			line = file.readLine();
		} while (null != line);
		findTheWidestRow();
	} catch (IOException ee) {System.out.println("Map 2.1: IO Error loading the map.");}
	}
	
	//below are some methods from code that seems to be readily isolatable and reusable.
	
	//please note that findTheWidestRow relies on most of the map's properties being already available.
	private void findTheWidestRow() {
		widestRow = 0;
		widestRowLength = 0;
		int temp = 0;
		for (int i = 0; i <= height; i++) {
			temp = widthb[i] - widtha[i];
			if (temp > widestRowLength) {
				widestRowLength = temp;
				widestRow = i;
			}
		}
	}
	
	public void dumpMap() {
	System.out.println("Map 2.1: Dumping Map Data Now");
	for (int i = 0; i <= height; i++) {
		for (int u = 0; u <= widthb[i]; u++) {
			System.out.print(map[0][u][i]);
			System.out.print(" ");
		}
		System.out.println("|||");
	}
	}

}