package co.dodo.dungeons.service;

import java.util.List;

import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.vo.PlayerVO;


public interface SaveFiles 
{
	List<PlayerVO> playerAllSelect(); // 유저 전체 조회
	PlayerVO playerSelect(PlayerVO vo); // 유저 조회
	int playerInsert(PlayerVO vo); // 세이브파일 새로 생성
	int playerUpdate(PlayerVO vo); // 세이브파일 덮어쓰기
	int playerDelete(PlayerVO vo); // 세이브파일 삭제
	int cardInsert(CardVO vo); // 카드 db 생성
	int cardUpdate(CardVO vo); // 카드 능력치 수정
	int itemInsert(ItemVO vo); // 아이템 db 생성
	ItemVO itemSelect(); // 최근에 넣은 아이템 하나 불러오기.
	int inventoryInsert(ItemVO vo1,PlayerVO vo2); // 인벤토리에 넣기
	int cardListInsert(CardVO vo1,PlayerVO vo2); // 카드리스트에 넣기
	List<CardVO> CardListSelect(PlayerVO vo); // 카드리스트 불러오기 
	List<CardVO> Card5Select(); // 초기 5개 카드
	int equipInsert(ItemVO vo1, PlayerVO vo2); // 플레이어 장비에 장비 넣기. (테이블에 넣기)
	int equipDelete(ItemVO vo); // 장비에 있던 아이템 equip테이블에서 제거.
	ItemVO[] equipLoad(PlayerVO vo); // 저장한 장비 상태 불러오기.
	List<ItemVO> showInven(PlayerVO p1); // db에 저장된 플레이어의 인벤토리 목록
	
	int saveDeletePlayer(PlayerVO vo); // 세이브파일 삭제 
	List<PlayerVO> playerBestSelect(); // kill 포인트로 계산.
	
	CardVO recentCardSelect(); // 가장 바로 최근에 넣은 카드를 가져오기.
	int itemDelete(ItemVO vo); // 아이템 한개 삭제 > 아이템 판매 기능.
	void cardDelete(CardVO vo); // 카드 한개 삭제
}
