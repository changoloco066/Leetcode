# Big O Notation Guide

## Table of Contents
- [What is Big O Notation?](#what-is-big-o-notation)
- [Why Should You Care?](#why-should-you-care)
- [Common Time Complexities](#common-time-complexities)
- [Visual Comparison](#visual-comparison)
- [Detailed Explanations](#detailed-explanations)
- [Space Complexity](#space-complexity)
- [How to Calculate Big O](#how-to-calculate-big-o)
- [Common Patterns in Code](#common-patterns-in-code)
- [Best, Average, and Worst Case](#best-average-and-worst-case)
- [Practical Tips](#practical-tips)

---

## What is Big O Notation?

Big O Notation is a way to describe **how fast an algorithm runs** or **how much memory it uses** as the input size grows. It answers the question: *"If I double my input, what happens to the runtime?"*

Think of it like fuel efficiency for cars:
- **O(1)** = Electric car - constant "fuel" usage no matter the distance
- **O(n)** = Regular car - fuel usage grows linearly with distance
- **O(n²)** = Gas guzzler - fuel usage grows exponentially with distance

### Key Points
- Big O describes the **worst-case scenario**
- It focuses on **growth rate**, not exact time
- We drop constants and lower-order terms
  - `O(2n)` → `O(n)`
  - `O(n² + n)` → `O(n²)`

---

## Why Should You Care?

### Real-World Impact

| Array Size | O(1) | O(log n) | O(n)     | O(n log n) | O(n²)             | O(2ⁿ)     |
|------------|------|----------|----------|------------|-------------------|-----------|
| 10         | 1    | 3        | 10       | 33         | 100               | 1,024     |
| 100        | 1    | 7        | 100      | 664        | 10,000            | 1.27×10³⁰ |
| 1,000      | 1    | 10       | 1,000    | 9,966      | 1,000,000         |     ∞     |
| 1,000,000  | 1    | 20       | 1,000,000| 19,931,569 | 1,000,000,000,000 |     ∞     |

**Example:** Searching for a name in a phone book
- **O(n)** - Linear Search: Check every name (could take minutes)
- **O(log n)** - Binary Search: Open middle, eliminate half (takes seconds)

---

## Common Time Complexities

From **fastest** to **slowest**:

O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(2ⁿ) < O(n!)
↑                                                        ↑
Fast                                                   Slow

### Quick Reference Table

| Notation | Name | Example Operation | When Input Doubles |
|----------|------|-------------------|-------------------|
| O(1) | Constant | Access array element | Time stays same |
| O(log n) | Logarithmic | Binary search | Time increases slightly |
| O(n) | Linear | Loop through array | Time doubles |
| O(n log n) | Linearithmic | Merge sort, Quick sort | Time slightly more than doubles |
| O(n²) | Quadratic | Nested loops | Time quadruples |
| O(2ⁿ) | Exponential | Recursive fibonacci | Time squares |
| O(n!) | Factorial | Generate all permutations | Time explodes |

---

## Visual Comparison

![Texto](https://www.savemyexams.com/a-level/computer-science/ocr/17/revision-notes/8-algorithms/8-1-algorithms/big-o-notation/)