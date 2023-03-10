package orchard.model.drawable_object.game_profil;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import orchard.interfaces.Showable;
import orchard.model.Player;
import orchard.model.drawable_object.DrawableObject;
import orchard.model.drawable_object.lobby_profil.LobbyBackgroundPlayerProfil;
import orchard.util.Position;

public class GamePlayerProfil implements Showable{
	
	private Player player = null;
	
	private int width;
	private int height;
	private static int basketSize = 140;
	
	DrawableObject background;
	Basket basket;
	JLabel fruitAmountText;
	JLabel playerNameText;
	
	public GamePlayerProfil(int x, int y, int width, int height, Player player) {
		this.player = player;
		this.width = width;
		this.height = height;
		
		if(player != null) {
			
			Color textColor = new Color(0,0,0);
			
			fruitAmountText = new JLabel(player.getFruits().toString(), JLabel.CENTER);
			fruitAmountText.setBounds(x, y, width, height);
			fruitAmountText.setForeground(textColor);
			fruitAmountText.setFont(new Font("Arial", Font.BOLD, 30));
			
			playerNameText = new JLabel(player.getName(), JLabel.CENTER);
			playerNameText.setBounds(x + 2 * height/3, y, width, height/3);
			playerNameText.setForeground(textColor);
			playerNameText.setFont(new Font("Arial", Font.BOLD, 30));
			
			basket = new Basket(x, y, basketSize);
		}
		
		background = new LobbyBackgroundPlayerProfil(x, y, width, height, player == null);
	}
	
	public GamePlayerProfil(Position position, int width, int height, Player player) {
		this(position.x(), position.y(), width, height, player);
	}
	
	public void setPosition(Position position) {
		if(this.player != null) {
			fruitAmountText.setBounds(position.x(), position.y(), width, height);
			playerNameText.setBounds(position.x(), position.y() + 2 * height/3, width, height/3);
			basket.setPosition(new Position(position.x() + width / 2, position.y() + height / 2 - 20));
		}
		background.setPosition(position);
	}
	
	public void updatePlayerScore() {
		fruitAmountText.setText(player.getFruits().toString());
	}

	@Override
	public void showOnJFrame(JFrame jframe) {
		
		if(fruitAmountText != null)jframe.add(fruitAmountText);
		if(playerNameText != null)jframe.add(playerNameText);
		if(basket != null)jframe.add(basket);
		jframe.add(background);
		
	}
}
