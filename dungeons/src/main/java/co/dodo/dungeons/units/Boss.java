package co.dodo.dungeons.units;

import lombok.Data;

@Data
public class Boss 
{
	private String name;
	private int money;
	private int attack;
	private int defense;
	private int hp;
	private String mAppear; // 등장 멘트
	private String mAttack; // attack 멘트
	private String mDefense; // defense 멘트
	private String mBossAttack; // 보스강공 멘트
	private String mBossUlt; // 궁극기 멘트
	private String mBossDeath; // 사망 멘트
	
	
	
	
	public Boss(String name, int money, int attack, int defense, int hp, String mAppear, String mAttack,
			String mDefense, String mBossAttack, String mBossUlt, String mBossDeath) 
	{
		this.name = name;
		this.money = money;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.mAppear = mAppear;
		this.mAttack = mAttack;
		this.mDefense = mDefense;
		this.mBossAttack = mBossAttack;
		this.mBossUlt = mBossUlt;
		this.mBossDeath = mBossDeath;
	}

	public int mobAttack() 
	{
		System.out.println(getMAttack());
		System.out.println("== "+this.getAttack()+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack();
	}

	public int mobDefense() 
	{
		System.out.println(getMDefense());
		System.out.println("== 10 만큼의 방어력을 가집니다...");
		System.out.println();
		int defense = this.getDefense()+10;
		return defense;
	}
	
	public int bossAttack()
	{
		System.out.println(getMBossAttack());
		System.out.println("== "+(int)(this.getAttack()*1.5)+"만큼의 위력을 가집니다...");
		System.out.println();
		return (int)Math.round(this.getAttack()*1.5);
	}
	
	public int bossUltimate() 
	{
		System.out.println(getMBossUlt());
		System.out.println("== "+this.getAttack()*2+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack()*2;
	}
	
	public int die()
	{
		System.out.println(mBossDeath);
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
	
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
