package com.aa.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import com.aa.card.CardCase;
import com.aa.player.Dealer;
import com.aa.player.Guest;
import com.aa.player.Player;
import com.aa.rule.Rule;
import com.aa.rule.consts.State;

public class BlackJack {
		ArrayList<Player> playerList = new ArrayList<Player>();
	Dealer dealer;
	Guest guest;
	CardCase cardCase;
	boolean isKeepGoing = true;
	int returnVal = 0;

	public int play(){				//반환 값 0: 정상 종료 	1: 새로운 게임
		
		@SuppressWarnings("unused")
		Scanner scan = new Scanner(System.in);
		String command = "";
		guest= new Guest();
		dealer = Dealer.getInstance();
		cardCase = CardCase.getInstance();
		
		//1.셔플 및 게임 초기화
		cardCase.shuffleDeck();
		playerList.clear();
		playerList.add(guest);
		playerList.add(dealer);
		for (Player player : playerList) {
			player.clearHands();
		}
		//2.카드분배
		for (Player player : playerList) {
			Rule.dealInitialCards(player);
		}
		
		while(isKeepGoing){	
			
			
			//3...
//			for (Player player : playerList) {
//				game(player);
//			}
			
			//게임 종료될 즈음
//			System.out.println("게임을 계속 진행 하시겠습니까?(Y/N)");
//			yN = scan.nextLine();
//			if(isFinalGame(yN))//트루면 게임 끝 false면 다시 게임
//				break;
//			else
//				continue;
			
			//오픈카드를 보여주고 히든 카드 수를 명시적으로 표현
			

			
			
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
			
			if(playerList.get(0).getState()==State.STAY){
//				System.out.println("dsds");
				while(playerList.get(1).getState()==State.PLAYING) {
					Rule.respondPlayer(playerList.get(1),playerList.get(1).nextAction(-1));
//					System.out.println(playerList.get(1).getHands());
//					System.out.println(playerList.get(1).getState());
//					for (int j = 0; j < playerList.get(1).getCards().size(); j++) {
//						System.out.println(playerList.get(1).getCards().get(j));
//					}
				}
			}	
			
			String dealersCard = new String(makeDealersCard());
			String guestsCard = new String(makeGuestCard());
			String result = "\t\t\t\t\t\t\t\t\t\t\t\t";
			
			if(playerList.get(0).getState()==State.STAY && playerList.get(1).getState()==State.STAY){
				System.out.println("dasdsa");
				result = new String(makeResultStr());
			}

			
			System.out.printf("*************************************************************************************************\n");
			System.out.printf("*\t\t\t\tWelcome to BlackJack Game!\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\tDealer's card : %s*\n" , dealersCard);//딜러의 카드
			System.out.printf("*\tYour card : %s*\n" , guestsCard);//게스트의  카드
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*%s*\n" , result);
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\tYour score : %d\t\t*\n" , guest.getHands());//점수표시
			System.out.printf("*\t\t\t\t\t\t\t\t\tstate : %s\t\t*\n" , guest.getState());//상태표시
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t(n) or (new) : start new game\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t(q) or (quit) : close this game\t*\n");
			System.out.printf("*************************************************************************************************\n");
			
//			for (Player player : playerList) {
//				game(player);
//			}
			System.out.println("Hit(H/HIT) Stay(S/Stay):");
			
			
			
			command = scan.nextLine();			//사용자의 명령어를 받음
			interpretCommand(command);			//사용자의 명령어를 해석함
			
			clearScreen();
						
		}
		
		return returnVal;
		
	}
	private String makeResultStr() {
		String result =  "\tresult : You ooo" + "\t\tdealer's card : " ;
		
		int size = dealer.getCards().size();
		for (int i = 1; i < size; i++) {
//			dCard+=" ■";
			result+=dealer.getCards().get(i);
		}
		
		result=result + "\t\tscore : " + dealer.getHands() + "\t";
		
		return result;
	}
	private String makeDealersCard() {
		String dCard = "" + dealer.getCards().get(0);
		int size = dealer.getCards().size();
		
		for (int i = 1; i < size; i++) {
			dCard+=" ■";
//			dCard+=dealer.getCards().get(i);
		}
		if(size>9)
			dCard+="\t\t\t\t\t\t";
		else if(size>5)
			dCard+="\t\t\t\t\t\t\t";
		else if(size>2)
			dCard+="\t\t\t\t\t\t\t\t";
		else
			dCard+="\t\t\t\t\t\t\t\t\t";
		return dCard;
	}
	
	private String makeGuestCard() {
		String gCard = "" +guest.getCards().get(0);
		int size = guest.getCards().size();
		
		for (int i = 1; i < size; i++) {
			gCard+=guest.getCards().get(i);
		}
		
		if(size>10)
			gCard+="";
		else if(size>9)
			gCard+="\t\t\t\t\t";
		else if(size>8)
			gCard+="\t\t\t\t\t\t";
		else if(size>7)
			gCard+="\t\t\t\t\t\t";
		else if(size>6)
			gCard+="\t\t\t\t\t\t";
		else if(size>5)
			gCard+="\t\t\t\t\t\t\t";
		else if(size>4)
			gCard+="\t\t\t\t\t\t\t";
		else if(size>3)
			gCard+="\t\t\t\t\t\t\t\t";
		else if(size>2)
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
		}else if(command.trim().compareToIgnoreCase("hit")==0 || command.trim().compareToIgnoreCase("h")==0){
			if(playerList.get(0).getState()==State.PLAYING)
				Rule.respondPlayer(playerList.get(0),playerList.get(0).nextAction(0));
			
		}else if(command.trim().compareToIgnoreCase("stay")==0 || command.trim().compareToIgnoreCase("s")==0){
			if(playerList.get(0).getState()==State.PLAYING)
				Rule.respondPlayer(playerList.get(0),playerList.get(0).nextAction(1));
			
		}
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}
	
//	private void game(Player p , int choice){
//		if(p.getState()==State.PLAYING) {
//			Rule.respondPlayer(p, p.nextAction(choice));
//		}
//	}
}
