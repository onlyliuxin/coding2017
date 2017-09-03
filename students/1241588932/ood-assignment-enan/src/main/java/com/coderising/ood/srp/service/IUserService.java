package com.coderising.ood.srp.service;

import com.coderising.ood.srp.entity.User;

import java.util.Collection;

/**
 * Created by Enan on 17/6/18.
 */
public interface IUserService {

    Collection<User> querySubscriptedUsersByProductID(String productID);
}
