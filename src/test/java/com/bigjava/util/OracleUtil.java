/**
 * 
 */
package com.bigjava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**  
* @ClassName: OracleUtil
* @Description: 数据库连接工具类  
* @author 邱高强  
* @date 2020年3月1日    
*/
public class OracleUtil {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER = "shanshan";
	public static final String PASSWORD = "shanshan123";
	
	
	/**  
	* @Title: getConnection  
	* @Description: 连接数据库
	* @return Connection
	*/
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return conn;
	}
	
	/**  
	* @Title: closeAll  
	* @Description: 断开数据库连接
	* @param rs
	* @param pstmt
	* @param conn void
	*/
	public static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
