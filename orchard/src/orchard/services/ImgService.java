package orchard.services;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import orchard.model.Player;

public class ImgService {
	
    public static JLabel loadImage(String filePath){
        BufferedImage image;
        JLabel imageContainer;
        try{
            image = ImageIO.read(new File("C:\\Users\\barcl\\IUT\\Java\\ORCH\\" + filePath));
            imageContainer = new JLabel(new ImageIcon(image));
            return imageContainer;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }
    
    public static JLabel getPlayerProfil(Player p){
        if(p==null) {
        	return null;
        }
        else {
        	return null;
        }
    }
    
	public static ImageIcon getImageIcon(String filePath, int w, int h){
	
		BufferedImage bi = null;
		Image image = null;
        try{
        	bi = ImageIO.read(new File("C:\\Users\\barcl\\IUT\\Java\\ORCH\\" + filePath));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        if(bi != null)image = bi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        
        return new ImageIcon(image);
	}
	
	public static ImageIcon getImageIcon(BufferedImage bi, int w, int h){
		
		Image image = null;
        if(bi != null)image = bi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        
        return new ImageIcon(image);
	}
    
    public static BufferedImage getImage(String filePath){
        BufferedImage image;
        try{
            image = ImageIO.read(new File("C:\\Users\\barcl\\IUT\\Java\\ORCH\\" + filePath));
            return image;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }
    
    public void drawCenteredImage(Graphics g, String filePath, int x, int y, int width, int height) {
		
		BufferedImage image = ImgService.getImage(filePath);
		g.drawImage(image, x - width/2, y - height/2, width, height, null);
		
	}
    
}
