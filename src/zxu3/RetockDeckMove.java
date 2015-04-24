package zxu3;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

public class RetockDeckMove extends Move{
	Pile wastePile;
	Deck deck;
	
	public RetockDeckMove (Pile from, Deck to) {
		this.wastePile = from;
		this.deck = to;
	}
	@Override
	public boolean doMove(Solitaire game) {
		if (!this.valid(game)) { return false; }
		int wastePileSize = wastePile.count();
		while (deck.count() != wastePileSize) {
			Card wasteCard = wastePile.get();
			wasteCard.setFaceUp(true);
			deck.add(wasteCard);
		}

		game.updateNumberCardsLeft(+deck.count());
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		while (!deck.empty()) {
			Card wasteCard = deck.get();
			wastePile.add(wasteCard);
		}
		game.updateNumberCardsLeft(-wastePile.count()); 
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		return deck.empty();
	}

}
