package co.dodo.dungeons.cards;

public class CardAttack extends CardVO
{
	public CardAttack() 
	{
		String cardName = "공격";
		int attack = 5;
		int defense = 0;
		int actionConsumption = 1;
		String readme = "기본적인 칼을 휘두릅니다  [ 행동력 -1 ] ";
		setCardName(cardName);
		setAttack(attack);
		setDefense(defense);
		setActionConsumption(actionConsumption);
		setReadme(readme);
	}
	
}
