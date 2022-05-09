package co.dodo.dungeons.units;

import lombok.Data;

@Data
public abstract class Boss 
{
	private String name;
	private int money;
	private int attack;
	private int defense;
	private int hp;
	
	public Boss(int money, int attack, int defense, int hp)
	{
		this.money = money;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
	}
	
	public abstract int mobAttack();
	public abstract int mobDefense();
	public abstract int die();
	public abstract int bossAttack();
	public abstract int bossUltimate();
	
	@Override
	public String toString()
	{
		System.out.println("== 보스 정보 ==");
		System.out.print("이름 : "+name+"  ");
		System.out.print("생명력 : "+hp+"  ");
		System.out.print("공격력 : "+attack+"  ");
		System.out.println("방어력 : "+defense);
		return null;
	}
}
