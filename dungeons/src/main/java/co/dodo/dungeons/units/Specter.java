package co.dodo.dungeons.units;

public class Specter extends Units
{
	public Specter(int money, int attack, int defense, int hp) // 일반 몹 유령.
	{
		super(money,attack,defense,hp);
		this.setName("유령");
		System.out.println("감옥 죄수들의 원혼이 담긴 유령을 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 유령은 홀연히 당신 앞에 나타나 당신을 미지의 힘으로 날려버립니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 폴터가이스트 공격");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 유령은 피해를 막는 초자연적인 방어막을 시전합니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 유령이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
