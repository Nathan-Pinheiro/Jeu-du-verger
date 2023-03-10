package orchard.model.drawable_object.fruit;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.tree.Tree;
import orchard.services.ImgService;
import orchard.util.Fruits;

public class Cherry extends Fruit{

	public Cherry(GameGUI gameDirector, Tree tree, int treeIndex) {
		super(ImgService.getImage("resources\\cherry.png"), gameDirector, tree, treeIndex, Fruits.CHERRY);
	}
	
	@Override
	public String toString() {
		return "Cherry";
	}
	
}
