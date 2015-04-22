package zxu3;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestStartMove extends TestCase {
	public void testSimple() {
		EagleWing eagleWing =  new EagleWing(); 
		GameWindow gw = Main.generateWindow(eagleWing, Deck.OrderBySuit);
		
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		
		assertTrue(ddm.valid(eagleWing));
		
		ddm.doMove(eagleWing);
		assertEquals(30, eagleWing.deck.count()); 
		
		ddm.undo(eagleWing);
		assertEquals(52, eagleWing.deck.count());
	}
}
