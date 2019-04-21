import java.util.*;

class KeySearch {

    private final char[][] grid;
    private final int i, j; // start point
    private Map<Character, Key> keys = new HashMap<>();
    private Map<Character, Door> doors = new HashMap<>();

    KeySearch(char[][] grid) {
        this.grid = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@') {
                    this.i = i;
                    this.j = j;
                }
                else if (isKey(grid[i][j])) {
                    keys.put(grid[i][j], new Key(i, j, grid[i][j]));
                }
                else if (isDoor(grid[i][j])) {
                    doors.put(Character.toLowerCase(grid[i][j]), new Door(i, j, grid[i][j]));
                }
            }
        }
    }

    private boolean isKey(char c) {
        return c >= 'a' && c <= 'f';
    }

    private boolean isDoor(char c) {
        return c >= 'A' && c <= 'F';
    }

    private static class KeyDoorNode extends Node {
        final char value;
        KeyDoorNode(int i, int j) { x = i; y = j; prio = 0; }
        KeyDoorNode(int i, int j, int val) { x = i; y = j; value = val; }
    }
    private static class Key extends KeyDoorNode {
        char getKey() { return value; }
    }
    private static class Door extends KeyDoorNode {
        char getDoor() { return value; }
    }

    private static class Node {
        Node parent = null;
        final int x, y;

        int f, g, h;

        Node(int i, int j) { x = i; y = j; }

        Node withF(int f) { this.f = f; return this; }
        Node withG(int g) { this.g = g; return this; }
        Node withH(int h) { this.h = h; return this; }
        Node withParent(Node p) { this.parent = p; return this; }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    List<Node> neighbours(Node node) {
        List<Node> neighbours = new ArrayList<>(4);
        if (inBounds(node.x - 1, node.y) && isCoridor(node.x - 1, node.y)) neighbours.add(new Node(node.x - 1, node.y).withG(node.g + 1).withParent(node));
        if (inBounds(node.x, node.y - 1) && isCoridor(node.x, node.y - 1)) neighbours.add(new Node(node.x, node.y - 1).withG(node.g + 1).withParent(node));
        if (inBounds(node.x + 1, node.y) && isCoridor(node.x + 1, node.y)) neighbours.add(new Node(node.x + 1, node.y).withG(node.g + 1).withParent(node));
        if (inBounds(node.x, node.y + 1) && isCoridor(node.x, node.y + 1)) neighbours.add(new Node(node.x, node.y + 1).withG(node.g + 1).withParent(node));
        return neighbours;
    }

    boolean isCoridor(int i, int j) {
        return grid[i][j] != '#';
    }

    /**
     * 865. Shortest Path to Get All Keys
     *
     * Example 1:
     * Input: ["@.a.#",
     *         "###.#",
     *         "b.A.B"]
     * Output: 8
     *
     * Example 2:
     * Input: ["@..aA",
     *         "..B#.",
     *         "....b"]
     * Output: 6
     */
    public List<Node> shortestPathAllKeys() {
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
     * Dijkstra
     */
    public List<Node> shortestPathDijkstra(int endI, int endJ) {
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
                        {0,0,0},
                        {0,0,0},
                        {0,0,0}
                },
                {
                        {0,1,0},
                        {1,0,0},
                        {0,0,0}
                },
                {
                        {0,0,0,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,1,1,1},
                        {0,0,0,0}
                },
                {
                        {0,0,0},
                        {0,0,0},
                        {0,0,0}
                },
                {
                        {0,0,0},
                        {0,0,0},
                        {0,0,0}
                }
        };

        for (int i = 0; i < inputs.length; i++) {
            PathSearch pathSearch = new PathSearch(inputs[i], 0, 0);
            pathSearch.print();
            List<Node> path = pathSearch.shortestPathDijkstra(inputs[i].length - 1, inputs[i][0].length - 1);
            System.out.println("Dijkstra : " + path);
            System.out.println("Dijkstra Length: " + path.size());
            //path = pathSearch.shortestPathAStar(inputs[i].length - 1, inputs[i][0].length - 1);
            //System.out.println("A* : " + path);
            //System.out.println("A* Length: " + path.size());
            System.out.println("==================");
        }

        int[][][] keySearchInputs = {
                {
                        {-1, 0, 2, 0, 1},
                        { 1, 1, 1, 0, 1},
                        { }
                }
        };

        for (int i = 0; i < keySearchInputs.length; i++) {
            PathSearch pathSearch = new PathSearch(inputs[i], 0, 0);
            pathSearch.print();
            List<Node> path = pathSearch.shortestPathDijkstra(inputs[i].length - 1, inputs[i][0].length - 1);
            System.out.println("Dijkstra : " + path);
            System.out.println("Dijkstra Length: " + path.size());

        }
    }
}
