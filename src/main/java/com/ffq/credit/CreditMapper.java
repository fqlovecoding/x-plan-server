package com.ffq.credit;

import org.apache.ibatis.annotations.Insert;

public interface CreditMapper {
	@Insert({ "insert into credit(cid, oid, uid) values('cid:1', 'oid:1', 'uid:1')" })
	void save();
}
