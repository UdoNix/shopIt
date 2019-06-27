package de.hdm.server.db;

import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.shared.bo.ReportObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportMapper {
	
	public static ReportMapper reportMapper = null;
	
	protected ReportMapper() {
		
	}

	public static ReportMapper reportMapper() {
		if (reportMapper == null) {
			reportMapper = new ReportMapper();
		}
		return reportMapper;
	}
	
	public Vector<ReportObject> createTeamTimeShopReport(int teamId, Timestamp startDate, Timestamp endDate, int shopId) {
		
		Connection con = DBConnection.connection();
		Vector <ReportObject> result = new Vector<ReportObject>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(item.id) AS 'count', article.name AS 'name', unit.quantity AS 'quantity', unit.unit AS 'unit', item.changeDate AS 'changeDate', responsibility.shopId AS 'shopId', item.teamId AS teamId "
					+ "FROM item JOIN article ON item.articleId = article.id JOIN unit ON item.unitId = unit.id JOIN team ON item.teamId = team.id JOIN responsibility ON item.id = responsibility.itemId "
					+ "WHERE item.teamId = " + teamId + "AND item.changeDate BETWEEN '"+ startDate +"' AND '" + endDate + "' AND shopId = " + shopId + " "
							+ "GROUP BY item.id ORDER BY COUNT(item.articleId) DESC");
			
			while(rs.next()) {
				ReportObject r = new ReportObject();
				r.setCount(rs.getInt("count"));
				r.setArticle(rs.getString("name"));
				r.setQuantity(rs.getFloat("quantity"));
				r.setUnit(rs.getString("unit"));
				r.setChangeDate(rs.getTimestamp("timeStamp"));
				r.setShop(rs.getInt("shopId"));
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
public Vector<ReportObject> createTeamTimeReport(int teamId, Timestamp startDate, Timestamp endDate) {
		
		Connection con = DBConnection.connection();
		Vector <ReportObject> result = new Vector<ReportObject>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(item.id) AS 'count', article.name AS 'name', unit.quantity AS 'quantity', unit.unit AS 'unit', item.changeDate AS 'changeDate', item.teamId AS teamId "
					+ "FROM item JOIN article ON item.articleId = article.id JOIN unit ON item.unitId = unit.id JOIN team ON item.teamId = team.id JOIN responsibility ON item.id = responsibility.itemId "
					+ "WHERE item.teamId = " + teamId + "AND item.changeDate BETWEEN '"+ startDate +"' AND '" + endDate + " "
							+ "GROUP BY item.id ORDER BY COUNT(item.articleId) DESC");
			
			while(rs.next()) {
				ReportObject r = new ReportObject();
				r.setCount(rs.getInt("count"));
				r.setArticle(rs.getString("name"));
				r.setQuantity(rs.getFloat("quantity"));
				r.setUnit(rs.getString("unit"));
				r.setChangeDate(rs.getTimestamp("timeStamp"));
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector<ReportObject> createTeamShopReport(int teamId, int shopId){
		
		Connection con = DBConnection.connection();
		Vector <ReportObject>  result = new Vector<ReportObject>();
		
		try {
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(item.id) AS 'count', article.name AS 'name', unit.quantity AS 'quantity', unit.unit AS 'unit', responsibility.shopId AS 'shopId', item.teamId AS teamId "
					+ "FROM item JOIN article ON item.articleId = article.id JOIN unit ON item.unitId = unit.id JOIN team ON item.teamId = team.id JOIN responsibility ON item.id = responsibility.itemId "
					+ "WHERE item.teamId = " + teamId + " AND shopId = " + shopId + " "
					+ "GROUP BY item.id ORDER BY COUNT(item.articleId) DESC");
			
			while(rs.next()) {
				ReportObject r = new ReportObject();
				r.setCount(rs.getInt("count"));
				r.setArticle(rs.getString("name"));
				r.setQuantity(rs.getFloat("quantity"));
				r.setUnit(rs.getString("unit"));
				r.setShop(rs.getInt("shopId"));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
