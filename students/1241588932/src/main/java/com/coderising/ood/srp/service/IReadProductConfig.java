package com.coderising.ood.srp.service;

import com.coderising.ood.srp.entity.Product;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Enan on 17/6/18.
 */
public interface IReadProductConfig {

    Collection<Product> read(File file) throws IOException;

}
