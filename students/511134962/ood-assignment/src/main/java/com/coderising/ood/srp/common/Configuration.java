/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp.common;

import java.util.HashMap;
import java.util.Map;

public class Configuration
{
    static Map< String, String > configurations = new HashMap<>();
    static
    {
        configurations.put( ConfigurationKeys.SMTP_SERVER, "smtp.163.com" );
        configurations.put( ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com" );
        configurations.put( ConfigurationKeys.EMAIL_ADMIN, "admin@company.com" );
    }

    /**
     * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
     *
     * @param key
     *
     * @return
     */
    public String getProperty( String key )
    {
        return configurations.get( key );
    }

}
