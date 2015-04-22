package zxu3;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.BuildablePile;
import ks.common.model.Card;
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
	protected Pile wingPile[] = new Pile [9]; 
	protected Pile foundationPile[] = new Pile [5];
	protected Pile reservePile, wastePile;
	protected BuildablePile trunkPile;
	
	protected PileView wingPileView[] = new PileView [9];
	protected PileView foundationPileView[] = new PileView[5];
	protected PileView wastePileView;
	protected BuildablePileView trunkPileView;
	protected DeckView deckView;
	
	protected IntegerView scoreView;
	protected IntegerView numLeftView;
	
	protected StringView startString;
	protected StringView scoreString;
	
	protected Card foundationCard;

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
		
		for (int i=1; i<=8; i++) {
			wingPileView[i].setMouseAdapter(new EagleWingWingPileController (this, wingPileView[i]));
			wingPileView[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			wingPileView[i].setUndoAdapter (new SolitaireUndoAdapter(this));
		}
		
		wastePileView.setMouseAdapter(new EagleWingWastePileController (this, deck, wastePileView));
		wastePileView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wastePileView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		for (int i=1; i<=4; i++) {
			foundationPileView[i].setMouseAdapter(new EagleWingFoundationController (this, foundationPileView[i]));
			foundationPileView[i].setMouseMotionAdapter(new SolitaireMouseMotionAdapter(this));
			foundationPileView[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}

		
		
	}

	private void initializeView() {
		CardImages ci = getCardImages();
		
		deckView = new DeckView (deck);
		deckView.setBounds (679,431, ci.getWidth(), ci.getHeight());
		container.addWidget (deckView);
		
		startString = new StringView("START");
		startString.setFontSize(20);
		startString.setBounds(643,10, 70, 27);
		container.addWidget(startString);
		
		for (int f=1; f<=4; f++) {
			wingPileView[f] = new PileView (wingPile[f]);
			wingPileView[f].setBounds(20+(f-1)*73, 184-(f-1)*30, ci.getWidth(), ci.getHeight());
			container.addWidget(wingPileView[f]);
		}
		
		for (int g=5; g<=8; g++) {
			wingPileView[g] = new PileView (wingPile[g]);
			wingPileView[g].setBounds(457+(g-5)*73, 94+(g-5)*30, ci.getWidth(), ci.getHeight());
			container.addWidget(wingPileView[g]);
			
		}
		
		for (int i=1; i<=4; i++) {
			foundationPileView[i] = new PileView (foundationPile[i]);
			foundationPileView[i].setBounds(238+(i-1)*73, 431, ci.getWidth(), ci.getHeight());
			container.addWidget(foundationPileView[i]);
		}
		
		wastePileView = new PileView (wastePile);
		wastePileView.setBounds(606, 431, ci.getWidth(), ci.getHeight());
		container.addWidget(wastePileView); 
		
		trunkPileView = new BuildablePileView (trunkPile);
		trunkPileView.setBounds(348, 75, ci.getWidth(), 200);
		container.addWidget(trunkPileView);
		
		scoreView = new IntegerView (this.getScore());
		scoreView.setFontSize(20);
		scoreView.setBounds(408, 10, 73, 27);
		container.addWidget(scoreView);
		
		scoreString = new StringView ("SCORE:");
		scoreString.setFontSize(20);
		scoreString.setBounds(335, 10, 73, 27);
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
		
		for (int i=1; i<=8; i++) {
			wingPile[i] = new Pile ("wingPile"+i);
			model.addElement(wingPile[i]);
		}

		
		for (int b=1; b<=4; b++) {
			foundationPile[b] = new Pile ("foundationPile" + b);
			model.addElement(foundationPile[b]);
		}
		
		reservePile = new Pile("reserve");
		model.addElement(reservePile);
		
		wastePile = new Pile("waste");
		model.addElement(wastePile);
		
		trunkPile = new BuildablePile("trunk");
		model.addElement(trunkPile);
		
		this.updateNumberCardsLeft(52);
		this.updateScore(0); 
	}
	
	public boolean isWingPile (Pile p) {
		for (int i=1; i<=8; i++) {
			if (wingPile[i] == p && p != wastePile) {return true;}
		}
		return false;
	}
	
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new EagleWing(), 1234);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Pile[] getWingPile() {
		return wingPile;
	}

	public void setWingPile(Pile[] wingPile) {
		this.wingPile = wingPile;
	}

	public Pile[] getFoundationPile() {
		return foundationPile;
	}

	public void setFoundationPile(Pile[] foundationPile) {
		this.foundationPile = foundationPile;
	}

	public Pile getReservePile() {
		return reservePile;
	}

	public void setReservePile(Pile reservePile) {
		this.reservePile = reservePile;
	}

	public Pile getWastePile() {
		return wastePile;
	}

	public void setWastePile(Pile wastePile) {
		this.wastePile = wastePile;
	}

	public BuildablePile getTrunkPile() {
		return trunkPile;
	}

	public void setTrunkPile(BuildablePile trunkPile) {
		this.trunkPile = trunkPile;
	}

	public PileView[] getWingPileView() {
		return wingPileView;
	}

	public void setWingPileView(PileView[] wingPileView) {
		this.wingPileView = wingPileView;
	}

	public PileView[] getFoundationPileView() {
		return foundationPileView;
	}

	public void setFoundationPileView(PileView[] foundationPileView) {
		this.foundationPileView = foundationPileView;
	}

	public PileView getWastePileView() {
		return wastePileView;
	}

	public void setWastePileView(PileView wastePileView) {
		this.wastePileView = wastePileView;
	}

	public BuildablePileView getTrunkPileView() {
		return trunkPileView;
	}

	public void setTrunkPileView(BuildablePileView trunkPileView) {
		this.trunkPileView = trunkPileView;
	}

	public DeckView getDeckView() {
		return deckView;
	}

	public void setDeckView(DeckView deckView) {
		this.deckView = deckView;
	}

	public IntegerView getScoreView() {
		return scoreView;
	}

	public void setScoreView(IntegerView scoreView) {
		this.scoreView = scoreView;
	}

	public IntegerView getNumLeftView() {
		return numLeftView;
	}

	public void setNumLeftView(IntegerView numLeftView) {
		this.numLeftView = numLeftView;
	}

	public StringView getStartString() {
		return startString;
	}

	public void setStartString(StringView startString) {
		this.startString = startString;
	}

	public StringView getScoreString() {
		return scoreString;
	}

	public void setScoreString(StringView scoreString) {
		this.scoreString = scoreString;
	}

	public Card getFoundationCard() {
		return foundationCard;
	}

	public void setFoundationCard(Card foundationCard) {
		this.foundationCard = foundationCard;
	}

}
