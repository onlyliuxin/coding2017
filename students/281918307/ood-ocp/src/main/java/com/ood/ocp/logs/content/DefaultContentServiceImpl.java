package com.ood.ocp.logs.content;

import org.springframework.stereotype.Service;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
@Service("contentService")
public class DefaultContentServiceImpl implements ContentService {
    @Override
    public String getConteng(String logMsg) {
        return logMsg;
    }
}
