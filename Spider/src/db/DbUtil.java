package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;


public class DbUtil {
	private static Connection conn = null;  
    private static Statement st = null;  
    private static ResultSet rs = null;  
  
    private DbUtil() {  
  
    }  
  
    private static final DbUtil instance = new DbUtil(); 
  
    public static DbUtil getInstance() {  
        return instance;  
    }  
  
    /** 
     * 连接数据库 
     *  
     * @return 
     */  
    public Connection connection() {  
        try {  
            Class.forName(Constants.DRIVER);  
        } catch (ClassNotFoundException e1) {  
            e1.printStackTrace();  
        }  
        try {  
            conn = DriverManager.getConnection(Constants.DBURL, Constants.USER,  
                    Constants.PASSWORD);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
  
    /** 
     * 关闭连接 
     *  
     * @param rs 
     * @param st 
     * @param conn 
     */  
    public void release(ResultSet rs, Statement st, Connection conn) {  
        try {  
            try {  
                if (null != rs) {  
                    rs.close();  
                }  
            } catch (Exception e) {  
                rs = null;  
            }  
            try {  
                if (null != st) {  
                    st.close();  
                }  
            } catch (Exception e) {  
                st = null;  
            }  
            try {  
                if (null != conn) {  
                    conn.close();  
                }  
            } catch (Exception e) {  
                conn = null;  
            }  
        } finally {  
            rs = null;  
            st = null;  
            conn = null;  
        }  
    }  
      
    /** 
     * 插入 
     * @param sql 
     */  
    public void insert(String sql){  
    	
        try{  
        	DbUtil.getInstance().connection();  
            st = conn.createStatement();  
            st.execute(sql);  
            DbUtil.getInstance().release(rs, st, conn);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
    public List<String> queryByUrl() 
    {
    	int totalCount=0;
   String rss=null;
      Connection conn = DbUtil.getInstance().connection();
   
      try {
		st=conn.createStatement();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
      //4.执行语句  
      try {
		rs=st.executeQuery("select id, url from link");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
       
      //5.处理结果  
      try {
		while(rs.next())  
		  {  
		      String s=  rs.getObject("url").toString(); 
		      rss=s+","; 
		  }	
	} catch (SQLException e) {
		e.printStackTrace();
	}    
      
    DbUtil.getInstance().release(rs, st, conn);  
    String[] arr = rss.split(",");
    List<String> list = java.util.Arrays.asList(rss);
   return list;
} 
    
}
    
  