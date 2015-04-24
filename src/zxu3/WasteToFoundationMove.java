package zxu3;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;

public class WasteToFoundationMove extends Move{
	protected Pile wastePile;
	protected Pile foundationPile;
	protected Card cardBeingDragged;
	protected EagleWing theGame;
	
	public WasteToFoundationMove (Pile from, Card cardBeingDragged, Pile to, EagleWing theGame) {
		this.wastePile = from;
		this.cardBeingDragged = cardBeingDragged;
		this.foundationPile = to;
		this.theGame = theGame;
		
	}
	@Override
	public boolean doMove(Solitaire game) {
		if (!this.valid(game)) { return false; };
		
		foundationPile.add(cardBeingDragged);
		game.updateScore(+100);
		return true;
	}
	@Override
	public boolean undo(Solitaire game) {
		wastePile.add(foundationPile.get());
		game.updateScore(-100);
		return true;
	}
	
	@Override
	public boolean valid(Solitaire game) {
		//if (wingPile.empty()) {return false;}
		
		Card topFoundationCard = foundationPile.peek();
		//System.out.print(topFoundationCard.getName());
		if (foundationPile.empty()) {
			if (cardBeingDragged.sameRank(theGame.getFoundationCard())) {
				return true;
			}
		}
		else if (cardBeingDragged.sameSuit(topFoundationCard)) {
			if (cardBeingDragged.getRank() != 1 && cardBeingDragged.compareTo(topFoundationCard) == 1){
				return true;
			}
			
			if (cardBeingDragged.getRank() == 1 && topFoundationCard.getRank() == 13) {
				return true; 
			}
		}
		return false;
	}
	
	
}