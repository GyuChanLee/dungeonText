package co.dodo.dungeons.service;

import java.util.List;

import co.dodo.dungeons.cards.CardVO;
import co.dodo.dungeons.items.ItemVO;
import co.dodo.dungeons.vo.CardListVO;
import co.dodo.dungeons.vo.InventoryVO;
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
	int inventoryInsert(ItemVO vo1,PlayerVO vo2); // 인벤토리에 넣기
	int cardListInsert(CardVO vo1,PlayerVO vo2); // 카드리스트에 넣기
	InventoryVO InventorySelect(PlayerVO vo); // 인벤토리 불러오기 해당 플레이어
	CardListVO CardListSelect(PlayerVO vo); // 카드리스트 불러오기 
	CardVO CardSelect(PlayerVO vo); // 카드 불러오기
	// 얻은 아이템 인벤토리에 넣기 > 아이템 고유번호 저장, 그 번호 인벤db에 저장.
}
