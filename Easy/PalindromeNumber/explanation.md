# 9. Palindrome Number

## Problem
Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

**Examples:**
- Input: `121` → Output: `true`
- Input: `-121` → Output: `false` (negative numbers are not palindromes)
- Input: `10` → Output: `false` (ends with 0, not a palindrome)

**Note:** A palindrome is a number that reads the same backward as forward.

---

## Solution: String Conversion with Two Pointers

### Algorithm

This solution converts the integer to a String and uses the **two pointers** technique. We place one pointer at the beginning (`left`) and one at the end (`right`), then move them toward each other comparing characters. If any characters don't match, it's not a palindrome. If we traverse the entire string without finding a mismatch, it is a palindrome.

### Code
```java
class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }  
            left++;
            right--;
        }
        return true;
    }
}
```

### Complexity
- **Time:** O(log n) where n is the number of digits (approximately log₁₀ of the input)
- **Space:** O(log n) for the String conversion

---

## Visual Example

**Input:** `x = 121`