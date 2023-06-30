(Also known as 2.1.3)
Map has some errors in the new generation scheme for bmaper 2.0 around the top and left edges.

case "bmaper 2.0":
				title = file.readLine();
				height = -1;
				candyCounter = 0;
				foundSpawn = false;
				line = file.readLine();
				do {
					height++;
					task = 0;
					widthb[height] = tilex;
					tilex = -1;
					lLength = line.length() - 1;
					System.out.println("Next Line");
					for (int i = 0; i <= lLength; i++) {
						sym = line.charAt(i);
						isym = sym - 48;
						switch (task) {
							case 0:
							tilex++;
							lsym = sym;
							if (isym == 3) {
								candyCounter++;
							}
							if (isym >= 0 && isym <= 9) {
								map[tilex][height] = isym;
							} else {
								switch (sym) {
									case 's':
									map[tilex][height] = 1;
									foundSpawn = true;
									spawnx = tilex;
									spawny = height;
									break;
									
									default:
									System.out.println("MapGen2.0: Unrecognized Tile Symbol: " + sym + " - Replaced with 2.");
									map[tilex][height] = 2;
									break;
								}
							}
							task = 1;
							break;
							
							case 1:
							type = sym;
							task = 2;
							break;
							
							case 2:
							switch (type) {
								case 'e':
								//means a tab was here instead of a digit, but we already compensated in task 1
								break;
								
								case 'u':
								//generate a updatable entity
								
								break;
								
								case 'r':
								//generate a runnable entity with its own thread
								
								break;
								
								default:
								System.out.println("MapGen2.0: Unrecognized entity type to place on tile.");
								break;
							}
							task = 3;
							break;
							
							case 3:
							//do nothing, this will be a tab.
							task = 0;
							break;
							
							default:
							System.out.println("MapGen2.0: Reading task messed up...must exit now.");
							System.exit(0);
							break;
							//be sure to place a barrier at end of line
						}
						if (i == lLength && lsym != '0') {
							widthb[height] = tilex + 1;
							map[tilex+1][height] = 0;
						}
						//debug
						System.out.println(tilex + "_" + i + " debug " + sym + " type: " + type);
						System.out.println("Task:" + task);
						//debug
					}
				line = file.readLine();
				} while (line != null);
				break;