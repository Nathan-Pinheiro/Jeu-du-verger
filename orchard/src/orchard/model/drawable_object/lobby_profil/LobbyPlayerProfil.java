package orchard.model.drawable_object.lobby_profil;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;

import orchard.interfaces.Showable;
import orchard.model.Player;
import orchard.model.drawable_object.DrawableObject;
import orchard.model.drawable_object.game_profil.Basket;
import orchard.util.Position;

public class LobbyPlayerProfil implements Showable{
	
	private Player player = null;
	
	private int width;
	private int height;
	private int x;
	private int y;
	private static int basketSize = 140;
	
	DrawableObject background;
	JLabel nameText;
	DrawableObject crossImage;
	
	public LobbyPlayerProfil(int x, int y, int width, int height, Player player) {
		this.player = player;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		
		if(player != null) {
			
			Color textColor = new Color(0,0,0);
			
			nameText = new JLabel(player.getName(), JLabel.CENTER);
			nameText.setBounds(x, y, width, height);
			nameText.setForeground(textColor);
			nameText.setFont(new Font("Arial", Font.BOLD, 30));
		}
		
		background = new LobbyBackgroundPlayerProfil(x, y, width, height, player == null);
	}
	
	public LobbyPlayerProfil(Position position, int width, int height, Player player) {
		this(position.x(), position.y(), width, height, player);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		
		if(player != null) {
			
			Color textColor = new Color(0,0,0);
			
			nameText = new JLabel(player.getName(), JLabel.CENTER);
			nameText.setBounds(x, y, width, height);
			nameText.setForeground(textColor);
			nameText.setFont(new Font("Arial", Font.BOLD, 30));
			
		}
		
		background = new LobbyBackgroundPlayerProfil(x, y, width, height, player == null);
		
		if(player != null)System.out.println("updated" + player.getName());
	}
	
	public void setPosition(Position position) {
		if(this.player != null) {
			nameText.setBounds(position.x(), position.y(), width, height);
		}
		background.setPosition(position);
	}

	@Override
	public void showOnJFrame(JFrame jframe) {
		
		if(nameText != null)jframe.add(nameText);
		jframe.add(background);
		
	}
}
