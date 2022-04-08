package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.Command;
import com.imooc.jdbc.hrapp.command.PstmtQueryCommand;
import com.imooc.jdbc.hrapp.command.QueryCommand;
import org.w3c.dom.ls.LSInput;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        Scanner in = new Scanner(System.in);
        Integer cmd = in.nextInt();
        switch (cmd){
            case 1: //查询部门员工
                Command command = new PstmtQueryCommand();
                command.execute();
                break;
        }
    }
}
