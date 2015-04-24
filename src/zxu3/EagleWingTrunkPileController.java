package zxu3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ks.common.view.BuildablePileView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class EagleWingTrunkPileController extends MouseAdapter{
	protected BuildablePileView src;
	protected EagleWing theGame;
	
	public EagleWingTrunkPileController (BuildablePileView trunkPileView, EagleWing theGame) {
		this.src = trunkPileView;
		this.theGame = theGame;
	}
	
	public void mouseReleased(MouseEvent me) {
		
		
		Container c = theGame.getContainer();

		/* Return if there is no card being dragged chosen. */
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
		
		fromWidget.returnWidget(draggingWidget);
		c.releaseDraggingObject();
		c.repaint();
	} 
}
