package com.killstan.graph;

import org.w3c.dom.Node;

import java.util.*;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/18 16:23
 * @Description:
 */
public class Graph {

    public static void main(String[] args) {


        Graph graph = new Graph(true);
        Vertex zero = new Vertex(0, "0");
        Vertex one = new Vertex(1, "1");
        Vertex two = new Vertex(2, "2");
        Vertex three = new Vertex(3, "3");
        Vertex four = new Vertex(4, "4");
        Vertex five = new Vertex(5, "5");
        Vertex six = new Vertex(6, "6");
        Vertex seven = new Vertex(7, "7");
        Vertex eight = new Vertex(8, "8");

        graph.addEdge(one, zero);
        graph.addEdge(three, one);
        graph.addEdge(two, seven);
        graph.addEdge(two, four);
        graph.addEdge(five, two);
        graph.addEdge(five, zero);
        graph.addEdge(six, five);
        graph.addEdge(six, three);
        graph.addEdge(six, eight);
        graph.addEdge(seven, five);
        graph.addEdge(seven, six);
        graph.addEdge(seven, eight);


        graph.printEdges();

        graph.breadthFirstSearch(seven);

        System.out.println("----------------------------------");
        Graph graphBFS = new Graph(false);
        Vertex a = new Vertex(0, "0");
        Vertex b = new Vertex(1, "1");
        Vertex c = new Vertex(2, "2");
        Vertex d = new Vertex(3, "3");
        Vertex e = new Vertex(4, "4");

       graphBFS.addEdge(a,d);
       graphBFS.addEdge(a,b);
       graphBFS.addEdge(a,c);
       graphBFS.addEdge(c,d);

       graphBFS.breadthFirstSearch(b);


    }

    private int vertexNum;
    private int edgeNum;
    private HashMap<Vertex, LinkedList<Vertex>> edgeMap = new HashMap<>();
    ;
    private boolean directed;

    public Graph(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public Graph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;
    }

    public Graph() {
    }

    public boolean addVertex(Vertex vertex) {
        if (edgeMap.keySet().contains(vertex)) {
            System.out.println("该定点已经存在");
            return false;
        } else {
            edgeMap.put(vertex, null);
            vertexNum++;
            return true;
        }
    }

    /**
     * 添加边
     *
     * @param source      源点
     * @param destination 终点
     */
    public void addEdge(Vertex source, Vertex destination) {
        if (!edgeMap.keySet().contains(source)) {
            vertexNum++;
            edgeMap.put(source, null);
        }
        if (!edgeMap.keySet().contains(destination)) {
            vertexNum++;
            edgeMap.put(destination, null);
        }
        addDetail(source, destination);
        edgeNum++;

        // 如果是无向图，在终点的链表中添加源点
        if (!directed) {
            addDetail(destination, source);
        }
    }

    private void addDetail(Vertex source, Vertex destination) {
        LinkedList edge = edgeMap.get(source);
        if (edge != null) {
            if (edge.contains(destination)) {
                edge.remove(destination);
            }
            edge.add(destination);
        } else {
            edge = new LinkedList<Vertex>();
            edge.add(destination);
            edgeMap.put(source, edge);
        }
    }

    public void printVertex() {
        Iterator<Vertex> iterator = edgeMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void printEdges() {
        Iterator<Map.Entry<Vertex, LinkedList<Vertex>>> it = edgeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex, LinkedList<Vertex>> entry = it.next();
            System.out.println("key:" + entry.getKey() + " "
                    + "Value:" + entry.getValue());
        }

        for (Vertex node : edgeMap.keySet()) {
            System.out.print("The " + node.name + " has an edge towards: ");
            if (edgeMap.get(node) != null) {
                for (Vertex neighbor : edgeMap.get(node)) {
                    System.out.print(neighbor.name + " ");
                }
                System.out.println();
            } else {
                System.out.println("none");
            }
        }
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        return edgeMap.keySet().contains(source) && edgeMap.get(source) != null && edgeMap.get(source).contains(destination);
    }

    /**
     * 深度优先搜索（DFS）
     *
     * @param vertex 开始顶点
     */
    public void DFS(Vertex vertex) {
        if (vertex == null) {
            return;
        }
        depthFirstSearch(vertex);
        // 如果有不与开始顶点的图相连的顶点，重复
        for (Vertex v : edgeMap.keySet()) {
            if (v.visited == false) {
                depthFirstSearch(v);
            }
        }
    }

    public void depthFirstSearch(Vertex vertex) {

        System.out.println(vertex);
        vertex.visited = true;

        List<Vertex> vertices = edgeMap.get(vertex);
        if (vertices == null) {
            return;
        }
        for (Vertex neighbor : vertices) {
            if (neighbor.visited == false) {
                depthFirstSearch(neighbor);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param vertex
     */
    public void BFS(Vertex vertex){
        if (vertex == null) {
            return;
        }
        breadthFirstSearch(vertex);
        // 如果有不与开始顶点的图相连的顶点，重复
        for (Vertex v : edgeMap.keySet()) {
            if (v.visited == false) {
                breadthFirstSearch(v);
            }
        }
    }

    public void breadthFirstSearch(Vertex vertex) {

        ArrayDeque<Vertex> queue = new ArrayDeque();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            Vertex v = queue.pop();
            if(v.visited){
                continue;
            }
            v.visited = true;
            System.out.println(v);
            List<Vertex> vertices = edgeMap.get(v);
            if (vertices != null) {
                for (Vertex neighbor : vertices) {
                    // 这里判断的话，如果有多个同层级顶点的下层顶点有重复，会重复加入，所以这里只是除掉一部分进出队列，增加效率。
                    if (!neighbor.visited) {
                        queue.add(neighbor);
                    }
                }
            }
        }

    }

    public void resetVertexsVisited() {
        for (Vertex vertex : edgeMap.keySet()) {
            vertex.visited = false;
        }
    }
}

class Vertex {

    int num;
    String name;
    boolean visited;

    public Vertex(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public Vertex(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return num == vertex.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
