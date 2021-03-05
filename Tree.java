import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class Tree {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Tree s = new Tree();
        HorizontalDistance hd = new HorizontalDistance();
        TreeNode root = s.sortedArrayToBalancedBst(data);

        System.out.println("LevelOrder:");
        System.out.println(s.levelOrder(root));
        System.out.println("LevelOrderIterative:");
        System.out.println(s.levelOrderIterative(root));
        System.out.println("PreOrder:");
        System.out.println(s.preOrder(root));
        System.out.println("PreOrderIterative:");
        System.out.println(s.preOrderIterative(root));
        System.out.println("InOrder:");
        System.out.println(s.inOrder(root));
        System.out.println("InOrderIterative:");
        System.out.println(s.inOrderIterative(root));
        System.out.println("PostOrder:");
        System.out.println(s.postOrder(root));
        System.out.println("PostOrderIterative:");
        System.out.println(s.postOrderIterative(root));

        System.out.println("ZigzagOrder:");
        System.out.println(s.zigzagOrder(root));
        System.out.println("ZigzagOrderIterative:");
        System.out.println(s.zigzagOrderIterative(root));

        System.out.println("Diameter = " + hd.diameter(root));

        System.out.println("5th smallest is " + s.kthSmallest(root, 5));
        System.out.println("0th smallest is " + s.kthSmallest(root, 0));
        System.out.println("13th smallest is " + s.kthSmallest(root, 13));
        System.out.println("16th smallest is " + s.kthSmallest(root, 16));
        System.out.println("5th smallest is " + s.kthSmallest(root, 5));
        System.out.println("0th smallest is " + s.kthSmallest(root, 0));
        System.out.println("13th smallest is " + s.kthSmallest(root, 13));
        System.out.println("16th smallest is " + s.kthSmallest(root, 16));

        List<List<TreeNode>> paths = new ArrayList<>();
        LinkedList<TreeNode> path = new LinkedList<>();
        s.dfs(root, path, paths, node -> node.value % 5 == 0);//null);
        System.out.println("All paths to elements divisible by 5:");
        System.out.println(paths);
        paths.clear();
        path.clear();
        s.dfs(root, path, paths, node -> node.value % 3 == 0);
        System.out.println("All paths to elements divisible by 3:");
        System.out.println(paths);

        System.out.println("Vertical Order:");
        System.out.println(s.verticalOrder(root));

        s.connect(root);
        System.out.println("Linked:");
        System.out.println(s.linkedTraversal(root));

        s.invert(root);
        System.out.println("Inverted:");
        System.out.println(s.levelOrder(root));

        System.out.println("hasPathSum of 17 ? " + (s.hasPathSum(root, 17) == 1 ? "YES" : "NO"));
        System.out.println("hasPathSum of 16 ? " + (s.hasPathSum(root, 16) == 1 ? "YES" : "NO"));
        System.out.println("hasPathSum of 15 ? " + (s.hasPathSum(root, 15) == 1 ? "YES" : "NO"));
        System.out.println("hasPathSum of 14 ? " + (s.hasPathSum(root, 14) == 1 ? "YES" : "NO"));

        s.flattenIterative(root);
        System.out.println("Flattened:");
        System.out.println(s.levelOrder(root));

        System.out.println("Diameter = " + hd.diameter(root));

        System.out.println("Construct from: ");
        int[] lvlOrder = new int[]{2, 1, 4, -1, -1, 3, -1, -1, -1};
        System.out.println(Arrays.toString(lvlOrder));
        System.out.println("Result:");
        root = s.constructFromLevelOrderWithNullMarkers(lvlOrder);
        System.out.println(s.levelOrder(root));

        System.out.println("Construct from: ");
        lvlOrder = new int[]{
                621367, 400139, 986434, 318453, 562082, 727076, -1, 208016, 340383, 409269, -1, 702531, 983736, 187691, -1, -1, 387077, -1,
                534779, 647033, 719463, 824451, -1, -1, -1, 373900, -1, 517606, -1, -1, -1, -1, 720965, -1, 834145, -1, -1, -1, -1, -1, -1, -1, -1
        };
        System.out.println(Arrays.toString(lvlOrder));
        System.out.println("Result:");
        root = s.constructFromLevelOrderWithNullMarkers(lvlOrder);
        System.out.println(s.levelOrder(root));
        s.connect(root);
        System.out.println(s.linkedTraversal(root));

        System.out.println("Diameter = " + hd.diameter(root));
    }

    private static class TreeNode {
        final int value;
        TreeNode left;
        TreeNode right;
        TreeNode next = null;

        TreeNode(int v) {
            this(v, null, null);
        }

        TreeNode(int v, TreeNode l, TreeNode r) {
            value = v;
            left = l;
            right = r;
        }

        public String toString() {
            return "" + value;
        }
    }

    private static class TreeNodePrio implements Comparable<TreeNodePrio> {
        final TreeNode node;
        final long prio;
        final int l, r, level;

        TreeNodePrio(TreeNode n, long p, int l0, int r0, int lvl) {
            node = n;
            prio = p;
            l = l0;
            r = r0;
            level = lvl;
        }

        @Override
        public int compareTo(TreeNodePrio p) {
            int res = Integer.compare(r - l, p.r - p.l);
            if (res == 0) res = Integer.compare(l, p.l);
            if (res == 0) res = Integer.compare(level, p.level);
            if (res == 0) res = Long.compare(prio, p.prio);
            return res;
        }

        @Override
        public String toString() {
            return "" + node + ",(" + l + "," + r + "),prio=" + prio;
        }
    }

    private TreeNode sortedArrayToBalancedBst(int[] a) {
        return sortedArrayToBalancedBst(a, 0, a.length - 1);
    }

    private TreeNode sortedArrayToBalancedBst(int[] a, int lo, int hi) {
        if (a == null || hi < lo || lo < 0 || hi >= a.length) return null;

        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(a[mid]);
        root.left = sortedArrayToBalancedBst(a, lo, mid - 1);
        root.right = sortedArrayToBalancedBst(a, mid + 1, hi);
        return root;
    }

    private void insert(TreeNode root, TreeNode ins) {
        if (root == null) return;
        if (ins == null) return;

        TreeNode node = root;
        while (node != null) {
            if (ins.value < node.value) {
                if (node.left == null) {
                    node.left = ins;
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = ins;
                    break;
                }
                node = node.right;
            }
        }
    }

    private TreeNode constructFromLevelOrderWithNullMarkers(int[] data) {
        if (data == null || data.length == 0) return null;

        TreeNode root = null;
        TreeNode node;
        for (int v : data) {
            if (v == -1)
                node = null;
            else
                node = new TreeNode(v);
            if (root == null) {
                root = node;
                continue;
            }

            insert(root, node);
        }
        return root;
    }

    private List<List<TreeNode>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<List<TreeNode>> traversal = new ArrayList<>();
        levelOrder0(root, 0, traversal);
        return traversal;
    }

    private void levelOrder0(TreeNode root, int level, List<List<TreeNode>> traversal) {
        List<TreeNode> list;
        if (traversal.size() <= level) {
            list = new ArrayList<>();
            traversal.add(list);
        } else {
            list = traversal.get(level);
        }
        if (list == null) {
            list = new ArrayList<>();
            traversal.add(list);
        }
        list.add(root);
        if (root.left != null) levelOrder0(root.left, level + 1, traversal);
        if (root.right != null) levelOrder0(root.right, level + 1, traversal);
    }

    public List<TreeNode> levelOrderIterative(TreeNode root) { // BFS
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.addLast(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.removeFirst();
            traversal.add(node);
            if (node.left != null) nodes.addLast(node.left);
            if (node.right != null) nodes.addLast(node.right);
        }
        return traversal;
    }

    public List<TreeNode> preOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        preOrder0(root, traversal);
        return traversal;
    }

    private void preOrder0(TreeNode root, List<TreeNode> trav) {
        if (root == null) return;
        trav.add(root);
        preOrder0(root.left, trav);
        preOrder0(root.right, trav);
    }

    public List<TreeNode> preOrderIterative(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            traversal.add(node);
            if (node.right != null) stack.addLast(node.right);
            if (node.left != null) stack.addLast(node.left);
        }
        return traversal;
    }

    public List<TreeNode> inOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        inOrder0(root, traversal);
        return traversal;
    }

    private void inOrder0(TreeNode root, List<TreeNode> trav) {
        if (root == null) return;
        inOrder0(root.left, trav);
        trav.add(root);
        inOrder0(root.right, trav);
    }

    public List<TreeNode> inOrderIterative(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.addLast(node);
                node = node.left;
            } else {
                node = stack.removeLast();
                traversal.add(node);
                node = node.right;
            }
        }
        return traversal;
    }

    public List<TreeNode> postOrder(TreeNode root) {
        List<TreeNode> traversal = new ArrayList<>();
        postOrder0(root, traversal);
        return traversal;
    }

    private void postOrder0(TreeNode root, List<TreeNode> trav) {
        if (root == null) return;
        postOrder0(root.left, trav);
        postOrder0(root.right, trav);
        trav.add(root);
    }

    public List<TreeNode> postOrderIterative(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        TreeNode lastSeen = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.addLast(node);
                node = node.left;
            } else {
                TreeNode peekNode = stack.peekLast();
                if (peekNode.right != null && lastSeen != peekNode.right)
                    node = peekNode.right;
                else {
                    traversal.add(peekNode);
                    lastSeen = stack.removeLast();
                }
            }
        }
        return traversal;
    }

    @SuppressWarnings("unchecked")
    public TreeNode dfs(TreeNode root, LinkedList<TreeNode> path, List<List<TreeNode>> paths, Predicate<TreeNode> p) {
        if (root == null) {
            return null;
        }

        path.add(root);
        //System.out.println("going down");
        if (p == null || p.test(root))
            paths.add((List<TreeNode>) path.clone()); // collect all possible paths on the way down

        TreeNode leftNode = dfs(root.left, path, paths, p);
        TreeNode rightNode = dfs(root.right, path, paths, p);
        //System.out.println("going up");
        //paths.add((List<TreeNode>) path.clone()); // collect all possible paths on the way up
        if (!path.isEmpty()) path.removeLast();
        return root;
    }

    public List<TreeNodePrio> verticalOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        PriorityQueue<TreeNodePrio> q = new PriorityQueue<>();
        verticalOrder0(root, 1, 0, 0, 0, q);

        List<TreeNodePrio> traversal = new ArrayList<>();
        while (!q.isEmpty()) {
            traversal.add(q.poll());
        }
        return traversal;
    }

    private void verticalOrder0(TreeNode node, long prio, int l, int r, int lvl, PriorityQueue<TreeNodePrio> q) {
        if (node == null) return;

        long p = prio;
        TreeNodePrio newTreeNode = new TreeNodePrio(node, p, l, r, lvl);
        q.add(newTreeNode);

        if (node.left != null) verticalOrder0(node.left, p * 2, l + 1, r, lvl + 1, q);
        if (node.right != null) verticalOrder0(node.right, p * 2 + 1, l, r + 1, lvl + 1, q);
    }

    public void invert(TreeNode root) {
        if (root == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);
    }

    public void connect(TreeNode root) {
        TreeNode nextRow;
        for (TreeNode row = root; row != null; row = nextRow) {
            nextRow = null;
            TreeNode curr = null;
            TreeNode prev = null;
            for (TreeNode col = row; col != null; col = col.next) {
                if (col.left != null) {
                    prev = curr;
                    curr = col.left;
                }
                if (curr != null && nextRow == null) nextRow = curr;
                if (prev != null) {
                    prev.next = curr;
                }
                if (col.right != null) {
                    prev = curr;
                    curr = col.right;
                }
                if (curr != null && nextRow == null) nextRow = curr;
                if (prev != null) {
                    prev.next = curr;
                }
            }
        }
    }

    public List<List<TreeNode>> linkedTraversal(TreeNode root) {
        List<List<TreeNode>> linked = new ArrayList<>();
        TreeNode nextRow;
        for (TreeNode row = root; row != null; row = nextRow) {
            nextRow = null;
            ArrayList<TreeNode> list = new ArrayList<>();
            for (TreeNode col = row; col != null; col = col.next) {
                list.add(col);
                if (col.left != null && nextRow == null) nextRow = col.left;
                else if (col.right != null && nextRow == null) nextRow = col.right;
            }
            linked.add(list);
        }
        return linked;
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten0(root, null);
    }

    private void flatten0(TreeNode root, TreeNode next) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null) {
            root.right = left;
            root.left = null;
            if (right == null) {
                flatten0(left, next);
            } else {
                flatten0(left, right);
                flatten0(right, next);
            }
        } else {
            if (right == null) {
                root.right = next;
            } else {
                flatten0(right, next);
            }
        }
    }

    private void flattenIterative(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode prev = node.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }

    private int hasPathSum(TreeNode root, int b) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return b == root.value ? 1 : 0;

        int res = hasPathSum(root.left, b - root.value);
        if (res == 1) return 1;
        res = hasPathSum(root.right, b - root.value);

        return res;
    }

    private ArrayList<LinkedList<TreeNode>> zigzagOrder(TreeNode root) {
        if (root == null) return new ArrayList<>(0);

        ArrayList<LinkedList<TreeNode>> traversal = new ArrayList<>();
        zigzagOrder0(root, 0, traversal);
        return traversal;
    }

    private void zigzagOrder0(TreeNode root, int level, ArrayList<LinkedList<TreeNode>> traversal) {
        LinkedList<TreeNode> list;
        if (traversal.size() <= level) {
            list = new LinkedList<>();
            traversal.add(list);
        } else {
            list = traversal.get(level);
        }
        if (list == null) {
            list = new LinkedList<>();
            traversal.add(list);
        }

        if (level % 2 == 0) list.addLast(root);
        else list.addFirst(root);

        if (root.left != null) zigzagOrder0(root.left, level + 1, traversal);
        if (root.right != null) zigzagOrder0(root.right, level + 1, traversal);
    }

    @SuppressWarnings("unchecked")
    private List<List<TreeNode>> zigzagOrderIterative(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<List<TreeNode>> traversal = new ArrayList<>();
        LinkedList<TreeNode> level = new LinkedList<>();
        ArrayDeque<TreeNode> nodes = new ArrayDeque<>();
        nodes.addLast(root);
        TreeNode marker = new TreeNode(Integer.MIN_VALUE);
        nodes.addLast(marker);
        int lvl = 0;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.removeFirst();
            if (node != marker) {
                if (lvl % 2 == 0) level.addLast(node);
                else level.addFirst(node);
                if (node.left != null) nodes.addLast(node.left);
                if (node.right != null) nodes.addLast(node.right);
            } else {
                traversal.add((List<TreeNode>) level.clone());
                level.clear();
                lvl++;
                if (!nodes.isEmpty())
                    nodes.addLast(marker);
            }
        }
        return traversal;
    }

    private int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;

        ArrayList<TreeNode> traversal = new ArrayList<>();
        kthSmallest0(root, traversal, k);
        if (k > traversal.size()) return -1;
        return traversal.get(k).value;
    }

    private void kthSmallest0(TreeNode root, ArrayList<TreeNode> trav, int k) {
        if (root == null) return;
        kthSmallest0(root.left, trav, k);
        trav.add(root);
        if (trav.size() > k) return;
        kthSmallest0(root.right, trav, k);
    }

    public int kthSmallestIterative(TreeNode root, int k) {
        if (root == null) return -1;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.addLast(node);
                node = node.left;
            } else {
                node = stack.removeLast();
                // visit(node);
                if (k == 0) return node.value;
                k--;
                node = node.right;
            }
        }
        return -1;
    }

    static class HorizontalDistance {
        int maxNeg = 0;
        int maxPos = 0;

        int diameter(TreeNode root) {
            if (root == null) return 0;
            maxNeg = 0;
            maxPos = 0;

            horizontalDistance0(root, 0, 0);

            return maxPos - maxNeg;
        }

        private void horizontalDistance0(TreeNode node, int l, int r) {
            if (node.left == null && node.right == null) {
                int hd = r - l;
                if (hd < 0 && hd < maxNeg) maxNeg = hd;
                if (hd > 0 && hd > maxPos) maxPos = hd;
                return;
            }

            if (node.left != null) horizontalDistance0(node.left, l + 1, r);
            if (node.right != null) horizontalDistance0(node.right, l, r + 1);
        }
    }
}
