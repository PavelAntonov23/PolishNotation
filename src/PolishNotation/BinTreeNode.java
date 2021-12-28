package PolishNotation;
class BinTreeNode<T> {
    private BinTreeNode<T> parent;
    private BinTreeNode<T> left;
    private BinTreeNode<T> right;
    private T data;

     protected BinTreeNode(T data, BinTreeNode<T> parent){
        this.parent = parent;
        this.left = null;
        this.right = null;
        this.data = data;
    };

    protected void setLeftNode(BinTreeNode<T> node){
        if (left!=null) left = null;
        left = node;
    };

    protected void setRightNode(BinTreeNode<T> node){
        if (right!=null) right = null;
        right = node;
    };

    public BinTreeNode<T> getParent() {
        return parent;
    }

    public BinTreeNode<T> getLeft() {
        return left;
    }

    public BinTreeNode<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public void setData(T Data) {
        data = Data;
    }
}
