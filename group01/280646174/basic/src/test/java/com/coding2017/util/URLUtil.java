package com.coding2017.util;

import com.google.common.base.Strings;

/**
 * Created by kaitao.li on 2017/3/12.
 */
public class URLUtil {

    public static String getFileNameFromURL(String url) {
        if (Strings.isNullOrEmpty(url)) {
            return "";
        }

        int index = url.lastIndexOf('/');
        if (index == -1) {
            return "";
        }

        return url.substring(index + 1);
    }
}
