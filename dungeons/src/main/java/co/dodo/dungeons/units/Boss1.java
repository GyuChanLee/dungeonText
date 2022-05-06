package co.dodo.dungeons.units;

import co.dodo.dungeons.items.ItemVO;

public class Boss1 extends Units
{
	
	public Boss1(int money, int attack, int defense, int hp) // stage1 - 10층 보스
	{
		super(money, attack, defense, hp);
		this.name = "성의 주인";
		System.out.println("버려진 성의 마지막 층의 주인이 나타났습니다...");
	}

	@Override
	public int mobAttack() 
	{
		System.out.println("== 성의 주인이 칼을 크게 휘두릅니다...");
		System.out.println("== "+attack+"만큼의 위력을 가집니다...");
		System.out.println();
		return attack;
	}

	@Override
	public int mobDefense() 
	{
		System.out.println("== 성의 주인이 큰 방패를 듭니다...");
		System.out.println("== "+defense+"만큼의 방어력을 가집니다...");
		System.out.println();
		return defense;
	}
	
	public int bossAttack()
	{
		System.out.println("== 성의 주인이 미지의 마법을 주창합니다...");
		System.out.println("== "+attack*2+"만큼의 위력을 가집니다...");
		System.out.println();
		return attack*2;
	}

	@Override
	public int die()
	{
		System.out.println("== 성의 주인이 죽었습니다!");
		System.out.println("== 보상 : "+money+"금을 얻었습니다! ");
		return money;
	}
	
	public ItemVO specialDrop()
	{
		int rand = (int)(Math.random()*10);
		if(rand >= 0 || rand <= 5)
		{
			ItemVO dropItem = new ItemVO();
			return dropItem;
		}
		else
		{
			return null;
		}
	}

}
