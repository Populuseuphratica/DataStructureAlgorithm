package com.killstan.tree;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/11 11:21
 * @Description:
 */
public class BinarySortTree {

    private BSTNode rootNode;
    private BSTNode parentNode;

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2, 4, 6};
        int[] arr1 = {8,16,13,20,18,17,19};
        BinarySortTree binarySortTree = new BinarySortTree();
        BSTNode bstNode = binarySortTree.arrayToBST(arr1);
        binarySortTree.LDR();
        binarySortTree.deleteByNum(19);
        System.out.println("---------------------");
        binarySortTree.LDR();
    }

    /**
     * 将数组转化为二叉排序树
     *
     * @param arr
     * @return
     */
    public BSTNode arrayToBST(int[] arr) {
        for (int i : arr) {
            add(new BSTNode(i));
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

    private BSTNode deleteDetail(BSTNode curNode, int num) {
        BSTNode temp = null;
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
            // 这里采用替换节点方法，也可以删除调用deleteDetailBak方法直接删除后继节点，然后后继节点的值覆盖删除节点的值
            else if (curNode.left != null && curNode.right != null) {
                BSTNode childNode = curNode.right;
                // 用来记录最小节点的父节点
                BSTNode parentTemp = curNode;
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
                BSTNode childNode = null;
                if (curNode.left != null){
                    childNode = curNode.left;
                }else{
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

    private BSTNode deleteDetailBak(BSTNode curNode, int num) {
        BSTNode temp = null;
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

            // 存在右子节点时，将右子节点树的最小节点（最后一个左子节点提到当前节点的位置）移动到删除节点位置，并将最小节点的父节点左指针置空
            if (curNode.right != null) {
                BSTNode childNode = curNode.right;
                // 用来记录最小节点的父节点
                BSTNode parentTemp = curNode;
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

                // 替换节点的父节点不是删除节点时
                if (parentTemp != curNode) {
                    // 将替换节点的父节点的左指针指向替换节点的右指针对象
                    parentTemp.left = childNode.right;
                    // 将替换节点的右指针指向删除节点的右指针对象
                    childNode.right = curNode.right;
                }

                childNode.left = curNode.left;

            }
            // 只存在左子节点时，将左子节点树的最大节点（最后一个右子节点提到当前节点的位置）移动到删除节点位置，并将最小节点的父节点右指针置空
            else if (curNode.left != null) {
                BSTNode childNode = curNode.left;
                // 用来记录最小节点的父节点
                BSTNode parentTemp = curNode;
                // 找到最小节点和其父节点
                while (childNode.right != null) {
                    parentTemp = childNode;
                    childNode = childNode.right;
                }
                if (parentNode != null) {
                    if (parentNode.left == curNode) {
                        parentNode.left = childNode;
                    }
                    if (parentNode.right == curNode) {
                        parentNode.right = childNode;
                    }
                } else {
                    rootNode = childNode;
                }

                if (parentTemp != curNode) {
                    parentTemp.right = childNode.left;
                    childNode.left = curNode.left;
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
    public void add(BSTNode addNode) {
        if (rootNode == null) {
            rootNode = addNode;
        } else {
            addDetail(rootNode, addNode);
        }
    }

    public void addDetail(BSTNode curNode, BSTNode addNode) {
        // 小于当前节点，往左子树走
        if (curNode.num > addNode.num) {
            if (curNode.left == null) {
                curNode.left = addNode;
            } else {
                addDetail(curNode.left, addNode);
            }
        } else {
            // 大于等于当前节点，往右子树走
            if (curNode.right == null) {
                curNode.right = addNode;
            } else {
                addDetail(curNode.right, addNode);
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

    public void LDRDetai(BSTNode curNode) {
        if (curNode.left != null) {
            LDRDetai(curNode.left);
        }
        System.out.println(curNode);
        if (curNode.right != null) {
            LDRDetai(curNode.right);
        }
    }
}

class BSTNode {
    int num;
    BSTNode left;
    BSTNode right;

    public BSTNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "num=" + num +
                '}';
    }
}
