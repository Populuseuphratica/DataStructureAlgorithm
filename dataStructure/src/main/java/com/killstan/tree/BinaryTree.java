package com.killstan.tree;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/12/26 11:50
 * @Description:
 */
public class BinaryTree {

    private TreeNode rootNode;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        BinaryTree binaryTree = new BinaryTree();

        binaryTree.rootNode = root;
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;


        TreeNode treeNode1 = binaryTree.remove(binaryTree, new TreeNode(2));
        System.out.println(treeNode1);
        binaryTree.LRD(binaryTree.rootNode);
//        TreeNode treeNode = binaryTree.LRDSearch(binaryTree.rootNode, new TreeNode(3));

//        System.out.println(treeNode);

    }

    /**
     * 前序遍历
     */
    public void DLR(TreeNode curNode) {
        // 先打印当前节点
        System.out.println(curNode);
        // 如果有左子节点，先往左递归
        if (curNode.left != null) {
            DLR(curNode.left);
        }

        // 左递归完后，如果有右子节点，往右递归
        if (curNode.right != null) {
            DLR(curNode.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param curNode
     */
    public void LDR(TreeNode curNode) {
        // 如果有左子节点，先往左递归
        if (curNode.left != null) {
            LDR(curNode.left);
        }

        System.out.println(curNode);

        if (curNode.right != null) {
            LDR(curNode.right);
        }
    }

    /**
     * 后序遍历
     * 根在后，从左往右，一棵树的左子树永远在右子树前面，右子树永远在根前面
     *
     * @param curNode
     */
    public void LRD(TreeNode curNode) {

        if (curNode.left != null) {
            LRD(curNode.left);
        }

        if (curNode.right != null) {
            LRD(curNode.right);
        }

        System.out.println(curNode);
    }

    /**
     * 前序查找
     */
    public TreeNode DLRSearch(TreeNode curNode, TreeNode target) {
        TreeNode result = null;
        // 先打印当前节点
        System.out.println(curNode);
        if (target.num == curNode.num) {
            return curNode;
        }

        // 如果有左子节点，先往左递归
        if (curNode.left != null) {
            result = DLRSearch(curNode.left, target);
        }

        if (result != null) {
            return result;
        }

        // 左递归完后，如果有右子节点，往右递归
        if (curNode.right != null) {
            result = DLRSearch(curNode.right, target);
        }
        return result;
    }

    /**
     * 中序查找
     *
     * @param curNode
     */
    public TreeNode LDRSearch(TreeNode curNode, TreeNode target) {

        TreeNode result = null;
        // 如果有左子节点，先往左递归
        if (curNode.left != null) {
            result = LDRSearch(curNode.left, target);
        }

        if (result != null) {
            return result;
        }

        System.out.println(curNode);
        if (curNode.num == target.num) {
            return curNode;
        }

        if (curNode.right != null) {
            result = LDRSearch(curNode.right, target);
        }
        return result;
    }

    /**
     * 后序查找
     *
     * @param curNode
     */
    public TreeNode LRDSearch(TreeNode curNode, TreeNode target) {

        TreeNode result = null;

        if (curNode.left != null) {
            result = LRDSearch(curNode.left, target);
        }

        if (result != null) {
            return result;
        }

        if (curNode.right != null) {
            result = LRDSearch(curNode.right, target);
        }

        if (result != null) {
            return result;
        }

        System.out.println(curNode);
        if (curNode.num == target.num) {
            return curNode;
        }

        return result;
    }

    /**
     * 递归删除结点
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     * @param binaryTree
     * @param target
     * @return 返回删除的节点。null:没找到要删除的节点
     */
    public TreeNode remove(BinaryTree binaryTree, TreeNode target) {
        TreeNode curNode = binaryTree.rootNode;
        if (curNode == null) {
            System.out.println("空树不能进行删除操作");
            return null;
        }

        if (curNode.num == target.num) {
            binaryTree.rootNode = null;
            return curNode;
        }

        return removeRecursion(curNode, target);
    }

    private TreeNode removeRecursion(TreeNode curNode, TreeNode target) {
        TreeNode returnNode = null;
        if (curNode.left != null) {
            if (curNode.left.num == target.num) {
                returnNode = curNode.left;
                curNode.left = null;
                return returnNode;
            }
            returnNode = removeRecursion(curNode.left, target);
        }

        if (returnNode != null) {
            return returnNode;
        }

        if (curNode.right != null) {
            if (curNode.right.num == target.num) {
                returnNode = curNode.right;
                curNode.right = null;
                return returnNode;
            }
            returnNode = removeRecursion(curNode.right, target);
        }

        return returnNode;
    }


}

/**
 * 二叉树节点
 */
class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int num;

    @Override
    public String toString() {
        return "TreeNode{" +
                "num=" + num +
                '}';
    }

    public TreeNode() {
    }

    public TreeNode(int num) {
        this.num = num;
    }

    public TreeNode(TreeNode left, TreeNode right, int num) {
        this.left = left;
        this.right = right;
        this.num = num;
    }
}
