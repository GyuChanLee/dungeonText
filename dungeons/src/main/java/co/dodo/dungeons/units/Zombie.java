package co.dodo.dungeons.units;

public class Zombie extends Units
{
	public Zombie(int money, int attack, int defense, int hp) // 일반 몹 좀비.
	{
		super(money,attack,defense,hp);
		this.setName("좀비");
		System.out.println("살점이 녹아내리고 있는 좀비를 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 좀비가 당신을 강하게 물어뜯습니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 물어뜯기");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 좀비가 몸을 숙이며 방어태세를 갖춥니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 좀비가 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
