public class TreeTraversal {
	public static class TreeNode {
		int val;
		TreeNode left, right;
	}

	public List<Integer> preOrderRecursive(TreeNode root){
		List<Integer> result = new ArrayList<>();
		preOrderRecursive(root, result);
		return result;
	}

	private void preOrderRecursive(TreeNode node, List<Integer> result){
		if(node == null) return;
		result.add(node.val);
		preOrderRecursive(node.left);
		preOrderRecursive(node.right);
	}

	public List<Integer> preOrderIterative(TreeNode root){
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		if(root == null) return result;
		stack.add(root);

		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			result.add(node.val);
			if(node.right != null){
				stack.push(node.right);
			}

			if(node.left != null){
				stack.push(node.left);
			}
		}

		return result;
	}

	public List<Integer> inOrderRecursive(TreeNode root){
		List<Integer> result = new ArrayList<>();
		inOrderRecursive(root, result);
		return result;
	}

	private void inOrderRecursive(TreeNode node, List<Integer> result){
		if(node == null) return;
		inOrderRecursive(node.left);
		result.add(node.val);	
		inOrderRecursive(node.right);
	}

	public List<Integer> inOrderIterative(TreeNode root){
		List<Integer> result = new ArrayList<>();
	}

	public List<Integer> postOrderRecursive(TreeNode root){
		List<Integer> result = new ArrayList<>();
		postOrderRecursive(root, result);
		return result;
	}

	private void postOrderRecursive(TreeNode node, List<Integer> result){
		if(node == null) return;
		postOrderRecursive(node.left);
		postOrderRecursive(node.right);
		result.add(node.val);	
	}

	public List<Integer> postOrderIterative(TreeNode root){
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		Set<TreeNode> visited = new HashSet<>();
		if(root == null) return result;
		stack.push(root);

		while(!stack.isEmpty()){
			TreeNode current = stack.pop();
			if(current.left == null && current.right == null || visited.contains(current)) result.add(current.val);
			else {
				stack.push(current);
				visited.add(current);
				if(current.right != null) stack.push(current.right);
				if(current.left != null) stack.push(current.left);
			}
		}

		return result;
	}

	public List<Integer> levelOrderRecursive(TreeNode root){
		List<Integer> result = new ArrayList<>();
		List<Integer> 
		levelOrderRecursive(Collections.singletonList<>(root), result);
		return result;
	}

	private void levelOrderRecursive(List<TreeNode> next, List<Integer> result){
		if(node == null || next.isEmpty()) return;
		List<TreeNode> todo = new ArrayList<>(next.size() * 2);
		for(TreeNode node : next){
			result.add(node.val);
			if(node.left != null) todo.add(node.left);
			if(node.right != null) todo.add(node.right);
		}

		levelOrderRecursive(todo, result);
	}

	public List<Integer> levelOrderIterative(TreeNode root){
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) return result;
		queue.offer(root);

		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				TreeNode node = queue.poll();
				result.add(node.val);
				if(node.left != null) queue.offer(node.left);
				if(node.left != null) queue.offer(node.right);
			}
		}

		return result;
	}

}













