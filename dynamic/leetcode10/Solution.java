package leetcode.dynamic.leetcode10;



/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (25.36%)
 * Likes:    807
 * Dislikes: 0
 * Total Accepted:    41.8K
 * Total Submissions: 164.9K
 * Testcase Example:  '"aa"\n"a"'
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 
 * 
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 
 * 说明:
 * 
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 
 * 
 * 示例 3:
 * 
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 
 * 
 * 示例 4:
 * 
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 
 * 示例 5:
 * 
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * 
 */

// @lc code=start
class Solution {
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty())
            return text.isEmpty();
        boolean firstMatch = false;
        if (!text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.')) {
            firstMatch = true;
        }

        // 回溯， 如果下个字符是*， 先尝试匹配0个，不行再尝试匹配一个
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch(text, pattern.substring(2))
            || firstMatch && isMatch(text.substring(1), pattern);
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        boolean r1 = s.isMatch("aa", "a");
        System.out.println(r1);

        boolean r2 = s.isMatch("aa", "a.");
        System.out.println(r2);

        boolean r3 = s.isMatch("aaa", "a*");
        System.out.println(r3);
    }
}
// @lc code=end
