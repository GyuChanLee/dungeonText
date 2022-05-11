package co.dodo.dungeons.units;

public class Witch extends Units
{
	public Witch(int money, int attack, int defense, int hp) // 일반 몹 마녀.
	{
		super(money,attack,defense,hp);
		this.setName("마녀");
		System.out.println("감옥의 시체들로 흑마법을 만들고 있는 마녀를 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 마녀는 상대의 생명력을 빼앗는 흑마법을 주창합니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 흑마법");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 마녀는 피해를 막아 줄 마법의 방패를 소환합니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 마녀가 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
