//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 465 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution{
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (null == strs || strs.length == 0) {
            return result;
        }
        HashMap<String, List<String>> map = new HashMap();
        for (String str : strs) {
            String tmp = getSortedStr(str);
            if (map.containsKey(tmp)) {
                map.get(tmp).add(str);
            } else {
                List<String> arr = new ArrayList<String>();
                arr.add(str);
                map.put(tmp, arr);
            }
        }
        /*
        for (Map.Entry<String, List<String>> m : map.entrySet()) {
            result.add(m.getValue());
        }
        */
        return new ArrayList<>(map.values());
    }

    String getSortedStr(String str) {
        if (null == str || str.length() == 0) {
            return "";
        }
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }
}



//leetcode submit region end(Prohibit modification and deletion)
