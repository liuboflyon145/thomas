package basic.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by liubo on 16/7/14.
 */
public class TreeNode {
    private int[] arr = {1,2,3,4,5,6,7,8,9,12};
    private static List<Node> nodeList = null;
    public static void main(String[] args) {
        TreeNode binTree = new TreeNode();
        binTree.createBinTree();
        // nodeList中第0个索引处的值即为根节点
        Node root = nodeList.get(0);

        System.out.println("先序遍历：");
        preOrderTraverse(root);
        System.out.println();

        System.out.println("中序遍历：");
        inOrderTraverse(root);
        System.out.println();

        System.out.println("后序遍历：");
        postOrderTraverse(root);
    }

    private static class Node {
        Node left,right;
        int data;

        public Node(int data) {
            left = right =null;
            this.data = data;
        }
    }

    public void createBinTree(){
        nodeList = new LinkedList<Node>();
        for (int i = 0; i < arr.length; i++) {
            nodeList.add(new Node(arr[i]));
        }

        for (int parentIndex = 0; parentIndex < arr.length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).left=nodeList.get(parentIndex*2+1);
            nodeList.get(parentIndex).right = nodeList.get(parentIndex*2+2);
            System.out.println("left:"+(parentIndex*2+1)+"  right:"+(parentIndex*2+2));
        }
        int lastParentIndex = arr.length/2-1;
        System.out.println("lastParentIndex:"+lastParentIndex);
        nodeList.get(lastParentIndex).left=nodeList.get(lastParentIndex*2+1);
        if (arr.length%2==1){
            nodeList.get(lastParentIndex).right=nodeList.get(lastParentIndex*2+2);
        }
    }

    private static void preOrderTraverse(Node node){
        if (node==null){
            return;
        }
        System.out.print(node.data+"  ");
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    private static void inOrderTraverse(Node node){
        if (node==null){
            return;
        }
        inOrderTraverse(node.left);
        System.out.print(node.data+"  ");
        inOrderTraverse(node.right);
    }
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.data + "  ");
    }
}
