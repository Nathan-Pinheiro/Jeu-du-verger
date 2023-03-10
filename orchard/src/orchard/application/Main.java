package orchard.application;

import javax.swing.SwingUtilities;

import orchard.gui.GUIDirector;

public class Main {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
                GUIDirector guiDirector = new GUIDirector();
                
            }
        });
    }
}
