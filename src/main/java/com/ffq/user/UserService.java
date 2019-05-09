package com.ffq.user;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffq.credit.CreditMapper;
import com.ffq.order.OrderMapper;
import com.google.common.collect.Maps;

@Service
public class UserService {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private CreditMapper creditMapper;

	/**
	 * @MethodName getCodeByUserId
	 * @Description 封装前端数据结构
	 * @param userId
	 * @return
	 */
	public Map<String, Set<String>> getCodeByUserId(String uid) {
		Map<String, Set<String>> authCode = Maps.newHashMap();
		List<User> daoData = userDao.listByUid(uid);
		Map<String, List<User>> map = daoData.stream().collect(Collectors.groupingBy(User::getModel));
		for (Entry<String, List<User>> en : map.entrySet()) {
			authCode.put(en.getKey(), en.getValue().stream().map(User::getTag).collect(Collectors.toSet()));
		}
		return authCode;
	}

	/**
	 * @MethodName pass
	 * @Description 接口URL鉴权
	 * @param userId
	 * @param url
	 * @return
	 */
	public boolean pass(String uid, String url) {
		Integer rs = userDao.boolByUidAndUrl(uid, url);
		if (rs == null || rs.intValue() == 0) {
			return false;
		}
		return true;
	}

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
