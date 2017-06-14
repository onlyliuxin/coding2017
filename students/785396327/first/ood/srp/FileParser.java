package first.ood.srp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by gongxun on 2017/6/12.
 */
public abstract class FileParser {
    protected String[] data;

    protected FileParser(String filePath) {
        try {
            if (StringUtils.isEmpty(filePath))
                throw new RuntimeException("init file parser must contains a legal file");
            readFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException("parse file cause errors");
        }
    }

    private void readFile(String filePath) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String temp = br.readLine();
            data = temp.split(" ");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }

    protected abstract void parseInfoFromFile(Email email);

}
