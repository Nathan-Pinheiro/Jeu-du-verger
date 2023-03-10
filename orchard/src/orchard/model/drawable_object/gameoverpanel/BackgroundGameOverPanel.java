package orchard.model.drawable_object.gameoverpanel;

import java.awt.Color;
import java.awt.Graphics;

import orchard.interfaces.Showable;
import orchard.model.drawable_object.DrawableObject;

public class BackgroundGameOverPanel extends DrawableObject{
	
	private static int margin = 35;
	
	private static Color backgroundColor = new Color(225, 198, 153);
	
	public BackgroundGameOverPanel(int width, int height) {
		super(margin, margin, width - 2*margin, height- 2*margin);
	}
	
	public void setDimension(int width, int height) {
		setBounds(margin, margin, width - 2*margin, height- 2*margin);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(backgroundColor);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		
	}
}
