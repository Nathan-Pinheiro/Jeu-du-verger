package orchard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;

import orchard.interfaces.Showable;
import orchard.model.Player;
import orchard.model.drawable_object.background.GameBackground;
import orchard.model.drawable_object.dice.Dice;
import orchard.model.drawable_object.fruit.Apple;
import orchard.model.drawable_object.fruit.Cherry;
import orchard.model.drawable_object.fruit.Pear;
import orchard.model.drawable_object.fruit.Plum;
import orchard.model.drawable_object.game_profil.GameBackgroundPlayerProfil;
import orchard.model.drawable_object.game_profil.GamePlayerProfil;
import orchard.model.drawable_object.gameoverpanel.GameOverPanel;
import orchard.model.drawable_object.jigsaw.RavenJigsaw;
import orchard.model.drawable_object.tree.AppleTree;
import orchard.model.drawable_object.tree.CherryTree;
import orchard.model.drawable_object.tree.PearTree;
import orchard.model.drawable_object.tree.PlumTree;
import orchard.model.drawable_object.tree.Tree;
import orchard.util.Fruits;
import orchard.util.Position;

public class GameGUI implements Showable{
	
	private final JFrame jframe;
	private Player[] players;
	
	private static int playerProfilMargin = 25;
	private static int playerProfilWidth = 250;
	private static int playerProfilHeight = 175;
	
	private static int treeDistance = 165;
	private static int treeSize = 400;
	
	private static int ravenJigsawSize = 400;
	
	private GameOverPanel gameOverPanel;
	private Dice dice;
	private GamePlayerProfil[] playersProfils;
	private Tree[] trees;
	private RavenJigsaw ravenJigsaw;
	private GameBackground gameBackground;

	private List<Showable> showables = new ArrayList<>();
	
	private Position[] profilPositions = new Position[]{
    		new Position(0,0), 
    		new Position(0,0), 
    		new Position(0,0), 
    		new Position(0,0)
    	};
	
	Position[] treesPositions = new Position[] {
			new Position(0, 0),
			new Position(0, 0),
			new Position(0, 0),
			new Position(0, 0)
	};
	
	private int turn = 0;
	
	private int fruitsAmount = 0;
	private Fruits selectableFruit = null;
	private Tree treeTookenFruit = null;
	private Integer indexTookenFruit = null;

	public GameGUI(GUIDirector jframe, Player[] players) {
		this.jframe = jframe;
		this.players = players;
		
		gameOverPanel = new GameOverPanel(jframe.getWidth(), jframe.getHeight(), jframe);
		dice = new Dice(0, 0, this);
		
		updatePositions();
		
		playersProfils = new GamePlayerProfil[] {
				new GamePlayerProfil(profilPositions[0], playerProfilWidth, playerProfilHeight, players[0]),
				new GamePlayerProfil(profilPositions[1], playerProfilWidth, playerProfilHeight, players[1]),
				new GamePlayerProfil(profilPositions[2], playerProfilWidth, playerProfilHeight, players[2]),
				new GamePlayerProfil(profilPositions[3], playerProfilWidth, playerProfilHeight, players[3])
			};
		
		trees = new Tree[] {
				new PearTree(treesPositions[0], treeSize, this),
				new CherryTree(treesPositions[1], treeSize, this),
				new AppleTree(treesPositions[2], treeSize, this),
				new PlumTree(treesPositions[3], treeSize, this)
			};
		
		ravenJigsaw = new RavenJigsaw(0, 0,ravenJigsawSize, ravenJigsawSize);
		gameBackground = new GameBackground(jframe.getWidth(), jframe.getHeight());
        
		showables.add(gameOverPanel);
		showables.add(dice);
		showables.addAll(Arrays.asList(playersProfils));
		showables.addAll(Arrays.asList(trees));
		showables.add(ravenJigsaw);
		showables.add(gameBackground);
		
        jframe.addComponentListener(new ComponentAdapter() {
        	
            @Override
            public void componentResized(ComponentEvent e) {
            	setJLabelsPositions();
            }
        });
	}

	private void setJLabelsPositions() {
		
		dice.setPosition(new Position((jframe.getWidth() - 14)/2, (jframe.getHeight()- 37)/2));
        ravenJigsaw.setPosition(new Position(jframe.getWidth()/2 - 14 + 7, jframe.getHeight() - ravenJigsawSize/2 - 37 - 14));
        
        updatePositions();
        
        for(int i=0; i < players.length ;i++) {
        	playersProfils[i].setPosition(profilPositions[i]);
        }
        
        for(int i=0; i < players.length ;i++) {
        	trees[i].setPosition(treesPositions[i]);
        	trees[i].updateFruitsPositions();
        }
        
        gameOverPanel.setDimension(jframe.getWidth()-14, jframe.getHeight()-37);
	}

	private void updatePositions() {
		profilPositions = new Position[]{
        		new Position(playerProfilMargin, playerProfilMargin), 
        		new Position((jframe.getWidth() - 14) - playerProfilMargin - playerProfilWidth, playerProfilMargin), 
        		new Position(playerProfilMargin, (jframe.getHeight()- 37) - playerProfilMargin - playerProfilHeight), 
        		new Position((jframe.getWidth() - 14) - playerProfilMargin - playerProfilWidth, (jframe.getHeight()- 37) - playerProfilMargin - playerProfilHeight)
        	};
		
		treesPositions = new Position[]{
	    		new Position(jframe.getWidth()/2 - treeDistance, jframe.getHeight()/2 - treeDistance),
	    		new Position(jframe.getWidth()/2 + treeDistance, jframe.getHeight()/2 - treeDistance),
	   			new Position(jframe.getWidth()/2 - treeDistance * 2, jframe.getHeight()/2 + 2 * treeDistance / 3),
	    		new Position(jframe.getWidth()/2 + treeDistance * 2, jframe.getHeight()/2 + 2 * treeDistance / 3)
	   		};
	}
	
	public void showOnJFrame(JFrame jframe) {
		for(Showable s : showables) {
			s.showOnJFrame(jframe);
		}
		jframe.repaint();
	}
	
	public void collectFruit(Player p, Tree t, int fruitIndex) {
		
		if(t.getFruits()[fruitIndex] instanceof Apple)p.addApple();
		if(t.getFruits()[fruitIndex] instanceof Pear)p.addPear();
		if(t.getFruits()[fruitIndex] instanceof Cherry)p.addCherry();
		if(t.getFruits()[fruitIndex] instanceof Plum)p.addPlum();
		
		playersProfils[turn].updatePlayerScore();
		
		jframe.remove(t.getFruits()[fruitIndex]);
		t.getFruits()[fruitIndex] = null;
		
		fruitsAmount--;
		
		jframe.revalidate();
		jframe.repaint();
		
		if(fruitsAmount <= 0)endTurn();
	}
	
	public void endTurn() {
		
		fruitsAmount = 0;
		selectableFruit = null;
		
		if(ravenJigsaw.isJigsawFinished()) {
			gameOver(false);
		}
		else {
			Boolean gameFinished = true;
			for(Tree t : trees) {
				if(!Boolean.TRUE.equals(t.isTreeEmpty()))gameFinished = false;
			}
			if(gameFinished)gameOver(true);
			else {
				nextTurn();
			}
		}
	}
	
	public void gameOver(Boolean isWon) {
		gameOverPanel.setWin(isWon);
		gameOverPanel.setVisible(true);
	}
	
	public void setupNewGame(Player[] players) {
		
		this.players = players;
		
		Position[] profilPositions = new Position[]{
        		new Position(playerProfilMargin, playerProfilMargin), 
        		new Position((jframe.getWidth() - 14) - playerProfilMargin - playerProfilWidth, playerProfilMargin), 
        		new Position(playerProfilMargin, (jframe.getHeight()- 37) - playerProfilMargin - playerProfilHeight), 
        		new Position((jframe.getWidth() - 14) - playerProfilMargin - playerProfilWidth, (jframe.getHeight()- 37) - playerProfilMargin - playerProfilHeight)
        	};
		
		playersProfils = new GamePlayerProfil[] {
				new GamePlayerProfil(profilPositions[0], playerProfilWidth, playerProfilHeight, players[0]),
				new GamePlayerProfil(profilPositions[1], playerProfilWidth, playerProfilHeight, players[1]),
				new GamePlayerProfil(profilPositions[2], playerProfilWidth, playerProfilHeight, players[2]),
				new GamePlayerProfil(profilPositions[3], playerProfilWidth, playerProfilHeight, players[3])
			};
		
		for(int i=0; i < players.length ;i++) {
        	playersProfils[i].setPosition(profilPositions[i]);
        }
		
		for(Player p : players) {
			if(p!=null)System.out.println(p.getName());
		}
		
		for(Tree t : trees) {
			t.refill();
		}
		
		showables.clear();
		
		showables.add(gameOverPanel);
		showables.add(dice);
		showables.addAll(Arrays.asList(playersProfils));
		showables.addAll(Arrays.asList(trees));
		showables.add(ravenJigsaw);
		showables.add(gameBackground);
		
	}
	
	public void addJigsawPiece() {
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				ravenJigsaw.addAPiece();
				endTurn();
		    }
		});
		
		timer.setRepeats(false);
		timer.start();
	}
	
	private void nextTurn() {
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				if(players.length <= turn + 1 || players[turn + 1] == null) {
					turn = 0;
				}
				else {
					turn++;
				}
				
				dice.showPanel();
		    }
		});
		
		timer.setRepeats(false);
		timer.start();
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public Tree[] getTrees() {
		return trees;
	}

	public int getTurn() {
		return turn;
	}

	public int getFruitsAmount() {
		return fruitsAmount;
	}

	public void setFruitsAmount(int fruitsAmount) {
		this.fruitsAmount = fruitsAmount;
	}

	public Fruits getSelectableFruit() {
		return selectableFruit;
	}
	
	public JFrame getJframe() {
		return jframe;
	}

	public void setSelectableFruit(Fruits selectableFruit) {
		this.selectableFruit = selectableFruit;
		
		if(selectableFruit != Fruits.ALL) {
			for(Tree t : trees) {
				if(t.getFruit() == selectableFruit && Boolean.TRUE.equals(t.isTreeEmpty())) {
					Timer timer = new Timer(1000, new ActionListener() {
						@Override
					    public void actionPerformed(ActionEvent e) {
							nextTurn();
					    }
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		}
	}
	
}
