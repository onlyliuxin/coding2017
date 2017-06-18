/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.util.DBUtil;

import java.util.HashMap;
import java.util.List;

public class ProductPromotionDAO
{
    private String sendMailQuery = null;

    public ProductPromotionDAO() { }

    public void setLoadQuery( String productID ) throws Exception
    {
        sendMailQuery
                = "Select name from subscriptions " + "where product_id= '" + productID + "' " + "and send_mail=1 ";
        System.out.println( "loadQuery set" );
    }

    public List<HashMap > loadMailingList() throws Exception
    {
        return DBUtil.query( this.sendMailQuery );
    }
}