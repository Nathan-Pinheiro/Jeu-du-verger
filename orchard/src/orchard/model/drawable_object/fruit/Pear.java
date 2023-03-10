package orchard.model.drawable_object.fruit;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.tree.Tree;
import orchard.services.ImgService;
import orchard.util.Fruits;

public class Pear extends Fruit {

	public Pear(GameGUI gameDirector, Tree tree, int treeIndex) {
		super(ImgService.getImage("resources\\pear.png"), gameDirector, tree, treeIndex, Fruits.PEAR);
	}

	@Override
	public String toString() {
		return "Pear";
	}
	
}
