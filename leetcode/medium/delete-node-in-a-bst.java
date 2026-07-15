class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } 
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } 
        else {
            // Node with one or no child
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Node with two children
            root.val = minValue(root.right);
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }
}