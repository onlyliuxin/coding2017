package MultiLevelNesting;

import java.util.ArrayList;

/**
 * Tag构造器
 *
 * @author LanyuanXiaoyao
 * @create 2017-07-18
 */
public class TagBuilder {

    private TagNode root;
    private TagBuilder rootBuilder;

    public TagBuilder(String rootName) {
        this.root = new TagNode(rootName);
        this.root.setChildren(new ArrayList<>());
        this.root.setAttributes(new ArrayList<>());
    }

    public TagBuilder(String rootName, TagBuilder tagBuilder) {
        this.root = new TagNode(rootName);
        this.root.setChildren(new ArrayList<>());
        this.root.setAttributes(new ArrayList<>());
        this.rootBuilder = tagBuilder;
    }

    public TagBuilder addChild(String childName) {
        TagBuilder childBuilder = new TagBuilder(childName, this);
        root.getChildren().add(childBuilder.toTagTreeNode());
        return childBuilder;
    }

    public TagBuilder setAttribute(String name, String value) {
        TagNode.Attribute attribute = new TagNode.Attribute(name, value);
        root.getAttributes().add(attribute);
        return this;
    }

    public TagBuilder and() {
        return rootBuilder;
    }

    public void toXML() {
        if (rootBuilder == null)
            System.out.println(root.toXML());
        else
            rootBuilder.toXML();
    }

    public TagNode toTagTreeNode() {
        return root;
    }

    public static void main(String[] args) {
        new TagBuilder("root")
                .setAttribute("attr0","0")
                .addChild("child")
                    .setAttribute("attr1","1")
                    .setAttribute("attr1","1")
                    .setAttribute("attr1","1")
                    .addChild("child2")
                        .setAttribute("attr2","2")
                        .setAttribute("attr2","2")
                        .setAttribute("attr2","2")
                        .and()
                    .and()
                .addChild("child3")
                    .setAttribute("attr3","3")
                .toXML();
    }
}
