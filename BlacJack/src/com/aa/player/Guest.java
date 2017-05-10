package com.aa.player;

import java.util.ArrayList;
import java.util.Scanner;

public class Guest extends Player{

	public Guest() {
		super();
	}



	@Override
	public int nextAction(int choice){
		//0:힛트	1:스테이

<<<<<<< HEAD
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
=======
		int choice = -1;
		

		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("your card : " + cards+"score : "+hands);
			System.out.println("Hit을 원하시면 0입력 - Stay를 원하시면 1입력");
			try {
				choice = scan.nextInt();
				
				if(choice == 1){
					break;
				}else if(choice ==0){
					break;
				}else{
					System.out.println("잘못 입력하셨습니다.");
				}
			} catch (Exception e) {				
				System.out.println("정수만 입력하세요.");
				scan.reset();
			}
	
		}
>>>>>>> branch 'master' of https://github.com/woori407/BlackJack.git
		return choice;
	}
}
