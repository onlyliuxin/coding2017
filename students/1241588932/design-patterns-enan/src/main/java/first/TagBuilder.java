package first;

public class TagBuilder {

    private TagNode tagNode;
    private TagNode currentTagNode;
    private TagNode superTagNode;

    public TagBuilder(String tagName) {
        this.tagNode = new TagNode(tagName);
        this.currentTagNode = this.tagNode;
        this.superTagNode = null;
    }

    public TagBuilder addChild(String childTagName) {
        this.superTagNode = this.currentTagNode;
        TagNode tagNode = new TagNode(childTagName);
        this.currentTagNode.add(tagNode);
        this.currentTagNode = tagNode;
        return this;
    }

    public TagBuilder setAttribute(String key, String value) {
        this.currentTagNode.setAttribute(key, value);
        return this;
    }

    public TagBuilder addSibling(String siblingTagName) {
        TagNode tagNode = new TagNode(siblingTagName);
        this.superTagNode.add(tagNode);
        this.currentTagNode = tagNode;
        return this;
    }

    public TagNode build() {
        return tagNode;
    }

}
