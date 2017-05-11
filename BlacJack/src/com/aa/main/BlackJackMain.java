package com.aa.main;

import com.aa.blackjack.BlackJack;
import com.aa.card.CardCase;
import com.aa.player.Guest;

public class BlackJackMain {

	public static void main(String[] args) {
		
		Guest guest = new Guest();
		
		BlackJack black;
		do{		//play 반환값이 0이면 정상 종료 1이면 새 게임
			guest.betting();
			black = new BlackJack();
		}while(black.play(guest)==1);
		
		System.out.println("게임이 종료 되었습니다.");
	}

}
