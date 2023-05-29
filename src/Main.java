import java.util.ArrayList;
import java.util.List;

public class Main {

    // SRC : BST : https://www.youtube.com/watch?v=zIX3zQP0khM
    // SRC : AVL : https://www.youtube.com/watch?v=Jj9Mit24CWk
    // SRC : Print2D : https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/

/*
    Terms
    =======
    Level of a node is the number of edges between root node and node in question
    Height is the level of between the node in question and the further null node of the tree

    Balance
    =======
    a tree or sub tree is considered balanced when : balance(root) E [-1, 1]
    balance(node) = height(leftChild) - height(rightChild)

    negative balances means left rotation
    positive balances means right rotation

    Rotation scenario
    =================
    leftleft situation => balance > 1 => solved using a single right rotation
    rightright situation  =>balance < -1 => solved using a singler left rotation
    leftright sitation => balances needs to have the same sign as it's parent => apply a left roation then a right
    rightleft situation => balances needs to have the same sign as it's parent =>  apply a right rotation then a left

 */

    public static void main(String[] args) {
	// write your code here
        List<Integer> list = new ArrayList<>();

        list.add(7);
        list.add(8);
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(12);

        AVLTree avl = new AVLTree<>(new Node<Integer>(6));

        for (Integer i :list) {
            avl.insert(i);
        }

        //abr.search(12);

        print2D(avl.getRoot());
    }

    // Wrapper over print2DUtil()
    static void print2D(Node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    // Function to print binary tree in 2D
    // It does reverse inorder traversal
    static void print2DUtil(Node node, int space)
    {
        final int COUNT = 10;

        // Base case
        if (node == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(node.getRightChild(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.getData() + "\n");

        // Process left child
        print2DUtil(node.getLeftChild(), space);
    }


}
