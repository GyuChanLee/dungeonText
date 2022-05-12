package co.dodo.dungeons.units;

import java.util.ArrayList;
import java.util.List;


public class BossList
{
	private List<Boss> bossList = new ArrayList<Boss>();
	
	private String boss1M1 = "버려진 성의 마지막 층의 주인이 나타났습니다...";
	private String boss1M2 = "== 성의 주인이 칼을 크게 휘두릅니다...";
	private String boss1M3 = "== 성의 주인이 큰 방패를 듭니다...";
	private String boss1M4 = "== 성의 주인이 미지의 마법을 주창합니다...";
	private String boss1M5 = "== <궁극기 주의> 성의 주인이 거대한 마법 화살을 발사합니다...";
	private String boss1M6 = "== 성의 주인이 죽었습니다!";
	private Boss boss1 = new Boss("성의 주인",1000, 40, 20, 150,boss1M1,boss1M2,boss1M3,boss1M4,boss1M5,boss1M6);
	
	private String boss2M1 = "감옥 탑의 숨어있던 괴물이 나타났습니다...";
	private String boss2M2 = "== 괴물이 날카로운 발톱을 크게 휘두릅니다...";
	private String boss2M3 = "== 괴물이 큰 몸을 웅크립니다...";
	private String boss2M4 = "== 괴물이 한껏 웅크렸다가 당신에게 날아듭니다...";
	private String boss2M5 = "== <궁극기 주의> 괴물이 미친듯한 속도로 당신을 물어뜯기 위해 달려듭니다...";
	private String boss2M6 = "== 괴물을 퇴치했습니다!";
	private Boss boss2 = new Boss("감옥탑의 괴물",2000, 60, 20, 200,boss2M1,boss2M2,boss2M3,boss2M4,boss2M5,boss2M6);
	
	private String boss3M1 = "불탄 탑의 군주이자 불의 군주, 라그라노스가 나타났습니다...";
	private String boss3M2 = "== 라그나로스는 작렬하는 불덩어리를 던집니다...";
	private String boss3M3 = "== 라그나로스는 불의 장벽을 시전합니다...";
	private String boss3M4 = "== 라그나로스는 용암 덩어리를 당신에게 던집니다...";
	private String boss3M5 = "== <궁극기 주의> 라그나로스는 당신에게 불의 세례를 선사합니다...";
	private String boss3M6 = "== 라그나로스를 굴복시켰습니다!";
	private Boss boss3 = new Boss("라그나로스",4000, 80, 30, 300,boss3M1,boss3M2,boss3M3,boss3M4,boss3M5,boss3M6);
	
	public void loadBossList()
	{
		bossList.add(boss1);
		bossList.add(boss2);
		bossList.add(boss3);
	}
	
	public Boss getBoss(String name) // 보스이름으로 검색해서 보스 인스턴스 반환
	{
		Boss boss = null;
		for(int i = 0; i<bossList.size(); i++)
		{
			if(bossList.get(i).getName().equals(name))
			{
				boss = bossList.get(i);
			}
		}
		return boss;
	}
}
