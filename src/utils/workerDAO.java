package utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class workerDAO {

	public static worker login(worker w) throws SQLException
	{
		worker wresult=null;
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select * from worker where wId='"+w.getwId()+"'";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			wresult=new worker();
			wresult.setwId(rs.getString("wId"));
			wresult.setwPass(rs.getString("wPass"));
			wresult.setwName(rs.getString("wName"));
			wresult.setwType(rs.getInt("wType"));
		}
		
		return wresult;
	
	}
	
	public static void signin(String wid) throws SQLException
	{
		Connection conn=dbUtil.getConnection();
		String sql=" update sign set sCondition=1,sOnTime=now() where sWid=? ";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		ptmt.setString(1,wid);
		ptmt.execute();
	}
	
	public static void signout(String wid) throws SQLException
	{
		Connection conn=dbUtil.getConnection();
		String sql=" update sign set sCondition=0,sOffTime=now() where sWid=? ";
		String sql2=" update worker set wCondition=0 where wId=? ";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		PreparedStatement ptmt2=(PreparedStatement)conn.prepareStatement(sql2);
		ptmt.setString(1,wid);
		ptmt2.setString(1,wid);
		ptmt.execute();
		ptmt2.execute();
	}
	
	public static void updateSafe(String wid,Double lat,Double lon) throws SQLException
	{
		Connection conn=dbUtil.getConnection();
		String sql=" update worker set wCondition=1,wLat=?,wLon=?,wTime=now() where wId=? ";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		ptmt.setDouble(1,lat);
		ptmt.setDouble(2,lon);
		ptmt.setString(3,wid);
		ptmt.execute();
	}
	
	public static void updateDanger(String wid,Double lat,Double lon) throws SQLException
	{
		Connection conn=dbUtil.getConnection();
		String sql=" update worker set wCondition=2,wLat=?,wLon=?,wTime=now() where wId=? ";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		ptmt.setDouble(1,lat);
		ptmt.setDouble(2,lon);
		ptmt.setString(3,wid);
		ptmt.execute();
	}
	
	public static void help(String wid,Double lat,Double lon) throws SQLException
	{
		Connection conn=dbUtil.getConnection();
		String sql=" update worker set wCondition=3,wLat=?,wLon=?,wTime=now() where wId=? ";
		PreparedStatement ptmt=(PreparedStatement)conn.prepareStatement(sql);
		ptmt.setDouble(1,lat);
		ptmt.setDouble(2,lon);
		ptmt.setString(3,wid);
		ptmt.execute();
	}
	
	public static boolean warningWorker(String wid,Double lat,Double lon) throws SQLException
	{
		boolean pd=false;
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select * from worker where wCondition!=0 and wType=1 and wId!='"+wid+"'";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			double latp=rs.getDouble("wLat");
			double lonp=rs.getDouble("wLon");
			
			double lat1 = (Math.PI/180)*lat;
			double lat2 = (Math.PI/180)*latp;
			     
			double lon1 = (Math.PI/180)*lon;
			double lon2 = (Math.PI/180)*lonp;
			     
			//µØÇò°ë¾¶
			double R = 6378.1;
			     
			double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
			    
			double distance=new BigDecimal(d).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			System.out.println("distance: "+distance);
			
			if(distance<=100)
			{
				pd=true;
			}
			
		}
		
		return pd;
	}
	
	public static boolean warningTrain(String wid,Double lat,Double lon) throws SQLException
	{
		boolean pd=false;
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select * from worker where wCondition!=0 and wType=0 and wId!='"+wid+"'";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			double latp=rs.getDouble("wLat");
			double lonp=rs.getDouble("wLon");
			
			double lat1 = (Math.PI/180)*lat;
			double lat2 = (Math.PI/180)*latp;
			     
			double lon1 = (Math.PI/180)*lon;
			double lon2 = (Math.PI/180)*lonp;
			     
			//µØÇò°ë¾¶
			double R = 6378.1;
			     
			double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
			    
			double distance=new BigDecimal(d).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			System.out.println("distance: "+distance);
			
			if(distance<=100)
			{
				pd=true;
			}
			
		}
		
		return pd;
	}
	
	public static List<worker> allWorker() throws SQLException
	{
		List<worker> wlist=new ArrayList<worker>();
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select * from worker where wType=0";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			worker wresult=new worker();
			wresult.setwId(rs.getString("wId"));
			wresult.setwPass(rs.getString("wPass"));
			wresult.setwName(rs.getString("wName"));
			wresult.setwType(rs.getInt("wType"));
			wresult.setwCondition(rs.getInt("wCondition"));
			wresult.setwLat(rs.getDouble("wLat"));
			wresult.setwLon(rs.getDouble("wLon"));
			wresult.setwTime(rs.getString("wTime"));
			wlist.add(wresult);
		}
		
		return wlist;
	}
	
	public static List<worker> allTrain() throws SQLException
	{
		List<worker> wlist=new ArrayList<worker>();
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select * from worker where wType=1";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			worker wresult=new worker();
			wresult.setwId(rs.getString("wId"));
			wresult.setwPass(rs.getString("wPass"));
			wresult.setwName(rs.getString("wName"));
			wresult.setwType(rs.getInt("wType"));
			wresult.setwCondition(rs.getInt("wCondition"));
			wresult.setwLat(rs.getDouble("wLat"));
			wresult.setwLon(rs.getDouble("wLon"));
			wresult.setwTime(rs.getString("wTime"));
			wlist.add(wresult);
		}
		
		return wlist;
	}
	
	public static List<sign> allSign() throws SQLException
	{
		List<sign> slist=new ArrayList<sign>();
		
		Connection conn=(Connection)dbUtil.getConnection();
		String sql=" select sWid,wName,sOnTime,sOffTime,sCondition from worker,sign where sWid=wid";
		Statement sm=(Statement) conn.createStatement();
		ResultSet rs=sm.executeQuery(sql);
		
		while(rs.next())
		{
			sign sresult=new sign();
			sresult.setsWid(rs.getString("sWid"));
			sresult.setwName(rs.getString("wName"));
			sresult.setsOnTime(rs.getString("sOnTime"));
			sresult.setsOffTime(rs.getString("sOffTime"));
			sresult.setsCondition(rs.getInt("sCondition"));
			slist.add(sresult);
		}
		
		return slist;
	}
	
}
