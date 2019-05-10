package com.ffq.order;

import java.util.UUID;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffq.store.StoreMapper;

@Service
public class OrderService {
	@Autowired
	private RedissonClient rc;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private StoreMapper storeMapper;

	public void order(String id, Integer num) {
		RLock lock = rc.getLock("iphone_stock");
		lock.lock();
		Long leftNum = storeMapper.getGoodsNumById(id);
		int cnum = leftNum.intValue() - num.intValue();
		if (cnum >= 0) {
			orderMapper.create(UUID.randomUUID().toString(), "ORDER");
			storeMapper.reduce(id, cnum);
		}
		lock.unlock();
	}
}
