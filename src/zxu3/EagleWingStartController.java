package zxu3;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;

public class EagleWingStartController extends SolitaireReleasedAdapter {
	/** The game. */
	protected EagleWing theGame;


	/** The Deck of interest. */
	protected Deck deck;

	public EagleWingStartController(EagleWing theGame, Deck deck) {
		super(theGame);

		this.theGame = theGame;
		this.deck = deck;
	}

	/**
	 * Coordinate reaction to the beginning of a Drag Event. In this case,
	 * no drag is ever achieved, and we simply deal upon the pres.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {

		// Attempting a DealFourCardMove
		Move m = new StartMove (deck, theGame);
		if (m.doMove(theGame)) {
			theGame.pushMove (m);     // Successful DealFour Move
			theGame.refreshWidgets(); // refresh updated widgets.
		}
	}

}
