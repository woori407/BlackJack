package com.aa.blackjack;

import java.util.Scanner;

import com.aa.card.CardCase;
import com.aa.rule.Rule;

public class BlackJack {
	
	Rule rule;
	boolean isKeepGoing = true;
	int returnVal = 0;

	public int play(){				//반환 값 0: 정상 종료 	1: 새로운 게임
		
		@SuppressWarnings("unused")
		Scanner scan = new Scanner(System.in);
		String command = "";
		rule = new Rule();	
		rule.dealInitialCards();	//카드 셔플
		
		while(isKeepGoing){	
			
			//1.셔플
			//2.카드분배
			//3...
			
			
			//게임 종료될 즈음
//			System.out.println("게임을 계속 진행 하시겠습니까?(Y/N)");
//			yN = scan.nextLine();
//			if(isFinalGame(yN))//트루면 게임 끝 false면 다시 게임
//				break;
//			else
//				continue;
			
			//오픈카드를 보여주고 히든 카드 수를 명시적으로 표현
			
			String dealersCard = new String(makeDealersCard());
			String guestsCard = new String(makeGuestCard());
//			String dealersCard = "" + rule.getDealer().getCards().get(0);
//			String guestsCard = "" + rule.getGuest().getCards().get(0);
			
//			for (int i = 0; i < rule.getDealer().getCards().size()-1; i++) {
//				dealersCard+=" ■";
//			}
//			for (int i = 0; i < rule.getGuest().getCards().size()-1; i++) {
//				guestsCard+=" □";
//			}
//			dealersCard+="\t\t\t\t\t\t\t\t\t";
//			guestsCard+="\t\t\t\t\t\t\t\t\t";
//			String temp =  "" + rule.getGuest().getCards().get(0) + " ■ ■ ■ ■" + "\t\t\t\t\t\t\t\t\t";
			
			
			System.out.printf("*************************************************************************************************\n");
			System.out.printf("*\t\t\t\tWelcome to BlackJack Game!\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\tDealer's card : %s*\n" , dealersCard);//딜러의 카드
			System.out.printf("*\tYour card : %s*\n" , guestsCard);//게스트의  카드
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\tYour score : %d\t\t*\n" , 21);//점수표시
			System.out.printf("*\t\t\t\t\t\t\t\t\tstate : %s\t\t*\n" , "Playing");//상태표시
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t(n) or (new) : start new game\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t(q) or (quit) : close this game\t*\n");
			System.out.printf("*************************************************************************************************\n");
			
			command = scan.nextLine();			//사용자의 명령어를 받음
			interpretCommand(command);			//사용자의 명령어를 해석함
			
			clearScreen();
						
		}
		
		return returnVal;
		
	}
	private String makeDealersCard() {
		String dCard = "" + rule.getDealer().getCards().get(0);
		int size = rule.getDealer().getCards().size()-1;
		
		for (int i = 0; i < size; i++) {
			dCard+=" ■";
		}
		if(size>9)
			dCard+="\t\t\t\t\t\t";
		else if(size>5)
			dCard+="\t\t\t\t\t\t\t";
		else if(size>1)
			dCard+="\t\t\t\t\t\t\t\t";
		else
			dCard+="\t\t\t\t\t\t\t\t\t";
		return dCard;
	}
	
	private String makeGuestCard() {
		String gCard = "" + rule.getGuest().getCards().get(0);
		int size = rule.getGuest().getCards().size()-1;
		
		for (int i = 0; i < size; i++) {
			gCard+=" □";
		}
		if(size>11)
			gCard+="\t\t\t\t\t\t";
		else if(size>7)
			gCard+="\t\t\t\t\t\t\t";
		else if(size>3)
			gCard+="\t\t\t\t\t\t\t\t";
		else
			gCard+="\t\t\t\t\t\t\t\t\t";

		return gCard;
	}

	

	//트루면 게임 끝 false면 다시 게임
//	private boolean isFinalGame(String yN) {
//		// TODO Auto-generated method stub
//		boolean isc = false;
//		return isc;
//	}
	
	private void interpretCommand(String command) {
		
		if(command.trim().compareToIgnoreCase("quit")==0 || command.trim().compareToIgnoreCase("q")==0){
			isKeepGoing = false;
			returnVal = 0;		//0이면 정상종료
		}else if(command.trim().compareToIgnoreCase("new")==0 || command.trim().compareToIgnoreCase("n")==0){
			isKeepGoing = false;
			returnVal = 1;		//1이면 새 게임
		}
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}
}
