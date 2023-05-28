import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<Integer> list = new ArrayList<>();

        list.add(7);
        list.add(8);
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(12);

        Node avl = new Node(6);

        for (Integer i :list) {
            avl.insert(i);
        }

        //abr.search(12);

        print2D(avl);
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
        print2DUtil(node.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.x + "\n");

        // Process left child
        print2DUtil(node.left, space);
    }

    // Wrapper over print2DUtil()
    static void print2D(Node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
}
