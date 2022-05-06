package co.dodo.dungeons.gameStart.operationPlayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.dodo.dungeons.dao.DataSource;

public class Operation
{
	 private DataSource dao = DataSource.getInstance();
	 private Connection conn;
	 
	 private PreparedStatement psmt;
	 private ResultSet rs;
	 
	 public int newChar(Object vo)
	 {
		 int n =0;
		 String sql = "";
		 
		 
		 return n;
	 }
}
