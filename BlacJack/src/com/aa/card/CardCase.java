package com.aa.card;

import java.util.ArrayList;

public class CardCase {

	private ArrayList<CardOne> cardCase;

	public ArrayList<CardOne> getCardCase() {
		return cardCase;
	}

	public CardCase() {
		cardCase = new ArrayList<CardOne>();
		inputCard();
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
}
