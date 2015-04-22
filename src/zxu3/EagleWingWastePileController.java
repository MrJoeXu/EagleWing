package zxu3;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

public class EagleWingWastePileController extends SolitaireReleasedAdapter {
	protected EagleWing theGame;
	protected Deck deck;
	protected Pile wastePile;
	
	public EagleWingWastePileController(EagleWing theGame, Deck deck, Pile wastePile) {
		super(theGame);
		
		this.theGame = theGame;
		this.deck = deck;
		this.wastePile = wastePile;
	}

	public void mousePressed (java.awt.event.MouseEvent me) {

		// Attempting a DealFourCardMove
		
		Move m = new RetockDeckMove (theGame.wastePile, theGame.deck);
		if (m.doMove(theGame)) {
			theGame.pushMove (m);     // Successful DealFour Move
			theGame.refreshWidgets(); // refresh updated widgets.
		}
	}
	
	
	
	

}
