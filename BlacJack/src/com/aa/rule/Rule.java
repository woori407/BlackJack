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
		dealer.getCards().add(cardCase.getCardCase().get(0));
		cardCase.getCardCase().remove(0);
		guest.getCards().add(cardCase.getCardCase().get(0));
		cardCase.getCardCase().remove(0);
		dealer.getCards().add(cardCase.getCardCase().get(0));
		cardCase.getCardCase().remove(0);
		guest.getCards().add(cardCase.getCardCase().get(0));
		cardCase.getCardCase().remove(0);
	}

}
