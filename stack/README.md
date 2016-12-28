# Stack problem. 
Usually stack problems need to store the historical state of the problem. Eg, not only min, but also second min, third min, etc... So that it can calculate the optimal answer with all traces. 
Problem for this kind typically is: 
..* [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/):  we use a stack that store the indices with decreasing bar height, once we find a bar who's height is larger, then let the top of the stack be bot, the cur bar is right boundary, and the previous bar is left boundary.
..* [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/): use a max stack to record and when there is a drop of height, calculate the area as it's the bar of the rectangle area size.