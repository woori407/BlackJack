package com.aa.player;

import java.util.ArrayList;

import com.aa.card.CardOne;

public abstract class Player implements InterfacePlayer{
	protected ArrayList<CardOne> cards;
	
	
	private static final int CARD_POINT = 16;
	
	
	public Player() {
		cards = new ArrayList<CardOne>();
		
		// TODO Auto-generated constructor stub
	}

	public void receiveCard(){
		
	}



	public ArrayList<CardOne> getCards() {
		return cards;
	}

//	public abstract int nextAction();
}
