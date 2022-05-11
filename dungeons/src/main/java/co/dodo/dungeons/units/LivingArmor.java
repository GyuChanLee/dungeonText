package co.dodo.dungeons.units;

public class LivingArmor extends Units
{
	public LivingArmor(int money, int attack, int defense, int hp) // 일반 몹 걸어다니는 갑옷.
	{
		super(money,attack,defense,hp);
		this.setName("걸어다니는 갑옷");
		System.out.println("당신이 오자 살아나 걸어다니는 갑옷을 조우하였습니다! ");
	}
	
	@Override
	public int mobAttack() 
	{
		System.out.println("== 걸어다니는 갑옷이 당신에게 대검을 휘두릅니다...");
		System.out.println("== "+this.getAttack()+"만큼의 피해를 가지는 대검");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 걸어다니는 갑옷은 고풍스러운 방패를 들어 방어태세를 갖춥니다...");
		System.out.println("== 5 만큼 방어력이 상승");
		System.out.println();
		int defense = this.getDefense()+5;
		return defense;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 걸어다니는 갑옷이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
