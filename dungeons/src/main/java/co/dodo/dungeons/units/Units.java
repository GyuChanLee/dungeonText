package co.dodo.dungeons.units;

import lombok.Data;

@Data
public class Units 
{
	private String name;
	private int mobId;
	private int money;
	private int attack;
	private int defense;
	private int hp;
	private String mAppear;
	private String mAttack;
	private String mAttack2;
	private String mDefense;
	private int savingHp = 0;
	private int savinDef = 0;
	
	public Units(String name, int mobId ,int money, int attack, int defense, int hp, String mAppear, String mAttack,
			String mAttack2, String mDefense) 
	{
		this.name = name;
		this.mobId = mobId;
		this.money = money;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.mAppear = mAppear;
		this.mAttack = mAttack;
		this.mAttack2 = mAttack2;
		this.mDefense = mDefense;
		savingHp = hp;
		savinDef = defense;
	}
	
	public int mobAttack() 
	{
		System.out.println(mAttack);
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 "+mAttack2);
		System.out.println();
		return this.getAttack();
	}

	public int mobDefense() 
	{
		System.out.println(mDefense);
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	public int die()
	{
		System.out.println("== "+name+" 이/가 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		setHp(savingHp);
		setDefense(savinDef);
		return this.getMoney();
	}
	
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
