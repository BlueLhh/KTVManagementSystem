package com.lhh.test.yuding;

import java.util.Scanner;

import com.lhh.ktv.exception.ServiceException;
import com.lhh.ktv.model.entity.Reserve;
import com.lhh.ktv.model.service.IReserveService;
import com.lhh.ktv.model.service.impl.ReserveServiceImpl;

public class Add {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String resTime;// 预定时间
		String resendTime;// 保留时间
		String resPname;// 预定人姓名
		Long resmemId;// 会员编号(0为非会员)
		String resPhone;// 预定人号码

		// 设置时间类型
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		IReserveService irs = new ReserveServiceImpl();
		Reserve res = new Reserve();
		Long roomId;

		System.out.println("请输入房间的ID:");
		roomId = new Scanner(System.in).nextLong();
		System.out.println("请输入预定的时间:");
		resTime = new Scanner(System.in).nextLine();
		System.out.println("请输入保留的时间:");
		resendTime = new Scanner(System.in).nextLine();
		System.out.println("请输入预定人的名字:");
		resPname = new Scanner(System.in).next();
		System.out.println("请输入会员编号（0为非会员）");
		resmemId = new Scanner(System.in).nextLong();
		System.out.println("请输入联系方式:");
		resPhone = new Scanner(System.in).next();

		// 转换时间格式 String 强转 Date
//		try {
//			resTime = dateFormat.parse(startTime);
//			resendTime = dateFormat.parse(endTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		res.getRoom().setRoomId(roomId);
		res.setResTime(resTime);
		res.setResendTime(resendTime);
		res.setResPname(resPname);
		res.setResPname(resPname);
		res.setResmemId(resmemId);
		res.setResPhone(resPhone);

		try {
			System.out.println("-----------------------");
			irs.addReserve(res);
			System.out.println("预定成功！");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
