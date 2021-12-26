package com.killstan.hashTable;

import java.util.*;

/**
 * @Auther: Kill_Stan
 * @Date: 2021/12/23 11:48
 * @Description:
 */
public class Zipper {

    public static void main(String[] args) {

        //创建哈希表
        HashTableByZipper hashTab = new HashTableByZipper(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Employee emp = new Employee(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    Employee employee = hashTab.getEmpById(id);
                    System.out.println(employee);
                    break;
                case "remove":
                    System.out.println("请输入要删除 id");
                    id = scanner.nextInt();
                    boolean result = hashTab.remove(id);
                    System.out.println(result);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

/**
 * 哈希表
 */
class HashTableByZipper {

    // 链表数组
    private List[] elementArray;

    private int size;

    public HashTableByZipper() {
        this.size = 10;
        elementArray = new LinkedList[size];
        for (int i = 0; i < this.size; i++) {
            elementArray[i] = new LinkedList<Employee>();
        }
    }

    public HashTableByZipper(int size) {
        this.size = size;
        elementArray = new LinkedList[size];
        for (int i = 0; i < this.size; i++) {
            elementArray[i] = new LinkedList<Employee>();
        }
    }

    public int size() {
        return this.size;
    }

    public void add(Employee emp) {
        int index = hash(emp.id);
        elementArray[index].add(emp);
    }

    public void list() {
        for (int i = 0; i < this.size; i++) {
            List lists = elementArray[i];
            Iterator<Employee> iterator = lists.iterator();
            System.out.println("第" + i + "个元素的Emp");
            while (iterator.hasNext()) {
                Employee emp = iterator.next();
                System.out.println(emp);
            }
            System.out.println("-------------");
        }
    }

    /**
     * 根据id查找雇员
     * @param id
     * @return
     */
    public Employee getEmpById(int id) {
        int index = hash(id);
        List list = elementArray[index];
        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.id == id) {
                return emp;
            }
        }
        return null;
    }

    public boolean contains(Employee emp) {
        int index = hash(emp.id);
        List list = elementArray[index];
        if (list.contains(emp)) {
            return true;
        }
        return false;
    }

    public boolean remove(Employee emp) {
        int index = hash(emp.id);
        List list = elementArray[index];
        return list.remove(emp);
    }

    public boolean remove(int id) {
        int index = hash(id);
        List list = elementArray[index];
        return list.remove(new Employee(id));
    }

    /**
     * 散列函数，用size取莫
     *
     * @param code
     * @return
     */
    private int hash(int code) {
        return code % this.size;
    }

}

class Employee {
    public int id;
    public String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
