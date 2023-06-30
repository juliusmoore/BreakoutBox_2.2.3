import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import java.awt.Dimension;

public class MainWindow extends JFrame implements ActionListener {

	World world;
	
	//The menus
	JMenu helpm;
	JMenuItem help;
	JMenuItem about;
	JMenuItem release;
	
	JMenu firem;
	JMenuItem exit;
	JMenuItem newGame;
	
	JMenuBar bar;

	public MainWindow (Map map, Player player) {
		this.setTitle("BreakoutBox 2.2.3");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Input input = new Input();
		this.addKeyListener(input);
		
		//Menus
		help = new JMenuItem("Help");
		about = new JMenuItem("About");
		release = new JMenuItem("Release Info");
		helpm = new JMenu("Help");
		helpm.add(help);
		helpm.addSeparator();
		helpm.add(release);
		helpm.add(about);
		
		exit = new JMenuItem("Exit");
		newGame = new JMenuItem("New Game");
		firem = new JMenu("Fire");
		firem.add(newGame);
		firem.addSeparator();
		firem.add(exit);
		
		bar = new JMenuBar();
		bar.add(firem);
		bar.add(helpm);
		this.setJMenuBar(bar);
		
		world = new World(map, player);
		this.setPreferredSize(new Dimension(world.width + 30, world.height + 60));
		this.add(world, BorderLayout.PAGE_START);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}