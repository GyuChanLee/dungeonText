package co.dodo.dungeons.units;

public class Goblin extends Units
{
	public Goblin(int money, int attack, int defense, int hp) // 일반 몹 고블린.
	{
		super(money,attack,defense,hp);
		this.setName("고블린");
		System.out.println("호시탐탐 숨어서 당신을 노리던 고블린과 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 고블린이 당신에게 달려들어 검을 찌릅니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 찌르기");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 고블린은 작은 방패를 들어 방어태세를 갖춥니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 고블린이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
