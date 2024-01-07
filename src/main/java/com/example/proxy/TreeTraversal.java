package com.example.proxy;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class TreeTraversal {
    public static void printAllBranches(TreeNode root, String path) {
        if (root == null) {
            return;
        }

        // Append the current node's data to the path
        path += root.data + " ";

        // If the current node is a leaf (has no left or right child), print the path
        if (root.left == null && root.right == null) {
            System.out.println("Branch: " + path);
            return;
        }

        // Recursively traverse the left and right branches
        printAllBranches(root.left, path);
        printAllBranches(root.right, path);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Call the recursive function to print all branches
        printAllBranches(root, "");
    }
}

