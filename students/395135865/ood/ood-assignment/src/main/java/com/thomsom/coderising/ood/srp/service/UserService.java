package com.thomsom.coderising.ood.srp.service;

import com.thomsom.coderising.ood.srp.UserInfo;

import java.util.List;

/**
 * the user service
 *
 * @author Thomson Tang
 * @version Created: 29/06/2017.
 */
public interface UserService {
    List<UserInfo> listUser() throws Exception;
}
