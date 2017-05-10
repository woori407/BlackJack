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
		
		while(isKeepGoing){	
			
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
			//3...
			for (Player player : playerList) {
				game(player);
			}
			
			//게임 종료될 즈음
//			System.out.println("게임을 계속 진행 하시겠습니까?(Y/N)");
//			yN = scan.nextLine();
//			if(isFinalGame(yN))//트루면 게임 끝 false면 다시 게임
//				break;
//			else
//				continue;
			
			//오픈카드를 보여주고 히든 카드 수를 명시적으로 표현
			String dealersCard = "" + dealer.getCards().get(0);
			String guestsCard = "";
			
			for (int i = 0; i < dealer.getCards().size()-1; i++) {
				dealersCard+=" ■";
			}
			for (int i = 0; i < guest.getCards().size(); i++) {
				guestsCard+=" "+guest.getCards().get(i);
			}
			
			System.out.printf("*************************************************************************************************\n");
			System.out.printf("*\t\t\t\tWelcome to BlackJack Game!\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\tDealer's card : %s\t\t\t\t\t\t\t\t\t*\n" , dealersCard);//딜러의 카드
			System.out.printf("*\tYour card : %s\t\t\t\t\t\t\t\t\t*\n" , guestsCard);//게스트의  카드
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
			System.out.printf("*\t\t\t\t\t\t\t\t\tYour score : %d\t\t*\n" , guest.getHands());//점수표시
			System.out.printf("*\t\t\t\t\t\t\t\t\tstate : %s\t\t*\n" , guest.getState());//상태표시
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
	
	private void game(Player p){
		while (p.getState()==State.PLAYING) {
			Rule.respondPlayer(p, p.nextAction());
		}
		if(p.equals(guest))
			System.out.println("You "+p.getState());
	}
}
