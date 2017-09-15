package com.lhh.test.emp;

import java.sql.Connection;

import com.lhh.ktv.common.ConnectionFactory;

/**
 * 
 * 测试连接数据库
 * 
 * @author 46512
 *
 */
public class Test4 {
	public static void main(String[] args) {
		Connection conn = null;
		
		try{
			//加载驱动包了连接数据库
			conn = ConnectionFactory.getConnection();
			System.out.println("加载驱动包成功！\n连接数据库成功！\n"+conn);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
