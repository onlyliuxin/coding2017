package com.coderising.ood.srp.dao;

import java.util.List;

/**
 * Created with IDEA
 * Created by fuyi.ren on 2017/6/17  14:07
 * Description:
 */
public interface PromotionMailDao
{

    /**
     *  从数据库中读取信息，人员信息
     */
    public  List loadMailingList() throws Exception;




}

