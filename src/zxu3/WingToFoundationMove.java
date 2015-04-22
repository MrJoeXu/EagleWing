package zxu3;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;

public class WingToFoundationMove extends Move{
	protected Pile wingPile;
	protected Pile foundationPile;
	protected Card cardBeingDragged;
	
	public WingToFoundationMove (Pile from, Card cardBeingDragged, Pile to) {
		this.wingPile = from;
		this.cardBeingDragged = cardBeingDragged;
		this.foundationPile = to;
		
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
		wingPile.add(foundationPile.get());
		return true;
	}
	
	@Override
	public boolean valid(Solitaire game) {
		if (wingPile.empty()) {return false;}
		
		Card topFoundationCard = foundationPile.peek();
		if (cardBeingDragged.sameSuit(topFoundationCard)) {game.
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
