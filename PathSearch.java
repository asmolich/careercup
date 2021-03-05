import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class PathSearch {

    private final int[][] grid;
    private final int i, j; // start point

    PathSearch(int[][] grid, int i, int j) {
        this.grid = grid;
        this.i = i;
        this.j = j;
    }

    private static class Node {
        Node parent = null;
        final int x, y;

        int f, g, h;

        Node(int i, int j) {
            x = i;
            y = j;
        }

        Node withG(int g) {
            this.g = g;
            return this;
        }

        Node withH(int h) {
            this.h = h;
            return this;
        }

        Node withParent(Node p) {
            this.parent = p;
            return this;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    List<Node> neighbours(Node node) {
        List<Node> neighbours = new ArrayList<>(4);
        if (inBounds(node.x - 1, node.y) && isCorridor(node.x - 1, node.y))
            neighbours.add(new Node(node.x - 1, node.y).withG(node.g + 1).withParent(node));
        if (inBounds(node.x, node.y - 1) && isCorridor(node.x, node.y - 1))
            neighbours.add(new Node(node.x, node.y - 1).withG(node.g + 1).withParent(node));
        if (inBounds(node.x + 1, node.y) && isCorridor(node.x + 1, node.y))
            neighbours.add(new Node(node.x + 1, node.y).withG(node.g + 1).withParent(node));
        if (inBounds(node.x, node.y + 1) && isCorridor(node.x, node.y + 1))
            neighbours.add(new Node(node.x, node.y + 1).withG(node.g + 1).withParent(node));
        return neighbours;
    }

    boolean isCorridor(int i, int j) {
        return grid[i][j] != 1 && grid[i][j] < 10;
    }

    /**
     * A* search
     * --- not done
     */
    public List<Node> shortestPathAStar(int endI, int endJ) {
        //if (i == endI && j == endJ) {
        //    return Collections.emptyList();
        //}

        //Node start = new Node(i, j);
        throw new UnsupportedOperationException("just do it");
    }

    /**
     * BFS
     */
    public List<Node> shortestPathBFS(int endI, int endJ) {
        if (i == endI && j == endJ) {
            return Collections.emptyList();
        }

        Node start = new Node(i, j);
        LinkedList<Node> path = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        Node finish = null;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == endI && node.y == endJ) {
                finish = node;
                break;
            }
            queue.addAll(neighbours(node));
        }

        if (finish == null) return Collections.emptyList();

        while (finish != null) {
            path.addFirst(finish);
            finish = finish.parent;
        }
        return path;
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    public void print() {
        System.out.println("[");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(this.i == i && this.j == j ? "@" : grid[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[][][] inputs = {
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                },
                {
                        {0, 1, 0},
                        {1, 0, 0},
                        {0, 0, 0}
                },
                {
                        {0, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 1, 1, 1},
                        {0, 0, 0, 0}
                },
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                },
                {
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }
        };

        for (int i = 0; i < inputs.length; i++) {
            PathSearch pathSearch = new PathSearch(inputs[i], 0, 0);
            pathSearch.print();
            List<Node> path = pathSearch.shortestPathBFS(inputs[i].length - 1, inputs[i][0].length - 1);
            System.out.println("BFS : " + path);
            System.out.println("BFS Length: " + path.size());
            //path = pathSearch.shortestPathAStar(inputs[i].length - 1, inputs[i][0].length - 1);
            //System.out.println("A* : " + path);
            //System.out.println("A* Length: " + path.size());
            System.out.println("==================");
        }
    }
}

