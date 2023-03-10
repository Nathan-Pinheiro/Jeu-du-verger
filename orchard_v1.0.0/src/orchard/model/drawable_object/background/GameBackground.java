package orchard.model.drawable_object.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import orchard.interfaces.Showable;

public class GameBackground extends JPanel implements Showable{

	private static final long serialVersionUID = 1L;
	private static int jigsawSize = 200;
	private static int jigsawMarginSize = 15;
	
	private int width;
	private int height;
	
	public GameBackground(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
		System.out.println("created");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color backgroundColor = new Color(200, 175, 38);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		int [] polx = {width/2 - jigsawSize - jigsawMarginSize, width/2, width/2 + jigsawSize + jigsawMarginSize, width/2};
		int [] poly = {width - jigsawSize - jigsawMarginSize, height - (jigsawSize + jigsawMarginSize) * 2, height - jigsawSize - jigsawMarginSize, height};
		Polygon losange = new Polygon(polx, poly, 4);
		
		Color jigsawBackgroundColor = new Color(113, 36, 0);
		g.setColor(jigsawBackgroundColor);
		g.fillPolygon(losange);
		
		System.out.println("painted");
	}

	public void showOnJFrame(JFrame jframe) {
		jframe.add(this);
		paintImmediately(0, 0, width, height);
		System.out.println("added");
	}
	
}
