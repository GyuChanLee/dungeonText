package co.dodo.dungeons.gameStart.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.dodo.dungeons.cards.CardList;
import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.gameStart.Mains;
import co.dodo.dungeons.items.ItemList;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.maps.MapStage1;
import co.dodo.dungeons.maps.MapStage2;
import co.dodo.dungeons.maps.MapStage3;
import co.dodo.dungeons.maps.MapStage4;
import co.dodo.dungeons.maps.Maps;
import co.dodo.dungeons.maps.Stages;
import co.dodo.dungeons.service.SaveFiles;
import co.dodo.dungeons.service.SaveFilesImpl;
import co.dodo.dungeons.units.Boss;
import co.dodo.dungeons.units.BossList;
import co.dodo.dungeons.units.UnitList;
import co.dodo.dungeons.units.Units;
import co.dodo.dungeons.vo.PlayerVO;

public class Game extends Thread // 게임 구현 
{	
	private SaveFiles sf = new SaveFilesImpl();
	private Scanner scn = new Scanner(System.in);
	private Maps map;
	private Stages stage = new Stages();
	private ItemList items = new ItemList(); // 아이템 객체 구현
	private CardList cards = new CardList(); // 카드 객체 구현
	private List<ItemVO> inventory = new ArrayList<ItemVO>();
	private ItemVO[] equipment = new ItemVO[2];
	private PlayerVO p1;
	private List<CardVO> allCards;
	private boolean no = true;
	private BossList bossLists = new BossList();
	private UnitList unitLists = new UnitList();
	private Mains main = new Mains();

	
	private void game()
	{
		checkLogin();
		if(no==false) // 로그인 체크
		{
			return;
		}
		items.makeAllItem();
		bossLists.loadBossList();
		unitLists.loadUnitList();
		allCards = sf.CardListSelect(p1); // 유저가 가진 카드리스트 불러오기.
		if(allCards.isEmpty() && p1.getProgress()==0)
		{
			main.Card5(p1); // 죽어서 초기화되었을 때, 초기 5개 기본 카드 다시 증정.
			allCards = sf.CardListSelect(p1);
		}
		// 플레이어가 착용하던 장비 불러오기 기능.
		equipment = sf.equipLoad(p1);
		// 저장해둔 인벤토리 나오게 하기
		inventory  = sf.showInven(p1);
		
		boolean t = true;
		while(t)
		{
			// 던전탐험 첫시작.
			if(p1.getProgress()>=0 && p1.getProgress()<10) // 진행상황에 따라 맵 선택
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
			else if(p1.getProgress()>=20 && p1.getProgress()<30)
			{
				sleeps(1000);
				stage.stage3();
				if(map==null)
				{
					map = new MapStage3();
				}
			}
			else if(p1.getProgress()>=30 && p1.getProgress()<40)
			{
				sleeps(1000);
				stage.stage4();
				if(map==null)
				{
					map = new MapStage4();
				}
			}
			else // 모두 클리어
			{
				sleeps(1000);
				System.out.println("== ");
				System.out.println("== 모든 던전들을 탐험하셨습니다!");
				System.out.println();
				System.out.println();
				System.out.println("██████╗ ██╗   ██╗███╗   ██╗ ██████╗ ███████╗ ██████╗ ███╗   ██╗     ██████╗██╗     ███████╗ █████╗ ██████╗ ██╗██╗\r\n"
						+ "██╔══██╗██║   ██║████╗  ██║██╔════╝ ██╔════╝██╔═══██╗████╗  ██║    ██╔════╝██║     ██╔════╝██╔══██╗██╔══██╗██║██║\r\n"
						+ "██║  ██║██║   ██║██╔██╗ ██║██║  ███╗█████╗  ██║   ██║██╔██╗ ██║    ██║     ██║     █████╗  ███████║██████╔╝██║██║\r\n"
						+ "██║  ██║██║   ██║██║╚██╗██║██║   ██║██╔══╝  ██║   ██║██║╚██╗██║    ██║     ██║     ██╔══╝  ██╔══██║██╔══██╗╚═╝╚═╝\r\n"
						+ "██████╔╝╚██████╔╝██║ ╚████║╚██████╔╝███████╗╚██████╔╝██║ ╚████║    ╚██████╗███████╗███████╗██║  ██║██║  ██║██╗██╗\r\n"
						+ "╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝\r\n"
						+ "                                                                                                                 \r\n"
						+ "");
				sleeps(1000);
				System.out.println();
				System.out.println();
				System.out.println("== 세이브파일을 저장하시겠습니까? ");
				System.out.println("== 1. 세이브  |  2. 나가기 >");
				try
				{
					int save = Integer.parseInt(scn.nextLine());
					if(save == 1)
					{
						saveGame();
						System.out.println("");
						System.out.println("== 세이브 완료! ");
						System.out.println("");
					}
				} 
				catch (Exception e) 
				{}
				return;
			}
			
			sleeps(500);
			System.out.println("== 1. 들어가기.");
			System.out.println("== 2. 나가기.");
			System.out.println("== 3. 게임저장.");
			int select = Integer.parseInt(scn.nextLine());
			
			if(select == 3)
			{
				saveGame();
				sleeps(500);
				System.out.println("== 게임 저장 완료! ");
			}
			else if(select == 2)
			{
				System.out.println("== 집으로 돌아갑니다...");
				t =false;
			}
			else if(select == 1)
			{
				while(true)
				{
					if((p1.getProgress()%10)==4) // 진행상황 5층 > 특별 이벤트 방.
					{
						int pause = stairs();
						if(pause == 1)
						{
							return;
						}
						climbUpEvent();
					}
					else if((p1.getProgress()%10)==9) // 10층 > 보스방
					{
						int pause = stairs();
						if(pause == 1)
						{
							return;
						}
						climbUpBoss();
					}
					else
					{
						int pause = stairs();
						if(pause == 1)
						{
							return;
						}
						climbUp();
					}
					
					if(p1.getHp() <= 0) // hp가 0. 사망해서 게임 끝나서 나왔을 때, 초기화하기.
					{
						p1.setProgress(0);
						p1.setKills(0);
						p1.setHp(100);
						p1.setKills(0);
						p1.setAttack(15);
						p1.setDefense(5);
						p1.setMoney(0);
						saveGame(); // 죽으면 캐릭터 객체 초기화
						// 인벤토리 초기화
						for(int i = 0; i < inventory.size(); i++)
						{
							sf.itemDelete(inventory.get(i)); // db에 있는 인벤토리 아이템 삭제.
						}
						inventory = sf.showInven(p1); // db에 비어있는 인벤 반환
						// 카드리스트 초기화
						for(int i = 0; i < allCards.size(); i++)
						{
							sf.cardDelete(allCards.get(i)); // db에 있는 카드리스트 카드 삭제.
						}
						allCards = sf.CardListSelect(p1); // db에 비어있는 카드리스트 반환
						return;
					}
					if((p1.getProgress()%10)==0)
					{
						break;
					}
				}
				
				if(p1.getProgress()==10) // 스테이지 클리어 시, 완료메시지
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
				else if(p1.getProgress()==20)
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
				else if(p1.getProgress()==30)
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
	
	private Units mob() // Stage에 맞춰 몹 랜덤 생성.
	{
		int randSel = (int)(Math.random()*5);
		Units mobs;
		if(p1.getProgress()<10) // 진행상황이 stage1일 시.
		{
			if(randSel==0)
			{
				if(p1.getProgress()>=0 && p1.getProgress() <= 4) // 0-3층일 때 약하게 리젠
				{
					mobs = unitLists.getUnit("오크", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("오크", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==1)
			{
				if(p1.getProgress()>=0 && p1.getProgress() <= 4) 
				{
					mobs = unitLists.getUnit("고블린", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("고블린", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==2)
			{
				if(p1.getProgress()>=0 && p1.getProgress() <= 4) 
				{
					mobs = unitLists.getUnit("걸어다니는 갑옷", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("걸어다니는 갑옷", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==3)
			{
				if(p1.getProgress()>=0 && p1.getProgress() <= 4) 
				{
					mobs = unitLists.getUnit("슬라임", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("슬라임", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==4)
			{
				if(p1.getProgress()>=0 && p1.getProgress() <= 4) 
				{
					mobs = unitLists.getUnit("뱀파이어", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("뱀파이어", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
		}
		else if(p1.getProgress()>=10 && p1.getProgress()<20) // 진행상황이 stage2일 시.
		{
			if(randSel==0)
			{
				if(p1.getProgress()>=10 && p1.getProgress() <= 14) 
				{
					mobs = unitLists.getUnit("해골", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("해골", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==1)
			{
				if(p1.getProgress()>=10 && p1.getProgress() <= 14) 
				{
					mobs = unitLists.getUnit("좀비", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("좀비", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==2)
			{
				if(p1.getProgress()>=10 && p1.getProgress() <= 14) 
				{
					mobs = unitLists.getUnit("마녀", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("마녀", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==3)
			{
				if(p1.getProgress()>=10 && p1.getProgress() <= 14) 
				{
					mobs = unitLists.getUnit("유령", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("유령", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==4)
			{
				if(p1.getProgress()>=10 && p1.getProgress() <= 14) 
				{
					mobs = unitLists.getUnit("감옥 파수꾼", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("감옥 파수꾼", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
		}
		else if(p1.getProgress()>=20 && p1.getProgress()<30) // 진행상황이 stage3일 시
		{
			if(randSel==0)
			{
				if(p1.getProgress()>=20 && p1.getProgress() <= 24) 
				{
					mobs = unitLists.getUnit("불의 정령", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("불의 정령", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==1)
			{
				if(p1.getProgress()>=20 && p1.getProgress() <= 24) 
				{
					mobs = unitLists.getUnit("화염 개", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("화염 개", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==2)
			{
				if(p1.getProgress()>=20 && p1.getProgress() <= 24) 
				{
					mobs = unitLists.getUnit("불탄 하수인", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("불탄 하수인", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==3)
			{
				if(p1.getProgress()>=20 && p1.getProgress() <= 24) 
				{
					mobs = unitLists.getUnit("화염술사", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("화염술사", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==4)
			{
				if(p1.getProgress()>=20 && p1.getProgress() <= 24) 
				{
					mobs = unitLists.getUnit("방화광", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("방화광", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
		}
		else if(p1.getProgress()>=30 && p1.getProgress()<40) // stage4.
		{
			if(randSel==0)
			{
				if(p1.getProgress()>=30 && p1.getProgress() <= 34) 
				{
					mobs = unitLists.getUnit("메두사", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("메두사", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==1)
			{
				if(p1.getProgress()>=30 && p1.getProgress() <= 34) 
				{
					mobs = unitLists.getUnit("리자드맨", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("리자드맨", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==2)
			{
				if(p1.getProgress()>=30 && p1.getProgress() <= 34) 
				{
					mobs = unitLists.getUnit("괴물 박쥐", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("괴물 박쥐", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==3)
			{
				if(p1.getProgress()>=30 && p1.getProgress() <= 34) 
				{
					mobs = unitLists.getUnit("임프", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("임프", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
			else if(randSel==4)
			{
				if(p1.getProgress()>=30 && p1.getProgress() <= 34) 
				{
					mobs = unitLists.getUnit("저주받은 검", 1);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
				else
				{
					mobs = unitLists.getUnit("저주받은 검", 2);
					System.out.println(mobs.getMAppear());
					return mobs;
				}
			}
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
		int p1Action = p1.getAction();
		int turn = 1;
		
		while(t)
		{
			int p1Att = 0;
			int p1Def = 0;
			int allAtt = 0;
			int allDef = p1.getDefense();
			int heal = 0;
			int instanceDamage = 0;
			int attackCount = 0;
			
			sleeps(500);
			System.out.println();
			mob.toString();
			sleeps(500);
			System.out.println();
			p1.toString();
			
			sleeps(2000);
			
			System.out.println("== "+turn+"번째 턴 =====================================================================================================================================");
			System.out.println("== 당신의 차례 ==");
			System.out.println();
			
			List<CardVO> selectCard5 = new ArrayList<CardVO>(); // 전체 소지카드 중에서 5개를 랜덤으로 추출해낸 리스트.
			for(int i=0; i<5; i++)
			{
				int ran = (int)(Math.random()*allCards.size());
				selectCard5.add(allCards.get(ran));
				for(int j=0; j<i; j++)
				{
					if(selectCard5.get(i)==selectCard5.get(j))
					{
						selectCard5.remove(i);
						i--;
					}
				}
			}
			
			while(p1Action>0)
			{
				System.out.println();
				System.out.println("== 이번 턴의 카드 ==");
				int j = 1;
				for(CardVO card : selectCard5) // 카드 5개를 보여줌.
				{
					sleeps(100);
					System.out.print(j+"번째 카드 : ");
					System.out.println(card.toString());
					j++;
				}
				sleeps(500);
				System.out.println();
				
				System.out.println("== 1 - 5번 카드 중에서 선택 > ");
				System.out.println("== 아이템 사용 : 6번 > ");
				System.out.println("== 턴 종료 : 0번 > ");
				System.out.println("== 현재 행동력 : "+ p1Action);
				System.out.println("== ");
				try 
				{
					int select = Integer.parseInt(scn.nextLine());
					CardVO selectCard = null;
					if(select==0)
					{
						System.out.println("== 턴을 종료합니다...");
						System.out.println();
						p1Action = 0;
					}
					else if(select!=6)
					{
						selectCard = selectCard5.get(select-1);
						if(selectCard.getActionConsumption() > p1Action)
						{
							sleeps(500);
							System.out.println();
							System.out.println("== 선택한 카드를 수행할 행동력이 부족합니다...");
							System.out.println();
							continue;
						}
					}
					
					if(select==6) // 전투용 아이템 사용. 일회용
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
									System.out.println("== "+inventory.get(i).getItemName()+"을 선택");  // 일치하는 이름 중 제일 먼저 찾아지는 것부터 사용.
									System.out.println("== "+inventory.get(i).getReadme());
									if(inventory.get(i).getInstantDamage()>0)
									{
										instanceDamage += inventory.get(i).getInstantDamage();
										sf.itemDelete(inventory.get(i));
										sf.showInven(p1);
										yes = true;
										break;
									}
									else if(inventory.get(i).getInstantDamage()<0)
									{
										heal += -inventory.get(i).getInstantDamage();
										sf.itemDelete(inventory.get(i));
										sf.showInven(p1);
										yes = true;
										break;
									}
									// 사용한 아이템 삭제 > 삭제 후 인벤토리 갱신.
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
						}
					}
					else
					{
						if(p1Action != 0 && p1Action >= selectCard.getActionConsumption())
						{
							sleeps(500);
							System.out.println();
							System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
							System.out.println("== "+selectCard.getReadme());
							System.out.println();
							sleeps(500);
							p1Att = selectCard.getAttack();
							allAtt += (p1Att+p1.getAttack());
							p1Def = selectCard.getDefense();
							allDef += p1Def;
							
							if(selectCard.getAttack()!=0)
							{
								attackCount++;
							}
							p1Action -= selectCard.getActionConsumption();
							selectCard5.remove(selectCard);
						}
						else if(p1Action != 0 && p1Action < selectCard.getActionConsumption())
						{
							sleeps(500);
							System.out.println("== 행동력이 부족합니다...");
							System.out.println();
							sleeps(500);
						}
					}
				} 
				catch (Exception e) 
				{
					System.out.println("== 올바른 선택지를 고르세요...");
				}
			}
			
			p1.setHp(p1.getHp()+heal);
			mobHp -= instanceDamage;
			int dmg = allAtt-(mobDef*attackCount);
			if(dmg > 0)
			{
				mobHp -= (dmg);
			}
			else
			{
				mobHp -= 1;
			}
			mob.setHp(mobHp);
			// 데미지 계산 공식 :: 데미지 == (공격자 공격력 + 무기공격력 + 기타 특별아이템 도핑) - (방어자 방어력 + 방어구 + 기타 특별 아이템 도핑)
			
			if(mob.getHp() > 0) // 몹이 살았을 때 행동패턴.
			{
				sleeps(1000);
				System.out.println();
				mob.toString();
				System.out.println();
				sleeps(1000);
				
				System.out.println();
				System.out.println();
				System.out.println("== "+mob.getName()+"의 차례 =====================================");
				System.out.println();
				// 랜덤하게 행동 결정
				int rand1 = (int)(Math.random()*2);
				if(rand1==0)
				{
					int mobAtt1 = mob.mobAttack();
					int mobDmg = mobAtt1-allDef;
					if(mobAtt1-allDef < 0) // 상대 공격보다 내 방어가 높아서 데미지가 안들어 갈 때.
					{
						mobDmg = 1;
					}
					else
					{
						p1Hp -= mobDmg;
					}
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
				p1.setKills(p1.getKills()+1);
				System.out.println();
				dropCards();
				System.out.println();
				dropItems();
				t= false;
			}
			
			if(p1Hp <= 0)
			{
				System.out.println("== "+p1.getUserName()+"은/는 장렬하게 사망하였습니다...");
				System.out.println("== Game Over == ");
				return;
			}
			turn++; // 전투중 소모한 턴수 더하기.
		}
		System.out.println();
		System.out.println();
		System.out.println("== 전투 종료 =====================================================================================================================================");
		System.out.println();
		sleeps(1000);
		p1.toString();
		sleeps(1000);
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
	
	private int stairs()
	{
		int pause = 0;
		int reinforce = 1;
		while(true)
		{
			if((p1.getProgress()+1)%10==0)
			{
				System.out.println("== 현재 층은 꼭대기 층 입니다...");
			}
			else
			{
				System.out.println("== 현재 층은 "+((p1.getProgress()+1)%10)+"층 입니다...");
			}
			System.out.println();
			System.out.println("== 1. 다음층으로 올라가기");
			System.out.println("== 2. 강화");
			System.out.println("== 3. 인벤토리");
			System.out.println("== 4. 게임 저장");
			System.out.println("== 5. 메인 메뉴");
			System.out.println();
	
			try 
			{
				int select = Integer.parseInt(scn.nextLine());
				if(select == 1)
				{
					break;
				}
				else if(select == 2 && reinforce != 0)
				{
					System.out.println("====  강  화  ====");
					System.out.println();
					sleeps(1000);
					System.out.println("== 소지중인 카드 목록 >");
					for(int i=0; i<allCards.size(); i++) //전체 소지카드 보여주기
					{
						sleeps(100);
						System.out.println((i+1)+"번 : "+allCards.get(i).toString());
					}
					sleeps(1000);
					System.out.println();
					System.out.println("== 강화는 한 층당 한 번 가능합니다");
					System.out.println("== 강화할 카드");
					int enforce = Integer.parseInt(scn.nextLine());
					sleeps(500);
					
					if(allCards.get(enforce-1).getAttack()!=0) // 공격카드일 시, 강화
					{
						if(allCards.get(enforce-1).getAttack() < 40)
						{
							allCards.get(enforce-1).setAttack(allCards.get(enforce-1).getAttack()+10);
						}
						else
						{
							allCards.get(enforce-1).setAttack((int)(Math.round(allCards.get(enforce-1).getAttack()*1.25)));
						}
						System.out.println("== 공격류 카드 강화 완료!  ==");
						System.out.println();
						System.out.println("강화 후 카드의 공격력 : "+allCards.get(enforce-1).getAttack());
						System.out.println();
						sf.cardUpdate(allCards.get(enforce-1));
					}
					else
					{
						if(allCards.get(enforce-1).getDefense() < 40)
						{
							allCards.get(enforce-1).setDefense(allCards.get(enforce-1).getDefense()+10);
						}
						else
						{
							allCards.get(enforce-1).setDefense((int)Math.round((allCards.get(enforce-1).getDefense()*1.25)));
						}
						System.out.println("== 방어류 카드 강화 완료!  ==");
						System.out.println();
						System.out.println("강화 후 카드의 방어력 : "+allCards.get(enforce-1).getDefense());
						System.out.println();
						sf.cardUpdate(allCards.get(enforce-1));
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
					saveGame();
					sleeps(500);
					System.out.println("== 게임 저장 완료! ");
				}
				else if(select == 5)
				{
					System.out.println("== 입구로 내려갑니다...");
					pause = 1; // 1일시 게임 나가도록 설정.
					return pause;
				}
				else if(reinforce== 0)
				{
					System.out.println();
					System.out.println("== 이미 강화를 모두 하셨습니다...");
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
		return pause;
	}
	
	private void climbUpBoss()
	{
		map.map10();
		sleeps(2000);
		System.out.println();
		// 몹 생성
		if(p1.getProgress()==9)
		{
			Boss boss = bossLists.getBoss("성의 주인");
			System.out.println(boss.getMAppear());
			System.out.println();
			fightBoss(p1,boss);
		}
		else if(p1.getProgress()==19)
		{
			Boss boss = bossLists.getBoss("감옥탑의 괴물");
			System.out.println(boss.getMAppear());
			System.out.println();
			fightBoss(p1,boss);
		}
		else if(p1.getProgress()==29) // boss3 구현하기
		{
			Boss boss = bossLists.getBoss("라그나로스");
			System.out.println(boss.getMAppear());
			System.out.println();
			fightBoss(p1,boss);
		}
		else if(p1.getProgress()==39) // boss4
		{
			Boss boss = bossLists.getBoss("드라큘라");
			System.out.println(boss.getMAppear());
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
		int turn = 1;
		
		while(t)
		{
			int p1Att = 0;
			int p1Def = 0;
			int allAtt = 0;
			int allDef = p1.getDefense();
			int p1Action = p1.getAction();
			int heal = 0;
			int instanceDamage = 0;
			int attackCount = 0;
			
			sleeps(500);
			System.out.println();
			boss.toString();
			sleeps(500);
			System.out.println();
			p1.toString();
			
			sleeps(2000);
			System.out.println("== "+turn+"번째 턴 =====================================================================================================================================");
			System.out.println("== 당신의 차례 ==");
			
			List<CardVO> selectCard5 = new ArrayList<CardVO>(); // 전체 소지카드 중에서 5개를 랜덤으로 추출해낸 리스트.
			for(int i=0; i<5; i++)
			{
				int ran = (int)(Math.random()*allCards.size());
				selectCard5.add(allCards.get(ran));
				for(int j=0; j<i; j++)
				{
					if(selectCard5.get(i)==selectCard5.get(j))
					{
						selectCard5.remove(i);
						i--;
					}
				}
			}
			
			while(p1Action>0)
			{
				System.out.println();
				System.out.println("== 이번 턴의 카드 ==");
				int j = 1;
				for(CardVO card : selectCard5) // 카드 5개를 보여줌.
				{
					sleeps(100);
					System.out.print(j+"번째 카드 : ");
					System.out.println(card.toString());
					j++;
				}
				sleeps(500);
				System.out.println();
				System.out.println("== 1 - 5번 카드 중에서 선택 > ");
				System.out.println("== 아이템 사용 : 6번 > ");
				System.out.println("== 턴 종료 : 0번 > ");
				System.out.println("== 현재 행동력 : "+ p1Action);
				System.out.println("== ");
				int select = Integer.parseInt(scn.nextLine());
				CardVO selectCard = null;
				
				if(select==0)
				{
					System.out.println("== 턴을 종료합니다...");
					System.out.println();
					p1Action=0;
				}
				else if(select!=6)
				{
					selectCard = selectCard5.get(select-1);
					if(selectCard.getActionConsumption() > p1Action)
					{
						sleeps(500);
						System.out.println();
						System.out.println("== 선택한 카드를 수행할 행동력이 부족합니다...");
						System.out.println();
						continue;
					}
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
								System.out.println("== "+inventory.get(i).getItemName()+"을 선택");  // 일치하는 이름 중 제일 먼저 찾아지는 것부터 사용.
								System.out.println("== "+inventory.get(i).getReadme());
								if(inventory.get(i).getInstantDamage()>0)
								{
									instanceDamage += inventory.get(i).getInstantDamage();
									sf.itemDelete(inventory.get(i));
									sf.showInven(p1);
									yes = true;
									break;
								}
								else if(inventory.get(i).getInstantDamage()<0)
								{
									heal += -inventory.get(i).getInstantDamage();
									sf.itemDelete(inventory.get(i));
									sf.showInven(p1);
									yes = true;
									break;
								}
								// 사용한 아이템 삭제 > 삭제 후 인벤토리 갱신.
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
					}
				}
				else if(p1Action!=0)
				{
					sleeps(500);
					System.out.println();
					System.out.println("== "+selectCard.getCardName()+" 카드 선택 ");
					System.out.println("== "+selectCard.getReadme());
					System.out.println();
					sleeps(500);
					p1Att = selectCard.getAttack();
					p1Def = selectCard.getDefense();
					allDef += p1Def;
					if(selectCard.getAttack()!=0)
					{
						allAtt += (p1Att+p1.getAttack());
						attackCount++;
					}
				}
				
				if(selectCard!=null)
				{
					p1Action -= selectCard.getActionConsumption();
				}
			}
			
			p1.setHp(p1.getHp()+heal);
			mobHp -= instanceDamage;
			int dmg = allAtt-(mobDef*attackCount);
			if(dmg > 0)
			{
				mobHp -= (dmg);
			}
			else
			{
				mobHp -= 1;
			}
			boss.setHp(mobHp);
			
			if(boss.getHp() > 0)
			{
				sleeps(500);
				System.out.println();
				boss.toString();
				System.out.println();
				sleeps(1000);
				
				System.out.println("== "+boss.getName()+"의 차례 =======================================");
				System.out.println();
				// 랜덤하게 행동 결정
				int rand1 = (int)(Math.random()*10);
				if(rand1<=0 && rand1<=3)
				{
					int mobAtt = boss.mobAttack();
					int mobDmg = mobAtt-allDef;
					if(mobAtt-allDef < 0) // 상대 공격보다 내 방어가 높아서 데미지가 안들어 갈 때.
					{
						mobDmg = 1;
					}
					else
					{
						p1Hp -= mobDmg;
					}
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
					int mobDmg = mobAtt-allDef;
					if(mobAtt-allDef < 0) // 상대 공격보다 내 방어가 높아서 데미지가 안들어 갈 때.
					{
						mobDmg = 1;
					}
					else
					{
						p1Hp -= mobDmg;
					}
					p1.setHp(p1Hp);
					sleeps(1000);
				}
				else if(rand1==8 || rand1==9)
				{
					int mobAtt = boss.bossUltimate();
					int mobDmg = mobAtt-allDef;
					if(mobAtt-allDef < 0) // 상대 공격보다 내 방어가 높아서 데미지가 안들어 갈 때.
					{
						mobDmg = 1;
					}
					else
					{
						p1Hp -= mobDmg;
					}
					p1.setHp(p1Hp);
					sleeps(1000);
				}
			}
			else
			{
				sleeps(1000);
				int getMoney = boss.die();
				p1.setMoney(p1.getMoney()+getMoney);
				p1.setKills(p1.getKills()+5);
				System.out.println();
				dropCards();
				System.out.println();
				dropItemsSpeical();
				t= false;
			}
			
			if(p1Hp <= 0)
			{
				System.out.println("== "+p1.getUserName()+"은/는 장렬하게 사망하였습니다...");
				System.out.println("== Game Over == ");
				return;
			}
			turn++;
		}
		System.out.println();
		System.out.println();
		System.out.println("== 전투 종료 =====================================================================================================================================");
		System.out.println();
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
		
		if(rand==0)
		{
			// 상점 이벤트
			System.out.println("== 수상한 망토를 쓰고 보따리를 풀고 있는 상인을 만났다...");
			System.out.println("== ");
			System.out.println("== 상인 : 어서오세요.. 없는 것 빼고 다 있답니다...");
			System.out.println();
			sleeps(500);
			// 여러가지 아이템 / 장비들 중 10가지를 랜덤하게 생성해서 판매하기. 5번은 나가기.
			boolean t = true;
			boolean check1 = false; //false:안팔림, true:매진.
			boolean check2 = false;
			boolean check3 = false;
			boolean check4 = false;
			while(t)
			{
				if(check1==true && check2==true && check3==true && check4==true)
				{
					System.out.println();
					System.out.println("== 상인 : 감사합니다... 당신이 무탈하게 탐험을 끝내길 기원하지요...");
					System.out.println();
					p1.setProgress(p1.getProgress()+1);
					return;
				}
				
				int randbuy1 = (int)(Math.random()*items.getItemList().size());
				int randbuy2 = (int)(Math.random()*items.getItemList().size());
				int randbuy3 = (int)(Math.random()*items.getItemList().size());
				int randbuy4 = (int)(Math.random()*items.getItemList().size());
				System.out.println("== 판매 중인 아이템 및 장비 목록");
				ItemVO sell1 = items.getItem(randbuy1);
				ItemVO sell2 = items.getItem(randbuy2);
				ItemVO sell3 = items.getItem(randbuy3);
				ItemVO sell4 = items.getItem(randbuy4);
				
				System.out.println();
				System.out.print("1번 상품 == ");
				if(check1==true)
				{
					System.out.print(" 매 진 ");
				}
				else
				{
					sell1.toString();
				}
				System.out.println();
				System.out.print("2번 상품 == ");
				if(check2==true)
				{
					System.out.print(" 매 진 ");
				}
				else
				{
					sell2.toString();
				}
				System.out.println();
				System.out.print("3번 상품 == ");
				if(check3==true)
				{
					System.out.print(" 매 진 ");
				}
				else
				{
					sell3.toString();
				}
				System.out.println();
				System.out.print("4번 상품 == ");
				if(check4==true)
				{
					System.out.print(" 매 진 ");
				}
				else
				{
					sell4.toString();
				}
				System.out.println();
				
				System.out.println("== 5. 판매  |  6. 나가기");
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
						sf.itemInsert(sell1); // 얻은 아이템 db에 정보 넣어 고유번호 받기.
						ItemVO tmp = sf.itemSelect(); // 바로 방금 넣은 아이템을 고유번호 받은 상태로 받아오기.
						sf.inventoryInsert(tmp,p1); // p1의 인벤토리에 넣기.
						inventory.clear(); // 기존 인벤토리 비우기.
						inventory = sf.showInven(p1); // 최신화된 인벤토리 불러오기.
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
						sf.itemInsert(sell2); 
						ItemVO tmp = sf.itemSelect();
						sf.inventoryInsert(tmp,p1); 
						inventory.clear(); 
						inventory = sf.showInven(p1); 
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
						sf.itemInsert(sell3); 
						ItemVO tmp = sf.itemSelect(); 
						sf.inventoryInsert(tmp,p1); 
						inventory.clear(); 
						inventory = sf.showInven(p1); 
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
						sf.itemInsert(sell4); 
						ItemVO tmp = sf.itemSelect(); 
						sf.inventoryInsert(tmp,p1); 
						inventory.clear(); 
						inventory = sf.showInven(p1); 
						check4 = true;
					}
				}
				else if(buy==5)
				{
					// 내 아이템 판매 기능.
					System.out.println("== 상인 : 판매할 아이템을 꺼내 보십시오...");
					System.out.println();
					showInven();
					System.out.println("== 판매할 아이템을 선택 > ");
					try 
					{
						int sellItem = Integer.parseInt(scn.nextLine());
						ItemVO tmp = inventory.get(sellItem-1);
						if(tmp.getItemId() == equipment[0].getItemId() || tmp.getItemId() == equipment[1].getItemId())
						{
							System.out.println("== 착용 중인 아이템은 팔 수 없습니다!");
						}
						else
						{
							p1.setMoney(p1.getMoney()+tmp.getPrice());
							System.out.println();
							System.out.println("== 아이템 판매 성공! +"+tmp.getPrice()+"금 획득!  >> 현재 소지금 : "+p1.getMoney());
							System.out.println();
							sf.itemDelete(tmp); // 아이템 삭제. CASCADE로 자동으로 인벤, 장비 정보도 삭제.
						}
					} 
					catch (Exception e) 
					{
						System.out.println("== 제대로 고르세요...");
					}
				}
				else
				{
					System.out.println("== 상인 : 벌써 가시게요..? 다음에 봅시다...");
					System.out.println("== ");
					p1.setProgress(p1.getProgress()+1);
					return;
				}
			}
		}
		else if(rand==1)
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
							System.out.println("== 대박이다! 500 골드를 얻었다! ");
							p1.setMoney(p1.getMoney()+500);
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
							sleeps(500);
							System.out.println();
							dropItems();
							sleeps(500);
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
				int money = (int)(Math.random()*500)+100;
				System.out.println();
				System.out.println("== "+money+"원을 획득하셨습니다!!! ");
				p1.setMoney(p1.getMoney()+money);
				System.out.println("== 현재 소지금 : "+p1.getMoney());
			}
			else if(lucky==1) // 랜덤 장비
			{
				System.out.println();
				dropItems();
				System.out.println();
			}
			else // hp 회복
			{
				System.out.println();
				System.out.println("== 행운의 여신이 hp를 추가 해주었다! [ hp +50 ]");
				p1.setHp(p1.getHp()+50);
			}
		}
		System.out.println("== ");
		System.out.println("== 예상치 않았던 전리품을 들고 기쁜 마음으로 방을 나간다...");
		System.out.println();
		p1.setProgress(p1.getProgress()+1);
		
	}
	
	private void showInven()
	{
		System.out.println("== 당신의 인벤토리 목록 ");
		System.out.println();
		inventory.clear();
		inventory  = sf.showInven(p1);
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
		inventory.clear();
		inventory  = sf.showInven(p1);
		int i = 1;
		for(ItemVO item : inventory)
		{
			if(item.getInstantDamage()!=0)
			{
				System.out.print(i+"번째 ");
				item.toString();
				System.out.println();
				i++;
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
				
				if(changeNew==0)
				{
					return;
				}
				
				ItemVO chageItem = inventory.get(changeNew-1);
				if(chageItem.getAttack()==0)
				{
					System.out.println("== 무기류를 선택하십시오...");
				}
				else
				{
					if(equipment[0]!=null) // 이미 착용중인 것이 있을 때, 착용해제.
					{
						p1.setAttack(p1.getAttack()-equipment[0].getAttack());
						sf.equipDelete(equipment[0]); // equip 테이블에 있던 착용장비 삭제.
					}
					equipment[0] = chageItem; // 무기칸 변경
					p1.setAttack(p1.getAttack()+equipment[0].getAttack());
					sf.equipInsert(chageItem, p1); // equip테이블에 착용장비 넣기.
				}
			}
			else if(changeOld==2)
			{
				showInven();
				System.out.println("== 대신 착용할 방어구를 입력 ( 취소 : 0번 ) > ");
				int changeNew = Integer.parseInt(scn.nextLine());
				
				if(changeNew==0)
				{
					return;
				}
				
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
						sf.equipDelete(equipment[1]); // equip 테이블에 있던 착용장비 삭제.
					}
					equipment[1] = chageItem; // 방어구칸 변경
					p1.setDefense(p1.getDefense()+equipment[1].getDefense());
					sf.equipInsert(chageItem, p1); // equip테이블에 착용장비 넣기.
				}
			}
		}
		else
		{
			System.out.println();
		}
	}
	
	private void checkLogin()
	{
		System.out.println("== 문지기 : 이문을 지나가려면 당신의 신원을 조회해야 합니다.");
		System.out.println("== 문지기 : 당신의 이름을 대시오...");
		String name = scn.nextLine();
		List<PlayerVO> nameList = sf.playerAllSelect();
		boolean t = false;
		for(PlayerVO vo : nameList)
		{
			if(name.equals(vo.getUserName()))
			{
				System.out.println("문지기 : 신원목록에 있군! 좋습니다!");
				System.out.println("문지기 : 그렇다면 비밀번호를 말해주십시오!");
				System.out.println();
				int pw = Integer.parseInt(scn.nextLine());
				t = true;
				PlayerVO vo1 = new PlayerVO(name,123);
				p1 = sf.playerSelect(vo1);
				if(pw==p1.getPw())
				{
					System.out.println("문지기 : 신원 확인했습니다! 가도 좋습니다!");
					System.out.println();
				}
				else
				{
					System.out.println("문지기 : 음 틀린거 같군요... 못갑니다...");
					System.out.println();
					System.out.println("== Game Over ==");
					no = false;
				}
			}
		}
		if(t==false)
		{
			System.out.println("문지기 : 목록에 없는 이름이군! 갈 수 없다!");
			System.out.println();
			System.out.println("== Game Over ==");
			no = false;
		}
	}
	
	private void saveGame()
	{
		// db > 현재 캐릭터 객체 상태 저장
		sf.playerUpdate(p1); // 캐릭터 상태 저장
	}
	
	private void dropItems()
	{
		ItemVO dropItem = null;
		if(p1.getProgress() < 20)
		{
			dropItem = items.randomItem();
		}
		else if(p1.getProgress() >= 20) // 진행상황 stage3일 때, 중반 아이템 드랍
		{
			dropItem = items.randomItem2();
		}
		System.out.println("== 아이템를 얻었습니다...");
		System.out.println("== 얻은 아이템 정보 : "+dropItem.getItemName()+" "+dropItem.getReadme());
		
		System.out.println();
		System.out.println("== 이 아이템을 사용하시겠습니까 ?");
		System.out.println("== 1. 사용   |   2. 거부 ");
		try 
		{
			int sel = Integer.parseInt(scn.nextLine());
			if(sel==1)
			{
				sf.itemInsert(dropItem);
				ItemVO tmp = sf.itemSelect();
				sf.inventoryInsert(tmp,p1); 
				inventory.clear(); 
				inventory = sf.showInven(p1); 
			}
		} 
		catch (Exception e) 
		{
			System.out.println("올바른 입력을 하세요...");
		}
	}
	
	private void dropItemsSpeical()
	{
		ItemVO dropItem = null;
		if(p1.getProgress() < 20)
		{
			dropItem = items.randomItemSpecial();
		}
		else if(p1.getProgress() >= 20) // 진행상황 stage3일 때, 중반 아이템 드랍
		{
			dropItem = items.randomItemSpecial2();
		}
		System.out.println("== 아이템를 얻었습니다...");
		sleeps(500);
		System.out.println("== 얻은 아이템 정보 : "+dropItem.getItemName()+"  "+dropItem.getReadme());
		
		System.out.println();
		System.out.println("== 이 아이템을 사용하시겠습니까 ?");
		System.out.println("== 1. 사용   |   2. 거부 ");
		try 
		{
			int sel = Integer.parseInt(scn.nextLine());
			if(sel==1)
			{
				sf.itemInsert(dropItem);
				ItemVO tmp = sf.itemSelect();
				sf.inventoryInsert(tmp,p1); 
				inventory.clear(); 
				inventory = sf.showInven(p1); 
			}
		} 
		catch (Exception e) 
		{
			System.out.println("올바른 입력을 하세요...");
		}
	}
	
	private void dropCards()
	{
		CardVO dropCard = cards.randomCard();
		System.out.println("== 카드를 얻었습니다...");
		System.out.println("== 얻은 카드 정보 : "+dropCard.getCardName()+"  "+dropCard.getReadme());
		
		System.out.println();
		System.out.println("== 이 카드를 사용하시겠습니까 ?");
		System.out.println("== 1. 사용   |   2. 거부 ");
		try 
		{
			int sel = Integer.parseInt(scn.nextLine());
			if(sel==1)
			{
				sf.cardInsert(dropCard); // 얻은 카드 db에 정보 넣어 고유번호 받기.
				CardVO tmp = sf.recentCardSelect(); // 바로 방금 넣은 카드를 고유번호 받은 상태로 받아오기.
				sf.cardListInsert(tmp,p1); // p1의 카드리스트에 넣기.
				allCards.clear(); // 기존 카드리스트 비우기.
				allCards = sf.CardListSelect(p1); // 최신화된 카드리스트 불러오기.
			}
		} 
		catch (Exception e) 
		{
			System.out.println("올바른 입력을 하세요...");
		}
	}
}