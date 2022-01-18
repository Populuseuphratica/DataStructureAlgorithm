package com.killstan.tree;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/11 11:21
 * @Description:
 */
public class BalancedBinaryTree {

    private BBTNode rootNode;
    private BBTNode parentNode;

    public static void main(String[] args) {
//        int[] arr = {17, 3, 10, 12, 5, 1, 9, 2, 4, 6};
//        int[] arr = {7, 8, 9};
        int[] arr = {8, 16, 13, 20, 18, 17, 19};
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        BBTNode bstNode = balancedBinaryTree.arrayToBBT(arr);
        balancedBinaryTree.LDR();
        System.out.println(balancedBinaryTree.rootNode);

    }

    /**
     * 获取节点高度
     *
     * @param node
     * @return
     */
    public int getHeight(BBTNode node) {
        if (node == null) {
            return 0;
        }
        int heightLeft = 0;
        int heightRight = 0;
        if (node.left == null && node.right == null) {
            return 1;
        }

        if (node.left != null) {
            heightLeft = getHeight(node.left) + 1;
        }
        if (node.right != null) {
            heightRight = getHeight(node.right) + 1;
        }

        return Math.max(heightLeft, heightRight);
    }

    /**
     * 将数组转化为二叉排序树
     *
     * @param arr
     * @return
     */
    public BBTNode arrayToBBT(int[] arr) {
        for (int i : arr) {
            add(new BBTNode(i));
        }

        return rootNode;
    }

    public void deleteByNum(int num) {
        if (rootNode == null) {
            System.out.println("空树不能删除");
        }
        parentNode = null;
        deleteDetail(rootNode, num);
    }
    //TODO 删除节点之后旋转
    private BBTNode deleteDetail(BBTNode curNode, int num) {
        BBTNode temp = null;
        // 找到节点时
        if (curNode.num == num) {

            // 如果为叶子节点，将父节点的指针置为空
            if (curNode.left == null && curNode.right == null) {
                if (parentNode.left == curNode) {
                    parentNode.left = null;
                }
                if (parentNode.right == curNode) {
                    parentNode.right = null;
                }
            }
            // 左右子树都存在的情况，用删除节点的直接前驱或者直接后继来替换当前节点（这里用直接后继）
            else if (curNode.left != null && curNode.right != null) {
                BBTNode childNode = curNode.right;
                // 用来记录最小节点的父节点
                BBTNode parentTemp = curNode;
                // 找到最小节点和其父节点
                while (childNode.left != null) {
                    parentTemp = childNode;
                    childNode = childNode.left;
                }
                // 删除的不是根节点的情况
                if (parentNode != null) {
                    if (parentNode.left == curNode) {
                        parentNode.left = childNode;
                    }
                    if (parentNode.right == curNode) {
                        parentNode.right = childNode;
                    }
                } else {
                    // 删除的是根节点的情况
                    rootNode = childNode;
                }

                // 替换节点的左指针指向删除节点的左指针
                childNode.left = curNode.left;

                // 替换节点的父节点不是删除节点时
                if (parentTemp != curNode) {
                    // 将替换节点的父节点的左指针指向替换节点的右指针对象
                    parentTemp.left = childNode.right;
                    // 将替换节点的右指针指向删除节点的右指针对象
                    childNode.right = curNode.right;
                }

            }
            // 仅有左或右子树的节点；（上移子树即可）
            else {
                BBTNode childNode = null;
                if (curNode.left != null) {
                    childNode = curNode.left;
                } else {
                    childNode = curNode.right;
                }
                // 删除的不是根节点的情况
                if (parentNode != null) {
                    if (parentNode.left == curNode) {
                        parentNode.left = childNode;
                    }
                    if (parentNode.right == curNode) {
                        parentNode.right = childNode;
                    }
                } else {
                    // 删除的是根节点的情况
                    rootNode = childNode;
                }
            }

            return curNode;
        }

        // 大于寻找节点，往左子树寻找
        if (curNode.num > num) {
            if (curNode.left != null) {
                parentNode = curNode;
                return deleteDetail(curNode.left, num);
            } else {
                return null;
            }
        }
        // 小于等于寻找节点，往右子树寻找
        else {
            if (curNode.right != null) {
                parentNode = curNode;
                return deleteDetail(curNode.right, num);
            } else {
                return null;
            }
        }
    }

    /**
     * 加入节点到二叉排序树
     *
     * @param addNode
     */
    public void add(BBTNode addNode) {
        if (rootNode == null) {
            rootNode = addNode;
        } else {
            addDetail(null,rootNode, addNode);
        }

    }


    /**
     * 右旋
     *
     * @param node
     * @return 旋转后该子树的根节点
     */
    private BBTNode rightRotate(BBTNode node) {
        if (node.left == null) {
            throw new RuntimeException("该节点没有左子节点，不能进行右旋。");
        }

        BBTNode temp = node.left;
        node.left = temp.right;
        temp.right = node;

        if (rootNode == node) {
            rootNode = temp;
        }
        return temp;
    }

    /**
     * 左旋
     *
     * @param node
     * @return 旋转后该子树的根节点
     */
    private BBTNode leftRotate(BBTNode node) {
        if (node.right == null) {
            throw new RuntimeException("该节点没有右子节点，不能进行左旋。");
        }

        BBTNode temp = node.right;
        node.right = temp.left;
        temp.left = node;

        if (node == rootNode) {
            rootNode = temp;
        }

        return temp;
    }

    public void addDetail(BBTNode curParentNode,BBTNode curNode, BBTNode addNode) {
        // 小于当前节点，往左子树走
        if (curNode.num > addNode.num) {
            if (curNode.left == null) {
                curNode.left = addNode;
            } else {
                addDetail(curNode,curNode.left, addNode);
            }
        } else {
            // 大于等于当前节点，往右子树走
            if (curNode.right == null) {
                curNode.right = addNode;
            } else {
                addDetail(curNode,curNode.right, addNode);
            }
        }

        int leftHeight = getHeight(curNode.left);
        int rightHeight = getHeight(curNode.right);

        BBTNode childNode = null;
        if (rightHeight - 1 > leftHeight) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            BBTNode curRightNode = curNode.right;
            if (curRightNode != null && getHeight(curRightNode.left) > getHeight(curRightNode.right)) {
                // 先对右子结点进行右旋转
                curNode.right = rightRotate(curRightNode);
            }
            // 然后在对当前结点进行左旋转
            childNode = leftRotate(curNode);
            // 将返回的子树根节点绑回二叉树中
            if (curParentNode != null) {
                if (curParentNode.left == curNode) {
                    curParentNode.left = childNode;
                } else if (curParentNode.right == curNode) {
                    curParentNode.right = childNode;
                }
            }
        } else if (leftHeight - 1 > rightHeight) {
            BBTNode curLeftNode = curNode.left;
            if (curLeftNode != null && getHeight(curLeftNode.right) > getHeight(curLeftNode.left)) {
                curNode.left = leftRotate(curLeftNode);
            }
            childNode = rightRotate(curNode);
            if (curParentNode != null) {
                if (curParentNode.left == curNode) {
                    curParentNode.left = childNode;
                } else if (curParentNode.right == curNode) {
                    curParentNode.right = childNode;
                }
            }
        }
    }

    /**
     * 中序遍历
     */
    public void LDR() {
        if (rootNode == null) {
            System.out.println("空树");
        } else {
            LDRDetai(rootNode);
        }
    }

    public void LDRDetai(BBTNode curNode) {
        if (curNode.left != null) {
            LDRDetai(curNode.left);
        }
        System.out.println(curNode);
        if (curNode.right != null) {
            LDRDetai(curNode.right);
        }
    }
}

class BBTNode {
    int num;
    BBTNode left;
    BBTNode right;

    public BBTNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BBTNode{" +
                "num=" + num +
                '}';
    }
}
