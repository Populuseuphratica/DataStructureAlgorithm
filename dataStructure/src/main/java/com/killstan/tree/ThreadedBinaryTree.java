package com.killstan.tree;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/5 15:05
 * @Description:
 */
public class ThreadedBinaryTree {

    private ThreadedNode rootNode;
    private ThreadedNode preNode;

    public static void main(String[] args) {
        ThreadedNode root = new ThreadedNode(1);
        ThreadedNode node2 = new ThreadedNode(2);
        ThreadedNode node3 = new ThreadedNode(3);
        ThreadedNode node4 = new ThreadedNode(4);
        ThreadedNode node5 = new ThreadedNode(5);

        ThreadedBinaryTree ThreadedBinaryTree = new ThreadedBinaryTree();

        ThreadedBinaryTree.rootNode = root;
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;
        ThreadedBinaryTree.LDRthread(ThreadedBinaryTree.rootNode);
//        System.out.println(node4.leftFlag);
//        System.out.println(node4.left);
//        System.out.println(node4.rightFlag);
//        System.out.println(node4.right);
        ThreadedBinaryTree.LDRthreadList(ThreadedBinaryTree.rootNode);
    }

    /**
     * 中序线索二叉树
     *
     * @param rootNode
     */
    public void LDRthread(ThreadedNode rootNode) {

        if (rootNode != null) {
            LDRthreadDetail(rootNode);
        }

    }

    public void LDRthreadDetail(ThreadedNode curNode) {
        if (curNode.left != null) {
            LDRthreadDetail(curNode.left);
        }

        // 左指针为空时，将其指向前续节点（最左节点开始时前续节点为空）
        if (curNode.left == null) {
            curNode.left = preNode;
            curNode.leftFlag = true;
        }

        // 右指针为空时将其指向后续节点
        if (preNode != null && preNode.right == null) {
            preNode.right = curNode;
            preNode.rightFlag = true;
        }

        // 节点轮换时将前续节点指向当前节点
        preNode = curNode;

        if (curNode.right != null) {
            LDRthreadDetail(curNode.right);
        }
    }

    /**
     * 中序遍历中序线索化二叉树
     *
     * @param rootNode
     */
    public void LDRthreadList(ThreadedNode rootNode) {
        if (rootNode == null) {
            System.out.println("空树");
        }

        ThreadedNode curNode = rootNode;
        // 找到最左节点
        while (curNode.left != null) {
            curNode = curNode.left;
        }

        while (curNode != null) {
            System.out.println(curNode);

            // 如果右指针指向后续节点
            if (curNode.rightFlag) {
                curNode = curNode.right;
            } else {
                // 找到当前节点的右子节点
                curNode = curNode.right;
                // 从这个节点开始找到子树的最左节点（开始节点）
                while (curNode != null && !curNode.leftFlag) {
                    curNode = curNode.left;
                }
            }
        }
    }
}

class ThreadedNode {
    ThreadedNode left;
    ThreadedNode right;
    boolean leftFlag = false;
    boolean rightFlag = false;
    int id;

    public ThreadedNode(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ThreadedNode{" +
                "id=" + id +
                '}';
    }
}