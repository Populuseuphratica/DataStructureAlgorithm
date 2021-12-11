package com.killstan.linked;

public class TestLinkedList {

    public static void main(String[] args) {

        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        MylinkedList mylinkedList = new MylinkedList();
        //加入
//        mylinkedList.add(hero1);
//        mylinkedList.add(hero4);
//        mylinkedList.add(hero2);
//        mylinkedList.add(hero3);

        //加入按照编号的顺序
        mylinkedList.addOrderById(hero1);
        mylinkedList.addOrderById(hero4);
        mylinkedList.addOrderById(hero2);
        mylinkedList.addOrderById(hero3);

        //显示一把
        mylinkedList.show();
        HeroNode lastIndexNode = findLastIndexNode(mylinkedList.getHeadCode(), 5);
        System.out.println(lastIndexNode);
////测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(3, "小卢", "玉麒麟~~");
//        mylinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况~~");
//        mylinkedList.show();
////删除一个节点
//        mylinkedList.remove(1);
//        mylinkedList.remove(3);
//
//        System.out.println("删除后的链表情况~~");
//        mylinkedList.show();

    }

    /**
     * 查找单链表中的倒数第 k 个结点
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){

        if(index <= 0 ){
            System.out.println("index需要为正整数");
            return null;
        }

        //排除头节点
        HeroNode temp = head.next;

        int count = 0;
        while(temp !=null){
            count++;
            temp = temp.next;
        }

        if(count < index){
            System.out.println("没有index个节点");
            return null;
        }

        temp = head.next;
        for(int i = 1; i< count - index + 1; i++){
            temp = temp.next;
        }

        return temp;
    }
}


/**
 * 链表，默认节点的No不重复
 */
class MylinkedList {

    /**
     * 头节点
     */
    private HeroNode headNode = new HeroNode(0, "", "");

    public HeroNode getHeadCode(){
        return headNode;
    }

    /**
     * 往链表尾添加节点
     *
     * @param heroNode
     * @return
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = headNode;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 按照节点的id顺序添加节点
     * @param heroNode
     * @return
     */
    public boolean addOrderById(HeroNode heroNode) {
        HeroNode temp = headNode;
        int addNum = heroNode.no;

        boolean added = false;

        while (temp.next != null) {
            int tempNextNo = temp.next.no;
            if (addNum < tempNextNo) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                added = true;
                break;
            } else if (addNum == tempNextNo) {
                System.out.println("该id的节点已经存在，无法添加");
                return false;
            }
            temp = temp.next;
        }
        if(!added){
            temp.next = heroNode;
        }

        return false;
    }

    /**
     * 根据节点No更新数据
     *
     * @param heroNode
     * @return 更新前的数据，null:没有更新
     */
    public HeroNode update(HeroNode heroNode) {

        HeroNode temp = headNode;
        HeroNode returnNode = null;

        while (temp.next != null) {
            int no = temp.next.no;
            if (no == heroNode.no) {
                returnNode = temp.next;
                heroNode.next = returnNode.next;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
        return returnNode;
    }

    /**
     * 根据no删除对应节点
     *
     * @param no
     * @return 删除前的数据，null:没有更新
     */
    public HeroNode remove(int no) {
        HeroNode temp = headNode;
        HeroNode returnNode = null;

        while (temp.next != null) {
            if (no == temp.next.no) {
                returnNode = temp.next;
                temp.next = returnNode.next;
                break;
            }
            temp = temp.next;
        }
        return returnNode;
    }

    public void show() {
        HeroNode temp = headNode;
        while (temp.next != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }

}

/*
链表的节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}