package co.dodo.dungeons.gameStart.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.dodo.dungeons.cards.CardAttack;
import co.dodo.dungeons.cards.CardDefense;
import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.maps.MapStage1;
import co.dodo.dungeons.units.Oak;
import co.dodo.dungeons.units.Units;
import co.dodo.dungeons.vo.PlayerVO;

public class Game extends Thread // 게임 구현 
{	
	private Scanner scn = new Scanner(System.in);
	
	private void game()
	{
		List<CardVO> allCards = generateCards();
		boolean t = true;
		while(t)
		{
			System.out.println("===================================== STAGE 1 =====================================");
			System.out.println();
			
			System.out.println("== Stage 1 : 버려진 성 탐험 ");
			System.out.println();
			System.out.println("== 당신은 버려진 성에 들어가게 되었습니다...");
			System.out.println("== 1. 들어가기.");
			System.out.println("== 2. 도망치기.");
			int select = Integer.parseInt(scn.nextLine());
			if(select == 2)
			{
				System.out.println("== 겁쟁이마냥 도망을 칩니다...");
				t =false;
			}
			else if(select == 1)
			{
				climbUp();
			}
			else
			{
				System.out.println("== 제대로 고르세요...");
			}
			
		}
	}
	
	public void gameRun()
	{
		game();
	}
	
	private void climbUp()
	{
		PlayerVO p1 = new PlayerVO("이규찬",1234);
		int x = 0;
		System.out.println("== 현재 층은 "+(x+1)+"층 입니다...");
		System.out.println();
		System.out.println("== 1. 다음층으로 올라가기");
		System.out.println("== 2. 강화");
		System.out.println("== 3. 게임 저장");
		System.out.println();
		int select = Integer.parseInt(scn.nextLine());
		try 
		{
			if(select==1)
			{
				// 다음 방 올라가기 > 랜덤 맵 생성, 그 안에 몬스터 리젠..
				// 다음 방 맵 생성
				MapStage1 map1 = new MapStage1();
				int rand = (int)(Math.random()*10)+1;
				switch(rand)
				{
				case 1:
				case 5:
				case 6:
					map1.stage1map1();
					break;
				case 2:
				case 7:
					map1.stage1map2();
					break;
				case 3:
				case 8:
				case 10:
					map1.stage1map3();
					break;
				case 4:
				case 9:
					map1.stage1map4();
					break;
				}
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				// 몹 생성
				Units mob = mob();
				System.out.println();
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 전투 실행.
				fight(p1,mob);
				
				
			}
			else if(select==2)
			{
				// 카드 강화 메서드 > 플레이어가 가진 카드를 가져와서 공격력을 더해서 강화하기.
			}
			else if(select==3)
			{
				// 유저 상태를 db에 저장.
			}
		}
		catch(Exception e)
		{
			System.out.println("== 올바른 입력이 필요합니다...");
		}
		
	}
	
	private Units mob()
	{
		int randSel = (int)(Math.random()*2);
		Units mobs;
		if(randSel==0)
		{
			mobs = new Oak(100,30,10,60);
			return mobs;
		}
		else if(randSel==1)
		{
			mobs = new Oak(200,25,10,50);
			return mobs;
		}
		return null;
	}
	
	private void fight(PlayerVO p1, Units mob)
	{
		System.out.println();
		System.out.println("== 전투 개시 ==");
		System.out.println();
		boolean t = true;
		int mobHp = mob.getHp();
		int mobDef = mob.getDefense();
		int p1Hp = p1.getHp();
		int p1Att = p1.getAttack();
		int p1Def = p1.getDefense();
		while(t)
		{
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			mob.toString();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			showInfo(p1);
			
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("== 당신의 차례 ==");
			
			List<CardVO> selectCard5 = new ArrayList<CardVO>(); // 전체 소지카드 중에서 5개를 랜덤으로 추출해낸 리스트.
			selectCard5 = generateCards(); // 임시.
			
			System.out.println();
			System.out.println("== 이번 턴의 카드 ==");
			for(CardVO card : selectCard5) // 카드 5개를 보여줌.
			{
				System.out.println(card.toString());
			}
			System.out.println();
			
			System.out.println("== 1 - 5번 카드 중에서 선택 > ");
			int select = Integer.parseInt(scn.nextLine());
			CardVO selectCard = selectCard5.get(select-1);
			
			System.out.println();
			System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
			System.out.println("== "+selectCard.getReadme());
			System.out.println();
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			p1Att = p1.getAttack()+selectCard.getAttack();
			p1Def = p1.getDefense()+selectCard.getDefense();
			p1.setDefense(p1Def);
			mobHp -= (p1Att-mobDef);
			mob.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
			
			System.out.println();
			mob.toString();
			System.out.println();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(mob.getHp() > 0)
			{
				System.out.println("== "+mob.getName()+"의 차례 ==");
				System.out.println();
				// 랜덤하게 행동 결정
				int rand1 = (int)(Math.random()*2);
				if(rand1==0)
				{
					int mobAtt1 = mob.mobAttack();
					p1Hp -= (mobAtt1-p1Def);
					p1.setHp(p1Hp);
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(rand1==1)
				{
					mobDef = mob.mobDefense();
					mob.setDefense(mobDef);
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else
			{
				int getMoney = mob.die();
				p1.setMoney(p1.getMoney()+getMoney);
				t= false;
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("== 전투 종료 ==");
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		showInfo(p1);
	}
	
	private void showInfo(PlayerVO p1)
	{
		p1.toString();
	}
	
	private List<CardVO> generateCards() // 처음 카드리스트 생성
	{
		List<CardVO> cardList = new ArrayList<CardVO>();
		cardList.add(new CardAttack("0"));
		cardList.add(new CardAttack("1"));
		cardList.add(new CardAttack("2"));
		cardList.add(new CardDefense("3"));
		cardList.add(new CardDefense("4"));
		
		return cardList;
	}
}
