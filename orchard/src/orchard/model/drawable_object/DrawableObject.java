package orchard.model.drawable_object;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import orchard.interfaces.Showable;
import orchard.services.ImgService;
import orchard.util.Dimension;
import orchard.util.Position;

public class DrawableObject extends JLabel implements Showable{
	
	public DrawableObject(Position position, Dimension dimension, ImageIcon icon, float alpha) {
		
		setBounds(position.x() - dimension.getWidth() / 2, position.y() - dimension.getHeight() / 2, dimension.getWidth(), dimension.getHeight());
		
		AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        BufferedImage translucentImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = translucentImage.createGraphics();
        g.setComposite(alphaComposite);
        g.drawImage(icon.getImage(), 0, 0, null);
        g.dispose();
        setIcon(new ImageIcon(translucentImage));
	}
	
	public DrawableObject(Position position, Dimension dimension, BufferedImage image, float alpha) {
		this(position, dimension, ImgService.getImageIcon(image, dimension.getWidth(), dimension.getHeight()), alpha);
	}
	
	public DrawableObject(Position position, Dimension dimension, ImageIcon icon) {
		setBounds(position.x() - dimension.getWidth() / 2, position.y() - dimension.getHeight() / 2, dimension.getWidth(), dimension.getHeight());
		setIcon(icon);
	}
	
	public DrawableObject(Position position, Dimension dimension, BufferedImage image) {
		
		this(position, dimension, ImgService.getImageIcon(image, dimension.getWidth(), dimension.getHeight()));
	}
	
	public DrawableObject(int x, int y, int width, int height, BufferedImage imageFile){
		this(new Position(x,y), new Dimension(width, height), imageFile);
	}
	
	public DrawableObject(int x, int y, int width, int height, String imageFile){
		this(new Position(x,y), new Dimension(width, height), ImgService.getImage(imageFile));
	}
	
	public DrawableObject(int width, int height, ImageIcon icon){
		this(new Position(0,0), new Dimension(width, height), icon);
	}
	
	public DrawableObject(int width, int height, BufferedImage imageFile){
		this(new Position(0,0), new Dimension(width, height), imageFile);
	}
	
	public DrawableObject(int x, int y, int width, int height){
		setBounds(x, y, width, height);
	}
	
	public DrawableObject(int width, int height){
		this(0, 0, width, height);
	}
	
	public DrawableObject(){}
	
	public void setIcon(String filePath) {
		setIcon(ImgService.getImageIcon(ImgService.getImage(filePath), getWidth(), getHeight()));
	}
	
	public void setPosition(Position position) {
		setBounds(position.x() - getWidth() / 2, position.y() - getHeight() / 2, getWidth(), getHeight());
	}
	
	public void showOnJFrame(JFrame jframe) {
		jframe.add(this);
	}
}
