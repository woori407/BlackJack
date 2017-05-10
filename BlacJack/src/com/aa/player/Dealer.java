package com.aa.player;

public class Dealer extends Player{


	//0:힛트	1:스테이


	public Dealer() {
		super();
	}


	@Override
	public int nextAction(){
		int choice = -1;

		if(hands<17){
			choice = 0;
		}else{
			System.out.println("카드의 합이 17이상으로 카드를 더 받을 수 없습니다.");
			choice = 1;
		}

		return choice;
	}

}
