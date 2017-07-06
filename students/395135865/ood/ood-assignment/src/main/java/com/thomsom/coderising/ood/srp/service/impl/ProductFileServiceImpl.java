package com.thomsom.coderising.ood.srp.service.impl;

import com.thomsom.coderising.ood.srp.Product;
import com.thomsom.coderising.ood.srp.service.ProductService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * the implementation of product service which listing the products by reading from a file.
 *
 * @author Thomson Tang
 * @version Created: 24/06/2017.
 */
public class ProductFileServiceImpl implements ProductService {
    private String fileName;

    public ProductFileServiceImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Product> listProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(getClass().getResource(fileName).toURI());
        Stream<String> lines = Files.lines(path);
        lines.forEach(line -> products.add(resolveProduct(line)));
        return products;
    }

    @Override
    public List<Product> listSubscriptProduct(String userId) {
        return null;
    }

    private Product resolveProduct(String line) {
        String[] items = line.split(" ");
        if (items.length > 2) {
            return Product.newInstance(items[0], items[1]);
        }
        return Product.newInstance();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
