package com.aa.main;

import com.aa.blackjack.BlackJack;

public class BlackJackMain {

	public static void main(String[] args) {
		
		BlackJack black = new BlackJack();
		while(black.play()==1){		//play 반환값이 0이면 정상 종료 1이면 새 게임
			black = new BlackJack();
		}
		
		System.out.println("게임이 종료 되었습니다.");

	}

}
