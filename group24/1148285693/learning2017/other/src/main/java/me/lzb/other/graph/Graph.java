package me.lzb.other.graph;

import java.util.*;

/**
 * 遍历无向图的所有最长一笔画
 * 深度优先，达到最深时，后退，继续搜索另一条路径
 * Created by LZB on 2017/4/8.
 */
public class Graph {
    /**
     * 换行符
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * 路径分割符号
     */
    private static final String PATH_SEPARATOR = "->";

    /**
     * 顶点数目
     */
    private int vertexCount;

    /**
     * 边的数目
     */
    private int edgeCount;

    /**
     * 出现过路径的最长变数
     * 如果等于总边数，说明存在欧拉路径
     */
    int maxEdge = 0;

    /**
     * 顶点数组，每个list是与顶点关联的所有边
     */
    private LinkedList<Edge>[] edgeList;

    /**
     * 边
     */
    private class Edge {
        /**
         * 边的id
         */
        int id;

        /**
         * 是否被正向搜索
         */
        boolean isSearched;

        /**
         * 顶点v
         */
        int v;

        /**
         * 顶点b
         */
        int w;

        /**
         * 保存回滚操作中，被回滚的的路径方向，以及，前提路径
         * 因为在不同级别的回滚中，可能会有多条临时路径，所以用list存放
         * 顶点->顶点:路径id->路径id->路径id
         * 1->2:0->1->2
         */
        ArrayList<String> to = new ArrayList<>();

        /**
         * 构造函数
         * @param v 顶点v
         * @param w 顶点w
         */
        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isSearched = false;
            id = edgeCount;
        }


        /**
         * 在当前前提路径下，是否有
         * @param v0 出发顶点
         * @param P  前提路径
         * @return true false
         */
        public boolean isFrom(int v0, String P) {
            return isTheSameTo(v0, getAnotherV(v0), P);
        }

        /**
         * 临时路径是否相同
         * @param v0 出发顶点
         * @param v1 到达顶点
         * @param p  前提路径
         * @return true false
         */
        public boolean isTheSameTo(int v0, int v1, String p) {
            if (to.size() == 0) {
                return false;
            }
            String ss = v0 + PATH_SEPARATOR + v1 + ":" + p;
            for (String s : to) {
                if (ss.equals(s)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 删除临时路径
         * @param v0 出发顶点
         * @param v1 到达顶点
         * @param p  前提路径
         */
        public void removeTo(int v0, int v1, String p) {
            if (to.size() == 0) {
                return;
            }
            String ss = v0 + PATH_SEPARATOR + v1 + ":" + p;
            for (Iterator<String> iterator = to.iterator(); iterator.hasNext(); ) {
                String s = iterator.next();
                if (ss.equals(s)) {
                    iterator.remove();
                    return;
                }
            }
        }

        /**
         * 增加临时路径
         * @param v0 出发顶点
         * @param v1 到达顶点
         * @param p  前提路径
         */
        public void addTo(int v0, int v1, String p) {
            String ss = v0 + PATH_SEPARATOR + v1 + ":" + p;
            for (String s : to) {
                if (ss.equals(s)) {
                    return;
                }
            }
            to.add(ss);

        }

        /**
         * 获取边的另外一条顶点
         * @param vertex
         * @return
         */
        public int getAnotherV(int vertex) {
            if (vertex == v) {
                return w;
            } else {
                return v;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge c = (Edge) obj;
            return this.id == c.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    /**
     * 构造函数
     * @param vertexNum 顶点总数
     * @param edgeCount 边的总数
     */
    public Graph(int vertexNum, int edgeCount) {
        this.vertexCount = vertexNum;
        this.edgeCount = 0;
        edgeList = new LinkedList[edgeCount];
        for (int i = 0; i < edgeCount; i++) {
            edgeList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v1, int v2) {
        Edge c = new Edge(v2, v1);
        edgeList[v1].add(c);
        edgeList[v2].add(c);
        edgeCount++;
    }


    public void addEdge(int[][] edgeArray) {
        for (int i = 0; i < edgeArray.length; i++) {
            addEdge(edgeArray[i][0], edgeArray[i][1]);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertexCount + " vertices, " + edgeCount + " edges " + NEWLINE);
        for (int v = 0; v < vertexCount; v++) {
            s.append(v + ": ");
            for (Edge w : edgeList[v]) {
                s.append(w.getAnotherV(v) + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    /**
     * 更新出现过路径的最长边数
     * @param a
     */
    private void updateMax(int a) {
        if (a > maxEdge) {
            maxEdge = a;
        }
    }


    public boolean isEuler() {
        int start = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        //TODO 退出递归的条件
//        try {
            search(start, start, stack, new Stack<>());
//        }catch (EmptyStackException e){

//        }

        System.out.println("最长边数：" + maxEdge);
        return maxEdge == edgeCount;
    }




    /**
     * 正向搜索
     * 传进去一个节点，顺着一条没有搜索过的边找到下一个节点。当搜索到死路时，回滚
     * @param v 当前提点
     * @param stack 当前路径的节点顺序
     * @param sp 当前路径的路径顺序
     */
    public void search(int start, int v, Stack<Integer> stack, Stack<Edge> sp) {

        LinkedList<Edge> list = edgeList[v];

        boolean anotherWay = false;
        for (Edge w : list) {
            if (!w.isSearched && !w.isTheSameTo(v, w.getAnotherV(v), getPath(sp))) {
                anotherWay = true;
                w.isSearched = true;
                stack.push(w.getAnotherV(v));
                updateMax(sp.size());
                sp.push(w);
                search(start, w.getAnotherV(v), stack, sp);
            }
        }

        if (!anotherWay) {
            System.out.println("最长：===============================");
            rollback(start, stack, sp);
        }

    }



    /**
     * 回滚，回滚当上一个节点，如果当前节点有可以使用的边，调用搜索，如果没有，递归继续回滚
     * 如果需要递归回滚，回滚到第二级之前，清空所有,当前路径下，从该点出发的方向
     * 被回滚的路径，需要保存路径方向，以及提前路径
     * @param stack 当前路径的节点顺序
     * @param sp 当前路径的路径顺序
     */
    public void rollback(int start, Stack<Integer> stack, Stack<Edge> sp) {

        String ss = getPath(sp);
        String output = "顶点：" + stack.toString()
            + NEWLINE + "路径：" + ss
            + NEWLINE;
        System.out.println(output);

//        if(stack.size() == 1){
//            return;
//        }


        Edge e = sp.pop();  //需要回滚的路径
        String pp = getPath(sp);    //前提路径

        int vz = stack.pop();
        int vy = stack.peek();

        boolean rollbakc2 = true;

        LinkedList<Edge> l = edgeList[vy];

        //判断当前节点是否存在空闲路径，是否要回滚两级
        //空闲路径：没有被正向搜索，也没有被缓存当前前提路径下，从改节点出发的方向
        for (Edge w : l) {
            if (!w.isSearched && !w.isTheSameTo(vy, w.getAnotherV(vy), pp)) {
                rollbakc2 = false;
                break;
            }
        }


        //回滚当前路径，回滚一级
        int r = vy;
        for (Edge w : l) {
            if (w.equals(e)) {
                w.addTo(vy, vz, pp);
                w.isSearched = false;
                break;
            }
        }

        if (rollbakc2) {
            //回滚两级, 清空所有,当前路径下，从该点出发的方向

            for (Edge w : l) {
                if (!w.isSearched && w.isFrom(vy, pp)) {
                    w.removeTo(vy, w.getAnotherV(vy), pp);
                }
            }
            rollback(start, stack, sp);
        }

        search(start, r, stack, sp);

    }

    public String getPath(Stack<Edge> stack) {
        String s = "";
        for (Edge x : stack) {
            s = s + x.id + PATH_SEPARATOR;
        }
        s = s.replaceAll(PATH_SEPARATOR + "$", "");
        return s;
    }


    public static void main(String[] args) {
        int[][] aa = new int[][]{{0, 1}, {0, 1}, {0, 3}, {1, 3}, {1, 2}, {1, 2}, {2, 3}};
        Graph g = new Graph(4, aa.length);
        g.addEdge(aa);
        System.out.println(g.toString());
        System.out.println(g.isEuler());
    }
}
