package main

/*
 * @lc app=leetcode.cn id=3 lang=golang
 *
 * [3] 无重复字符的最长子串
 *
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (39.09%)
 * Likes:    9021
 * Dislikes: 0
 * Total Accepted:    2.3M
 * Total Submissions: 5.8M
 * Testcase Example:  '"abcabcbb"'
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *
 * 示例 2:
 *
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *
 * 示例 3:
 *
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 *
 *
 */

// @lc code=start
func lengthOfLongestSubstring(s string) int {
	// map初始化为-1， 0是有意义的位置信息，代表第一个字符
	routeMap := [128]int{}
	for i := 0; i < 128; i++ {
		routeMap[i] = -1
	}

	// routeMap[int(s[0])] = 0
	l := 0
	maxLen := 0
	for i := 0; i < len(s); i++ {
		c := int(s[i])
		if routeMap[c] >= l {
			if i-l > maxLen {
				maxLen = i - l
			}
			l = routeMap[c] + 1
		}
		routeMap[c] = i
	}

	// 一直没有重复字符，直到字符串末尾
	if len(s)-l > maxLen {
		maxLen = len(s) - l
	}

	return maxLen
}

// @lc code=end
