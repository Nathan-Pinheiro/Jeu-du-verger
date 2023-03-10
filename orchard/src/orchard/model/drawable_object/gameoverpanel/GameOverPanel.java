package orchard.model.drawable_object.gameoverpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import orchard.gui.GUIDirector;
import orchard.interfaces.Showable;

public class GameOverPanel implements Showable {
	
	JLabel gameOverText;
	JLabel commentText;
	BackgroundGameOverPanel backgroundGameOverPanel;
	JButton menuButton;
	
	private static String commentWin = "Bravo ! Quelle fabuleuse victoire !";
	private static String commentLoose = "Dommage, vous avez perdu ...";
	
	private static Color gameOverTextColor = new Color(0,0,128);
	private static Color commentTextWinColor = new Color(0,128,0);
	private static Color commentTextLooseColor = new Color(128,0,0);
	
	public GameOverPanel(int width, int height, GUIDirector guiDirector) {
		
		gameOverText = new JLabel("GAME OVER", JLabel.CENTER);
		gameOverText.setBounds(-height/5, 0, width, height);
		gameOverText.setForeground(gameOverTextColor);
		gameOverText.setFont(new Font("Arial", Font.BOLD, 100));
		
		commentText = new JLabel(commentWin, JLabel.CENTER);
		commentText.setForeground(commentTextWinColor);
		commentText.setBounds(0, 0, width, height);
		commentText.setFont(new Font("Arial", Font.BOLD, 70));
		
		menuButton = new JButton("Menu");
		menuButton.setBounds(width * 1/3, height * 1/2 + height * 1/8, width * 1/3, height * 1/8);
		menuButton.setFont(new Font("Arial", Font.BOLD, 25));
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiDirector.setMenuGUI();
			}
		});
		
		backgroundGameOverPanel = new BackgroundGameOverPanel(height, height);
		
		setVisible(false);
	}
	
	public void setDimension(int width, int height) {
		gameOverText.setBounds(0, -height/5, width, height);
		commentText.setBounds(0, 0, width, height);
		backgroundGameOverPanel.setDimension(width, height);
	}
	
	public void setVisible(Boolean visibility) {
		commentText.setVisible(visibility);
		gameOverText.setVisible(visibility);
		backgroundGameOverPanel.setVisible(visibility);
	}
	
	public void setWin(Boolean isWon) {
		if(isWon) {
			commentText.setText(commentWin);
			commentText.setForeground(commentTextWinColor);
		}
		else {
			commentText.setText(commentLoose);
			commentText.setForeground(commentTextLooseColor);
		}
		commentText.repaint();
	}
	@Override
	public void showOnJFrame(JFrame jframe) {
		jframe.add(gameOverText);
		jframe.add(commentText);
		jframe.add(backgroundGameOverPanel);
	}
}
