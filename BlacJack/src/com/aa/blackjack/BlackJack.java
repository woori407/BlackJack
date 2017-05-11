package com.aa.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import com.aa.card.CardCase;
import com.aa.player.Dealer;
import com.aa.player.Guest;
import com.aa.player.Player;
import com.aa.rule.Rule;
import com.aa.rule.consts.Match;
import com.aa.rule.consts.State;

public class BlackJack {
	ArrayList<Player> playerList = new ArrayList<Player>();
	Dealer dealer;
//	Guest guest;
	CardCase cardCase;
	boolean isKeepGoing = true;
	int returnVal = 0;
	int index;
	boolean end;

	public int play(Guest guest){				//반환 값 0: 정상 종료 	1: 새로운 게임
		
		@SuppressWarnings("unused")
		Scanner scan = new Scanner(System.in);
		String command = "";
//		guest= new Guest();
		dealer = Dealer.getInstance();
		cardCase = CardCase.getInstance();
		index = 0;

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

		for (; index < playerList.size(); index++) {
			Guest p;
			if(index==playerList.size()-1)
				p = (Guest)playerList.get(index-1);
			else
				p = (Guest)playerList.get(index);
			//모든 게스트가 PLAYING 상태가 아님을 확인하는 변수. true:모든 게스트 state!=PLAYING
			end = true;

			//		}				
			for (int j = index; j < playerList.size()-1; j++) {
				if(playerList.get(j).getState()==State.PLAYING){
					end=false;
					break;
				}
			}
			//
			if(end){
				while(dealer.getState()==State.PLAYING) {
					Rule.respondPlayer(dealer,dealer.nextAction(-1));
				}
				//split 결과 금액 관리
				int prize=0;
				for (int j = 0; j < index; j++) {
					prize += p.getState()==State.BLACKJACK?p.getBetting()*2:Rule.isWin(p)==Match.WIN?p.getBetting()*3:Rule.isWin(p)==Match.DRAW?p.getBetting():0;
				}
				guest.setBudget(guest.getBudget()+prize);
			}	

			print(p, end);

			if(p.getState()==State.PLAYING){
				command = scan.nextLine();			//사용자의 명령어를 받음
				interpretCommand(p, command);			//사용자의 명령어를 해석함
			} else if(index==playerList.size()-1){
				command = scan.nextLine();			//사용자의 명령어를 받음
				interpretCommand(p, command);			//사용자의 명령어를 해석함
			}
			clearScreen();

		}

		return returnVal;

	}
	private void print(Guest p, boolean end) {
		String result = "\t\t\t\t\t\t\t\t\t\t\t\t";
		String dCardResult = "\t\t\t\t\t\t\t\t\t\t\t\t";
		String dealersCard = new String(makeDealersCard());
		String guestsCard = new String(makeGuestCard(p));


		if(p.getState()==State.BLACKJACK ){
			result = "\t\t\tBlackJack! You win!\t\t\t\t\t\t\t";
//			p.setBudget(p.getBudget() + p.getBetting()*2);
//			p.setBetting(0);
//			guestBudgetControl(p,2);
		}
		else if(dealer.getState()==State.BLACKJACK ){
//			result = "\t\t\tBlackJack! You lose!";
//			p.setBetting(0);
//			guestBudgetControl(p,0);
		}
		else if(end && dealer.getState()!=State.PLAYING){
			result = new String(makeResultStr(p));
			dCardResult = new String(makeDCardResultStr());
		}


		System.out.printf("*************************************************************************************************\n");
		System.out.printf("*\t\t\t\tWelcome to BlackJack Game!\t\t\t\t\t*\n");
		System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
		System.out.printf("*\tDealer's card : %s*\n" , dealersCard);//딜러의 카드
		System.out.printf("*\tYour card : %s*\n" , guestsCard);//게스트의  카드
		System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
		if(playerList.size()>2){
			System.out.print("*");
			int i;
			for (i = 0; i < playerList.size()-1; i++) {
				System.out.printf("\tsplit%d: %d\t", i+1, playerList.get(i).getHands());
			}
			for (; i < 4; i++) {
				System.out.printf("\t\t\t");
			}
			System.out.printf("*\n");
		}
		System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
		System.out.printf("*%s*\n" , result);
		System.out.printf("*%s*\n" , dCardResult);
		System.out.printf("*\t\t\t\t\t\t\t\t\tYour score : %d\t\t*\n" , p.getHands());//점수표시
		System.out.printf("*\t\t\t\t\t\t\t\t\tstate : %s\t\t*\n" , p.getState());//상태표시
		System.out.printf("*\t\t\t\t\t\t\t\t\t\t\t\t*\n");
		System.out.printf("*\t\t\t\t\t\t\t\t(n) or (new) : start new game\t*\n");
		System.out.printf("*\t\t\t\t\t\t\t\t(q) or (quit) : close this game\t*\n");
		System.out.printf("*\t\t\t\t\t\t\tYour budget : %d\tBetting : %d\t*\n" ,((Guest)playerList.get(0)).getBudget() , p.getBetting());
		System.out.printf("*************************************************************************************************\n");
		

		if(p.getCards().size()==2&&p.getCards().get(0).getValue()==p.getCards().get(1).getValue()&&p.getBudget()>p.getBetting()&&p.getState()==State.PLAYING)
			System.out.println("Hit(H/HIT) Split(SP/Split) DoubleDown(D/Double) Stay(S/Stay):");
		else if(p.getCards().size()==2&&p.getState()==State.PLAYING&&((Guest)p).getBudget()>((Guest)p).getBetting())
			System.out.println("Hit(H/HIT) Stay(S/Stay) DoubleDown(D/Double):");
		else if(p.getState()==State.PLAYING)
			System.out.println("Hit(H/HIT) Stay(S/Stay):");
		else if(end && dealer.getState()!=State.PLAYING)
			System.out.println("new Game?");		
	}
	
	private String makeDCardResultStr() {
		String result =  "" ;

		result+="\tdealer's card : " ;

		int size = dealer.getCards().size();
		for (int i = 0; i < size; i++) {
			//			dCard+=" ■";
			result+=dealer.getCards().get(i);
		}
		if(size>10)
			result+="";
		else if(size>9)
			result+="\t\t\t\t\t";
		else if(size>8)
			result+="\t\t\t\t\t\t";
		else if(size>7)
			result+="\t\t\t\t\t\t";
		else if(size>6)
			result+="\t\t\t\t\t\t";
		else if(size>5)
			result+="\t\t\t\t\t\t";
		else if(size>4)
			result+="\t\t\t\t\t\t\t";
		else if(size>3)
			result+="\t\t\t\t\t\t\t";
		else if(size>2)
			result+="\t\t\t\t\t\t\t\t";
		else
			result+="\t\t\t\t\t\t\t\t";
		return result;
	}
	
	private String makeResultStr(Guest guest) {
		String result =  "\tresult : " ;

		int dScore = dealer.getHands();
		int gScore = guest.getHands();

		if(guest.getState()==State.BURST){
			result += "You lose!";
//			guest.setBetting(0);
		}else{
			int dGap = 21-dScore;
			int gGap = 21-gScore;
			if(dGap<0){
				result += "You win!";
//				guest.setBudget(guest.getBudget() + guest.getBetting()*3);
//				guest.setBetting(0);
			}else if(dGap - gGap>0){
				result += "You win!";
//				guest.setBudget(guest.getBudget() + guest.getBetting()*3);
				guest.setBetting(0);
			}else if(dGap - gGap<0){
				result += "You lose!";
//				guest.setBetting(0);
			}else{
				result += "Draw~~~!";
//				guest.setBudget(guest.getBudget() + guest.getBetting());
//				guest.setBetting(0);
			}
		}

		result=result + "\tDealer's score : " + dealer.getHands() + "[" + dealer.getState() + "]" + "\t\t\t\t\t";

		return result;
	}
	private String makeDealersCard() {
		String dCard = "" + dealer.getCards().get(0);
		int size = dealer.getCards().size();

		for (int i = 1; i < size; i++) {
			dCard+=" ■";
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

	private String makeGuestCard(Guest guest) {
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


	private void interpretCommand(Player p, String command) {

		if(command.trim().compareToIgnoreCase("quit")==0 || command.trim().compareToIgnoreCase("q")==0){
			isKeepGoing = false;
			returnVal = 0;		//0이면 정상종료
		}else if(command.trim().compareToIgnoreCase("new")==0 || command.trim().compareToIgnoreCase("n")==0){
			isKeepGoing = false;
			returnVal = 1;		//1이면 새 게임
		}else if(command.trim().compareToIgnoreCase("hit")==0 || command.trim().compareToIgnoreCase("h")==0){
			if(p.getState()==State.PLAYING){
				Rule.respondPlayer(p,p.nextAction(0));
				index--;
			}

		}else if(command.trim().compareToIgnoreCase("split")==0 || command.trim().compareToIgnoreCase("sp")==0){
			if(p.getState()==State.PLAYING){
				Rule.split(playerList, index--);
			}
			
		}else if(command.trim().compareToIgnoreCase("stay")==0 || command.trim().compareToIgnoreCase("s")==0){
			if(p.getState()==State.PLAYING)
				Rule.respondPlayer(p,p.nextAction(1));
		}else if(command.trim().compareToIgnoreCase("double")==0 || command.trim().compareToIgnoreCase("d")==0){
			if(p.getState()==State.PLAYING && p.getCards().size()==2 && ((Guest)p).getBetting()<=((Guest)p).getBudget()){
				((Guest)p).doubleBetting();
				Rule.respondPlayer(p,p.nextAction(2));
			}
			else{
				System.out.println("you can not doubledown now");
				index--;
			}
		}else{
			System.out.println("잘못된 입력입니다.");
			if(index!=playerList.size()-1)
				index--;
			else
				returnVal=1;
		}
	}

	public static void clearScreen() {
		for (int i = 0; i < 40; i++)
			System.out.println("");
	}
	
	public void guestBudgetControl(Guest guest, int multi){
		guest.setBudget(guest.getBudget() + guest.getBetting()*multi);
		guest.setBetting(0);
	}

}
