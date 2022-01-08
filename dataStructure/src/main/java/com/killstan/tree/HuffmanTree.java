package com.killstan.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/7 11:54
 * @Description:
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree();
        Node node = huffmanTree.createHuffTree(arr);
        huffmanTree.LDR(node);
    }

    /**
     * 中序遍历
     *
     * @param curNode
     */
    public void LDR(Node curNode) {
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
     * 构建赫夫曼树
     *
     * @param arr
     * @return
     */
    public Node createHuffTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        // 将数组转化为节点并放在List中
        List<Node> list = new ArrayList<Node>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Node(arr[i]));
        }

        // List中有2个以上节点时
        while (list.size() > 1) {
            // 按权值给节点排序
            list.sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.value - o2.value;
                }
            });

            // 取出权值最小的两个节点
            Node left = list.get(0);
            Node right = list.get(1);

            // 将其权值相加形成一个新节点，随后将其从List中除去，将新节点加入
            Node node = new Node(left.value + right.value);
            node.left = left;
            node.right = right;

            list.remove(0);
            list.remove(0);

            list.add(node);
        }

        return list.get(0);
    }
}

class Node {
    Node left;

    Node right;

    /**
     * 节点权值
     */
    int value;

    public Node() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}