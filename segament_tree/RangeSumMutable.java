public class RangeSumMutable{
	privaet final TreeNode root;

	public RangeSumMutable(int[] nums){
		root = buildTree(nums, 0, nums.length - 1);
	}

	public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private TreeNode buildTree(int[] nums, int from, int to){
    	if(from > to) return null;

    	TreeNode node = new TreeNode(from, to);
    	if(from == to) node.sum = nums[from];
    	else{
    		int mid = from + (to - from) / 2;
    		node.left = buildTree(nums, from, mid);
    		node.right = buildTree(nums, mid + 1, to);
    		node.sum = node.left.sum + node.right.sum;
    	}

    	return node;
    }

    private void update(TreeNode node, int from, int val){
    	if(node.from == node.to) node.sum = val;
    	else{
    		int mid = node.from + (node.to - node.from) / 2;
    		if(from <= mid) update(node.left, from, val);
    		else update(node.right, from, val);

    		node.sum = node.left == null ? 0 : node.left.sum + node.right == null ? 0 : node.right.sum;
    	}
    }

    private int sumRange(TreeNode node, int from, int to){
    	if(node.from == from && node.to == to) return node.sum;
    	int mid = node.from + (node.to - node.from) / 2;
    	if(to <= mid) return sumRange(node.left, from, to);
    	if(from >= mid + 1) return sumRange(node.right, from, to);
    	return sumRange(node.left, from, mid) + sumRange(node.right, mid + 1, to);
    }

    public class TreeNode {
    	int from, to, sum;
    	TreeNode left, right;
    	public TreeNode(int from, int to){
    		this.from = from;
    		this.to = to;
    	}
    }
}