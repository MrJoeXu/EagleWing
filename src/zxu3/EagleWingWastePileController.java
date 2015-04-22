package zxu3;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class EagleWingWastePileController extends SolitaireReleasedAdapter {
	protected EagleWing theGame;
	protected Deck deck;
	protected PileView wastePileView;
	
	public EagleWingWastePileController(EagleWing theGame, Deck deck, PileView wastePileView) {
		super(theGame);
		
		this.theGame = theGame;
		this.deck = deck;
		this.wastePileView = wastePileView;
	}


	public void mousePressed (java.awt.event.MouseEvent me) {

		Container c = theGame.getContainer();
		Pile wastePile = (Pile) wastePileView.getModelElement();
		if (wastePile.count() == 0) {
			c.releaseDraggingObject();
			return;
		}
		CardView cardView = wastePileView.getCardViewForTopCard (me);
		if (cardView == null) { 
			c.releaseDraggingObject();
			return;
		}
		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println ("WastePileController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}
		
		c.setActiveDraggingObject (cardView, me);
		
		// Tell container which source widget initiated the drag
		c.setDragSource (wastePileView);

		wastePileView.redraw();;
		
		
		Move m = new RetockDeckMove (theGame.wastePile, theGame.deck);
		if (m.doMove(theGame)) {
			theGame.pushMove (m);     // Successful DealFour Move
			theGame.refreshWidgets(); // refresh updated widgets.
		}
		
		
		
	}
	
	
	
	

}
