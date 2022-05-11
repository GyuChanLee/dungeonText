package co.dodo.dungeons.units;

public class Slime extends Units
{
	public Slime(int money, int attack, int defense, int hp) // 일반 몹 슬라임.
	{
		super(money,attack,defense,hp);
		this.setName("슬라임");
		System.out.println("성안을 통통 튀어다니는 슬라임을 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 슬라임이 통통 튀어가 당신에게 달려듭니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 박치기");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 슬라임이 몸을 웅크리며 당신의 공격을 막습니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 슬라임이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
