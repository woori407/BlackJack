package com.aa.player;

import java.util.ArrayList;
import java.util.Scanner;

import com.aa.card.CardOne;

public class Guest extends Player{
	
	int budget = 1500;		//소지금
	int betting = 0;		//현재 베팅 금액

	

	public Guest() {
		super();
	}
	
	public void doubleBetting(){
		budget -= betting;
		betting *=2;
	}

	public void betting(){
		System.out.printf("How much would you bet(you budget : %d) :" , budget);
		Scanner scan = new Scanner(System.in);
		betting = scan.nextInt();
		budget-=betting;
	}
	
	public CardOne split(){
		CardOne splitCard = cards.get(1);
		cards.remove(1);
		return splitCard;
	}

	@Override
	public int nextAction(int choice){
		//0:힛트	1:스테이 
//		int choice = -1;
		
		
//		Scanner scan = new Scanner(System.in);

//		while(true){
//			System.out.println("your card : " + cards+"score : "+hands);
//			System.out.println("Hit을 원하시면 0입력 - Stay를 원하시면 1입력");
//			choice = scan.nextInt();
//			if(choice == 1){
//				break;
//			}else if(choice ==0){
//				break;
//			}else{
//				System.out.println("잘못 입력하셨습니다.");
//			}
//		}

		return choice;
	}
	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public int getBetting() {
		return betting;
	}

	public void setBetting(int betting) {
		this.betting = betting;
	}
}
