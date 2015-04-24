package zxu3;

import java.awt.event.MouseEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.view.Widget;
import ks.launcher.Main;
import ks.tests.KSTestCase;
import ks.tests.model.ModelFactory;

public class TestController extends KSTestCase {
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
	
	public void testStartController() {
		MouseEvent press = createPressed (eagleWing, eagleWing.startString, 0, 0);
		eagleWing.startString.getMouseManager().handleMouseEvent(press);
		assertEquals(eagleWing.deck.count(), 30);
	}
	
	public void testDeckController() {
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		ddm.doMove(eagleWing);
		
		MouseEvent press = createPressed (eagleWing, eagleWing.deckView, 0, 0);
		eagleWing.deckView.getMouseManager().handleMouseEvent(press);
		assertEquals(eagleWing.deck.count(), 29);
	} 
	
	public void testTrunkController() {
		StartMove ddm = new StartMove(eagleWing.deck, eagleWing); 
		ddm.doMove(eagleWing);
		
		MouseEvent release = createReleased (eagleWing, eagleWing.trunkPileView, 0, 0);
		eagleWing.trunkPileView.getMouseManager().handleMouseEvent(release);
		assertEquals(eagleWing.trunkPile.count(), 13);
	}
	
	public void testWasteController() {
		ModelFactory.init(eagleWing.deck, "");
		ModelFactory.init(eagleWing.wastePile, "JS KS");
		
		MouseEvent press = createPressed (eagleWing, eagleWing.wastePileView, 0, 0);
		eagleWing.wastePileView.getMouseManager().handleMouseEvent(press);
		assertEquals(eagleWing.wastePile.count(), 0);
		assertEquals(eagleWing.deck.count(), 1);
	}
	
	public void testWingController() {
		ModelFactory.init(eagleWing.wingPile[1], "");
		ModelFactory.init(eagleWing.wingPile[2], "JS");
		MouseEvent press1 = createPressed (eagleWing, eagleWing.wingPileView[1], 0, 0);
		MouseEvent press2 = createPressed (eagleWing, eagleWing.wingPileView[2], 0, 0);
		MouseEvent release1 = createReleased (eagleWing, eagleWing.wingPileView[2], 0, 0);
		MouseEvent release2 = createReleased (eagleWing, eagleWing.wingPileView[2], 0, 0);
		eagleWing.wingPileView[1].getMouseManager().handleMouseEvent(press1);
		eagleWing.wingPileView[1].getMouseManager().handleMouseEvent(release1);
		eagleWing.wingPileView[2].getMouseManager().handleMouseEvent(press2);
		eagleWing.wingPileView[2].getMouseManager().handleMouseEvent(release2);
		assertEquals(eagleWing.wingPile[1].count(), 0);
		assertEquals(eagleWing.wingPile[2].count(), 1);
	}
		
	public void testFoundationController() {
		ModelFactory.init(eagleWing.wingPile[1], "JS");
		ModelFactory.init(eagleWing.foundationPile[1], "10S");
		MouseEvent press1 = createPressed (eagleWing, eagleWing.wingPileView[1], 0, 0);
		MouseEvent release1 = createReleased (eagleWing, eagleWing.foundationPileView[1], 0,0);
		eagleWing.wingPileView[1].getMouseManager().handleMouseEvent(press1);
		eagleWing.foundationPileView[1].getMouseManager().handleMouseEvent(release1);
		assertEquals(eagleWing.wingPile[1].count(), 0);
		assertEquals(eagleWing.foundationPile[1].count(), 2);
	}
		
}
	

