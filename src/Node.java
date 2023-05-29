
public class Node<T extends Comparable<T>> {

    private Node<T> leftChild;
    private Node<T> rightChild;
    private T data;
    // as all nodes are inserted as leafs, atheir height are by default 1
    private int height = 1;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /*    public void insert(int x){
        int current = this.x;

        if(x<current){
            if(this.left != null)
                this.left.insert(x);
            else
                this.left = new Node(x);
                this.left.parent = this;
        }else{
            if(this.right != null)
                this.right.insert(x);
            else
                this.right = new Node(x);
                this.right.parent = this;
        }
    }

    public boolean search(int x){
        int current = this.x;

        if(x == current)
            return true;

        if(x<current){
            if(this.left != null)
                return this.left.search(x);
            else
                return false;

        }else{
            if(this.right != null)
                return this.right.search(x);
            else
                return false;
        }
    }*/


}

