package srp.refactor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tee on 2017/6/15.
 */
public class FileUtil {

    public static List<String> readFile(File file)throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String tmp = null;
            List<String> data = new ArrayList<>();
            while((tmp = br.readLine()) != null){
                String[] temp = tmp.split(" ");
                data.add(temp[0]);
                data.add(temp[1]);
            }

            return data;
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try{
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
