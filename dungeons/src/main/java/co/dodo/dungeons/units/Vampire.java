package co.dodo.dungeons.units;

public class Vampire extends Units
{
	public Vampire(int money, int attack, int defense, int hp) // 일반 몹 뱀파이어.
	{
		super(money,attack,defense,hp);
		this.setName("뱀파이어");
		System.out.println("잠을 자고 있던 뱀파이어와 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 뱀파이어가 당신을 할큅니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 할퀴기");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 뱀파이어가 몸을 단단하게 변화시킵니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 뱀파이어가 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
