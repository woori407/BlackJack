package com.aa;

public class CardOne {
	public static final String[] SHAPE = {"◆","♥","♣","♠"};
	public static final String[] NUMBER = {"A","2"};
	private String card;
	
	public CardOne(){
		makeCard();
	}
	
	public String getCard() {
		return card;
	}

	private void makeCard(){
		int a = (int)(Math.random()*SHAPE.length);
		int b = (int)(Math.random()*NUMBER.length);
		
		card = SHAPE[a]+NUMBER[b];
	}

	@Override
	public int hashCode() {
		return card.hashCode()+137;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isc = false;
		CardOne other = (CardOne)obj;
		if(card.equals(other.card))
			isc = true;
		return isc;
	}

	@Override
	public String toString() {
		return "[" + card + "]";
	}
}
