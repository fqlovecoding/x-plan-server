package com.ffq.jta;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffq.credit.CreditMapper;
import com.ffq.order.OrderMapper;

@Service
public class TCCService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CreditMapper creditMapper;
	
	/**
	 * @MethodName tcc
	 * @Description 分布式事务测试
	 */
	@Transactional
	public void tcc() {
		orderMapper.save();
		int i = 1 / 0;
		creditMapper.save();
	}
}
