package zxu3;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.BuildablePile;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
import ks.common.view.CardImages;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.common.view.StringView;
import ks.launcher.Main;

public class EagleWing extends Solitaire {
	
	protected Deck deck;
	protected Pile wingPile1, wingPile2, wingPile3, wingPile4,
				   wingPile5, wingPile6, wingPile7, wingPile8;
	protected Pile foundationPile1, foundationPile2,
				   foundationPile3, foundationPile4;
	protected Pile reservePile, wastePile;
	protected BuildablePile trunkPile;
	
	protected PileView wingPileView1, wingPileView2, wingPileView3, wingPileView4,
				       wingPileView5, wingPileView6, wingPileView7, wingPileView8;
	protected PileView foundationPileView1, foundationPileView2,
					   foundationPileView3, foundationPileView4;
	protected PileView wastePileView;
	protected BuildablePileView trunkPileView;
	protected DeckView deckView;
	
	protected IntegerView scoreView;
	protected IntegerView numLeftView;
	
	protected StringView startString;
	protected StringView scoreString;

	@Override
	public String getName() {
		return "zxu3-EagleWing";
	}

	@Override
	public boolean hasWon() {
		return false;
	}

	@Override
	public void initialize() {
		initializeModel(getSeed());
		initializeView();
		intiializeController();
	}
	

	private void intiializeController() {
		startString.setMouseAdapter(new EagleWingStartController (this, deck));
		startString.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		startString.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		deckView.setMouseAdapter(new EagleWingDeckController (this, deck));
		deckView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		deckView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wastePileView.setMouseAdapter(new EagleWingWastePileController (this, deck, wastePile));
		wastePileView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wastePileView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView1.setMouseAdapter(new EagleWingWingPileController (this, wingPileView1));
		wingPileView1.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView1.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView2.setMouseAdapter(new EagleWingWingPileController (this, wingPileView2));
		wingPileView2.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView2.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView3.setMouseAdapter(new EagleWingWingPileController (this, wingPileView3));
		wingPileView3.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView3.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView4.setMouseAdapter(new EagleWingWingPileController (this, wingPileView4));
		wingPileView4.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView4.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView5.setMouseAdapter(new EagleWingWingPileController (this, wingPileView5));
		wingPileView5.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView5.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView6.setMouseAdapter(new EagleWingWingPileController (this, wingPileView6));
		wingPileView6.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView6.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView7.setMouseAdapter(new EagleWingWingPileController (this, wingPileView7));
		wingPileView7.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView7.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wingPileView8.setMouseAdapter(new EagleWingWingPileController (this, wingPileView8));
		wingPileView8.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wingPileView8.setUndoAdapter (new SolitaireUndoAdapter(this));
		
	}

	private void initializeView() {
		CardImages ci = getCardImages();
		
		deckView = new DeckView (deck);
		deckView.setBounds (679,431, ci.getWidth(), ci.getHeight());
		container.addWidget (deckView);
		
		startString = new StringView("START");
		startString.setFontSize(20);
		startString.setBounds(643,28, 70, 27);
		container.addWidget(startString);
		
		wingPileView1 = new PileView (wingPile1);
		wingPileView1.setBounds(20, 184, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView1);
		
		wingPileView2 = new PileView (wingPile2);
		wingPileView2.setBounds(93, 154, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView2);
		
		wingPileView3 = new PileView (wingPile3);
		wingPileView3.setBounds(166, 124, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView3);
		
		wingPileView4 = new PileView (wingPile4);
		wingPileView4.setBounds(239, 94, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView4);
		
		wingPileView5 = new PileView (wingPile5);
		wingPileView5.setBounds(457, 94, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView5);
		
		wingPileView6 = new PileView (wingPile6);
		wingPileView6.setBounds(530, 123, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView6);
		
		wingPileView7 = new PileView (wingPile7);
		wingPileView7.setBounds(603, 153, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView7);
		
		wingPileView8 = new PileView (wingPile8);
		wingPileView8.setBounds(676, 183, ci.getWidth(), ci.getHeight());
		container.addWidget(wingPileView8);
		
		foundationPileView1 = new PileView (foundationPile1);
		foundationPileView1.setBounds(238, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(foundationPileView1);
		
		foundationPileView2 = new PileView (foundationPile2);
		foundationPileView2.setBounds(311, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(foundationPileView2);
		
		foundationPileView3 = new PileView (foundationPile3);
		foundationPileView3.setBounds(384, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(foundationPileView3);
		
		foundationPileView4 = new PileView (foundationPile4);
		foundationPileView4.setBounds(457, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(foundationPileView4);
		
		wastePileView = new PileView (wastePile);
		wastePileView.setBounds(606, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(wastePileView); 
		
		trunkPileView = new BuildablePileView (trunkPile);
		trunkPileView.setBounds(348, 3, ci.getWidth(), 395);
		container.addWidget(trunkPileView);
		
		scoreView = new IntegerView (this.getScore());
		scoreView.setFontSize(20);
		scoreView.setBounds(121, 24, 73, 27);
		container.addWidget(scoreView);
		
		scoreString = new StringView ("SCORE:");
		scoreString.setFontSize(20);
		scoreString.setBounds(48, 24, 73, 27);
		container.addWidget(scoreString);
		
		numLeftView = new IntegerView (this.getNumLeft());
		numLeftView.setFontSize(20);
		numLeftView.setBounds(702, 530, 23, 27);
		container.addWidget(numLeftView);
	}

	private void initializeModel(int seed) {
		deck = new Deck("deck");
		deck.create(seed);
		model.addElement (deck);   // add to our model (as defined within our superclass).
		
		wingPile1 = new Pile ("wingPile1");
		wingPile2 = new Pile ("wingPile2");
		wingPile3 = new Pile ("wingPile3");
		wingPile4 = new Pile ("wingPile4");
		wingPile5 = new Pile ("wingPile5");
		wingPile6 = new Pile ("wingPile6");
		wingPile7 = new Pile ("wingPile7");
		wingPile8 = new Pile ("wingPile8");
		model.addElement(wingPile1);
		model.addElement(wingPile2);
		model.addElement(wingPile3);
		model.addElement(wingPile4);
		model.addElement(wingPile5);
		model.addElement(wingPile6);
		model.addElement(wingPile7);
		model.addElement(wingPile8);
		
		foundationPile1 = new Pile ("foundationPile1");
		foundationPile2 = new Pile ("foundationPile2");
		foundationPile3 = new Pile ("foundationPile3");
		foundationPile4 = new Pile ("foundationPile4");
		model.addElement(foundationPile1);
		model.addElement(foundationPile2);
		model.addElement(foundationPile3);
		model.addElement(foundationPile4);
		
		reservePile = new Pile("reserve");
		model.addElement(reservePile);
		
		wastePile = new Pile("waste");
		model.addElement(wastePile);
		
		trunkPile = new BuildablePile("trunk");
		model.addElement(trunkPile);
		
		this.updateNumberCardsLeft(52);
		this.updateScore(0); 
	}
	
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new EagleWing(), 1234);
	}
}
