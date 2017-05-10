package com.aa.player;

import java.util.ArrayList;

import com.aa.card.CardOne;

public abstract class Player implements InterfacePlayer{
	protected ArrayList<CardOne> cards;
	
	public ArrayList<CardOne> getCards() {
		return cards;
	}

	public void setCards(CardOne card) {
		this.cards.add(card);
	}

//	public abstract int nextAction();
}
