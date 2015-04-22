package zxu3;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

public class DeckToWasteMove extends Move {
	Deck deck;
	Pile wastePile; 
		
	public DeckToWasteMove (Deck from, Pile to) {
		this.deck = from;
		this.wastePile = to;
	} 

	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { return false;}
		Card wasteCard = deck.get();
		wasteCard.setFaceUp(true);
		wastePile.add(wasteCard);
		game.updateNumberCardsLeft(-1);
		
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		Card wasteCard = wastePile.get();
		deck.add(wasteCard);
		game.updateNumberCardsLeft(+1);
		
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		return !deck.empty();
	}
}