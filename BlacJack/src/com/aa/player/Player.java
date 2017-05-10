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



	
	public abstract int nextAction();

	public int receiveCards(int choice){
		
		if(choice == 0){
			System.out.println("카드를 ");
			this.getCards();
		}
		return choice;
	}

	
	
	
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
	}

	//	public abstract int nextAction();
}
