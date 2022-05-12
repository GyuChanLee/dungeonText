package co.dodo.dungeons.units;

import java.util.ArrayList;
import java.util.List;

public class UnitList
{
	private List<Units> unitList = new ArrayList<Units>();
	
	// stage1
	private String oakAppear = "성에서 배회하던 오크를 조우하였습니다! ";
	private String oakAttack = "== 오크가 강하게 내려칩니다...";
	private String oakAttack2 = "강공";
	private String oakDefense = "== 오크가 방패를 들고 막습니다...";
	private Units oak1 = new Units("오크",1,100,15,5,30,oakAppear,oakAttack,oakAttack2,oakDefense); // 1-4 층 오크 
	private Units oak2 = new Units("오크",2,200,20,10,70,oakAppear,oakAttack,oakAttack2,oakDefense); // 6-9 층 오크
	
	private String goblinAppear = "호시탐탐 숨어서 당신을 노리던 고블린과 조우하였습니다! ";
	private String goblinAttack = "== 고블린이 당신에게 달려들어 검을 찌릅니다...";
	private String goblinAttack2 = "찌르기";
	private String goblinDefense = "== 고블린은 작은 방패를 들어 방어태세를 갖춥니다...";
	private Units goblin1 = new Units("고블린",1,200,15,0,30,goblinAppear,goblinAttack,goblinAttack2,goblinDefense); // 1-4 층
	private Units goblin2 = new Units("고블린",2,300,30,3,40,goblinAppear,goblinAttack,goblinAttack2,goblinDefense); // 6-9 층
	
	private String armorAppear = "당신이 오자 살아나 걸어다니는 갑옷을 조우하였습니다! ";
	private String armorAttack = "== 걸어다니는 갑옷이 당신에게 대검을 휘두릅니다...";
	private String armorAttack2 = "대검";
	private String armorDefense = "== 걸어다니는 갑옷은 고풍스러운 방패를 들어 방어태세를 갖춥니다...";
	private Units armor1 = new Units("걸어다니는 갑옷",1,100,10,8,30,armorAppear,armorAttack,armorAttack2,armorDefense); // 1-4 층
	private Units armor2 = new Units("걸어다니는 갑옷",2,200,10,15,50,armorAppear,armorAttack,armorAttack2,armorDefense); // 6-9 층
	
	private String slimeAppear = "성안을 통통 튀어다니는 슬라임을 조우하였습니다! ";
	private String slimeAttack = "== 슬라임이 통통 튀어가 당신에게 달려듭니다...";
	private String slimeAttack2 = "박치기";
	private String slimeDefense = "== 슬라임이 몸을 웅크리며 당신의 공격을 막습니다...";
	private Units slime1 = new Units("슬라임",1,100,15,5,30,slimeAppear,slimeAttack,slimeAttack2,slimeDefense); // 1-4 층
	private Units slime2 = new Units("슬라임",2,200,20,10,40,slimeAppear,slimeAttack,slimeAttack2,slimeDefense); // 6-9 층
	
	private String vampireAppear = "잠을 자고 있던 뱀파이어와 조우하였습니다! ";
	private String vampireAttack = "== 뱀파이어가 당신을 할큅니다...";
	private String vampireAttack2 = "할퀴기";
	private String vampireDefense = "== 뱀파이어가 몸을 단단하게 변화시킵니다...";
	private Units vampire1 = new Units("뱀파이어",1,150,25,5,50,vampireAppear,vampireAttack,vampireAttack2,vampireDefense); // 1-4 층
	private Units vampire2 = new Units("뱀파이어",2,300,35,5,50,vampireAppear,vampireAttack,vampireAttack2,vampireDefense); // 6-9 층
	
	// stage2
	private String skeletonAppear = "감옥을 배회하던 해골을 조우하였습니다! ";
	private String skeletonAttack = "== 해골이 강하게 내려칩니다...";
	private String skeletonAttack2 = "강공";
	private String skeletonDefense = "== 해골이 뼈다귀 방패를 들고 막습니다...";
	private Units skeleton1 = new Units("해골",1,300,16,5,50,skeletonAppear,skeletonAttack,skeletonAttack2,skeletonDefense); // 1-4 층
	private Units skeleton2 = new Units("해골",2,400,22,10,80,skeletonAppear,skeletonAttack,skeletonAttack2,skeletonDefense); // 6-9 층
	
	private String zombieAppear = "살점이 녹아내리고 있는 좀비를 조우하였습니다! ";
	private String zombieAttack ="== 좀비가 당신을 강하게 물어뜯습니다...";
	private String zombieAttack2 = "물어뜯기";
	private String zombieDefense = "== 좀비가 몸을 숙이며 방어태세를 갖춥니다...";
	private Units zombie1 = new Units("좀비",1,300,18,0,30,zombieAppear,zombieAttack,zombieAttack2,zombieDefense); // 1-4 층
	private Units zombie2 = new Units("좀비",2,450,32,3,40,zombieAppear,zombieAttack,zombieAttack2,zombieDefense); // 6-9 층
	
	private String witchAppear = "감옥의 시체들로 흑마법을 만들고 있는 마녀를 조우하였습니다! ";
	private String witchAttack = "== 마녀는 상대의 생명력을 빼앗는 흑마법을 주창합니다...";
	private String witchAttack2 = "흑마법";
	private String witchDefense = "== 마녀는 피해를 막아 줄 마법의 방패를 소환합니다...";
	private Units witch1 = new Units("마녀",1,250,15,18,40,witchAppear,witchAttack,witchAttack2,witchDefense); // 1-4 층
	private Units witch2 = new Units("마녀",2,400,25,25,80,witchAppear,witchAttack,witchAttack2,witchDefense); // 6-9 층
	
	private String specterAppear = "감옥 죄수들의 원혼이 담긴 유령을 조우하였습니다! ";
	private String specterAttack = "== 유령은 홀연히 당신 앞에 나타나 당신을 미지의 힘으로 날려버립니다...";
	private String specterAttack2 = "폴터가이스트 공격";
	private String specterDefense = "== 유령은 피해를 막는 초자연적인 방어막을 시전합니다...";
	private Units specter1 = new Units("유령",1,300,35,5,60,specterAppear,specterAttack,specterAttack2,specterDefense); // 1-4 층
	private Units specter2 = new Units("유령",2,500,40,10,90,specterAppear,specterAttack,specterAttack2,specterDefense); // 6-9 층
	
	private String wardenAppear = "오랜시간동안 감옥 탑을 지켜온 감옥 파수꾼을 조우하였습니다! ";
	private String wardenAttack = "== 감옥 파수꾼이 강력한 화살을 연달아 발사합니다...";
	private String wardenAttack2 = "화살 공격";
	private String wardenDefense = "== 감옥 파수꾼은 질긴 가죽을 두른 방패를 들었습니다...";
	private Units warden1 = new Units("감옥 파수꾼",1,250,35,5,90,wardenAppear,wardenAttack,wardenAttack2,wardenDefense); // 1-4 층
	private Units warden2 = new Units("감옥 파수꾼",2,600,40,5,110,wardenAppear,wardenAttack,wardenAttack2,wardenDefense); // 6-9 층
	
	// stage3
	private String fireElementalAppear = "불의 군주의 충직한 동료인 불의 정령을 조우하였습니다! ";
	private String fireElementalAttack = "== 불의 정령은 불의 힘을 이용한 화염탄을 발사합니다...";
	private String fireElementalAttack2 = "화염탄";
	private String fireElementalDefense = "== 불의 정령은 불로 다져진 자신의 몸을 뻗으며 표효합니다...";
	private Units fireElemental1 = new Units("불의 정령",1,400,45,5,90,fireElementalAppear,fireElementalAttack,fireElementalAttack2,fireElementalDefense); 
	private Units fireElemental2 = new Units("불의 정령",2,650,55,15,150,fireElementalAppear,fireElementalAttack,fireElementalAttack2,fireElementalDefense);
	
	private String flameDogAppear = "불의 군주의 충직한 애견인 화염 개를 조우하였습니다! ";
	private String flameDogAttack = "== 화염 개는 불의 힘을 머금으며 당신을 물어뜯습니다...";
	private String flameDogAttack2 = "물어뜯기";
	private String flameDogDefense = "== 불의 정령은 불을 두른 자신의 몸을 더욱 펼치며 위협합니다...";
	private Units flameDog1 = new Units("화염 개",1,350,65,15,110,flameDogAppear,flameDogAttack,flameDogAttack2, flameDogDefense); 
	private Units flameDog2 = new Units("화염 개",2,550,85,15,190,flameDogAppear,flameDogAttack,flameDogAttack2, flameDogDefense);
	
	private String burntMinionAppear = "불의 군주를 추종하는 불탄 하수인을 조우하였습니다! ";
	private String burntMinionAttack = "== 불탄 하수인은 괴성을 지르며 횃불을 당신에게 던집니다...";
	private String burntMinionAttack2 = "횃불 공격";
	private String burntMinionDefense = "== 불탄 하수인은 그을린 방패를 듭니다...";
	private Units burntMinion1 = new Units("불탄 하수인",1,550,35,35,150,burntMinionAppear,burntMinionAttack,burntMinionAttack2, burntMinionDefense); 
	private Units burntMinion2 = new Units("불탄 하수인",2,850,45,55,200,burntMinionAppear,burntMinionAttack,burntMinionAttack2, burntMinionDefense); 
	
	
	private String pyromancerAppear = "불탄 탑에 숨어있던 악명높은 화염술사를 조우하였습니다! ";
	private String pyromancerAttack = "== 화염술사는 뜨거운 화염 마법을 당신에게 시전합니다...";
	private String pyromancerAttack2 = "화염 마법";
	private String pyromancerDefense = "== 화염술사는 불의 방패를 마법으로 만듭니다...";
	private Units pyromancer1 = new Units("화염술사",1,700,80,0,100,pyromancerAppear,pyromancerAttack,pyromancerAttack2, pyromancerDefense); 
	private Units pyromancer2 = new Units("화염술사",2,1000,100,0,100,pyromancerAppear,pyromancerAttack,pyromancerAttack2, pyromancerDefense); 
	
	private String pyromaniaAppear = "방화에 미친 방화광을 조우하였습니다! ";
	private String pyromaniaAttack = "== 방화광은 뜨거운 화염 폭탄을 당신에게 던집니다...";
	private String pyromaniaAttack2 = "화염폭발";
	private String pyromaniaDefense = "== 방화광은 스스로 몸을 지지며 의지를 다집니다...";
	private Units pyromania1 = new Units("방화광",1,1000,100,0,100,pyromaniaAppear,pyromaniaAttack,pyromaniaAttack2, pyromaniaDefense);
	private Units pyromania2 = new Units("방화광",2,1300,150,0,120,pyromaniaAppear,pyromaniaAttack,pyromaniaAttack2, pyromaniaDefense);
	
	// stage 4
	
	public void loadUnitList()
	{
		// stage1
		unitList.add(oak1);
		unitList.add(oak2);
		unitList.add(goblin1);
		unitList.add(goblin2);
		unitList.add(armor1);
		unitList.add(armor2);
		unitList.add(slime1);
		unitList.add(slime2);
		unitList.add(vampire1);
		unitList.add(vampire2);
		// stage2
		unitList.add(skeleton1);
		unitList.add(skeleton2);
		unitList.add(zombie1);
		unitList.add(zombie2);
		unitList.add(witch1);
		unitList.add(witch2);
		unitList.add(specter1);
		unitList.add(specter2);
		unitList.add(warden1);
		unitList.add(warden2);
		// stage3
		unitList.add(fireElemental1);
		unitList.add(fireElemental2);
		unitList.add(flameDog1);
		unitList.add(flameDog2);
		unitList.add(burntMinion1);
		unitList.add(burntMinion2);
		unitList.add(pyromancer1);
		unitList.add(pyromancer2);
		unitList.add(pyromania1);
		unitList.add(pyromania2);
		// stage4
	}
	
	public Units getUnit(String name, int mobId) // 유닛이름과 아이디로 검색해서 유닛 인스턴스 반환
	{
		Units unit = null;
		for(int i = 0; i<unitList.size(); i++)
		{
			if(unitList.get(i).getName().equals(name) && unitList.get(i).getMobId()==mobId)
			{
				unit = unitList.get(i);
			}
		}
		return unit;
	}
}
