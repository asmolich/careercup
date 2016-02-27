import java.util.*;
import java.util.function.*;

public class ArrayToBST {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4,5,6,7,8,9,9,9,9,10,11,12};
        Solution s = new Solution();
        TreeNode root = s.sortedArrayToBalancedBst(data);
        List<TreeNode> traversal = s.inOrder(root);
        System.out.println(traversal);
        List<List<TreeNode>> paths = new ArrayList<List<TreeNode>>();
        LinkedList<TreeNode> path = new LinkedList<TreeNode>();
        s.dfs(root, path, paths, new Predicate<TreeNode>(){
            @Override
            public boolean test(TreeNode node) {
                return node.value % 5 == 0;
            }
        });//null);
        System.out.println(paths);

        System.out.println("Level traversal:");
        System.out.println(s.levelTraversal(root));

        System.out.println("Vertical traversal:");
        System.out.println(s.verticalTraversal(root));
    }
}

class TreeNode {
    final int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int v) {
        this(v, null, null);
    }
    TreeNode(int v, TreeNode l, TreeNode r) {
        value = v;
        left = l;
        right = r;
    }
    public String toString() {
        return ""+value;
    }
}

class TreeNodePrio implements Comparable<TreeNodePrio> {
    final TreeNode node;
    final long prio;
    final int l, r, level;
    TreeNodePrio(TreeNode n, long p, int l0, int r0, int lvl) {
        node = n; prio = p; l = l0; r = r0; level = lvl;
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
        return ""+node+",("+l+","+r+"),prio="+prio;
    }
}

class Solution {
    public TreeNode sortedArrayToBalancedBst(int[] a) {
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

    public ArrayList<List<TreeNode>> levelTraversal(TreeNode root) {
        if (root == null) return new ArrayList<List<TreeNode>>(0);

        ArrayList<List<TreeNode>> traversal = new ArrayList<List<TreeNode>>();
        levelTraversal0(root, 0, traversal);
        return traversal;
    }
    private void levelTraversal0(TreeNode root, int level, ArrayList<List<TreeNode>> traversal) {
        List<TreeNode> list;
        if (traversal.size() <= level) {
            list = new ArrayList<TreeNode>();
            traversal.add(list);
        }
        else {
            list = traversal.get(level);   
        }
        if (list == null) {
            list = new ArrayList<TreeNode>();
            traversal.add(list);
        }
        list.add(root);
        if (root.left != null) levelTraversal0(root.left, level + 1, traversal);
        if (root.right != null) levelTraversal0(root.right, level + 1, traversal);
    }

    public List<TreeNode> inOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<TreeNode> traversal = new ArrayList<TreeNode>();
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        while(!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            traversal.add(node);
            if (node.left != null) nodes.add(node.left);
            if (node.right != null) nodes.add(node.right);
        }
        return traversal;
    }

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

    public List<TreeNode> verticalTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        PriorityQueue<TreeNodePrio> q = new PriorityQueue<TreeNodePrio>();
        traverse(root, 1, 0, 0, 0, q);

        List<TreeNode> traversal = new ArrayList<TreeNode>();
        while (!q.isEmpty()) {
            traversal.add(q.poll().node);
        }
        return traversal;
    }

    private void traverse(TreeNode node, long prio, int l, int r, int lvl,  PriorityQueue<TreeNodePrio> q) {
        if (node == null) return;
        q.add(new TreeNodePrio(node, prio, l, r, lvl));

        if (node.left != null) traverse(node.left, prio * 2, l + 1, r, lvl + 1, q);
        if (node.right != null) traverse(node.right, prio * 2 + 1, l, r + 1, lvl + 1, q);
    }
}

