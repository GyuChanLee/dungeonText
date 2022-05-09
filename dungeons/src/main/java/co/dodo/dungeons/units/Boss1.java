package co.dodo.dungeons.units;

public class Boss1 extends Boss
{
	
	public Boss1(int money, int attack, int defense, int hp) // stage1 - 10층 보스
	{
		super(money, attack, defense, hp);
		this.setName("성의 주인");
		System.out.println("버려진 성의 마지막 층의 주인이 나타났습니다...");
	}

	@Override
	public int mobAttack() 
	{
		System.out.println("== 성의 주인이 칼을 크게 휘두릅니다...");
		System.out.println("== "+this.getAttack()+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack();
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 성의 주인이 큰 방패를 듭니다...");
		System.out.println("== 10 만큼의 방어력을 가집니다...");
		System.out.println();
		int defense = this.getDefense()+10;
		return defense;
	}
	
	@Override
	public int bossAttack()
	{
		System.out.println("== 성의 주인이 미지의 마법을 주창합니다...");
		System.out.println("== "+(int)(this.getAttack()*1.5)+"만큼의 위력을 가집니다...");
		System.out.println();
		return (int)Math.round(this.getAttack()*1.5);
	}
	
	@Override
	public int bossUltimate() 
	{
		System.out.println("== <궁극기 주의> 성의 주인이 거대한 마법 화살을 발사합니다...");
		System.out.println("== "+this.getAttack()*2+"만큼의 위력을 가집니다...");
		System.out.println();
		return this.getAttack()*2;
	}
	
	@Override
	public int die()
	{
		System.out.println("== 성의 주인이 죽었습니다!");
		System.out.println("== 보상 : "+this.getMoney()+"금을 얻었습니다! ");
		return this.getMoney();
	}
}
