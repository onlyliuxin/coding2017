/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil
{

    /**
     * 应该从数据库读， 但是简化为直接生成。
     *
     * @param sql
     *
     * @return
     */
    public static List< HashMap > query( String sql )
    {
        List< HashMap > userList = new ArrayList();
        for ( int i = 1; i <= 3; i++ )
        {
            HashMap< String, String > userInfo = new HashMap();
            userInfo.put( "NAME", "User" + i );
            userInfo.put( "EMAIL", "aa@bb.com" );
            userList.add( userInfo );
        }
        return userList;
    }
}
