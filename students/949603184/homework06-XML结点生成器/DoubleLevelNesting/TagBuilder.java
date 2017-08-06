package DoubleLevelNesting;

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
        if (rootBuilder == null)
            root.getChildren().add(childBuilder.build());
        else
            rootBuilder.build().getChildren().add(childBuilder.build());
        return childBuilder;
    }

    public TagBuilder setAttribute(String name, String value) {
        TagNode.Attribute attribute = new TagNode.Attribute(name, value);
        root.getAttributes().add(attribute);
        return this;
    }

    public TagBuilder end() {
        return rootBuilder;
    }

    public void toXML() {
        if (rootBuilder == null)
            System.out.println(root.toXML());
        else
            rootBuilder.toXML();
    }

    public TagNode build() {
        return root;
    }

    public static void main(String[] args) {
        new TagBuilder("root")
                .setAttribute("attr3", "value")
                .setAttribute("attr4", "value")
                .addChild("child")
                    .setAttribute("attr1", "value")
                    .setAttribute("attr2", "value")
                .addChild("child2")
                    .setAttribute("attr5", "value")
                    .setAttribute("attr6", "value")
                .toXML();
    }
}
