package orchard.model.drawable_object.game_profil;

import javax.swing.JFrame;

import orchard.model.drawable_object.DrawableObject;
import orchard.services.ImgService;
import orchard.util.Position;

public class Basket extends DrawableObject {
	
	public Basket(Position p, int size){
		super(p.x(), p.y(), size, size, ImgService.getImage("resources\\basket.png"));
	}
	
	public Basket(int x, int y, int size){
		super(x, y, size, size,ImgService.getImage("resources\\basket.png"));
	}
}
