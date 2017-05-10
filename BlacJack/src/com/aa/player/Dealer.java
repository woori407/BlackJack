package com.aa.player;

public class Dealer extends Player{


	//0:힛트	1:스테이

	private static Dealer me;
	

	private Dealer() {
		super();
	}


	@Override
	public int nextAction(){
		int choice = -1;

		if(hands<17){
			choice = 0;
		}else{
			choice = 1;
		}

		return choice;
	}
	
	public static Dealer getInstance(){
		if(me==null)
			me=new Dealer();
		return me;
	}

}
