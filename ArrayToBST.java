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
    }
}

class TreeNode {
    int value;
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
}

