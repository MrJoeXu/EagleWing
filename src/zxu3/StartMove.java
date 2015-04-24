package zxu3;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;

public class StartMove extends Move{
	Deck deck;
	EagleWing theGame; 
	
	public StartMove(Deck deck, EagleWing theGame) {
		this.deck = deck;
		this.theGame = theGame;
	} 
	
	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { return false;}
		
		Card w[] = new Card [9];
		for (int a=1; a<=8; a++) {
			w[a] = deck.get();
			w[a].setFaceUp(true);
		}
		
		
		
		for (int i=1; i<=8; i++) {
			theGame.wingPile[i].add(w[i]);;
		}
		
		Card foundationCard = deck.get();
		theGame.setFoundationCard(foundationCard);
		foundationCard.setFaceUp(true);
		theGame.foundationPile[1].add(foundationCard);
		
		System.out.print(theGame.getFoundationCard().getName());
		
		for (int i = 1; i < 14; i++) {
			Card trunkCard = deck.get();
			trunkCard.setFaceUp(false);
			theGame.trunkPile.add(trunkCard);
		}
		
		theGame.updateNumberCardsLeft(-22);
		
		return true;
	}
 
	@Override
	public boolean undo(Solitaire game) {
		
		for (int i=1; i<=8; i++) {
			deck.add(theGame.wingPile[i].get());
		}
		
		for (int i = 1; i < 14; i++) {
			Card trunkCard = theGame.trunkPile.get();
			deck.add(trunkCard);
		}
		
		Card foundationCard = theGame.foundationPile[1].get();
		foundationCard.setFaceUp(true);
		deck.add(foundationCard);
		
		theGame.updateNumberCardsLeft(+22);
		
		return true; 
		
	}

	@Override
	public boolean valid(Solitaire game) {
		return theGame.foundationPile[1].empty();
	}

}
