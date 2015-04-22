package zxu3;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;

public class EagleWingDeckController extends SolitaireReleasedAdapter {
	protected EagleWing theGame;
	protected Deck deck;
	
	public EagleWingDeckController(EagleWing theGame, Deck deck) {
		super(theGame);
		
		this.theGame = theGame;
		this.deck = deck;
	}

	public void mousePressed (java.awt.event.MouseEvent me) {

		// Attempting a DealFourCardMove
		
		Move m = new DeckToWasteMove (theGame.deck, theGame.wastePile);
		if (m.doMove(theGame)) {
			theGame.pushMove (m);     // Successful DealFour Move
			theGame.refreshWidgets(); // refresh updated widgets.
		}
	}
}
