package com.killstan.linked;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/22 16:38
 * @Description:
 */
public class Josepfu {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否 ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(12);// 加入 5 个小孩节点
        circleSingleLinkedList.showBoy();
//测试一把小孩出圈是否正确
//        circleSingleLinkedList.countBoy(10, 20, 125); // 2->4->1->5->3
        circleSingleLinkedList.countBoy(4, 4);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个 first 节点,当前没有编号
    private Boy first = null;

    // 创建一个 first 节点,当前没有编号
    private int boySums;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        boySums = nums;

        first = new Boy(1);
        first.next = first;
        // 辅助指针，帮助构建环形链表
        Boy curBoy = first;
        // 使用 for 来创建我们的环形链表
        for (int i = 2; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            curBoy.next = boy;
            boy.next = first;
            curBoy = boy;

        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为 first 不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (curBoy != null) {
            System.out.println(curBoy.no);
            curBoy = curBoy.next;
            if (curBoy == first) {
                break;
            }
        }

    }


    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     */
    public void countBoy(int startNo, int countNum) {
        if (startNo < 1 || startNo > boySums || first == null) {
            System.out.println("输入错误");
            return;
        }

        Boy preBoy = first;
        Boy curBoy = first;
        while (preBoy.next != first) {
            preBoy = preBoy.next;
        }

        while (preBoy != curBoy) {
            for (int i = 0; i < startNo - 1; i++) {
                preBoy = preBoy.next;
                curBoy = curBoy.next;
            }

            for (int i = 0; i < countNum - 1; i++) {
                preBoy = preBoy.next;
                curBoy = curBoy.next;
            }

            System.out.println("出列的小孩为" + curBoy.no);
            preBoy.next = curBoy.next;
            curBoy = curBoy.next;
        }

        System.out.println("最后出列的男孩为" + curBoy.no);
    }
}

// 创建一个 Boy 类，表示一个节点
class Boy {
    public int no;// 编号
    public Boy next; // 指向下一个节点,默认 null

    public Boy(int no) {
        this.no = no;
    }

}

