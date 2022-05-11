package co.dodo.dungeons.units;

public class Skeleton extends Units
{
	public Skeleton(int money, int attack, int defense, int hp) // 일반 몹 해골.
	{
		super(money,attack,defense,hp);
		this.setName("해골");
		System.out.println("감옥을 배회하던 해골을 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 해골이 강하게 내려칩니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 강공");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 해골이 뼈다귀 방패를 들고 막습니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 해골이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
