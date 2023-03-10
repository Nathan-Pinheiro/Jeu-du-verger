package orchard.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import orchard.interfaces.Showable;
import orchard.model.drawable_object.lobby_profil.LobbyPlayerProfil;
import orchard.util.LimitDocument;

public class MenuGUI implements Showable{
	
	GUIDirector jframe;
	
	JButton button;
	
	public MenuGUI(GUIDirector jframe) {
		this.jframe = jframe;
		
		jframe.setLayout(null);
		
		button = new JButton("Jouer");
		button.setBounds(jframe.getWidth() * 1/3, jframe.getHeight() * 1/2 - jframe.getHeight() * 1/8, jframe.getWidth() * 1/3, jframe.getHeight() * 1/4);
		button.setFont(new Font("Arial", Font.BOLD, 25));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.setLobbyGUI();
			}
			
		});
	}

	@Override
	public void showOnJFrame(JFrame jframe) {
		jframe.add(button);
	}
	
	
	
}
