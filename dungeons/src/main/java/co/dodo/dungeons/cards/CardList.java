package co.dodo.dungeons.cards;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CardList
{
	private List<CardVO> cardList = new ArrayList<CardVO>();
	// "[카드 이름 : " +cardName+"]  [카드 공격력 : "+attack+"]  [카드 방어력 : "+defense+"]  [소모 행동력 : "+actionConsumption+"]  [설명 : "+readme+"]
	private CardVO cardAtt1 = new CardVO("강타",7,0,1,"검으로 강하게 내려칩니다  [ 행동력 -1 ]");
	private CardVO cardAtt2 = new CardVO("몸통 박치기",10,0,1,"온몸을 날려 공격합니다 [ 행동력 -1 ] ");
	private CardVO cardAtt3 = new CardVO("대검",13,0,1,"거대한 검으로 내려칩니다 [ 행동력 -1 ] ");
	private CardVO cardAtt4 = new CardVO("마법 화살",30,0,2,"마법으로 만든 날카로운 화살을 발사합니다 [ 행동력 -2 ] ");
	private CardVO cardAtt5 = new CardVO("파괴 광선",50,0,3,"어떤 존재든 없앨 수 있는 강력한 주문을 발사합니다 [ 행동력 -3 ] ");
	private CardVO cardDef1 = new CardVO("버티기",0,7,1,"적의 공격을 온몸으로 버틸 준비를 합니다 [ 행동력 -1 ] ");
	private CardVO cardDef2 = new CardVO("귀갑진",0,10,1,"고대부터 전해오던 방어진법을 사용합니다 [ 행동력 -1 ] ");
	private CardVO cardDef3 = new CardVO("마법 방패",0,25,2,"견고한 마법의 방패를 만드는 주문을 시전합니다 [ 행동력 -2 ] ");
	private CardVO cardDef4 = new CardVO("바리케이트",0,50,3,"어떤 공격이든 막을 수 있는 방어진을 구축합니다 [ 행동력 -3 ] ");
	
	public CardVO randomCard() // 카드를 랜덤하게 리젠.
	{
		int rand = (int)(Math.random()*80); // 0-79 확률
		if(rand>=0 && rand<5) // 5%
		{
			return getCardAtt5();
		}
		else if(rand>=5 && rand<10) // 5%
		{
			return getCardDef4();
		}
		else if(rand>=10 && rand<20) // 10%
		{
			return getCardAtt1();
		}
		else if(rand>=20 && rand<30) // 10%
		{
			return getCardAtt2();
		}
		else if(rand>=30 && rand<40) // 10%
		{
			return getCardAtt3();
		}
		else if(rand>=40 && rand<50) // 10%
		{
			return getCardAtt4();
		}
		else if(rand>=50 && rand<60) // 10%
		{
			return getCardDef1();
		}
		else if(rand>=60 && rand<70) // 10%
		{
			return getCardDef2();
		}
		else if(rand>=70 && rand<80) // 10%
		{
			return getCardDef3();
		}
		
		return null;
	}
	
	public List<CardVO> getCardList()
	{
		return cardList;
	}
	
	public CardVO getCard(int a)
	{
		return cardList.get(a);
	}
}
