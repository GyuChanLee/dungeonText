package co.dodo.dungeons.vo;

import lombok.Data;

@Data
public class PlayerVO 
{
	
	public PlayerVO() {}
	
	public PlayerVO(String userId, int pw) 
	{
		this.userId = userId;
		this.pw = pw;
	}
	
	public PlayerVO(String userId, int pw, int progress, int kills, int action, int attack, int defense, int money, int hp) 
	{
		this.userId = userId;
		this.pw = pw;
		this.progress = progress;
		this.kills = kills;
		this.action = action;
		this.attack = attack;
		this.defense = defense;
		this.money = money;
		this.hp = hp;
	}
	
	private String userId; // 아이디 pk
	private int pw; // 비번
	private int progress = 0; // 진행상황
	private int kills = 0; //킬수
	private int action = 3; // 행동력
	private int attack = 20;
	private int defense = 20;
	private int money = 0;
	private int hp = 100;
	
	@Override
	public String toString()
	{
		System.out.println("== 캐릭터 정보 ==");
		System.out.println("이름   : "+userId);
		System.out.println("hp     : "+hp);
		System.out.println("공격   : "+attack);
		System.out.println("방어   : "+defense);
		System.out.println("무기   : ");
		System.out.println("방어구 : ");
		System.out.println("소지금 : "+money);
		System.out.println();
		return null;
	}
}
