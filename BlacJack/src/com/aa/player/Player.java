package com.aa.player;

import java.util.ArrayList;

import com.aa.card.CardOne;
import com.aa.rule.Status;

public abstract class Player implements InterfacePlayer{
	protected ArrayList<CardOne> cards;
	private Status states = Status.PLAYING;
	
	public Status getStates() {
		return states;
	}

	public void setStates(Status states) {
		this.states = states;
	}

	public ArrayList<CardOne> getCards() {
		return cards;
	}

	public void setCards(CardOne card) {
		this.cards.add(card);
	}

//	public abstract int nextAction();
}
