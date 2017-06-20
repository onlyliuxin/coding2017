package com.coderising.ood.srp.optimize;

import java.util.List;
import java.util.stream.Collectors;

import static com.coding.common.util.FileUtils2.openStream;
import static com.coding.common.util.IOUtils2.readToStringList;

/**
 * 产品文件解析器
 * Created by luoziyihao on 6/12/17.
 */
public class ProductParser {

    public List<Product> parse(String productClassPath) {
        List<String> stringList = readToStringList(openStream(productClassPath));
        return stringList.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> s.contains(SPLIT_STRING))
                .map(this::parseLine)
                .collect(Collectors.toList());

    }

    private static final String SPLIT_STRING = " ";

    private Product parseLine(String s) {
        int index = s.indexOf(SPLIT_STRING);
        String productID = s.substring(0, index);
        String productDesc = s.substring(index);
        Product product = new Product();
        product.setProductDesc(productDesc);
        product.setProductID(productID);
        return product;
    }
}
