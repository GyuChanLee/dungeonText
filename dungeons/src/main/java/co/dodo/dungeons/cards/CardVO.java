package co.dodo.dungeons.cards;

import lombok.Data;

@Data
public class CardVO 
{
	private String cardId;
	private String cardName;
	private int attack;
	private int defense;
	private int actionConsumption;
	private String readme;
	
	@Override
	public String toString()
	{
		return "[카드 이름 : " +cardName+"]  [카드 공격력 : "+attack+"]  [카드 방어력 : "+defense+"]  [소모 행동력 : "+actionConsumption+"]  [설명 : "+readme+"] ";
	}
}
