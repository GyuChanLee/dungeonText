package co.dodo.dungeons.gameStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.dodo.dungeons.cards.CardAttack;
import co.dodo.dungeons.cards.CardDefense;
import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.gameStart.game.Game;
import co.dodo.dungeons.service.SaveFiles;
import co.dodo.dungeons.service.SaveFilesImpl;
import co.dodo.dungeons.vo.PlayerVO;

public class Mains
{
	private Scanner scn = new Scanner(System.in);
	private SaveFiles sf = new SaveFilesImpl();
	
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
					
					makeUser();
					g.gameRun();
					
				}
				else if(menu == 2)
				{
					// 저장된 데이터를 바탕으로 게임 재구성해서 불러오기.
					Game g = new Game();
					g.gameRun();
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
	
	private void makeUser() // 새 세이브파일 생성 + 새 캐릭터, 카드리스트, 인벤토리 구성.
	{
		System.out.println("== 모험가의 이름을 정하세요 > ");
		String userName = scn.nextLine();
		System.out.println("== 세이브파일의 간단한 비밀번호를 정하세요 > ");
		int pw = Integer.parseInt(scn.nextLine());
		System.out.println();
		PlayerVO p1 = new PlayerVO(userName,pw);
		sf.playerInsert(p1);
		p1 = sf.playerSelect(p1);
		List<CardVO> allCards = new ArrayList<CardVO>();
		allCards = generateCards();
		
		for(int i=0; i<allCards.size(); i++) // 초기 생성 5개 카드를 생성 캐릭터의 카드리스트에 넣기.
		{
			CardVO tmp = allCards.get(i);
			sf.cardListInsert(tmp, p1);
		}
		
	}
	
	private List<CardVO> generateCards() // 처음 카드리스트 생성
	{
		List<CardVO> cardList = new ArrayList<CardVO>();
		CardVO general1 = new CardAttack(); 
		CardVO general2 = new CardAttack(); 
		CardVO general3 = new CardAttack(); 
		CardVO general4 = new CardDefense(); 
		CardVO general5 = new CardDefense(); 
		sf.cardInsert(general1);
		sf.cardInsert(general2);
		sf.cardInsert(general3);
		sf.cardInsert(general4);
		sf.cardInsert(general5);
		
		cardList = sf.Card5Select(); // 초기생성 5개 카드 뽑아옴.
		
		return cardList;
	}
}
