package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.ExitActionListener;
import controller.RemovePlayerActionListener;
import model.interfaces.GameEngine;

/**
 * @author Huirong Huang
 * s3615907	RMIT
 */
public class MenuBar extends JMenuBar
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuBar(GameEngine gameEngine, AppFrame appFrame)
	{
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);
		
		// add player function
		JMenuItem addPlayerItem = new JMenuItem("Add player", KeyEvent.VK_A);
		addPlayerItem.addActionListener(new AddPlayerActionListener(appFrame));
		
		// remove player function
		JMenuItem removePlayerItem = new JMenuItem("Remove player", KeyEvent.VK_R);
		removePlayerItem.addActionListener(new RemovePlayerActionListener(appFrame));
		
		// exit function
		JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitItem.addActionListener(new ExitActionListener(appFrame));
		
		// add menu items
		fileMenu.add(addPlayerItem);
		fileMenu.add(removePlayerItem);
		fileMenu.add(exitItem);
	}
}
