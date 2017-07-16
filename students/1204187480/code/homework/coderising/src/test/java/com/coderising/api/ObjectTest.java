package com.coderising.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by luoziyihao on 5/2/17.
 */
@Slf4j
public class ObjectTest {
    @Test
    public void test(){
        Object a[] = {1};
      log.info(a.getClass().getName());
      log.info(a.getClass().getCanonicalName());
      log.info(a.getClass().getSimpleName());
    }
}
