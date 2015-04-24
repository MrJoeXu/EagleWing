package zxu3;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import ks.tests.KSTestCase;
import ks.tests.model.ModelFactory;

public class TestMoves extends KSTestCase {
	
	EagleWing eagleWing;
	GameWindow gw;
	
	@Override
	protected void setUp() {
		eagleWing = new EagleWing();
		gw = Main.generateWindow(eagleWing, Deck.OrderBySuit);
	}
	
	@Override
	protected void tearDown() {
		gw.dispose();
	}
	
	public void testStartMove() {
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		
		assertTrue(ddm.valid(eagleWing)); 

		ddm.doMove(eagleWing);
		assertEquals(30, eagleWing.deck.count()); 
		assertEquals(1, eagleWing.foundationPile[1].count());
		
		ddm.undo(eagleWing);
		assertEquals(52, eagleWing.deck.count());
	}

	public void testDeckToWasteMove() {
		
		DeckToWasteMove dtwm = new DeckToWasteMove(eagleWing.deck, eagleWing.wastePile); 
		
		assertTrue(dtwm.valid(eagleWing));
		
		dtwm.doMove(eagleWing);
		assertEquals(51, eagleWing.deck.count()); 
		assertEquals(1, eagleWing.wastePile.count()); 
		
		dtwm.undo(eagleWing);
		assertEquals(52, eagleWing.deck.count());
		assertEquals(0, eagleWing.wastePile.count()); 
	}
	
	public void testRetockDeckMove() {
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		DeckToWasteMove dtwm = new DeckToWasteMove(eagleWing.deck, eagleWing.wastePile);
		RetockDeckMove rdm = new RetockDeckMove(eagleWing.wastePile, eagleWing.deck); 
		
		
		ddm.doMove(eagleWing);
		
		while (!eagleWing.deck.empty()) {
			dtwm.doMove(eagleWing);
		}
		
		assertTrue(rdm.valid(eagleWing));

		rdm.doMove(eagleWing);
		assertEquals(30, eagleWing.deck.count()); 
		
		rdm.undo(eagleWing);
		assertEquals(0, eagleWing.deck.count()); 
	}
	
	public void testWingsToFoundation() {	
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		ddm.doMove(eagleWing);
		
		ModelFactory.init(eagleWing.wingPile[1], "JS");
		ModelFactory.init(eagleWing.foundationPile[1], "10S");
		ModelFactory.init(eagleWing.wingPile[2], "AC");
		ModelFactory.init(eagleWing.foundationPile[2], "KC");
		Card cardBeingDragged1 = eagleWing.wingPile[1].peek();
		Card cardBeingDragged2 = eagleWing.wingPile[2].peek();	
		
		WingToFoundationMove wtfm1 = new WingToFoundationMove(eagleWing.wingPile[1], cardBeingDragged1, eagleWing.foundationPile[1], eagleWing, eagleWing.trunkPile);
		assertTrue(wtfm1.valid(eagleWing)); 	
		wtfm1.doMove(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 100);
		assertEquals(eagleWing.trunkPile.count(), 12);
		wtfm1.undo(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 0);
		assertEquals(eagleWing.trunkPile.count(), 13);
	
		WingToFoundationMove wtfm2 = new WingToFoundationMove(eagleWing.wingPile[2], cardBeingDragged2, eagleWing.foundationPile[2], eagleWing, eagleWing.trunkPile);
		assertTrue(wtfm2.valid(eagleWing));  
		wtfm2.doMove(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 100);
		assertEquals(eagleWing.trunkPile.count(), 12);
		wtfm2.undo(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 0);
		assertEquals(eagleWing.trunkPile.count(), 13); 
	}
	
	public void testWasteToFoundationMove() {
		ModelFactory.init(eagleWing.wastePile, "QS");
		ModelFactory.init(eagleWing.foundationPile[1], "JS");
		Card cardBeingDragged1 = eagleWing.wastePile.get();
		
		WasteToFoundationMove wm1 = new WasteToFoundationMove(eagleWing.wastePile, cardBeingDragged1, eagleWing.foundationPile[1], eagleWing);
		assertTrue(wm1.valid(eagleWing));
		wm1.doMove(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 100);
		assertEquals(eagleWing.wastePile.count(), 0);
		assertEquals(eagleWing.foundationPile[1].count(), 2);
		wm1.undo(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 0);
		assertEquals(eagleWing.wastePile.count(), 1);
		assertEquals(eagleWing.foundationPile[1].count(), 1);
		
		ModelFactory.init(eagleWing.wastePile, "AS");
		ModelFactory.init(eagleWing.foundationPile[1], "KS");
		Card cardBeingDragged2 = eagleWing.wastePile.get();
		
		WasteToFoundationMove wm2 = new WasteToFoundationMove(eagleWing.wastePile, cardBeingDragged2, eagleWing.foundationPile[1], eagleWing);
		assertTrue(wm2.valid(eagleWing));
		wm2.doMove(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 100);
		assertEquals(eagleWing.wastePile.count(), 0);
		assertEquals(eagleWing.foundationPile[1].count(), 2);
		wm2.undo(eagleWing);
		assertEquals(eagleWing.getScoreValue(), 0);
		assertEquals(eagleWing.wastePile.count(), 1);
		assertEquals(eagleWing.foundationPile[1].count(), 1);
		
	}

}