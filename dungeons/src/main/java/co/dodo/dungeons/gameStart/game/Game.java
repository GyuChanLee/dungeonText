package co.dodo.dungeons.gameStart.game;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import co.dodo.dungeons.cards.CardAttack;
import co.dodo.dungeons.cards.CardDefense;
import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.items.ItemList;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.maps.MapStage1;
import co.dodo.dungeons.maps.MapStage2;
import co.dodo.dungeons.maps.Maps;
import co.dodo.dungeons.maps.Stages;
import co.dodo.dungeons.units.Boss;
import co.dodo.dungeons.units.Boss1;
import co.dodo.dungeons.units.Boss2;
import co.dodo.dungeons.units.Oak;
import co.dodo.dungeons.units.Units;
import co.dodo.dungeons.vo.PlayerVO;

public class Game extends Thread // 게임 구현 
{	
	private Scanner scn = new Scanner(System.in);
	private PlayerVO p1 = new PlayerVO("탐험가",1234,0,0,3,30,30, 0,999);
	private List<CardVO> allCards = generateCards();
	private Maps map;
	private Stages stage = new Stages();
	private ItemList items = new ItemList(); 
	private List<ItemVO> inventory = new ArrayList<ItemVO>();
	private ItemVO[] equipment = new ItemVO[2];


	
	private void game()
	{
		items.makeAllItem();
		inventory.add(items.getItem(0));
		inventory.add(items.getItem(1));
		inventory.add(items.getItem(2));
		inventory.add(items.getItem(3));
		
		boolean t = true;
		while(t)
		{
			// 던전탐험 첫시작.
			if(p1.getProgress()>=0 && p1.getProgress()<10)
			{
				sleeps(1000);
				stage.stage1();
				if(map == null)
				{
					map = new MapStage1();
				}
			}
			else if(p1.getProgress()>=10 && p1.getProgress()<20)
			{
				sleeps(1000);
				stage.stage2();
				if(map==null)
				{
					map = new MapStage2();
				}
			}	
			
			sleeps(500);
			System.out.println("== 1. 들어가기.");
			System.out.println("== 2. 나가기.");
			System.out.println("== 3. 게임저장.");
			int select = Integer.parseInt(scn.nextLine());
			
			if(select == 3)
			{
				System.out.println("== 게임 저장 완료! ");
			}
			else if(select == 2)
			{
				System.out.println("== 집으로 돌아갑니다...");
				t =false;
			}
			else if(select == 1)
			{
				for(int i=0; i<10; i++)
				{
					if(p1.getProgress()==4) // 진행상황 5층 > 특별 이벤트 방.
					{
						stairs();
						climbUpEvent();
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
				
				if(p1.getProgress()==10) // 스테이지 클리어 시, 완료메시지+자동저장기능 
				{
					sleeps(500);
					map.cong();
					sleeps(500);
					System.out.println();
					p1.toString();
					System.out.println();
					System.out.println();
					System.out.println();
					sleeps(1000);
				}
				if(p1.getProgress()==20)
				{
					sleeps(500);
					map.cong();
					sleeps(500);
					System.out.println();
					p1.toString();
					System.out.println();
					System.out.println();
					System.out.println();
					sleeps(1000);
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
			map.map1();
			break;
		case 2:
		case 7:
			map.map2();
			break;
		case 3:
		case 8:
		case 10:
			map.map3();
			break;
		case 4:
		case 9:
			map.map4();
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
		int p1Att = 0;
		int p1Def = 0;
		int allAtt = p1.getAttack();
		int allDef = p1.getDefense();
		int p1Action = p1.getAction();
		// 힐 / 투척무기
		int heal = 0;
		int instanceDamage = 0;
		
		while(t)
		{
			sleeps(2000);
			System.out.println();
			mob.toString();
			sleeps(1000);
			System.out.println();
			p1.toString();
			
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
			while(p1Action>0)
			{
				System.out.println("== 1 - 5번 카드 중에서 선택 > ");
				System.out.println("== 아이템 사용 : 6번 > ");
				System.out.println("== ");
				System.out.println("== 현재 행동력 : "+ p1Action);
				System.out.println("== ");
				int select = Integer.parseInt(scn.nextLine());
				CardVO selectCard = null;
				if(select!=6)
				{
					selectCard = selectCard5.get(select-1);
				}
				if(select==6) // 전투용 아이템 사용.
				{
					while(true)
					{
						showInvenFight();
						System.out.println();
						System.out.println("== 쓰고싶은 아이템의 이름을 적으세요 > ");
						System.out.println("== 뒤로가기 : \"back\" 입력 > ");
						String itemSelect = scn.nextLine();
						boolean yes = false;
						for(int i=0; i<inventory.size();i++)
						{
							if(itemSelect.equals(inventory.get(i).getItemName()))
							{
								System.out.println("== "+inventory.get(i).getItemName()+"을 선택");
								System.out.println("== "+inventory.get(i).getReadme());
								if(inventory.get(i).getInstantDamage()>0)
								{
									instanceDamage = inventory.get(i).getInstantDamage();
								}
								else if(inventory.get(i).getInstantDamage()<0)
								{
									heal = -inventory.get(i).getInstantDamage();
								}
								inventory.remove(i);
								yes = true;
							}
						}
						if(itemSelect.equals("back"))
						{
							break;
						}
						
						if(yes==false)
						{
							System.out.println("== 그런 이름의 아이템을 찾을 수 없습니다...");
						}
						else
						{
							break;
						}
					}
				}
				else
				{
					sleeps(500);
					System.out.println();
					System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
					System.out.println("== "+selectCard.getReadme());
					System.out.println();
					sleeps(500);
					p1Att = selectCard.getAttack();
					allAtt += p1Att;
					p1Def = selectCard.getDefense();
					allDef += p1Def;
				}
				if(selectCard!=null)
				{
					p1Action -= selectCard.getActionConsumption();
				}
			}
			
			p1.setDefense(allDef);
			p1.setHp(p1.getHp()+heal);
			mobHp -= instanceDamage;
			mobHp -= (allAtt-mobDef*3);
			mob.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
			
			if(mob.getHp() > 0) // 몹이 살았을 때 행동패턴.
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
				// p1 행동력 회복
				p1Action = p1.getAction();
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
		p1.toString();
		sleeps(1000);
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
//		try 
//		{
//			sleep(second);
//		} 
//		catch (InterruptedException e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	private void stairs()
	{
		int reinforce = 1;
		while(true)
		{
			System.out.println("== 현재 층은 "+(p1.getProgress()+1)+"층 입니다...");
			System.out.println();
			System.out.println("== 1. 다음층으로 올라가기");
			System.out.println("== 2. 강화");
			System.out.println("== 3. 인벤토리");
			System.out.println("== 4. 게임 저장");
			System.out.println();
	
			try 
			{
				int select = Integer.parseInt(scn.nextLine());
				if(select == 1)
				{
					break;
				}
				else if(select == 2 && reinforce == 1)
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
					reinforce--;
				}
				else if(select == 3)
				{
					showInven();
					editEquipment();
				}
				else if(select == 4)
				{
					// p1 객체 db에 저장 > 게임 저장
				}
				else if(reinforce!=1)
				{
					System.out.println();
					System.out.println("== 이미 강화를 한번 하셨습니다...");
					System.out.println();
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
		map.map10();
		sleeps(2000);
		System.out.println();
		// 몹 생성
		if(p1.getProgress()==9)
		{
			Boss boss = new Boss1(1000, 40, 30, 150);
			System.out.println();
			fightBoss(p1,boss);
		}
		else if(p1.getProgress()==19)
		{
			Boss boss = new Boss2(2000, 50, 50, 200);
			System.out.println();
			fightBoss(p1,boss);
		}
		
		p1.setProgress(p1.getProgress()+1);
	}
	
	private void fightBoss(PlayerVO p1, Boss boss) // 보스전 전투 기능
	{
		System.out.println();
		System.out.println("== 보스전 전투 개시 ==");
		System.out.println();
		boolean t = true;
		int mobHp = boss.getHp();
		int mobDef = boss.getDefense();
		int p1Hp = p1.getHp();
		int p1Att = 0;
		int p1Def = 0;
		int allAtt = p1.getAttack();
		int allDef = p1.getDefense();
		int p1Action = p1.getAction();
		// 힐 / 투척무기
		int heal = 0;
		int instanceDamage = 0;
		
		while(t)
		{
			sleeps(2000);
			System.out.println();
			boss.toString();
			sleeps(1000);
			System.out.println();
			p1.toString();
			
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
			while(p1Action>0)
			{
				System.out.println("== 1 - 5번 카드 중에서 선택 > ");
				System.out.println("== 아이템 사용 : 6번 > ");
				System.out.println("== ");
				System.out.println("== 현재 행동력 : "+ p1Action);
				System.out.println("== ");
				int select = Integer.parseInt(scn.nextLine());
				CardVO selectCard = selectCard5.get(select-1);
				if(select==6) // 전투용 아이템 사용.
				{
					while(true)
					{
						showInvenFight();
						System.out.println();
						System.out.println("== 쓰고싶은 아이템의 이름을 적으세요 > ");
						System.out.println("== 뒤로가기 : \"back\" 입력 > ");
						String itemSelect = scn.nextLine();
						boolean yes = false;
						for(int i=0; i<inventory.size();i++)
						{
							if(itemSelect.equals(inventory.get(i).getItemName()))
							{
								System.out.println("== "+inventory.get(i).getItemName()+"을 선택");
								System.out.println("== "+inventory.get(i).getReadme());
								if(inventory.get(i).getInstantDamage()>0)
								{
									instanceDamage = inventory.get(i).getInstantDamage();
								}
								else if(inventory.get(i).getInstantDamage()<0)
								{
									heal = -inventory.get(i).getInstantDamage();
								}
								inventory.remove(i);
								yes = true;
							}
						}
						if(itemSelect.equals("back"))
						{
							break;
						}
						
						if(yes==false)
						{
							System.out.println("== 그런 이름의 아이템을 찾을 수 없습니다...");
						}
						else
						{
							break;
						}
					}
				}
				else
				{
					sleeps(500);
					System.out.println();
					System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
					System.out.println("== "+selectCard.getReadme());
					System.out.println();
					sleeps(500);
					p1Att = selectCard.getAttack();
					allAtt += p1Att;
					p1Def = selectCard.getDefense();
					allDef += p1Def;
				}
				if(selectCard!=null)
				{
					p1Action -= selectCard.getActionConsumption();
				}
			}
			
			p1.setDefense(allDef);
			p1.setHp(p1.getHp()+heal);
			mobHp -= instanceDamage;
			mobHp -= (allAtt-mobDef*3);
			boss.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
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
				else if(rand1<=4 && rand1<=5)
				{
					mobDef = boss.mobDefense();
					boss.setDefense(mobDef);
					sleeps(1000);
				}
				else if(rand1==6 || rand1==7)
				{
					int mobAtt = boss.bossAttack();
					p1Hp -= (mobAtt-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				else if(rand1==8 || rand1==9)
				{
					int mobAtt = boss.bossUltimate();
					p1Hp -= (mobAtt-p1Def);
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				p1Action = p1.getAction();
			}
			else
			{
				sleeps(1000);
				int getMoney = boss.die();
				p1.setMoney(p1.getMoney()+getMoney);
				ItemVO dropItem = specialDrop(); // 랜덤 아이템 드랍.
				inventory.add(dropItem);
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
		p1.toString();
		sleeps(1000);
	}
	
	private void climbUpEvent() // 5층 이벤트 방 생성
	{
		System.out.println();
		map.map5();
		System.out.println();
		System.out.println();
		sleeps(1000);
		int rand = (int)(Math.random()*3); // 3가지 경우의 수
		
		if(rand==1)
		{
			// 상점 이벤트
			System.out.println("== 수상한 망토를 쓰고 보따리를 풀고 있는 상인을 만났다...");
			System.out.println("== ");
			System.out.println("== 상인 : 어서오세요.. 없는 것 빼고 다 있답니다...");
			System.out.println();
			sleeps(500);
			// 여러가지 아이템 / 장비들 중 10가지를 랜덤하게 생성해서 판매하기. 5번은 나가기.
			boolean t = true;
			boolean check1 = false;
			boolean check2 = false;
			boolean check3 = false;
			boolean check4 = false;
			while(t)
			{
				int randbuy = (int)(Math.random()*items.getItemList().size());
				System.out.println("== 판매 중인 아이템 및 장비 목록");
				ItemVO sell1 = items.getItem(randbuy);
				ItemVO sell2 = items.getItem(randbuy);
				ItemVO sell3 = items.getItem(randbuy);
				ItemVO sell4 = items.getItem(randbuy);
				System.out.println();
				System.out.print("1번 상품 == ");
				if(check1==true)
				{
					System.out.println(" 매 진 ");
				}
				else
				{
					sell1.toString();
				}
				System.out.println();
				System.out.print("2번 상품 == ");
				if(check2==true)
				{
					System.out.println(" 매 진 ");
				}
				else
				{
					sell2.toString();
				}
				System.out.println();
				System.out.print("3번 상품 == ");
				if(check3==true)
				{
					System.out.println(" 매 진 ");
				}
				else
				{
					sell3.toString();
				}
				System.out.println();
				System.out.print("4번 상품 == ");
				if(check4==true)
				{
					System.out.println(" 매 진 ");
				}
				else
				{
					sell4.toString();
				}
				System.out.println();
				System.out.println("== 선택 > ");
				int buy = Integer.parseInt(scn.nextLine());
				System.out.println();
				if(buy==1)
				{
					if(sell1.getPrice()>p1.getMoney())
					{
						System.out.println("== 상인 : 돈이 부족하군요...");
						System.out.println();
					}
					else
					{
						p1.setMoney(p1.getMoney()-sell1.getPrice());
						inventory.add(sell1);
						check1 = true;
					}
				}
				else if(buy==2)
				{
					if(sell2.getPrice()>p1.getMoney())
					{
						System.out.println("== 상인 : 돈이 부족하군요...");
						System.out.println();
					}
					else
					{
						p1.setMoney(p1.getMoney()-sell2.getPrice());
						inventory.add(sell2);
						check2 = true;
					}
				}
				else if(buy==3)
				{
					if(sell3.getPrice()>p1.getMoney())
					{
						System.out.println("== 상인 : 돈이 부족하군요...");
						System.out.println();
					}
					else
					{
						p1.setMoney(p1.getMoney()-sell3.getPrice());
						inventory.add(sell3);
						check3 = true;
					}
				}
				else if(buy==4)
				{
					if(sell4.getPrice()>p1.getMoney())
					{
						System.out.println("== 상인 : 돈이 부족하군요...");
						System.out.println();
					}
					else
					{
						p1.setMoney(p1.getMoney()-sell4.getPrice());
						inventory.add(sell4);
						check4 = true;
					}
				}
				else if(buy==5)
				{
					System.out.println("== 상인 : 벌써 가시게요..? 다음에 봅시다...");
					System.out.println("== ");
					p1.setProgress(p1.getProgress()+1);
					return;
				}
			}
		}
		else if(rand==2)
		{
			// hp 빠지고, 이득 얻는 이벤트
			System.out.println("== 날카로운 가시가 널려있는 가시밭에 무엇인가 반짝이는게 보인다...");
			System.out.println("== ");
			System.out.println("== 손을 넣으면 상당히 아플것 같지만 저 반짝이는 것은 그정도 가치가 있을지도 모른다...");
			System.out.println();
			boolean t =true;
			while(p1.getHp()>10||t)
			{
				System.out.println("== 현재 당신의 hp : "+p1.getHp());
				System.out.println("== 1. 손을 넣어 집어본다( hp -10 )  2. 위험을 감수하지 않는다 ");
				System.out.println("== 선택 > ");
				try
				{
					int sel = Integer.parseInt(scn.nextLine());
					if(sel==1)
					{
						System.out.println();
						System.out.println("== 저 반짝이는 것을 포기할 수 없어! 내 보물! ");
						System.out.println();
						p1.setHp(p1.getHp()-10);
						
						int thorn = (int)(Math.random()*3);
						switch(thorn)
						{
						case 0:
							sleeps(500);
							System.out.println("== 아프지만 반짝이는 것을 한움큼 쥐어냈다...");
							System.out.println();
							sleeps(1000);
							System.out.println("== 대박이다! 2000 골드를 얻었다! ");
							p1.setMoney(p1.getMoney()+5000);
							System.out.println();
							System.out.println("== 피에 물든 전리품을 들고 기쁜 마음으로 방을 나간다...");
							System.out.println();
							p1.setProgress(p1.getProgress()+1);
							return;
						case 1:
							sleeps(500);
							System.out.println("== 아프지만 반짝이는 것을 한움큼 쥐어냈다...");
							System.out.println();
							sleeps(1000);
							System.out.println("== 대박이다! 아이템을 얻었다! ");
//							나중에 랜덤 아이템 얻는 기능 넣기.
							System.out.println();
							System.out.println("== 피에 물든 전리품을 들고 기쁜 마음으로 방을 나간다...");
							System.out.println();
							p1.setProgress(p1.getProgress()+1);
							return;
						case 2:
							sleeps(500);
							System.out.println("== 악! 손이 너무 아파 아무 소득없이 가시밭에서 손을 빼냈다...");
							System.out.println("== 당신은 피투성이 손만 얻게되었습니다...");
							System.out.println();
							sleeps(1000);
							System.out.println("== 고통을 참고 한번만 더 보면 어떨까 ?");
							break;
						}
					}
					else if(sel==2)
					{
						System.out.println();
						System.out.println("== 뭐가 나올지도 모르는데 위험을 감수할 수는 없다...");
						System.out.println("== 당신은 그대로 이 방을 나섭니다...");
						System.out.println();
						p1.setProgress(p1.getProgress()+1);
						return;
					}
					else
					{
						System.out.println("== 제대로 입력하세요...");
					}
				}
				catch (NumberFormatException e) 
				{
					System.out.println("== 제대로 입력하세요...");
				}
				
			}
		}
		else
		{
			// 그냥 이득보는 이벤트
			System.out.println("== 행운의 방! 이라고 쓰여져 있다. ");
			System.out.println("== 푯말 : 버튼을 누르면 랜덤하게 당신에게 도움이 될 물건이 주어집니다!!!");
			System.out.println("== ");
			sleeps(500);
			System.out.println("== 당신은 밑져도 본전이라는 생각에 눌러본다...");
			sleeps(500);
			System.out.println("== 행운상자 돌리는 중...");
			sleeps(2000);
			int lucky = (int)(Math.random()*3);
			if(lucky==0) // 랜덤 돈 
			{
				int money = (int)(Math.random()*4500)+500;
				System.out.println();
				System.out.println("== "+money+"원을 획득하셨습니다!!! ");
				p1.setMoney(p1.getMoney()+money);
				System.out.println("== 현재 소지금 : "+p1.getMoney());
			}
			else if(lucky==1) // 랜덤 장비
			{
				System.out.println();
				System.out.println("== ");
			}
			else // hp 회복
			{
				System.out.println();
				System.out.println("== ");
			}
		}
		System.out.println("== ");
		System.out.println("== 예상치 않았던 전리품을 들고 기쁜 마음으로 방을 나간다...");
		System.out.println();
		p1.setProgress(p1.getProgress()+1);
		
	}
	
	private ItemVO specialDrop()
	{
		int rand = (int)(Math.random()*10);
		if(rand >= 0 && rand <= 2)
		{
			// 랜덤으로 아이템 생성
			int randItem = (int)(Math.random()*items.getItemList().size());
			return items.getItemList().get(randItem);
		}
		else
		{
			return null;
		}
	}
	
	private void showInven()
	{
		System.out.println("== 당신의 인벤토리 목록 ");
		System.out.println();
		int i = 1;
		for(ItemVO item : inventory)
		{
			System.out.print(i+"번째 ");
			item.toString();
			System.out.println();
			i++;
		}
		System.out.println();
	}
	
	private void showInvenFight()
	{
		System.out.println("== 당신의 인벤토리 목록 ");
		System.out.println();
		for(ItemVO item : inventory)
		{
			if(item.getInstantDamage()!=0)
			{
				sleeps(500);
				item.toString();
			}
		}
	}
	
	private void showEquipment()
	{
		System.out.println("== 착용 장비");
		System.out.println();
		System.out.println("== ");
		if(equipment[0]!=null)
		{
			equipment[0].toString();
			System.out.println();
		}
		if(equipment[1]!=null)
		{
			equipment[1].toString();
			System.out.println();
		}
		System.out.println();
	}
	
	private void editEquipment()
	{
		System.out.println("== 착용 장비를 관리하려면 0 입력 > ");
		System.out.println("== 거부 : 아무 숫자 > ");
		int equip = Integer.parseInt(scn.nextLine());
		if(equip==0)
		{
			showEquipment();
			System.out.println();
			System.out.println("== 변경할 착용장비를 입력 ( 1: 무기, 2 : 방어구, 3 : 취소 ) > ");
			System.out.println();
			int changeOld = Integer.parseInt(scn.nextLine());
			if(changeOld==1)
			{
				showInven();
				System.out.println("== 대신 착용할 무기를 입력 ( 취소 : 0번 ) > ");
				int changeNew = Integer.parseInt(scn.nextLine());
				ItemVO chageItem = inventory.get(changeNew-1);
				if(chageItem.getAttack()==0)
				{
					System.out.println("== 무기류를 선택하십시오...");
				}
				else
				{
					if(equipment[0]!=null)
					{
						p1.setAttack(p1.getAttack()-equipment[0].getAttack());
					}
					equipment[0] = chageItem; // 무기칸 변경
					p1.setAttack(p1.getAttack()+equipment[0].getAttack());
				}
			}
			else if(changeOld==2)
			{
				showInven();
				System.out.println("== 대신 착용할 방어구를 입력 ( 취소 : 0번 ) > ");
				int changeNew = Integer.parseInt(scn.nextLine());
				ItemVO chageItem = inventory.get(changeNew-1);
				if(chageItem.getDefense()==0)
				{
					System.out.println("== 방어구류를 선택하십시오...");
				}
				else
				{
					if(equipment[1]!=null)
					{
						p1.setDefense(p1.getDefense()-equipment[1].getDefense());
					}
					equipment[1] = chageItem; // 방어구칸 변경
					p1.setDefense(p1.getDefense()+equipment[1].getDefense());
				}
			}
		}
		else
		{
			System.out.println();
		}
	}
}
