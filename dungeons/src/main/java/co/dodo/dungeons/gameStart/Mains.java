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

public class Mains extends Thread
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
					List<PlayerVO> Scores = sf.playerBestSelect();
					int i = 1;
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("===================================== 명예의 전당 =====================================");
					System.out.println();
					for(PlayerVO vo : Scores)
					{
						sleeps(500);
						System.out.print("== "+i+"등 : ");
						vo.rank();
						System.out.println();
						i++;
					}
					System.out.println();
					System.out.println();
					sleeps(3000);
				}
				else if(menu == 4)
				{
					// 캐릭터 삭제 기능.
					try 
					{
						System.out.println("== 삭제할 탐험가의 이름을 적으시오...");
						String delName = scn.nextLine();
						System.out.println("== 비밀번호를 입력하세요...");
						int delPw = Integer.parseInt(scn.nextLine());
						PlayerVO vo = new PlayerVO(delName,delPw);
						sf.saveDeletePlayer(vo);
						System.out.println("== 삭제 완료!");
						System.out.println();
					} 
					catch (Exception e) 
					{
						System.out.println("== 삭제할 이름 혹은 비밀번호가 다릅니다!");
						System.out.println("== 삭제 불가!");
					}
				}
				else if(menu == 5)
				{
					System.out.println("게임 종료!");
					break;
				}
				else if(menu == 6)
				{
					System.out.println();
					readMe();
					System.out.println();
				}
				else
				{
					System.out.println("올바른 선택지를 고르세요... [err1]");
					System.out.println();
				}
			}
			catch(Exception e)
			{
				System.out.println("올바른 선택지를 고르세요...[err2]");
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
		System.out.println();
		System.out.println();
		System.out.println("██████╗ ██╗   ██╗███╗   ██╗ ██████╗ ███████╗ ██████╗ ███╗   ██╗    ███████╗██╗  ██╗██████╗ ██╗      ██████╗ ██████╗  █████╗ ████████╗██╗ ██████╗ ███╗   ██╗\r\n"
				+ "██╔══██╗██║   ██║████╗  ██║██╔════╝ ██╔════╝██╔═══██╗████╗  ██║    ██╔════╝╚██╗██╔╝██╔══██╗██║     ██╔═══██╗██╔══██╗██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║\r\n"
				+ "██║  ██║██║   ██║██╔██╗ ██║██║  ███╗█████╗  ██║   ██║██╔██╗ ██║    █████╗   ╚███╔╝ ██████╔╝██║     ██║   ██║██████╔╝███████║   ██║   ██║██║   ██║██╔██╗ ██║\r\n"
				+ "██║  ██║██║   ██║██║╚██╗██║██║   ██║██╔══╝  ██║   ██║██║╚██╗██║    ██╔══╝   ██╔██╗ ██╔═══╝ ██║     ██║   ██║██╔══██╗██╔══██║   ██║   ██║██║   ██║██║╚██╗██║\r\n"
				+ "██████╔╝╚██████╔╝██║ ╚████║╚██████╔╝███████╗╚██████╔╝██║ ╚████║    ███████╗██╔╝ ██╗██║     ███████╗╚██████╔╝██║  ██║██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║\r\n"
				+ "╚═════╝  ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝ ╚══════╝ ╚═════╝ ╚═╝  ╚═══╝    ╚══════╝╚═╝  ╚═╝╚═╝     ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝\r\n"
				+ "                                                                                                                                                           \r\n"
				+ "");
		
		
		System.out.println("==== 1. 게임 새로시작     ====");
		System.out.println("==== 2. 게임 불러오기     ====");
		System.out.println("==== 3. 명예의 전당       ====");
		System.out.println("==== 4. 세이브파일 삭제   ====");
		System.out.println("==== 5. 게임 종료         ====");
		System.out.println("==== 6. 설 명 서          ====");
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
		
		Card5(p1);
		
	}
	
	public List<CardVO> generateCards() // 처음 카드리스트 생성
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
	
	public void Card5(PlayerVO p1)
	{
		List<CardVO> allCards = new ArrayList<CardVO>();
		allCards = generateCards();
		for(int i=0; i<allCards.size(); i++) // 초기 생성 5개 카드를 생성 캐릭터의 카드리스트에 넣기.
		{
			CardVO tmp = allCards.get(i);
			sf.cardListInsert(tmp, p1);
		}
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
	
	private void readMe()
	{
		System.out.println("== 던전 탐험 플레이 설명서 == ");
		System.out.println();
		System.out.println("== 1. 던전 탐험 플레이 개요");
		System.out.println("던전 탐험은 누구도 돌파하지 못한 미지의 장소를 탐험하는 게임입니다.");
		System.out.println("Stage당 10층의 방을 돌파하셔야 합니다.");
		System.out.println("각 방에는 몬스터가 존재하며, 그 몬스터를 이기지 못한다면 올라가실 수 없습니다.");
		System.out.println();
		System.out.println("각 스테이지마다 5층은 랜덤한 이벤트가 발생합니다.");
		System.out.println("5층에는 1. 물건을 파는 상인 이벤트, 2.hp를 대가로 이득을 얻는 이벤트, 3. 룰렛 당첨 이벤트 세 가지가 있습니다.");
		System.out.println();
		System.out.println("10층은 각 스테이지의 보스가 있으며, 여타 몬스터보다 강력한 위력을 갖고, 보상 또한 더욱 좋습니다.");
		System.out.println("10층의 보스를 클리어를 해야 스테이지가 클리어되며, 다음 스테이지로 넘어갈 수 있습니다.");
		System.out.println("5/13 0.13버전은 현재 스테이지4까지 구현되어 있습니다.");
		System.out.println();
		System.out.println();
		System.out.println("== 2. 던전 탐험 진행 ");
		System.out.println("던전에 들어가면 방에 들어서기 직전에 현재 층을 확인할 수 있습니다.");
		System.out.println("4번으로 게임을 저장하고 5번으로 메인메뉴로 나갈 수 있습니다.");
		System.out.println();
		System.out.println("3번을 눌러 현재 인벤토리를 확인할 수 있으며, 거기서 현재 착용중인 장비를 확인하고 관리할 수 있습니다.");
		System.out.println("장비를 착용함으로써 캐릭터의 능력치를 높여보세요.");
		System.out.println("인벤토리 아이템 중, 투척아이템 (화염병, 수류탄, 구급상자)들은 몬스터와 전투 중에만 사용가능 합니다.");
		System.out.println("2번을 눌러 현재 갖고 있는 카드를 강화할 수 있습니다. 강화한 카드는 전투 중에 사용가능합니다.");
		System.out.println("강화는 한 층당 한번만 가능하니 조심해서 사용하십시오.");
		System.out.println("");
		System.out.println("올라가 싸울 준비가 되었다면 1번을 눌러 게임을 진행합니다.");
		System.out.println();
		System.out.println();
		System.out.println("== 게임 중 전투 플레이");
		System.out.println("기본적으로 던전탐험은 턴제게임으로, 당신의 턴이 시작하고 몬스터의 턴이 그 뒤에 시작됩니다.");
		System.out.println("방에 들어서면, 몬스터가 나타나고 당신의 턴이 시작됩니다.");
		System.out.println("당신이 갖고 있는 전체 카드 중 5개의 카드가 돌아오는 당신의 턴마다 선택되고 그 턴에는 그 카드들만 사용가능합니다.");
		System.out.println();
		System.out.println("카드는 각각 행동력을 갖고 있으며 당신이 갖고 있는 행동력만큼 당신의 카드들을 사용할 수 있습니다.");
		System.out.println("6번을 눌러 투척/회복 아이템을 사용가능합니다.");
		System.out.println();
		System.out.println("당신의 턴내에 최대한 카드를 활용하여 몬스터의 hp를 0으로 만들어 죽여야 합니다.");
		System.out.println();
		System.out.println("당신이 당신의 턴 내에 몬스터를 죽이지 못한다면, 몬스터의 턴이 와서 몬스터가 행동을 할 수 있습니다");
		System.out.println("몬스터는 확률적으로 당신을 공격하거나, 방어 태세를 취할 것 입니다.");
		System.out.println("만약 몬스터의 공격을 받고 hp가 0이 된다면 게임은 종료되고, 당신이 해왔던 게임진행상태는 초기화됩니다.");
		System.out.println("당신이 이긴다면, 당신은 보상으로 고정적으로 돈을 얻고 확률적으로 아이템과 카드를 얻습니다.");
		
	}
}
