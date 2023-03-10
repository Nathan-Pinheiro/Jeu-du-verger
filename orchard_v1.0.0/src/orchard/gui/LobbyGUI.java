package orchard.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import orchard.interfaces.Showable;
import orchard.model.Player;
import orchard.model.drawable_object.lobby_profil.LobbyPlayerProfil;
import orchard.util.LimitDocument;

public class LobbyGUI implements Showable {

	private static int playerProfilMargin = 25;
	private static int playerProfilGap = 25;
	private static int PlayerTextFieldGap = 100;
	
	private int lobbyPlayerProfilWidth;
	private int lobbyPlayerProfilHeigth;
	
	List<Showable> showables = new ArrayList<>();
	
	JTextField playerTextField;
	JButton button;
	JButton playButton;
	Player[] players;
	LobbyPlayerProfil[] lobbyPlayerProfils;
	
	GUIDirector jframe;
	
	public LobbyGUI(GUIDirector jframe, Player[] players) {
		this.jframe = jframe;
		this.players = players;
		
		jframe.setLayout(null);
		
		playerTextField = new JTextField(12);
		playerTextField.setBounds(PlayerTextFieldGap, PlayerTextFieldGap, (jframe.getWidth()-2*PlayerTextFieldGap) * 2/3, 50);
		playerTextField.setFont(new Font("Arial", Font.BOLD, 25));
		playerTextField.setHorizontalAlignment(JTextField.CENTER);
		playerTextField.setDocument(new LimitDocument(20));
		
		button = new JButton("Ajouter joueur");
		button.setBounds(PlayerTextFieldGap + (jframe.getWidth()-2*PlayerTextFieldGap) * 2/3, PlayerTextFieldGap, (jframe.getWidth()-2*PlayerTextFieldGap) * 1/3, 50);
		button.setFont(new Font("Arial", Font.BOLD, 25));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addPlayer(playerTextField.getText());
				
				jframe.repaint();
				jframe.revalidate();
			}
			
		});
		
		playButton = new JButton("Jouer");
		playButton.setBounds(jframe.getWidth() * 1/3, jframe.getHeight() * 1/2 + jframe.getHeight() * 1/8, jframe.getWidth() * 1/3, jframe.getHeight() * 1/8);
		playButton.setFont(new Font("Arial", Font.BOLD, 25));
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.getGameGUI().setupNewGame(players);
				jframe.setGameGUI();
			}
		});
		
		lobbyPlayerProfilWidth = (jframe.getWidth() - 2*playerProfilMargin - 3*playerProfilGap)/4;
		lobbyPlayerProfilHeigth = (jframe.getHeight() * 1/4);
		
		lobbyPlayerProfils = new LobbyPlayerProfil[] {
				new LobbyPlayerProfil(playerProfilMargin, PlayerTextFieldGap + 3*playerProfilMargin + 50, lobbyPlayerProfilWidth, lobbyPlayerProfilHeigth, players[0]),
				new LobbyPlayerProfil(playerProfilMargin + 1*(playerProfilGap + lobbyPlayerProfilWidth), PlayerTextFieldGap + 3*playerProfilMargin + 50, lobbyPlayerProfilWidth, lobbyPlayerProfilHeigth, players[1]),
				new LobbyPlayerProfil(playerProfilMargin + 2*(playerProfilGap + lobbyPlayerProfilWidth), PlayerTextFieldGap + 3*playerProfilMargin + 50, lobbyPlayerProfilWidth, lobbyPlayerProfilHeigth, players[2]),
				new LobbyPlayerProfil(playerProfilMargin + 3*(playerProfilGap + lobbyPlayerProfilWidth), PlayerTextFieldGap + 3*playerProfilMargin + 50, lobbyPlayerProfilWidth, lobbyPlayerProfilHeigth, players[3])
		};
		
		showables.addAll(Arrays.asList(lobbyPlayerProfils));
		
		printPlayers();
	}

	public void printPlayers() {
		for(Player p : players) {
			if(p!=null)System.out.println(p.getName());
		}
	}
	
	public void addPlayer(String name) {
		
		if(name.length()>2 && name.length() <= 20) {

				Boolean isPlayerExist = false;
				int placeAvaillable = 0;
				
				for(int i = 0; i<4; i++) {
					if(jframe.getPlayers()[i] != null) {
						placeAvaillable++;
						if(jframe.getPlayers()[i].getName().equals(name))isPlayerExist = true;
					}
				}
				
				if(!isPlayerExist && placeAvaillable<4) {
					
					jframe.getPlayers()[placeAvaillable] = new Player(name);
					lobbyPlayerProfils[placeAvaillable] = new LobbyPlayerProfil(playerProfilMargin + placeAvaillable*(playerProfilGap + lobbyPlayerProfilWidth), PlayerTextFieldGap + 3*playerProfilMargin + 50, lobbyPlayerProfilWidth, lobbyPlayerProfilHeigth, players[placeAvaillable]);
					
					showables.clear();
					showables.addAll(Arrays.asList(lobbyPlayerProfils));
					
					playerTextField.setText("");
					players = jframe.getPlayers();
					
					jframe.getContentPane().removeAll();
					showOnJFrame(jframe);
				}
			}
	}
	
	@Override
	public void showOnJFrame(JFrame jframe) {
		jframe.add(playerTextField);
		jframe.add(button);
		jframe.add(playButton);
		
		for(Showable s : showables) {
			s.showOnJFrame(jframe);
		}
	}

}
