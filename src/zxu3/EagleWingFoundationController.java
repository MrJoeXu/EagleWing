package zxu3;

import heineman.Klondike;
import heineman.klondike.MoveCardToFoundationMove;
import heineman.klondike.MoveWasteToFoundationMove;

import java.awt.event.MouseAdapter;
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

public class EagleWingFoundationController extends MouseAdapter{
	protected EagleWing theGame;
	protected PileView src;
	
	public EagleWingFoundationController(EagleWing theGame, PileView foundation) {
		super();

		this.theGame = theGame;
		this.src = foundation;
	}
	
	public void mouseReleased(MouseEvent me) {
		
		
		Container c = theGame.getContainer();

		/** Return if there is no card being dragged chosen. */
		Widget draggingWidget = c.getActiveDraggingObject();
		if (draggingWidget == Container.getNothingBeingDragged()) {
			System.err.println ("FoundationController::mouseReleased() unexpectedly found nothing being dragged.");
			c.releaseDraggingObject();		
			return;
		}
		
		/** Recover the from BuildablePile OR waste Pile */
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("FoundationController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}

		// Determine the To Pile
		Pile foundation = (Pile) src.getModelElement();
		
		// Determine the From Pile
		Pile fromPile = (Pile) fromWidget.getModelElement();
		
		// Determine the Card
		CardView cardView = (CardView) draggingWidget;
		Card theCard = (Card) cardView.getModelElement();
		if (theCard == null) {
			System.err.println ("FoundationController::mouseReleased(): somehow CardView model element is null.");
			c.releaseDraggingObject();
			return;
		}

		if (theGame.isWingPile(fromPile)) {
			//System.out.print("trueWing/" +"\n");
			Move m1 = new WingToFoundationMove (fromPile, theCard, foundation, theGame, theGame.getTrunkPile());
			//System.out.print(fromPile.getName()+"\n");
			//System.out.print(foundation.getName() +"\n");

			//if (theGame.getFoundationCard().getRank() == theCard.getRank()) {
				
			//}
			if (m1.doMove (theGame)) {
				// Success
				//System.out.print("success\n");
				theGame.pushMove (m1);
				theGame.refreshWidgets();
			} else {
				//System.out.print("fail\n");
				fromWidget.returnWidget (draggingWidget);
			}
		}
		else {
			Move m2 = new WasteToFoundationMove (fromPile, theCard, foundation, theGame);
			if (m2.doMove(theGame)){
				theGame.pushMove(m2);
				theGame.refreshWidgets();
			} else {
				//System.out.print("fail\n");
				fromWidget.returnWidget (draggingWidget);
			}
		}

		// Ahhhh. Instead of dealing with multiple 'instanceof' difficulty, why don't we allow
		// for multiple controllers to be set on the same widget? Each will be invoked, one
		// at a time, until someone returns TRUE (stating that they are processing the event).
		// Then we have controllers for each MOVE TYPE, not just for each entity. In this way,
		// I wouldn't have to convert the CardView from wastePile into a ColumnView. I would
		// still have to do some sort of instanceOf check, however, to validate: But if the
		// instanceof failed, the controller could safely return and say NOT ME! See! There
		// always is a way to avoid layered if statements in OO.

		// release the dragging object, (this will reset dragSource)
		c.releaseDraggingObject();
		
		// finally repaint
		c.repaint();
	}
}
