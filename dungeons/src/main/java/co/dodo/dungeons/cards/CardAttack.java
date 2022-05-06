package co.dodo.dungeons.cards;

public class CardAttack extends CardVO
{
	public CardAttack(String cardId) 
	{
//		String cardId = "0";
		String cardName = "공격";
		int attack = 30;
		int defense = 0;
		int actionConsumption = 1;
		String readme = "기본적인 칼을 휘두릅니다  [ +공격30 ]  [ -행동력1 ] ";
		setCardId(cardId);
		setCardName(cardName);
		setAttack(attack);
		setDefense(defense);
		setActionConsumption(actionConsumption);
		setReadme(readme);
	}
	
}
