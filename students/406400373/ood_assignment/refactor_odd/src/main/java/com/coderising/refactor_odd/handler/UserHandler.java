package com.coderising.refactor_odd.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coderising.ood.srp.DBUtil;
import com.coderising.refactor_odd.constant.Constant;
import com.coderising.refactor_odd.entity.UserEntity;

/**
 * @author cenkailun
 * @Date 17/6/19
 * @Time 下午9:18
 */
public class UserHandler {

	public List<UserEntity> fetchUser(String sql) {
		List<HashMap> temp = DBUtil.query(sql);
		List<UserEntity> result = new ArrayList<>();
		for (HashMap hashMap : temp) {
			UserEntity user = new UserEntity();
			user.setEmail((String) hashMap.get(Constant.EMAIL_KEY));
			user.setName((String) hashMap.get(Constant.NAME_KEY));
			result.add(user);
		}
		return result;
	}
}
