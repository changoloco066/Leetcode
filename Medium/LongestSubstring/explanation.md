# 3. Longest Substring Without Repeating Characters

## Problem
Given a string `s`, find the length of the longest substring without repeating characters.

**Examples:**
- Input: `"abcabcbb"` → Output: `3` (substring: "abc")
- Input: `"bbbbb"` → Output: `1` (substring: "b")
- Input: `"pwwkew"` → Output: `3` (substring: "wke" or "kew")

---

## Solution: Sliding Window with indexOf

### Algorithm

This solution uses the **sliding window technique** with two pointers (`left` and `right`). As we expand the window by moving `right`, we use `indexOf(char, left)` to check if the current character already exists in our current window. If we find a duplicate (when `index != right`), we move `left` to one position after the duplicate. After each step, we calculate the window size and update the maximum length.

### Code
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for(int right = 0, left = 0; right < s.length(); right++){
            int index = s.indexOf(s.charAt(right), left);
            if (index != right){
                left = index + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
```

### Complexity
- **Time:** O(n²) worst case (indexOf can be O(n)), but performs well in practice (2ms runtime)
- **Space:** O(1) - no extra data structures

---

## Visual Example

**Input:** `s = "abcabcbb"`