public class BinarySearchTree {
	public static class TreeNode {
		int val, size, count;
		TreeNode left, right;
		public TreeNode(int val){
			this.val = val;
			size = 1;
			count = 1;
		}
	}	

	// validate it's a valid BST. 
	// idea is to set lower boundary and higher boundary for validation of next level of node.
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }


	public TreeNode buildTree(int[] nums){
		TreeNode root = null;
		for(int num : nums){
			root = insert(root, num);
		}

		return root;
	} 

	public TreeNode insert(TreeNode node, int num){
		if(node == null) return new TreeNode(num);
		if(node.val == num) node.count++;
		else if(node.val > num) node.left = insert(node.left, num);
		else node.right = insert(node.right, num);
		node.size++;
		return node;
	}

	// search for node whose val is k, or null if non-exist 
	public TreeNode find(TreeNode node, int k){
		if(node == null) return null;
		if(node.val == k) return node;
		if(node.val > k) return select(node.left, k);
		return select(node.right, k);
	}

	// select the kth's node in tree. if k is 0, return null.
	public TreeNode select(TreeNode node, int k){
		if(node == null || node.size < k || k <= 0) return null;
		int left = node.left == null ? 0 : node.left.size;
		if(left >= k) return select(node.left, k);
		if(left + node.count >= k) return node;
		return select(node.right, k - left - node.count);
	}

	public int rank(TreeNode node, int num){
		if(node == null) return 0;
		if(node.val == num) return node.left == null ? 0 : node.left.size;
		if(node.val > num) return rank(node.left, num);
		return (node.left == null ? 0 : node.left.size) + node.count + rank(node.right, num);
	}

	public TreeNode max(TreeNode node){
		while(node != null && node.right != null){
			node = node.right;
		}
		return node;
	}

	public TreeNode min(TreeNode node){
		while(node != null && node.left != null) node = node.left;
		return node;
	}

	public TreeNode deleteMin(TreeNode node){

	}

	public TreeNode delete(TreeNode node, int num){
		if(node == null) return null;
		if(node.val > num) root.left = delete(node.left, num);
		if(node.val < num) root.right = delete(node.right, num);
		if(node.count > 1) node.count--;
		else{
			if(node.left == null) return node.right;
			if(node.right == null) return node.left;
			TreeNode node = min(node.right);
			node.left = 
		}

		

		

	}

	public TreeNode successor(TreeNode node){
		
	}
 

 	// problem: bst中有两个节点错误的互调了，找出并恢复bst的顺序
	// BST的中序遍历就是从小到大的顺序排列， 因此可以比较前后顺序来确定哪两个节点错了

	// 正确的顺序： 1，2，3，4，5，6，7
	// 错误的顺序： 1，2，6，4，5，3，7  （调换了3和6），于是first -> 6, second -> 3
	// 这里有点小的tricky，每次发现 prev.val > current.val的时候，都令 second = current，而只有first是null的时候才first=prev，这样就记录了两个坏点

	TreeNode first, second, prev; // 错误的两个节点, prev纪录上一个访问的节点
	public void recoverTree(TreeNode root) {
    	//use inorder traversal to detect incorrect node
    
    	inOrder(root);

	    int temp = first.val;
	    first.val = second.val;
	    second.val = temp;
	}

	// 中序遍历 -》 左 -> 根 -> 右
	public void inOrder(TreeNode root){
	    if(root == null) return;
	    //search left tree
	    inOrder(root.left);
	    
	    //in inorder traversal of BST, prev should always have smaller value than current value
	    if(prev != null && prev.val >= root.val){
	        //incorrect smaller node is always found as prev node
	        if(first == null) first = prev;
	      //incorrect larger node is always found as curr(root) node
	        second = root;
	    }
	    
	    
	    //update prev node
	    prev = root;

	    //search right tree
	    inOrder(root.right);
	}
}