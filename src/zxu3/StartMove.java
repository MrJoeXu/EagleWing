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
		
		Card w1 = deck.get();
		Card w2 = deck.get();
		Card w3 = deck.get();
		Card w4 = deck.get();
		Card w5 = deck.get();
		Card w6 = deck.get();
		Card w7 = deck.get();
		Card w8 = deck.get();
		
		w1.setFaceUp(true);
		w2.setFaceUp(true);
		w3.setFaceUp(true);
		w4.setFaceUp(true);
		w5.setFaceUp(true);
		w6.setFaceUp(true);
		w7.setFaceUp(true);
		w8.setFaceUp(true);
		
		theGame.wingPile1.add(w1);
		theGame.wingPile2.add(w2);
		theGame.wingPile3.add(w3);
		theGame.wingPile4.add(w4);
		theGame.wingPile5.add(w5);
		theGame.wingPile6.add(w6);
		theGame.wingPile7.add(w7);
		theGame.wingPile8.add(w8);
		
		Card foundationCard = deck.get();
		foundationCard.setFaceUp(true);
		theGame.foundationPile1.add(foundationCard);
		
		for (int i = 1; i < 14; i++) {
			Card trunkCard = deck.get();
			theGame.trunkPile.add(trunkCard);
		}
		
		theGame.updateNumberCardsLeft(-22);
		
		return true;
	}
 
	@Override
	public boolean undo(Solitaire game) {
		
		deck.add(theGame.wingPile1.get());
		deck.add(theGame.wingPile2.get());
		deck.add(theGame.wingPile3.get());
		deck.add(theGame.wingPile4.get());
		deck.add(theGame.wingPile5.get());
		deck.add(theGame.wingPile6.get());
		deck.add(theGame.wingPile7.get());
		deck.add(theGame.wingPile8.get());
		
		for (int i = 1; i < 14; i++) {
			Card trunkCard = theGame.trunkPile.get();
			deck.add(trunkCard);
		}
		
		Card foundationCard = theGame.foundationPile1.get();
		foundationCard.setFaceUp(true);
		deck.add(foundationCard);
		
		theGame.updateNumberCardsLeft(+22);
		
		return true;
		
	}

	@Override
	public boolean valid(Solitaire game) {
		return theGame.foundationPile1.empty();
	}

}
