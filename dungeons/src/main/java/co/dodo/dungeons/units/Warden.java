package co.dodo.dungeons.units;

public class Warden extends Units
{
	public Warden(int money, int attack, int defense, int hp) // 일반 몹 감옥탑 파수꾼.
	{
		super(money,attack,defense,hp);
		this.setName("감옥 파수꾼");
		System.out.println("오랜시간동안 감옥 탑을 지켜온 감옥 파수꾼을 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 감옥 파수꾼이 강력한 화살을 연달아 발사합니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 화살 공격");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 감옥 파수꾼은 질긴 가죽을 두른 방패를 들었습니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 감옥 파수꾼이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
