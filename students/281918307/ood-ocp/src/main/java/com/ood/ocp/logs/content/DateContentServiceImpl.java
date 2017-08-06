package com.ood.ocp.logs.content;

import com.ood.ocp.util.DateUtil;
import org.springframework.stereotype.Service;

/**
 * 日期+log
 * Created by ajaxfeng on 2017/6/24.
 */
@Service("dateContentService")
public class DateContentServiceImpl implements ContentService {

    @Override
    public String getConteng(String logMsg) {
        return DateUtil.getCurrentDateAsString() + ":" + logMsg;
    }
}
