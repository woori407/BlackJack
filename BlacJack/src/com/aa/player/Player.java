package com.aa.player;

import java.util.ArrayList;

import com.aa.card.CardOne;

public abstract class Player implements InterfacePlayer{
	protected ArrayList<CardOne> cards;
	
	public ArrayList<CardOne> getCards() {
		return cards;
	}

//	public abstract int nextAction();
}
