package dp.builder;

import java.util.List;

/**
 * 标签构造
 * Created by lx on 2017/7/22.
 */
public class TagBuilder {
    private TagNode root;// 根节点
    private TagNode currentNode;// 当前节点
    private TagNode prevNode;// 上一节点

    public TagBuilder(String order) {
        root = new TagNode(order);
        currentNode = root;
    }

    /**
     * 添加子标签
     * @param nodeName 节点名称
     * @return TagBuilder
     */
    public TagBuilder addChild(String nodeName) {
        TagNode node = new TagNode(nodeName);
        List<TagNode> children = currentNode.getChildren();
        children.add(node);
        prevNode = currentNode;
        currentNode = node;
        return this;
    }

    /**
     * 添加当前标签属性
     * @param key
     * @param value
     * @return TagBuilder
     */
    public TagBuilder setAttribute(String key, String value) {
        currentNode.setAttribute(key, value);
        return this;
    }

    /**
     * 添加兄弟标签
     * @param nodeName 节点名称
     * @return TagBuilder
     */
    public TagBuilder addSibling(String nodeName) {
        TagNode node = new TagNode(nodeName);
        prevNode.getChildren().add(node);
        currentNode = node;
        return this;
    }

    /**
     * 设置文本值
     * @param value
     * @return 节点名称
     */
    public TagBuilder setText(String value) {
        return null;
    }

    public String toXML() {
        return root.toXML();
    }
}
