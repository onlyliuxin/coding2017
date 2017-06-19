package com.mimieye.odd.srp.service;

import com.mimieye.odd.srp.util.FileReadUtil;

import java.io.IOException;
import java.util.*;

/**
 * Created by Pierreluo on 2017/6/15.
 */
public interface PromotionInfoService {
    List<Map<String, String>> listPromotions(String filePath) throws Exception;
}
