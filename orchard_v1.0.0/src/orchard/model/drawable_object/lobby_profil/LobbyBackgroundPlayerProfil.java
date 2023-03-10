package orchard.model.drawable_object.lobby_profil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;

import orchard.model.drawable_object.DrawableObject;
import orchard.util.Position;

public class LobbyBackgroundPlayerProfil extends DrawableObject{
	
	private Boolean isPlayerNull;
	
	private static int crossLineSize = 13;
	private static int crossMarginFromCorner = 35;
	
	private static Color profilEmptyBackgroundColor = new Color(153, 86, 0);
	private static Color profilBackgroundColor = new Color(113, 36, 0);
	private static Color profilEmptyCrossColor = new Color(123, 46, 0);
	
	public LobbyBackgroundPlayerProfil(int x, int y, int width, int height, Boolean isPlayerNull) {
		super(x, y, width, height);
		this.isPlayerNull = isPlayerNull;
	}
	
	@Override
	public void setPosition(Position position) {
		setBounds(position.x(), position.y(), getWidth(), getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(isPlayerNull) {
			g.setColor(profilEmptyBackgroundColor);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(profilEmptyCrossColor);
            g2.setStroke(new BasicStroke(15));
            g2.draw(new Line2D.Float(crossMarginFromCorner, crossMarginFromCorner, getWidth() - crossMarginFromCorner, getHeight() - crossMarginFromCorner));
            g2.draw(new Line2D.Float(getWidth() - crossMarginFromCorner, crossMarginFromCorner, crossMarginFromCorner, getHeight() - crossMarginFromCorner));
		}
		else {
			g.setColor(profilBackgroundColor);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
			
		}
	}
}
