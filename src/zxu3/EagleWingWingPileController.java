package zxu3;

import heineman.klondike.MoveCardToFoundationMove;
import heineman.klondike.MoveWasteToFoundationMove;

import java.awt.event.MouseEvent;

import ks.common.model.BuildablePile;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
import ks.common.view.CardView;
import ks.common.view.ColumnView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class EagleWingWingPileController extends java.awt.event.MouseAdapter {
	PileView src;
	EagleWing theGame;

	public EagleWingWingPileController(EagleWing theGame, PileView src) {
		this.theGame = theGame;
		this.src = src;
	}
	
	public void mousePressed(MouseEvent me) { 
		Container c = theGame.getContainer();
		
		/** Return if there is no card to be chosen. */
		Pile wingPile = (Pile) src.getModelElement();
		if (wingPile.count() == 0) {
			c.releaseDraggingObject();
			return;
		}
	
		CardView cardView = src.getCardViewForTopCard (me);
		
		// an invalid selection of some sort.
		if (cardView == null) { 
			c.releaseDraggingObject();
			return;
		}

		Widget w = c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println ("WastePileController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}
	
		// Tell container which object is being dragged, and where in that widget the user clicked.
		c.setActiveDraggingObject (cardView, me);
		
		// Tell container which source widget initiated the drag
		c.setDragSource (src);

		src.redraw();
	}
	
	
	
}
