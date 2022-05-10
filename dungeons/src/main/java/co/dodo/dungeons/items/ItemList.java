package co.dodo.dungeons.items;

import java.util.ArrayList;
import java.util.List;

public class ItemList 
{
	// ItemVO(String itemName, int attack, int defense, int instantDamage, String readme, int price)
	List<ItemVO> itemList = new ArrayList<ItemVO>();
	private ItemVO sword1 = new ItemVO("롱소드", 20, 0, 0, "== 잘 닦여있는 롱 소드. [ attack +20 ] ", 300);
	private ItemVO sword2 = new ItemVO("장미칼", 40, 0, 0, "== 현대 기술의 산물인 검. [ attack +40 ] ", 300);
	private ItemVO sword3 = new ItemVO("엑스칼리버", 60, 0, 0, "== 아서왕이 썼던 명검. [ attack +60 ] ", 300);
	private ItemVO shield1 = new ItemVO("철제 방패", 0, 20, 0, "== 단단한 철제 방패. [ defense +20 ] ", 300);
	private ItemVO shield2 = new ItemVO("미스릴 방패", 0, 30, 0, "== 부서지지 않는 미스릴제 방패. [ defense +30 ] ", 300);
	private ItemVO shield3 = new ItemVO("아메리칸 방패", 0, 50, 0, "== 캡틴의 방패. [ defense +50 ] ", 300);
	private ItemVO restorative1 = new ItemVO("구급상자", 0, 0, -50, "== 상처를 치료할 구급 상자를 사용합니다 [ heal +50 ] ", 400);
	private ItemVO Molotov = new ItemVO("화염병", 0, 0, 100, "== 화염병을 던집니다 [ damage +50 ] ", 200);
	
	public void makeAllItem() // 게임 시작 시, 모든 아이템을 로딩.
	{
		itemList.add(Molotov);
		itemList.add(sword1);
		itemList.add(sword2);
		itemList.add(sword3);
		itemList.add(shield1);
		itemList.add(shield2);
		itemList.add(shield3);
		itemList.add(restorative1);
	}
	
	public void randomItem() // 아이템을 랜덤하게 리젠.
	{
		
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