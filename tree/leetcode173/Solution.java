package leetcode.tree.leetcode173;

import java.util.*;
import leetcode.common.*;

/*
 * @lc app=leetcode.cn id=173 lang=java
 *
 * [173] 二叉搜索树迭代器
 *
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/description/
 *
 * algorithms
 * Medium (69.36%)
 * Likes:    117
 * Dislikes: 0
 * Total Accepted:    11.8K
 * Total Submissions: 16.9K
 * Testcase Example:  '["BSTIterator","next","next","hasNext","next","hasNext","next","hasNext","next","hasNext"]\n' +
  '[[[7,3,15,null,null,9,20]],[null],[null],[null],[null],[null],[null],[null],[null],[null]]'
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 * 
 * 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// @lc code=start
class BSTIterator {
    private LinkedList<TreeNode> stack;
    public BSTIterator(TreeNode root) {
       stack = new LinkedList<>();
       while(root != null){
           stack.addFirst(root);
           root = root.left;
       }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.removeFirst();
        int value = node.val;

        node = node.right;
        while(node != null){
            stack.addFirst(node);
            node = node.left;
        }
        
        return value;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

class BSTIteratorByMirror {
    private TreeNode pre = null;
    private TreeNode cur;

    public BSTIteratorByMirror(TreeNode root){
       cur = root; 
    }

    public int next(){
        while(cur != null){
            if(cur.left == null){
                int v = cur.val;
                cur = cur.right;
                return v;
            } else {
                pre= cur.left;
                while(pre.right != null && pre.right != cur){
                    pre= pre.right;
                }
                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    int v = cur.val;
                    pre.right = null;
                    cur = cur.right;
                    return v;
                }
            }
        }
        return -1;
    }

    public boolean hasNext(){
        return cur != null;
    }

    public static List<Integer> traversalByMirror(TreeNode node){
        if(node == null){
            return Collections.emptyList();
        }
        List<Integer> results = new LinkedList<>();
        TreeNode predecessor = null;
        while(node != null){
            if(node.left == null){
                results.add(node.val);
                node = node.right;
            } else {
                predecessor = node.left;
                while(predecessor.right != null && predecessor.right != node){
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null){
                    predecessor.right = node;
                    node = node.left;
                } else {
                    results.add(node.val);
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }

        return results;
    }

}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end

public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(20);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        List<Integer> res = BSTIteratorByMirror.traversalByMirror(root);
        System.out.println(res);

        BSTIteratorByMirror iterator = new BSTIteratorByMirror(root);
        System.out.println(iterator.next());   // 返回 3
        System.out.println(iterator.next());    // 返回 7
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 9
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 15
        System.out.println(iterator.hasNext()); // 返回 true
        System.out.println(iterator.next());    // 返回 20
        System.out.println(iterator.hasNext()); // 返回 false
    }
}