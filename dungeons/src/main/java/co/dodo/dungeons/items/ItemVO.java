package co.dodo.dungeons.items;

import lombok.Data;

@Data
public class ItemVO 
{
	private int itemId;
	private String itemName;
	private int attack;
	private int defense;
	private int instantDamage; // 즉발 데미지
	private String readme;
	private int price; // 판매가격, 플레이어가 팔 때는 반값으로.
	
	public ItemVO() {}
	
	public ItemVO(String itemName, int attack, int defense, int instantDamage, String readme, int price)
	{
		this.itemName = itemName;
		this.attack = attack;
		this.defense = defense;
		this.instantDamage = instantDamage;
		this.readme = readme;
		this.price = price;
	}
	
	@Override
	public String toString()
	{
		System.out.print("아이템 이름 : "+itemName+" ");
		if(attack!=0)
		{
			System.out.print("아이템 공격력 : "+attack+" ");
		}
		if(defense!=0)
		{
			System.out.print("아이템 방어력 : "+defense+" ");
		}
		if(instantDamage> 0)
		{
			System.out.print("투척 아이템 데미지 : "+instantDamage+" ");
		}
		else if(instantDamage> 0)
		{
			System.out.print("치료 아이템 효과 : "+(-instantDamage)+" ");
		}
		System.out.print("설명 : "+readme+" ");
		System.out.print("가격 : "+price+" ");
		return null;
	}
}
