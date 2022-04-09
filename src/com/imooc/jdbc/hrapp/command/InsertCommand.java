package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 新增员工数据
 */
public class InsertCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入员工编号:");
        int eno = in.nextInt();
        System.out.print("请输入员工姓名:");
        String ename = in.next();
        System.out.print("请输入员工薪资:");
        float salary = in.nextFloat();
        System.out.print("请输入隶属部门:");
        String dname = in.next();
        System.out.print("请输入入职日期：");
        String strHiredate = in.next();
        //String到java.sql.Date分为两步
        //1.String转为java.util.Date
        java.util.Date udHiredate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            udHiredate = sdf.parse(strHiredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //2.java.util.Date转为java.sql.Date
        long time = udHiredate.getTime();//获取自1970年到现在的毫秒数
        java.sql.Date sdHiredate = new java.sql.Date(time);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee(eno,ename,salary,dname,hiredate ) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setString(2, ename);
            pstmt.setFloat(3, salary);
            pstmt.setString(4,dname);
            pstmt.setDate(5,sdHiredate); //java.sql.Date
            int cnt = pstmt.executeUpdate();//所有写操作都使用executeUpdate
            System.out.println("cnt:" + cnt);
            System.out.println(ename + "员工入职手续已办理");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(null,pstmt,conn);
        }
    }
}
