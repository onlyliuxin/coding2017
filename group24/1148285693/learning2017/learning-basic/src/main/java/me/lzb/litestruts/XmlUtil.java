package me.lzb.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * xml工具类
 * Created by LZB on 2017/3/16.
 */
public class XmlUtil {

    Document document;

    public XmlUtil(String xmlPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        document = reader.read(new File(XmlUtil.class.getResource("/").getFile() + "litestruts/" + xmlPath));
    }

    private Node getAuctionNodeByName(String auctionName) {
        return document.selectSingleNode("//action[@name='" + auctionName + "']");
    }

    public String getAuctionPathByName(String auctionName) {
        return getAuctionNodeByName(auctionName).valueOf("@class");
    }

    public String getResultJsp(String auctionName, String resultName) {
        Node auction = getAuctionNodeByName(auctionName);
        return auction.getDocument().selectSingleNode("//result[@name='" + resultName + "']").getText();
    }

}
