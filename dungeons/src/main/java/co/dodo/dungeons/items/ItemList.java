package co.dodo.dungeons.items;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ItemList 
{
	// ItemVO(String itemName, int attack, int defense, int instantDamage, String readme, int price)
	private List<ItemVO> itemList = new ArrayList<ItemVO>();
	// 기본 아이템
	private ItemVO sword1 = new ItemVO("롱소드", 5, 0, 0, "== 잘 닦여있는 롱 소드. [ attack +5 ] ", 300);
	private ItemVO sword2 = new ItemVO("장미칼", 7, 0, 0, "== 현대 기술의 산물인 검. [ attack +7 ] ", 300);
	private ItemVO sword3 = new ItemVO("엑스칼리버", 10, 0, 0, "== 아서왕이 썼던 명검. [ attack +10 ] ", 300);
	private ItemVO shield1 = new ItemVO("철제 방패", 0, 5, 0, "== 단단한 철제 방패. [ defense +5 ] ", 300);
	private ItemVO shield2 = new ItemVO("미스릴 방패", 0, 7, 0, "== 부서지지 않는 미스릴제 방패. [ defense +7 ] ", 300);
	private ItemVO shield3 = new ItemVO("아메리칸 방패", 0, 10, 0, "== 캡틴의 방패. [ defense +10 ] ", 300);
	private ItemVO restorative1 = new ItemVO("구급상자", 0, 0, -50, "== 상처를 치료할 구급 상자를 사용합니다 [ heal +50 ] ", 400);
	private ItemVO molotov = new ItemVO("화염병", 0, 0, 20, "== 화염병을 던집니다 [ damage +20 ] ", 300);
	// 중반 아이템
	private ItemVO sword4 = new ItemVO("귀철", 20, 0, 0, "== 주인을 배신한다는 소문난 마검. [ attack +20 ] ", 1000);
	private ItemVO sword5 = new ItemVO("청룡언월도", 25, 0, 0, "== 강호의 고수가 썼다고 알려진 도. [ attack +5 ] ", 2000);
	private ItemVO sword6 = new ItemVO("전설의 곡괭이", 35, 0, 0, "== 전설의 곡괭이. [ attack +5 ] ", 3000);
	private ItemVO shield4 = new ItemVO("양은 냄비 뚜껑", 0, 15, 0, "== 라면 먹기에 최적화. [ defense +5 ] ", 1000);
	private ItemVO shield5 = new ItemVO("다이아몬드 방패", 0, 20, 0, "== 깨지지 않는 다이아몬드 방패. [ defense +5 ] ", 2000);
	private ItemVO shield6 = new ItemVO("자기장 방패막", 0, 30, 0, "== 당신을 지켜줄 자기장이 나타납니다. [ defense +5 ] ", 3000);
	private ItemVO restorative2 = new ItemVO("자가치료머신", 0, 0, -50, "== 상처를 치료해주는 자가치료 기계를 사용합니다 [ heal +100 ] ", 700);
	private ItemVO grenade = new ItemVO("수류탄", 0, 0, 60, "== 수류탄을 던집니다 [ damage +60 ] ", 800);
	
	public void makeAllItem() // 게임 시작 시, 모든 아이템을 로딩.
	{
		// 기본 아이템
		itemList.add(molotov);
		itemList.add(sword1);
		itemList.add(sword2);
		itemList.add(sword3);
		itemList.add(shield1);
		itemList.add(shield2);
		itemList.add(shield3);
		itemList.add(restorative1);
		// stage3부터 드랍되는 중반 아이템
		itemList.add(sword4);
		itemList.add(sword5);
		itemList.add(sword6);
		itemList.add(shield4);
		itemList.add(shield5);
		itemList.add(shield6);
		itemList.add(restorative2);
		itemList.add(grenade);
	}
	
	public ItemVO randomItem() // 아이템을 랜덤하게 리젠.
	{
		int rand = (int)(Math.random()*70); // 0-69 확률
		if(rand>=0 && rand<5) // 5
		{
			return getSword3();
		}
		else if(rand>=5 && rand<10) // 5
		{
			return getShield3();
		}
		else if(rand>=10 && rand<20) // 10
		{
			return getShield1();
		}
		else if(rand>=20 && rand<30) // 10
		{
			return getShield2();
		}
		else if(rand>=30 && rand<40) // 10
		{
			return getSword1();
		}
		else if(rand>=40 && rand<50) // 10
		{
			return getSword2();
		}
		else if(rand>=50 && rand<60) // 10
		{
			return getMolotov();
		}
		else if(rand>=60 && rand<70) // 10
		{
			return getRestorative1();
		}
		
		return null;
	}
	
	public ItemVO randomItem2() // 아이템을 랜덤하게 리젠2 >> stage3부터 적용. 
	{
		int rand = (int)(Math.random()*70); // 0-69 확률
		if(rand>=0 && rand<5) // 5
		{
			return getSword6();
		}
		else if(rand>=5 && rand<10) // 5
		{
			return getShield6();
		}
		else if(rand>=10 && rand<20) // 10
		{
			return getShield4();
		}
		else if(rand>=20 && rand<30) // 10
		{
			return getShield5();
		}
		else if(rand>=30 && rand<40) // 10
		{
			return getSword4();
		}
		else if(rand>=40 && rand<50) // 10
		{
			return getSword5();
		}
		else if(rand>=50 && rand<60) // 10
		{
			return getGrenade();
		}
		else if(rand>=60 && rand<70) // 10
		{
			return getRestorative2();
		}
		
		return null;
	}
	
	public ItemVO randomItemSpecial() // 아이템을 랜덤하게 리젠.
	{
		int rand = (int)(Math.random()*100); // 0-89 확률
		if(rand>=0 && rand<5) // 5
		{
			return getSword3();
		}
		else if(rand>=5 && rand<10) // 5
		{
			return getShield3();
		}
		else if(rand>=10 && rand<20) // 10
		{
			return getShield1();
		}
		else if(rand>=20 && rand<30) // 10
		{
			return getShield2();
		}
		else if(rand>=30 && rand<40) // 10
		{
			return getSword1();
		}
		else if(rand>=40 && rand<50) // 10
		{
			return getSword2();
		}
		else if(rand>=50 && rand<60) // 10
		{
			return getMolotov();
		}
		else if(rand>=60 && rand<70) // 10
		{
			return getRestorative1();
		}
		else if(rand>=70 && rand<85) // 10 > 총 20
		{
			return getSword3();
		}
		else if(rand>=85 && rand<100) // 10 > 총 20
		{
			return getShield3();
		}
		
		return null;
	}
	
	public ItemVO randomItemSpecial2() // 아이템을 랜덤하게 리젠2. >> stage3에서 적용
	{
		int rand = (int)(Math.random()*100); // 0-99 확률
		if(rand>=0 && rand<5) // 5
		{
			return getSword6();
		}
		else if(rand>=5 && rand<10) // 5
		{
			return getShield6();
		}
		else if(rand>=10 && rand<20) // 10
		{
			return getShield4();
		}
		else if(rand>=20 && rand<30) // 10
		{
			return getShield5();
		}
		else if(rand>=30 && rand<40) // 10
		{
			return getSword4();
		}
		else if(rand>=40 && rand<50) // 10
		{
			return getSword5();
		}
		else if(rand>=50 && rand<60) // 10
		{
			return getGrenade();
		}
		else if(rand>=60 && rand<70) // 10
		{
			return getRestorative2();
		}
		else if(rand>=70 && rand<85) // 15 > 총 20
		{
			return getSword6();
		}
		else if(rand>=85 && rand<100) // 15 > 총 20
		{
			return getShield6();
		}
		
		return null;
	}
	
	public List<ItemVO> getItemList()
	{
		return itemList;
	}
	
	public ItemVO getItem(int a)
	{
		return itemList.get(a);
	}
}