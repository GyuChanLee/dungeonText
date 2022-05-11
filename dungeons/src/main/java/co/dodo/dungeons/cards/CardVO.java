package co.dodo.dungeons.cards;

import lombok.Data;

@Data
public class CardVO 
{
	private int cardId;
	private String cardName;
	private int attack;
	private int defense;
	private int actionConsumption;
	private String readme;
	
	public CardVO() {}
	
	public CardVO(String cardName, int attack, int defense, int actionConsumption, String readme) 
	{
		this.cardName = cardName;
		this.attack = attack;
		this.defense = defense;
		this.actionConsumption = actionConsumption;
		this.readme = readme;
	}
	
	@Override
	public String toString()
	{
		return "[카드 이름 : " +cardName+"]  [카드 공격력 : "+attack+"]  [카드 방어력 : "+defense+"]  [소모 행동력 : "+actionConsumption+"]  [설명 : "+readme+"] ";
	}



}
