package com.ood.ocp.logs.config;

import com.ood.ocp.logs.content.ContentService;

import java.util.List;

/**
 * Created by ajaxfeng on 2017/6/24.
 */
public interface LoggerConfig {

    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    /**
     *
     * @return
     */
    public ContentService getContentService();

    /**
     * 设置类型
     *
     * @param sendTypeList
     */
    public void setSendTypeList(List<Integer> sendTypeList);

}
