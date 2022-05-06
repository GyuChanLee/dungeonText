package co.dodo.dungeons.cards;

public class CardDefense extends CardVO
{
	public CardDefense(String cardId) 
	{
		
//		String cardId = "1";
		String cardName = "방어";
		int attack = 0;
		int defense = 5;
		int actionConsumption = 1;
		String readme = "작은 방패를 듭니다 [ +방어5 ]  [ -행동력1 ] ";
		setCardId(cardId);
		setCardName(cardName);
		setAttack(attack);
		setDefense(defense);
		setActionConsumption(actionConsumption);
		setReadme(readme);
	}
}
