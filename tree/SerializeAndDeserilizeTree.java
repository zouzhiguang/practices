public class SerializeAndDeserilizeTree {
	  private static final String SPLITTER = ",";
      private static final String NN = "X";
	  public String serialize(TreeNode root) {
	  	StringBuilder builder = new StringBuilder();
	  	buildString(root, builder);
	  	return builder.toString();
	  }

	  private void buildString(TreeNode node, StringBuilder builder){
	  	builder.append(node == null ? NN : String.valueOf(node.val)).append(SPLITTER);
	  	if(node != null){
	  		buildString(node.left, builder);
	  		buildString(node.right, builder);
	  	}
	  }


	  public TreeNode deserialize(String data) {
	  	LinkedList<String> list = new LinkedList<>();
	  	list.addAll(Arrays.asList(data.split(SPLITTER)));
	  	return buildTree(list);
	  }

	  private TreeNode buildTree(LinkedList<String> nodes){
	  	String val = nodes.poll();
	  	if(val.equals(NN)) return null;
	  	else{
	  		TreeNode node = new TreeNode(Integer.parse(val));
	  		node.left = buildTree(nodes);
	  		node.right = buildTree(nodes);
	  		return node;
	  	}
	  }
}