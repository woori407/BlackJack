package com.aa.rule;

import com.aa.card.CardCase;
import com.aa.player.Dealer;
import com.aa.player.Guest;
import com.aa.player.Player;

public class Rule {
	
	Dealer dealer;
	Guest guest;
	CardCase cardCase;
	
	public Rule(){
		dealer = new Dealer();
		guest = new Guest();
		cardCase = new CardCase();
	}
	
	//시작시 카드 분배
	public void dealInitialCards(){
		dealer.setCards(cardCase.drawCard());
		guest.setCards(cardCase.drawCard());
		dealer.setCards(cardCase.drawCard());
		guest.setCards(cardCase.drawCard());
	}
	
	//플레이어의 선택에 대한 룰 클래스의 응답
	public void respondPlayer(int choice){
		switch (choice) {
		case 0:
			//hit
//			hit();
			break;
		case 1:
			//stay
			break;
		default:
			//예외처리
			break;
		}
	}
	
	//
	private void hit(Player p){
		
	}
	
	//비교
	private void whoIsWinner(){
		//상태 비교해서 승패 결정. Player.getStates
	}
}
