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


	public int getHands() {
		return hands;
	}


	private void score(){
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
		cards.add(card);
		score();
	}

	public boolean hasAce(){
		boolean isc = false;
		for (CardOne cardOne : cards) {
			if(cardOne.getValue()==1){
				isc=true;
				break;
			}
		}
		return isc;
	}
	
	public void clearHands(){
		cards.clear();
		hands=0;
		state=State.PLAYING;
	}
	//	public abstract int nextAction();
}
