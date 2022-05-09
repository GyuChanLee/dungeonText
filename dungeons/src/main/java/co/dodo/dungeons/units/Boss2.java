package co.dodo.dungeons.units;

public class Boss2 extends Boss
{

	public Boss2(int money, int attack, int defense, int hp) 
	{
		super(money, attack, defense, hp);
		this.setName("감옥탑의 괴물");
		System.out.println("감옥 탑의 숨어있던 괴물이 나타났습니다...");
	}

	@Override
	public int mobAttack() 
	{
		System.out.println("== 괴물이 날카로운 발톱을 크게 휘두릅니다...");
		System.out.println("== "+this.getAttack()+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 괴물이 큰 몸을 웅크립니다...");
		System.out.println("== 10 만큼의 방어력을 가집니다...");
		System.out.println();
		int defense = this.getDefense()+10;
		return defense;
	}
	
	@Override
	public int bossAttack()
	{
		System.out.println("== 괴물이 한껏 웅크렸다가 당신에게 날아듭니다...");
		System.out.println("== "+(int)(this.getAttack()*1.5)+"만큼의 위력을 가집니다...");
		System.out.println();
		return (int)Math.round(this.getAttack()*1.5);
	}
	
	@Override
	public int bossUltimate() 
	{
		System.out.println("== <궁극기 주의> 괴물이 미친듯한 속도로 당신을 물어재끼기 위해 달려듭니다...");
		System.out.println("== "+this.getAttack()*2+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack()*2;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 괴물을 퇴치했습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
