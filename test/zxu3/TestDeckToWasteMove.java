package zxu3;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestDeckToWasteMove extends TestCase {
	public void testSimple() {
		EagleWing eagleWing =  new EagleWing(); 
		GameWindow gw = Main.generateWindow(eagleWing, Deck.OrderBySuit);
		
		DeckToWasteMove dtwm = new DeckToWasteMove(eagleWing.deck, eagleWing.wastePile); 
		
		assertTrue(dtwm.valid(eagleWing));
		
		dtwm.doMove(eagleWing);
		assertEquals(51, eagleWing.deck.count()); 
		assertEquals(1, eagleWing.wastePile.count()); 
		
		dtwm.undo(eagleWing);
		assertEquals(52, eagleWing.deck.count());
		assertEquals(0, eagleWing.wastePile.count()); 
	}
}
