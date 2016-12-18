public class RestoreIPAddress {
	public List<String> restoreIpAddresses(String s) {
	    List<String> result = new LinkedList<>();
	    if(s == null || s.isEmpty()) return result;
	    List<Integer> segment = new ArrayList<>();
	    dfs(s, 0, segment, result);
	    return result;
	}

	private void dfs(String s, int offset, List<Integer> segment, List<String> result){
	   if(segment.size() == 4 && offset == s.length()){
	   		result.add("" + segment.get(0) + "." + segment.get(1) + "." + segment.get(2) + "." + segment.get(3));
	   		return;
	   }

	   if(segment.size() == 4 || offset >= s.length()) return;

	   for(int len = 1; len <= 3 && len + offset <= s.length(); len ++){
	   		String sec = s.substring(offset, offset + len);
	   		Integer ip = Integer.parseInt(sec);
	   		// ip can not be start with 0, but can be single 0, eg, 127.0.0.10, but can not be 127.00.1.0
	   		if(ip > 255 || len > 1 && sec.charAt(0) == '0') return;
	   		segment.add(ip);
	   		dfs(s, offset + len, segment, result);
	   		segment.remove(segment.size() - 1);
	   }
	}
}