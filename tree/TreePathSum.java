public class TreePathSum{

	// check if have a path sum equal to given sum.
	// path means from root to any single leaf
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return false;
		if(root.left == null && root.right == null && root.val == sum){
			return true;
		}

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	// each node is value from 0 ~ 9 means for a single digit, 
	// each path from root to leaf means a single number, 1 -> 2 -> 3 means 123.
	// return the sum of all numbers which are from all paths.
	// eg: Tree:

	//          1
	//        2   3
	// the number should be 12 + 13 and return 25
	public int sumAllPath(TreeNode root){
		int[] sum = new int[1];
		dfs(root, 0, sum);
		return sum[0];
	}


	private void dfs(TreeNode node, int current, int[] sum){
		if(node == null) return;
		current += current * 10 + node.val;
		if(node.left == null && node.right == right){
			sum[0] += current;
			return;
		}

		dfs(node.left, current, sum);
		dfs(node.right, current, sum);
	}


	// path can be any node, and not necessarily to cross root
	// a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
	// The path must contain at least one node and does not need to go through the root.
	// eg: 
	//          1
	//        2   3

	// return 6. path is 2 -> 1 -> 3
	public int maxPathSum(TreeNode root) {
		if(root.left == null && root.right == null) return root.val;
		return max(root).max;
	}

	private Result max(TreeNode node){
		if(root == null) return new Result(0, Integer.MIN_VALUE);
		Result left = max(node.left);
		Result right = max(node.right);

		int single = Math.max(left.single, right.single) + node.val;
		single = Math.max(0, max);

		int max = Math.max(left.max, right.max);
		max = Math.max(max, left.single + right.single + root.val);
		return new Result(single, max);
	}

	// single is max sum for single path(can with root, but does not with right branch)
	// max is currently max for any single branch or two single branch with root value

	// the problem is if a sum is chosen as left subtree + right subtree, 
	// it can not use this value to merge with another single/multiple path. 
	// or else it would be 3 paths or multiple path which violates the given condition.
	public static class Result {
		int single, max;
		Result(int single, int max){
			this.single = single;
			this.max = max;
		}
	}
}