package com.aa.blackjack;

import java.util.Scanner;

import com.aa.card.CardCase;
import com.aa.rule.Rule;

public class BlackJack {
	
	Rule rule;

	public void play(){
		
		@SuppressWarnings("unused")
		Scanner scan = new Scanner(System.in);
		String yN = "";
		
		while(true){
			rule = new Rule();			
			
			//게임 종료될 즈음
			System.out.println("게임을 계속 진행 하시겠습니까?");
			yN = scan.nextLine();
			if(isFinalGame(yN))//트루면 게임 끝 false면 다시 게임
				break;
			else
				continue;
						
		}
		
		System.out.println("게임이 종료 되었습니다.");
		
	}
	
	//트루면 게임 끝 false면 다시 게임
	private boolean isFinalGame(String yN) {
		// TODO Auto-generated method stub
		boolean isc = false;
		return isc;
	}
}
