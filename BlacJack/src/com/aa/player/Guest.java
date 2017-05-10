package com.aa.player;

import java.util.ArrayList;
import java.util.Scanner;

public class Guest extends Player{

	public Guest() {
		super();
	}



	@Override
	public int nextAction(){
		//0:힛트	1:스테이

		int choice = -1;

		Scanner scan = new Scanner(System.in);
		choice = scan.nextInt();

		while(true){
			System.out.println("Hit을 원하시면 0입력 - Stay를 원하시면 1입력");
			if(choice == 1){
				break;
			}else if(choice ==0){
				break;
			}else{
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		return choice;
	}
}
