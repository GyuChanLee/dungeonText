package co.dodo.dungeons.items;

import lombok.Data;

@Data
public class ItemVO 
{
	private String itemId;
	private String itemName;
	private int attack;
	private int defense;
	private int instantDamage; // 즉발 데미지
	private String readme;
	private int price; // 판매가격, 플레이어가 팔 때는 반값으로.
}
