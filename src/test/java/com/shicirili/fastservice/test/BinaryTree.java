package com.shicirili.fastservice.test;

import java.util.Stack;

// https://www.cnblogs.com/yaobolove/p/6213936.html
public class BinaryTree {

    public Node init() {//注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;   //返回根节点
    }

    public void printNode(Node node){
        System.out.print(node.getData());
    }



    public void firsttravel(Node root){

        Stack<Node> stack = new Stack<>();
        Node node = root;
        while(node!=null && stack.size()>0){
            if (node!=null){
                printNode(node);
                stack.push(node);
                node = node.getLeftNode();
            }else {
                node = stack.pop().getRightNode();
            }

        }


    }

    public void theFirstTraversal_Stack(Node root) {  //先序遍历
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while (node != null || stack.size() > 0) {  //将所有左孩子压栈
            if (node != null) {   //压栈之前先访问
                printNode(node);
                stack.push(node);
                node = node.getLeftNode();
            } else {
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }


    public void theFirstTraversal(Node root) {  //先序遍历
        printNode(root);
        if (root.getLeftNode() != null) {  //使用递归进行遍历左孩子
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null) {  //递归遍历右孩子
            theFirstTraversal(root.getRightNode());
        }
    }

    public static void preScan(Node b) {
        int length = 0;
        Node[] stack = new Node[20];
        stack[length ++] = b;
        Node temp;

        while(length > 0) {
            temp = stack[-- length];
            System.out.print(temp.getData() + " ");

            if(temp.getRightNode() != null) {
                stack[length ++] = temp.getRightNode();
            }
            if(temp.getLeftNode() != null) {
                stack[length ++] = temp.getLeftNode();
            }
        }
    }




    public static void scan(Node b) {
        if(b != null) {
            System.out.print(b.getData() + " ");
        }
        if(b.getLeftNode() != null) scan(b.getLeftNode());
        if(b.getRightNode() != null) scan(b.getRightNode());
    }



    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = tree.init();
        System.out.println("先序遍历");
        tree.theFirstTraversal(root);
        tree.preScan(root);
    }
}
