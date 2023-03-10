package orchard.model.drawable_object.fruit;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.tree.Tree;
import orchard.services.ImgService;
import orchard.util.Fruits;

public class Plum extends Fruit {

	public Plum(GameGUI gameDirector, Tree tree, int treeIndex) {
		super(ImgService.getImage("resources\\plums.png"), gameDirector, tree, treeIndex, Fruits.PLUM);
	}
	
	@Override
	public String toString() {
		return "Plums";
	}
	
}
