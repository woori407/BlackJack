package com.aa.player;

import java.util.ArrayList;

import com.aa.card.CardOne;
import com.aa.rule.consts.State;

public abstract class Player implements InterfacePlayer{
	protected ArrayList<CardOne> cards;
	protected State state = State.PLAYING;
	protected int hands;



	public Player() {
		cards = new ArrayList<CardOne>();
		hands=0;
	}


	public void score(){
		int count=0;
		int sum=0;
		for (CardOne card : cards) {
			sum+=card.getValue();
			if(card.getValue()==1)
				count++;
		}
		if(count>0 && sum<12)
			sum += 10;
		hands = sum;
	}

	
	public abstract int nextAction();

	
	public State getState() {
		return state;
	}

	public void setState(State states) {
		this.state = states;
	}

	public ArrayList<CardOne> getCards() {
		return cards;
	}

	public void setCards(CardOne card) {
		this.cards.add(card);
		score();
	}

	//	public abstract int nextAction();
}
