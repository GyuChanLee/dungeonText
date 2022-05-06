package co.dodo.dungeons.units;

import lombok.Data;

@Data
public abstract class Units 
{
	protected String name;
	protected int money;
	protected int attack;
	protected int defense;
	protected int hp;
	
	public Units(int money, int attack, int defense, int hp)
	{
		this.money = money;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
	}
	
	public abstract int mobAttack();
	public abstract int mobDefense();
	public abstract int die(); // 몹이 죽었을 때 실시.
	
	@Override
	public String toString()
	{
		System.out.println("== 몹 정보 ==");
		System.out.print("이름 : "+name+"  ");
		System.out.print("생명력 : "+hp+"  ");
		System.out.print("공격력 : "+attack+"  ");
		System.out.println("방어력 : "+defense);
		return null;
	}
}
