public class BinarySearchTree {
	public static class TreeNode {
		TreeNode left, right;
		int size, val, count;
		public TreeNode(int val){
			this.val = val;
			this.count = 1;
		}
	}

	public TreeNode buildTree(int[] vals){

	}

	public TreeNode insert(TreeNode root, int val){
		if(root == null) return new TreeNode(val);
		if(val < root.val) root.left = insert(root.left, val);
		else if(val > root.val) root.right = insert(root.right, val);
		else root.count++;
		root.size++;
		return root;
	}

	public TreeNode max(TreeNode root){
		while(root.right != null) root = root.right;
		return root;
	}

	public TreeNode min(TreeNode root){
		while(root.left != null) root = root.left;
		return root;
	}

	public TreeNode search(TreeNode root, int val){
		if(root == null || val == root.val) return root;
		if(val > root.val) return search(root.right, val);
		return search(root.left, val);
	}

	public int rank(TreeNode root, int val){
		if(root == null) return 0;
		if(val == root.val) return root.left == null ? 0 : root.left.size;
		else if(val < root.val) return rank(root.left, val);
		return rank(root.right, val) + (root.left == null ? 0 : root.left.size) + root.count;
	}

	public TreeNode select(TreeNode root, int k){
		if(root == null || k > root.size) return null;
		int left = root.left == null ? 0 : root.left.size;
		if(k > left && (k - left) <= root.count) return root;
		if(k < left) return select(root.left, k);
		else return select(root.right, k - left - root.count);
	}
}