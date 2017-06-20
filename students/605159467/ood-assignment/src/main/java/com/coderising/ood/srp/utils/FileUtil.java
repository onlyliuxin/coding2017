package com.coderising.ood.srp.utils;

import com.coderising.ood.srp.bean.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * Created by fuyi.ren on 2017/6/17  11:08
 * Description:
 */
public class FileUtil
{

    /**
     *  读取文件内容，返回List<Product>
     * @param file
     * @return
     * @throws IOException
     */
    public static List  readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        List list = null;
        try
        {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            Product product = null;
            list = new ArrayList<Product>();
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(" ");
                product = new Product();
                product.setProductID(data[0]);
                product.setProductDesc(data[1]);

                list.add(product);
            }
            return  list;
        } catch (IOException e)
        {
            throw new IOException(e.getMessage());
        } finally
        {
            br.close();
        }
    }

}
