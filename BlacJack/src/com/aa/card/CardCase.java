package com.aa.card;

import java.util.ArrayList;

public class CardCase {

	private ArrayList<CardOne> cardCase;
	private static CardCase me = null;

	public ArrayList<CardOne> getCardCase() {
		return cardCase;
	}

	private CardCase() {
		cardCase = new ArrayList<CardOne>();
		shuffleDeck();
	}
	
	private void inputCard(){
		int count = 0;
		while(true){
			CardOne one = new CardOne();
			if(!cardCase.contains(one)){
				cardCase.add(one);
				count++;
			}
			if(count==(CardOne.NUMBER.length*CardOne.SHAPE.length))
				break;
		}
	}
	
	public CardOne drawCard(){
		CardOne drawedCard = cardCase.get(0);
		this.cardCase.remove(0);
		return drawedCard;
	}
	
	public void shuffleDeck(){
		cardCase.clear();
		inputCard();
	}
	
	public static CardCase getInstance(){
		if(me!=null)
			me = new CardCase();
		return me;
	}
}
