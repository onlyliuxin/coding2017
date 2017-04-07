package week3.thread;

/**
 * Created by zndbl on 2017/3/26.
 */
public class ThreadDownload {

    public static void main(String[] args) {
//        //单线程下载
//        try {
//            String url = "http://img.alicdn.com/bao/uploaded/i2/412712826/TB2eNIZXXYC11BjSspfXXXcPFXa_!!412712826.jpg_240x240.jpg";
//            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//            conn.setRequestMethod("GET");
//            conn.setReadTimeout(5000);
//            conn.setConnectTimeout(5000);
//            InputStream in = conn.getInputStream();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
//            File file = new File("D:/a.jpg");
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
//            byte[] buffer = new byte[1024];
//            int len = -1;
//            while ((len = bufferedInputStream.read(buffer)) != -1) {
//                bufferedOutputStream.write(buffer, 0, len);
//                bufferedOutputStream.flush();
//            }
//        } catch (Exception e) {ScheduledThreadPoolExecutor
//            e.printStackTrace();
//        }

        //多线程部分参考网上的
        String url = "http://wx.qlogo.cn/mmopen/fqCl7qHPjf2JaKGXwqRe3WoMwnBouoSNG2Xd3kYAcfLEmibXEpZH9HVDyDiassfPgiav8kx9wNDypGxaibxdQFIXzIhib2N2ibuo07/0";
        FileDownload fileDownload = new FileDownload(url);
        fileDownload.download(3);
    }
}
