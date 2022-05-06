package co.dodo.dungeons.gameStart;

import java.util.Scanner;

import co.dodo.dungeons.gameStart.game.Game;

public class Mains
{
	private Scanner scn = new Scanner(System.in);
	
	private void gameStrat() // 게임 메뉴
	{
		while(true)
		{
			mainMenus();
			try
			{
				int menu = Integer.parseInt(scn.nextLine());
				
				if(menu == 1)
				{
					// 새로운 게임 > 1. 간단 유저 생성, 2. 새 게임 시작.
					Game g = new Game();
					g.gameRun();
					
				}
				else if(menu == 2)
				{
					// 저장된 데이터를 바탕으로 게임 재구성해서 불러오기.
				}
				else if(menu == 3)
				{
					// db에서 포인트가 높은 유저의 순위를 뽑아서 가져오기.
				}
				else if(menu == 4)
				{
					// 캐릭터 삭제 기능.
				}
				else if(menu == 5)
				{
					System.out.println("던전 탈출!");
					break;
				}
				else
				{
					System.out.println("올바른 선택지를 고르세요...");
					System.out.println();
				}
			}
			catch(Exception e)
			{
				System.out.println("올바른 선택지를 고르세요...");
				System.out.println();
			}
			
		}
	}
	
	public void run() // 외부 게임 구동 메서드
	{
		gameStrat();
	}
	
	private void mainMenus() // 메인메뉴
	{
		System.out.println("===================================== 던 전 탐 험 =====================================");
		System.out.println();
		System.out.println("==== 1. 게임 새로시작     ====");
		System.out.println("==== 2. 게임 불러오기     ====");
		System.out.println("==== 3. 명예의 전당       ====");
		System.out.println("==== 4. 세이브파일 삭제   ====");
		System.out.println("==== 5. 게임 종료         ====");
		System.out.println();
	}
}
