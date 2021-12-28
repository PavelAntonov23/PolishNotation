package PolishNotation;

class BinTree<T> {
    private BinTreeNode<T> root;
    protected BinTree(){
        root = null;
    };
    protected BinTreeNode<T> getRoot(){
        return root;
    }
    protected void setRoot(BinTreeNode<T> root){
        this.root = root;
    }
    protected BinTreeNode<T> addLeftChild(T data, BinTreeNode<T> node){
        if (node == null)
        {
            root = new BinTreeNode<T>(data, null);
            return root;
        }
        else {
            node.setLeftNode(new BinTreeNode<T>(data, node));
            return node.getLeft();
        }
    };

    protected BinTreeNode<T> addRightChild(T data, BinTreeNode<T> node){
        if (node == null)
        {
            root = new BinTreeNode<T>(data, null);
            return root;
        }
        else {
            node.setRightNode(new BinTreeNode<T>(data, node));
            return node.getRight();
        }
    };

    protected void delete(BinTreeNode<T> node){
        if (node==root) {
            root = null;
        }
        else {
            if (node.getParent().getLeft() == node)
                node.getParent().setLeftNode(null);
            else
                node.getParent().setRightNode(null);
        }
    };

    protected void clear(){
        if (root!=null) {
            root = null;
        }
    };
}
