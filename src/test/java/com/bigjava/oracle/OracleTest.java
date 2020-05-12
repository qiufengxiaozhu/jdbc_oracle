package com.bigjava.oracle;

import com.bigjava.util.OracleUtil;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.Test;

import java.sql.*;

/**
 * @ClassName : OracleTest
 * @Description : 测试Oracle数据库连接
 * @Author : 邱高强
 * @Date: 2020-05-07 19:30
 */
public class OracleTest {

    /**
     * @description test    测试连接
     * @Param []
     * @return void
     * @date 2020/5/7 19:38
     */
    @Test
    public void test(){

        String sql = "select * from emp where empno = ?";

        Connection conn = OracleUtil.getConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, 7788);

            rs = pstmt.executeQuery();
            while(rs.next()) {

                System.out.println("员工姓名："+rs.getString("ename"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        OracleUtil.closeAll(rs, pstmt, conn);

    }


    /**
     * @description testProcedrue    java调用存储过程
     * @Param []
     * @return void
     * @date 2020/5/7 19:38
     */
    @Test
    public void testProcedrue(){

        String sql = "{call p_yearsal(?, ?)}";

        Connection conn = OracleUtil.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareCall(sql);
            pstmt.setObject(1, 7788);
            pstmt.registerOutParameter(2, OracleTypes.NUMBER);

            pstmt.execute();

            //输出第二个参数
            System.out.println(pstmt.getObject(2));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        OracleUtil.closeAll(rs, pstmt, conn);

    }

    /**
     * @description testFunction    java调用存储函数
     * @Param []
     * @return void
     * @date 2020/5/7 19:38
     */
    @Test
    public void testFunction(){

        String sql = "{? = call f_yearsal(?)}";

        Connection conn = OracleUtil.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareCall(sql);
            pstmt.registerOutParameter(1, OracleTypes.NUMBER);
            pstmt.setObject(2, 7788);

            pstmt.execute();

            //输出第一个参数
            System.out.println(pstmt.getObject(1));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        OracleUtil.closeAll(rs, pstmt, conn);

    }
}
