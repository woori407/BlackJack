package com.aa.rule;

import com.aa.card.CardCase;
import com.aa.player.Dealer;
import com.aa.player.Guest;

public class Rule {
	
	Dealer dealer;
	Guest guest;
	CardCase cardCase;
	
	public Rule(){
		dealer = new Dealer();
		guest = new Guest();
		cardCase = new CardCase();
	}
	
	public void dealInitialCards(){
		dealer.setCards(cardCase.getCardCase().get(0));
		cardCase.remove(0);
		guest.setCards(cardCase.getCardCase().get(0));
		cardCase.remove(0);
		dealer.setCards(cardCase.getCardCase().get(0));
		cardCase.remove(0);
		guest.setCards(cardCase.getCardCase().get(0));
		cardCase.remove(0);
	}

}
