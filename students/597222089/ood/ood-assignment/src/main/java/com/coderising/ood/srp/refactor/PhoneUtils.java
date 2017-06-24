package com.coderising.ood.srp.refactor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PhoneUtils {
    private static Set<Phone> getPhones() {
        Set<Phone> phones = new HashSet<>();

//        Map<String, String> phoneInfos = readFile("file");
//
//        for (String id : phoneInfos.keySet()) {
//            Phone photo = new Phone();
//            photo.setProductID(id);
//            photo.setProductDesc(phoneInfos.get(id));
//            phones.add(photo);
//        }
        phones.add(new Phone("P8756", "iPhone8"));
        phones.add(new Phone("P3946", "XiaoMi10"));
        phones.add(new Phone("P8904", "Oppo_R15"));
        phones.add(new Phone("P4955", "Vivo_X20"));

        return phones;
    }

    public static String getMessage(String name) {
        StringBuffer infos = new StringBuffer();

        Set<Phone> phones = getPhones();
        for (Phone phone : phones) {
            infos.append("尊敬的 " + name + ", 您关注的产品 " + phone.getProductDesc() + " 降价了，欢迎购买!");
        }
        return infos.toString();
    }

    private static Map<String, String> readFile (String filePath) {
        BufferedReader br = null;
        Map<String, String> phoneMap = new HashMap<>();
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));

            String temp;
            while (null != (temp = br.readLine())) {
                String[] data = temp.split(" ");

                phoneMap.put(data[0], data[1]);
                System.out.println("产品ID = " + data[0] + "\n");
                System.out.println("产品描述 = " + data[1] + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return phoneMap;
    }
}
