package orchard.model.drawable_object.fruit;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.tree.Tree;
import orchard.services.ImgService;
import orchard.util.Fruits;

public class Apple extends Fruit{

	public Apple(GameGUI gameDirector, Tree tree, int treeIndex) {
		super(ImgService.getImage("resources\\apple.png"), gameDirector, tree, treeIndex, Fruits.APPLE);
	}
	
	@Override
	public String toString() {
		return "Apple";
	}
}
