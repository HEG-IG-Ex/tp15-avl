public class AVLTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    public AVLTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /*    @Override

        public Tree<T> insert(T data) {
             if(isEmpty()){
                 root = new Node<>(data);
             }else{
                 insert(data, root);
             }
            return this;
        }

        private void insert(T data, Node<T> node){
            if(data.compareTo(node.getData())<0){
                if(node.getLeftChild() == null){
                    Node<T> newNode = new Node<>(data);
                    node.setLeftChild(newNode);
                } else {
                    insert(data, node.getLeftChild());
                }
            }else if(data.compareTo(node.getData())> 0){
                if(node.getRightChild() == null){
                    Node<T> newNode = new Node<>(data);
                    node.setRightChild(newNode);
                } else {
                    insert(data, node.getRightChild());
                }
            }
        }

    */

    //Heavily recusrive implementation
    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        // indicate on which side of the tree Im in
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        } else {
            return node;
        }

        // Require for AVL
        updateHeight(node);
        return applyRotation(node);

    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        // indicate on which side of the tree Im in
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setLeftChild(delete(data, node.getRightChild()));
        } else {
            //One child or leaf node (no children)
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                node.getLeftChild();
            }
            // Two Childrem
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));

        }

        // Require for AVL
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        // traverse the tree from the left end to the right end
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMin(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));
        node.setHeight(maxHeight + 1);
    }

    private int height(Node<T> node) {
        // Avoid null pointer exception when calling getHeight on Nll Nodes
        return node != null ? node.getHeight() : 0;
    }


    private Node<T> applyRotation(Node<T> node) {
        int balance = balance(node);

        // Left heavy
        if(balance > 1){
            // Next Left Child of the node unbalanced on the other side
            // parent and child doesn't ahve the balance of the same sign
            if(balance(node.getLeftChild())<0){
                //LeftRight Rotation
                // The setRLeftChild call here allow to ensure that the parent node after the firt left rotation
                // has the correct reference to the new node rotated and doesn't keep it's old child ref
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            //Right Rotation
            return rotateRight(node);
        }
        // Right heavy
        if(balance < -1){
            // Next Right Child of the node unbalanced on the other side
            // parent and child doesn't ahve the balance of the same sign
            if(balance(node.getLeftChild())>0){
                // RightLeft Rotation
                // The setRightChild call here allow to ensure that the parent node after the firt right rotation
                // has the correct reference to the new node rotated and doesn't keep it's old child ref
                node.setRightChild(rotateRight(node.getLeftChild()));
            }
            //Left Rotation
            return rotateLeft(node);
        }
        return node;
    }

    private int balance(Node<T> node){
        return node != null
                ? height(node.getLeftChild()) - height(node.getRightChild())
                : 0;
    }

    private Node<T> rotateRight(Node<T> node){
        Node<T> leftNode = node.getLeftChild();
        Node<T> centerNode = node.getRightChild();

        // Left Node up one and Node Down one to the right
        leftNode.setRightChild(node);

        // Detach the center node and attach if to the new far right node at his left
        node.setLeftChild(centerNode);

        // recalculate the height for rotated node (center has rotate it has only been detach/attach at the same level)
        updateHeight(node);
        updateHeight(leftNode);

        // return the node that took the place of the "root" node. the higher node in the rotation
        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node){
        Node<T> rightNode = node.getRightChild();
        Node<T> centerNode = node.getLeftChild();

        // Right Node up one and Node Down one to the Left
        rightNode.setLeftChild(node);

        // Detach the center node and attach if to the new far left node at his right
        node.setRightChild(centerNode);

        // recalculate the height for rotated node (center has rotate it has only been detach/attach at the same level)
        updateHeight(node);
        updateHeight(rightNode);

        // return the node that took the place of the "root" node. the higher node in the rotation
        return rightNode;
    }


    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
