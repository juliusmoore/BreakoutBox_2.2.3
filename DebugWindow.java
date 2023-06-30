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

public class DebugWindow extends JFrame{

	public DebugWindow (Output output, String title) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Input input = new Input();
		this.addKeyListener(input);

		this.setPreferredSize(new Dimension(output.width + 34, output.height + 34));
		this.add(output, BorderLayout.PAGE_START);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

}