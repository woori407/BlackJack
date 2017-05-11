package com.aa.rule;

import java.util.ArrayList;

import com.aa.card.CardCase;
import com.aa.card.CardOne;
import com.aa.player.Dealer;
import com.aa.player.Guest;
import com.aa.player.Player;
import com.aa.rule.consts.Match;
import com.aa.rule.consts.State;

public class Rule {
	
	private static Dealer dealer = Dealer.getInstance();
	private static CardCase cardCase = CardCase.getInstance();
	
	private Rule(){
	}
	//시작시 카드 분배
	public static void dealInitialCards(Player p){
		p.setCards(cardCase.drawCard());
		p.setCards(cardCase.drawCard());
		setPlayerState(p);
	}
	
	//플레이어의 선택에 대한 룰 클래스의 응답
	public static void respondPlayer(Player p, int choice){
//		setPlayerState(p);
		if(p.getState()==State.PLAYING){
			switch (choice) {
			case 0:
				//hit
				hit(p);
				break;
			case 1:
				//stay
				stay(p);
				break;
			case 2:
				//double down
				doubleDown(p);
//				break;
			default:
				//예외처리
				break;
			}
			if(p.getState()==State.STAY)
				return;
			setPlayerState(p);
		}
	}

	private static void doubleDown(Player p) {
		hit(p);
		setPlayerState(p);
		if(p.getState()==State.PLAYING)
			stay(p);
	}
	//
	private static void hit(Player p){
		p.setCards(cardCase.drawCard());
		setPlayerState(p);
	}
	
	//승패결정. true면 승리 enum 승무패
	public static Match isWin(Player p){
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
				isc = compareHands(p);
				break;
			default:
				break;
			}
		}
		return isc;
	}

	private static Match compareHands(Player p) {
		Match isc = Match.LOSE;
		isc = dealer.getHands()>p.getHands()?Match.LOSE:
			dealer.getHands()<p.getHands()?Match.WIN:Match.DRAW;		
		return isc;
	}
	
	private static void setPlayerState(Player p){
		if(p.getCards().size()==2 && p.getHands()==21 && p.hasAce()){
			p.setState(State.BLACKJACK);
		}
		else if(p.getHands()>21)
			p.setState(State.BURST);
		else
			p.setState(State.PLAYING);
	}
	
	private static void stay(Player p){
		p.setState(State.STAY);
	}
	
	public static void split(ArrayList<Player> playerList, int index){
		Guest current = (Guest)playerList.get(index);
		CardOne card = current.split();
		Guest splitOne = new Guest();
		splitOne.setCards(card);
		playerList.add(1,splitOne);
		splitOne.setBetting(current.getBetting());
		splitOne.setBudget(current.getBudget()-current.getBetting());
		for (int i = 0; i <= index; i++) {
			Guest g = (Guest)playerList.get(i);
			g.setBudget(splitOne.getBudget());
		}
		current.setCards(cardCase.drawCard());
		setPlayerState(current);
		splitOne.setCards(cardCase.drawCard());
		setPlayerState(splitOne);
	}
}
