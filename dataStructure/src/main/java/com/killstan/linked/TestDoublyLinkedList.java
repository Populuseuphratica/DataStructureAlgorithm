package com.killstan.linked;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/11/21 15:18
 * @Description:
 */
public class TestDoublyLinkedList {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        MyDoublyLinkedList mylinkedList = new MyDoublyLinkedList();


        // 加入按照编号的顺序
        mylinkedList.addOrderById(hero1);
        mylinkedList.addOrderById(hero4);
        mylinkedList.addOrderById(hero2);
        mylinkedList.addOrderById(hero3);

        //显示一把
        mylinkedList.show();


//测试修改节点的代码
        HeroNode2 newHeroNode2 = new HeroNode2(4, "小卢", "玉麒麟~~");
        mylinkedList.update(newHeroNode2);
        System.out.println("修改后的链表情况~~");
        mylinkedList.show();
//删除一个节点
        mylinkedList.remove(1);
        mylinkedList.remove(4);

        System.out.println("删除后的链表情况~~");
        mylinkedList.show();

    }


}


/**
 * 链表，默认节点的No不重复
 */
class MyDoublyLinkedList {

    /**
     * 头节点
     */
    private HeroNode2 headNode = new HeroNode2(0, "", "");

    public HeroNode2 getHeadCode() {
        return headNode;
    }

    /**
     * 往链表尾添加节点
     *
     * @param heroNode2
     * @return
     */
    public void add(HeroNode2 heroNode2) {
        HeroNode2 temp = headNode;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode2;
        if (heroNode2 != null) {
            heroNode2.pre = temp;
        }
    }

    /**
     * 按照节点的id顺序添加节点
     *
     * @param heroNode2
     * @return
     */
    public boolean addOrderById(HeroNode2 heroNode2) {
        if (heroNode2 == null) {
            System.out.println("要添加节点不能为空");
            return false;
        }

        HeroNode2 temp = headNode;
        int addNum = heroNode2.no;

        boolean added = false;

        while (temp.next != null) {
            int tempNextNo = temp.next.no;
            if (addNum < tempNextNo) {
                heroNode2.next = temp.next;
                temp.next.pre = heroNode2;
                temp.next = heroNode2;
                heroNode2.pre = temp;
                added = true;
                break;
            } else if (addNum == tempNextNo) {
                System.out.println("该id的节点已经存在，无法添加");
                return false;
            }
            temp = temp.next;
        }

        if(!added){
            heroNode2.next = null;
            heroNode2.pre = temp;
            temp.next = heroNode2;
        }

        return true;
    }

    /**
     * 根据节点No更新数据
     *
     * @param heroNode2
     * @return 更新前的数据，null:没有更新
     */
    public HeroNode2 update(HeroNode2 heroNode2) {

        if (heroNode2 == null) {
            System.out.println("参数节点不能为空");
            return null;
        }

        HeroNode2 temp = headNode;
        HeroNode2 returnNode = null;

        while (temp.next != null) {
            int no = temp.next.no;
            if (no == heroNode2.no) {
                returnNode = temp.next;
                heroNode2.next = returnNode.next;
                heroNode2.pre = temp;
                temp.next = heroNode2;
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
    public HeroNode2 remove(int no) {
        HeroNode2 temp = headNode;
        HeroNode2 returnNode = null;

        while (temp.next != null) {
            if (no == temp.next.no) {
                returnNode = temp.next;
                temp.next = returnNode.next;
                if (returnNode.next != null) {
                    returnNode.next.pre = temp;
                }
                break;
            }
            temp = temp.next;
        }
        return returnNode;
    }

    public void show() {
        HeroNode2 temp = headNode;
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
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode2 next;
    //指向上一个节点
    public HeroNode2 pre;

    public HeroNode2() {
    }

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pre=" + pre +
                '}';
    }
}
