package zxu3;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestWingToFoundationMove extends TestCase {
	public void testSimple() {
		EagleWing eagleWing =  new EagleWing(); 
		GameWindow gw = Main.generateWindow(eagleWing, Deck.OrderBySuit);
		
		StartMove sm = new StartMove(eagleWing.deck, eagleWing);
		Card cardBeingDragged = eagleWing.wingPile1.get(); 
		
		WingToFoundationMove wfm = new WingToFoundationMove(eagleWing.wingPile1, cardBeingDragged, eagleWing.foundationPile1);
		
		sm.doMove(eagleWing);
		assertTrue(wfm.valid(eagleWing));
		
		wfm.doMove(eagleWing);
		assertEquals(0, eagleWing.wingPile1.count());
		assertEquals(2, eagleWing.foundationPile1.count());
		
		
		wfm.undo(eagleWing);
		assertEquals(1, eagleWing.wingPile1.count());
		assertEquals(1, eagleWing.foundationPile1.count());
	}
}
