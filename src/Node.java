public class Node {
    int x;
    int bf;
    Node left;
    Node right;
    Node parent;


    public Node(int x){
        this.x = x;
    };

    public void insert(int x){
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
    }


}

