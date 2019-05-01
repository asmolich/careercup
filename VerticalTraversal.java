import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class VerticalTraversal {
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode head) {
        if (head == null) return new ArrayList<>();

        Map<Integer, ArrayList<Integer>> res = new HashMap<>();
        Deque<TreeNodeLevel> q = new ArrayDeque<>();
        q.add(TreeNodeLevel.of(head, 0));

        int minLevel = 0;
        int maxLevel = 0;
        while (!q.isEmpty()) {
            TreeNodeLevel nodeLevel = q.pollFirst();
            ArrayList<Integer> currentLevel = res.getOrDefault(nodeLevel.level, new ArrayList<>());
            currentLevel.add(nodeLevel.node.val);
            res.put(nodeLevel.level, currentLevel);
            if (nodeLevel.level < minLevel) minLevel = nodeLevel.level;
            if (nodeLevel.level > maxLevel) maxLevel = nodeLevel.level;
            if (nodeLevel.node.left != null) q.addLast(TreeNodeLevel.of(nodeLevel.node.left, nodeLevel.level - 1));
            if (nodeLevel.node.right != null) q.addLast(TreeNodeLevel.of(nodeLevel.node.right, nodeLevel.level + 1));
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>(maxLevel - minLevel + 1);
        for (int i = minLevel; i <= maxLevel; i++) {
            result.add(res.get(i));
        }
        return result;
    }

    static class TreeNodeLevel {
        TreeNode node;
        int level;

        static TreeNodeLevel of(TreeNode node, int level) {
            TreeNodeLevel nodeLevel = new TreeNodeLevel();
            nodeLevel.node = node;
            nodeLevel.level = level;
            return nodeLevel;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}
