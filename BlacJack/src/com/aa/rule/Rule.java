package com.aa.rule;

import java.util.ArrayList;

import com.aa.card.CardCase;
import com.aa.player.Dealer;
import com.aa.player.Guest;
import com.aa.player.Player;
import com.aa.rule.consts.Match;
import com.aa.rule.consts.State;

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
	public void respondPlayer(Player p, int choice){
		switch (choice) {
		case 0:
			//hit
			hit(p);
			break;
		case 1:
			//stay
			break;
		case 2:
			//double down
//			break;
		default:
			//예외처리
			System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			break;
		}
	}
	
	public Dealer getDealer() {
		return dealer;
	}

	public Guest getGuest() {
		return guest;
	}

	public CardCase getCardCase() {
		return cardCase;
	}

	//
	private void hit(Player p){
		p.setCards(cardCase.drawCard());
	}
	
	//승패결정. true면 승리 enum 승무패
	private Match isWin(Player p){
		//상태 비교해서 게스트 승패 결정. Player.getStates
		Match isc = Match.LOSE;
		State guestState = p.getState();
		if(guestState==State.BURST){
			isc = Match.LOSE;
		}else if(guestState == State.BLACKJACK){
			isc = Match.WIN;
		}else{
			State dealerState = dealer.getState();
			switch (dealerState) {
			case BURST:
				isc = Match.WIN;
				break;
			case BLACKJACK:
				isc=Match.LOSE;
				break;
			case STAY:
				isc = compareHands();
				break;
			default:
				break;
			}
		}
		return isc;
	}

	private Match compareHands() {
		Match isc = Match.LOSE;
		/*isc = dealer.getHands()>guest.getHands()?Match.LOSE:
			dealer.getHands()<guest.getHands()?Match.WIN:Match.DRAW;*/		
		return isc;
	}
}
