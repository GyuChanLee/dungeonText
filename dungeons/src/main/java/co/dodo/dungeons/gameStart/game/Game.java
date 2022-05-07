package co.dodo.dungeons.gameStart.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.dodo.dungeons.cards.CardAttack;
import co.dodo.dungeons.cards.CardDefense;
import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.maps.MapStage1;
import co.dodo.dungeons.units.Boss1;
import co.dodo.dungeons.units.Oak;
import co.dodo.dungeons.units.Units;
import co.dodo.dungeons.vo.PlayerVO;

public class Game extends Thread // 게임 구현 
{	
	private Scanner scn = new Scanner(System.in);
	private PlayerVO p1 = new PlayerVO(0,"탐험가",1234,0,0,3,1000,40, 0,999);
	private List<CardVO> allCards = generateCards();
	private MapStage1 map = new MapStage1();
	
	private void game()
	{
		boolean t = true;
		while(t)
		{
			// 던전탐험 첫시작.
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
				for(int i=0; i<10; i++)
				{
					if(p1.getProgress()==4) // 진행상황 5층 > 특별 이벤트 방.
					{
						stairs();
//						climbUpEvent();
						climbUpBoss();
					}
					else if(p1.getProgress()==9) // 10층 > 보스방
					{
						stairs();
						climbUpBoss();
					}
					else
					{
						stairs();
						climbUp();
					}
					if(p1.getHp() <= 0)
					{
						p1.setProgress(0);
						return;
					}
				}
				if(p1.getProgress()==10)
				{
					sleeps(500);
					System.out.println("== STAGE1 - 버려진 성 탐험 완료 ==");
					System.out.println("== ");
					System.out.println("== ");
					System.out.println("== 축하합니다! 당신은 악명높은 던전을 돌파하였습니다!");
					sleeps(500);
					System.out.println();
					System.out.println();
					System.out.println(p1.toString());
					System.out.println();
					sleeps(500);
				}
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
		// 임시 맵 생성 기능
		int rand = (int)(Math.random()*10)+1;
		switch(rand)
		{
		case 1:
		case 5:
		case 6:
			map.stage1map1();
			break;
		case 2:
		case 7:
			map.stage1map2();
			break;
		case 3:
		case 8:
		case 10:
			map.stage1map3();
			break;
		case 4:
		case 9:
			map.stage1map4();
			break;
		}
		sleeps(2000);
		System.out.println();
		// 몹 생성
		Units mob = mob();
		System.out.println();
		// 전투 실행.
		fight(p1,mob);
		// 다음 층 진행 > 층수 +
		p1.setProgress(p1.getProgress()+1);
				
			
	}
	
	private Units mob() // 임시 몹 랜덤 생성 기능
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
	
	private void fight(PlayerVO p1, Units mob) // 전투 기능
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
			sleeps(2000);
			System.out.println();
			mob.toString();
			sleeps(1000);
			System.out.println();
			showInfo(p1);
			
			sleeps(2000);
			
			System.out.println("== 당신의 차례 ==");
			
			List<CardVO> selectCard5 = new ArrayList<CardVO>(); // 전체 소지카드 중에서 5개를 랜덤으로 추출해낸 리스트.
			selectCard5 = allCards;
			
			System.out.println();
			System.out.println("== 이번 턴의 카드 ==");
			for(CardVO card : selectCard5) // 카드 5개를 보여줌.
			{
				sleeps(500);
				System.out.println(card.toString());
			}
			sleeps(500);
			System.out.println();
			
			System.out.println("== 1 - 5번 카드 중에서 선택 > ");
			int select = Integer.parseInt(scn.nextLine());
			CardVO selectCard = selectCard5.get(select-1);
			
			sleeps(500);
			System.out.println();
			System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
			System.out.println("== "+selectCard.getReadme());
			System.out.println();
			
			p1Att = p1.getAttack()+selectCard.getAttack();
			p1Def = p1.getDefense()+selectCard.getDefense();
			p1.setDefense(p1Def);
			if(p1.getAttack()!=p1Att)
			{
				mobHp -= (p1Att-mobDef);
			}
			mob.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
			
			if(mob.getHp() > 0)
			{
				sleeps(1000);
				System.out.println();
				mob.toString();
				System.out.println();
				sleeps(1000);
				
				System.out.println("== "+mob.getName()+"의 차례 ==");
				System.out.println();
				// 랜덤하게 행동 결정
				int rand1 = (int)(Math.random()*2);
				if(rand1==0)
				{
					int mobAtt1 = mob.mobAttack();
					p1Hp -= (mobAtt1-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				else if(rand1==1)
				{
					mobDef = mob.mobDefense();
					mob.setDefense(mobDef);
					sleeps(1000);
				}
			}
			else
			{
				sleeps(1000);
				int getMoney = mob.die();
				p1.setMoney(p1.getMoney()+getMoney);
				t= false;
			}
			
			if(p1Hp <= 0)
			{
				System.out.println("== "+p1.getUserName()+"은/는 장렬하게 사망하였습니다...");
				System.out.println("== Game Over == ");
				return;
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("== 전투 종료 ==");
		sleeps(1000);
		showInfo(p1);
		sleeps(1000);
	}
	
	private void showInfo(PlayerVO p1)
	{
		p1.toString();
	}
	
	private List<CardVO> generateCards() // 처음 카드리스트 생성
	{
		List<CardVO> cardList = new ArrayList<CardVO>();
		cardList.add(new CardAttack(0));
		cardList.add(new CardAttack(1));
		cardList.add(new CardAttack(2));
		cardList.add(new CardDefense(3));
		cardList.add(new CardDefense(4));
		
		return cardList;
	}
	
	private void sleeps(int second)
	{
		try 
		{
			sleep(second);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void stairs()
	{
		while(true)
		{
			System.out.println("== 현재 층은 "+(p1.getProgress()+1)+"층 입니다...");
			System.out.println();
			System.out.println("== 1. 다음층으로 올라가기");
			System.out.println("== 2. 강화");
			System.out.println("== 3. 게임 저장");
			System.out.println();
	
			try 
			{
				int select = Integer.parseInt(scn.nextLine());
				if(select == 1)
				{
					break;
				}
				else if(select == 2)
				{
					System.out.println("====  강  화  ====");
					System.out.println();
					sleeps(1000);
					System.out.println("== 소지중인 카드 목록 >");
					for(int i=0; i<allCards.size(); i++) //전체 소지카드 보여주기
					{
						sleeps(500);
						System.out.println((i+1)+"번 : "+allCards.get(i).toString());
					}
					System.out.println();
					System.out.println("== 강화할 카드");
					int enforce = Integer.parseInt(scn.nextLine());
					sleeps(500);
					
					if(allCards.get(enforce-1).getAttack()!=0) // 공격카드일 시, 강화
					{
						allCards.get(enforce-1).setAttack((allCards.get(enforce-1).getAttack()+15));
						System.out.println("== 공격류 카드 강화 완료!  ==");
						System.out.println();
						System.out.println("강화 후 카드의 공격력 : "+allCards.get(enforce-1).getAttack());
						System.out.println();
					}
					else
					{
						allCards.get(enforce-1).setDefense((allCards.get(enforce-1).getDefense()+10));
						System.out.println("== 방어류 카드 강화 완료!  ==");
						System.out.println();
						System.out.println("강화 후 카드의 방어력 : "+allCards.get(enforce-1).getDefense());
						System.out.println();
					}
				}
				else if(select == 3)
				{
					// p1 객체 db에 저장 > 게임 저장
				}
				else
				{
					System.out.println("== 올바르게 입력하세요...");
				}
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("== 올바르게 입력하세요...");
			}
			sleeps(500);
		}
	}
	
	private void climbUpBoss()
	{
		map.stage1map10();
		sleeps(2000);
		System.out.println();
		// 몹 생성
		Boss1 boss1 = new Boss1(1000, 20, 30, 100);
		System.out.println();
		fightBoss(p1,boss1);
		p1.setProgress(p1.getProgress()+1);
	}
	
	private void fightBoss(PlayerVO p1, Units boss) // 보스전 전투 기능
	{
		System.out.println();
		System.out.println("== 보스전 전투 개시 ==");
		System.out.println();
		boolean t = true;
		int mobHp = boss.getHp();
		int mobDef = boss.getDefense();
		int p1Hp = p1.getHp();
		int p1Att = p1.getAttack();
		int p1Def = p1.getDefense();
		while(t)
		{
			sleeps(1000);
			System.out.println();
			boss.toString();
			sleeps(1000);
			System.out.println();
			showInfo(p1);
			
			sleeps(1000);
			
			System.out.println("== 당신의 차례 ==");
			
			List<CardVO> selectCard5 = new ArrayList<CardVO>(); // 전체 소지카드 중에서 5개를 랜덤으로 추출해낸 리스트.
			selectCard5 = allCards;
			
			System.out.println();
			System.out.println("== 이번 턴의 카드 ==");
			for(CardVO card : selectCard5) // 카드 5개를 보여줌.
			{
				sleeps(500);
				System.out.println(card.toString());
			}
			sleeps(500);
			System.out.println();
			
			System.out.println("== 1 - 5번 카드 중에서 선택 > ");
			int select = Integer.parseInt(scn.nextLine());
			CardVO selectCard = selectCard5.get(select-1);
			
			sleeps(500);
			System.out.println();
			System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
			System.out.println("== "+selectCard.getReadme());
			System.out.println();
			sleeps(500);
			
			p1Att = p1.getAttack()+selectCard.getAttack();
			p1Def = p1.getDefense()+selectCard.getDefense();
			p1.setDefense(p1Def);
			if(p1.getAttack()!=p1Att)
			{
				mobHp -= (p1Att-mobDef);
			}
			boss.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
			
			if(boss.getHp() > 0)
			{
				sleeps(500);
				System.out.println();
				boss.toString();
				System.out.println();
				sleeps(1000);
				
				System.out.println("== "+boss.getName()+"의 차례 ==");
				System.out.println();
				// 랜덤하게 행동 결정
				int rand1 = (int)(Math.random()*10);
				if(rand1<=0 && rand1<=3)
				{
					int mobAtt = boss.mobAttack();
					p1Hp -= (mobAtt-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				else if(rand1<=4 && rand1<=6)
				{
					mobDef = boss.mobDefense();
					boss.setDefense(mobDef);
					sleeps(1000);
				}
				else if(rand1==7 || rand1==8)
				{
					int mobAtt = ((Boss1) boss).bossAttack();
					p1Hp -= (mobAtt-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				else if(rand1==9)
				{
					int mobAtt = ((Boss1) boss).bossUltimate();
					p1Hp -= (mobAtt-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
			}
			else
			{
				sleeps(1000);
				int getMoney = boss.die();
				p1.setMoney(p1.getMoney()+getMoney);
				ItemVO dropItem = ((Boss1) boss).specialDrop(); // 랜덤 아이템 드랍.
				t= false;
			}
			
			if(p1Hp <= 0)
			{
				System.out.println("== "+p1.getUserName()+"은/는 장렬하게 사망하였습니다...");
				System.out.println("== Game Over == ");
				return;
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("== 전투 종료 ==");
		sleeps(1000);
		showInfo(p1);
		sleeps(1000);
	}
}
