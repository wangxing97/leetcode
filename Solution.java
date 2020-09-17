import java.util.ArrayList;

public class Solution {
    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     */

    // 集合
    public int singleNumber(int[] nums) {
        ArrayList<String> lists = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(lists.contains(""+nums[i])){
                lists.remove(""+nums[i]);
            }else{
                lists.add(""+nums[i]);
            }
        }
        return Integer.parseInt(lists.get(0));
    }
    // 位运算 异或运算
    public int singleNumber1(int[] nums) {
        int result = 0;
        for(int i=0;i<nums.length;i++){
            result ^=nums[i];
        }
        return result;
    }
    // 傻子写法
    public int singleNumber2 (int[] nums) {
        boolean[] flag = new boolean[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(flag[i]){
                continue;
            }
            for(int j = i+1; j < nums.length; j++){
                if(flag[j]){
                    continue;
                }
                if(nums[i] == nums[j]){
                    flag[i] = true;
                    flag[j] = true;
                    break;
                }
            }
            if(!flag[i]){
                return nums[i];
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{2,4,4,1,2};
        Solution solution = new Solution();
        System.out.println(solution.singleNumber(nums));
    }
}
