package com.aa;

import java.util.Scanner;

public class BlackJack {
	
	CardCase cardCase;

	public void play(){
		
		@SuppressWarnings("unused")
		Scanner scan = new Scanner(System.in);
		String yN = "";
		
		while(true){
			
			
			
			
			
			
			
			//게임 종료될 즈음
			System.out.println("게임을 계속 진행 하시겠습니까?");
			yN = scan.nextLine();
			if(isFinalGame(yN))//트루면 게임 끝 false면 다시 게임
				break;
			else
				continue;
						
		}
		
	}

	private boolean isFinalGame(String yN) {
		// TODO Auto-generated method stub
		boolean isc = false;
		return isc;
	}
}
