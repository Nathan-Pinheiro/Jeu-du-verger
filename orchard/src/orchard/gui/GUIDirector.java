package orchard.gui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JFrame;

import orchard.model.Player;
import orchard.services.ImgService;

public class GUIDirector extends JFrame{
	
	private MenuGUI menuGUI;
	private LobbyGUI lobbyGUI;
	private GameGUI gameGUI;
	
	private Player[] players;
	
	public GUIDirector() {
		super("Jeu du Verger");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1600, 950));
        setPreferredSize(new Dimension(1400, 800));
        pack();
        setResizable(true);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        
        Image appIcon = ImgService.getImage("resources\\iconOrchard.png"); 
        setIconImage(appIcon);
        
        players = new Player[] {
				new Player("Nathan"), 
				new Player("Jimmy"), 
				null,
				null
			};
        
        gameGUI = new GameGUI(this, players);
        lobbyGUI = new LobbyGUI(this, players);
        menuGUI = new MenuGUI(this);
        
        setMenuGUI();
	}
	
	public GameGUI getGameGUI() {
		return gameGUI;
	}

	public void setGameGUI() {
		getContentPane().removeAll();
		gameGUI.showOnJFrame(this);
		repaint();
		System.out.println("game");
	}
	
	public void setMenuGUI() {
		getContentPane().removeAll();
		menuGUI.showOnJFrame(this);
		repaint();
		System.out.println("menu");
	}
	
	public void setLobbyGUI() {
		getContentPane().removeAll();
		lobbyGUI.showOnJFrame(this);
		repaint();
		System.out.println("lobby");
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	
	
}
