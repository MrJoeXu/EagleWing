package zxu3;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestRetockDeckMove extends TestCase {
	public void testSimple() {
		EagleWing eagleWing =  new EagleWing(); 
		GameWindow gw = Main.generateWindow(eagleWing, Deck.OrderBySuit);
		
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		DeckToWasteMove dtwm = new DeckToWasteMove(eagleWing.deck, eagleWing.wastePile);
		RetockDeckMove rdm = new RetockDeckMove(eagleWing.wastePile, eagleWing.deck); 
		
		
		ddm.doMove(eagleWing);
		//System.out.print(eagleWing.deck.count());
		while (!eagleWing.deck.empty()) {
			dtwm.doMove(eagleWing);
		}
		//System.out.print(eagleWing.deck.count());
		//assertEquals(0, eagleWing.deck.count());
		
		assertTrue(rdm.valid(eagleWing));
		
		//System.out.print(eagleWing.deck.count());
		//System.out.print(eagleWing.wastePile.count());
		rdm.doMove(eagleWing);
		//System.out.print(eagleWing.deck.count());
		//System.out.print(eagleWing.wastePile.count());
		assertEquals(30, eagleWing.deck.count()); 
		
		rdm.undo(eagleWing);
		assertEquals(0, eagleWing.deck.count()); 
	}
}
