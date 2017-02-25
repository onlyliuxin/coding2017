    
    //2017年2月24日17:02:55
    public BinaryTreeNode insert (Integer key) 
    {
        // 新增节点
        BinaryTreeNode newNode = new BinaryTreeNode(key);
        // 当前节点
        BinaryTreeNode current = root;
        // 上个节点
        BinaryTreeNode parent  = null;
        // 如果根节点为空
        if (current == null) {
            root = newNode;
            return newNode;
        }
        while (true) 
        {
            parent = current;
            if (key < current.value) 
            {
                current = current.left;
                if (current == null) 
                {
                    parent.left = newNode;
                    return newNode;
                }
            } else 
            {
                current = current.right;
                if (current == null) 
                {
                    parent.right = newNode;
                    return newNode;
                }
            }
        }
    }
