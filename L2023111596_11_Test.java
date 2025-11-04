import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 测试用例设计总体原则：
 * 1. 等价类划分：将输入数据划分为有效等价类和无效等价类
 * 2. 边界值分析：测试输入范围的边界条件  
 * 3. 路径覆盖：确保覆盖所有可能的执行路径
 * 4. 异常测试：验证特殊输入的处理
 * 5. 性能测试：验证算法在合理时间内的执行
 */
public class L2023111596_11_Test {
    
    private Solution solution = new Solution();
    
    /**
     * 测试目的：验证正常情况下的功能正确性
     * 测试用例：使用题目提供的示例1
     * - 输入: 包含多个有效三元组的数组
     * - 预期输出: 所有不重复的三元组
     */
    @Test
    public void testExample1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        
        // 创建期望结果
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1, -1, 2));
        expected.add(Arrays.asList(-1, 0, 1));
        
        assertEquals("应该找到2个三元组", 2, result.size());
        assertTrue("结果应包含所有期望的三元组", containsAll(expected, result));
    }
    
    /**
     * 测试目的：验证无解情况
     * 测试用例：使用题目提供的示例2
     * - 输入: 无法组成和为0的三元组的数组
     * - 预期输出: 空列表
     */
    @Test
    public void testExample2() {
        int[] nums = {0, 1, 1};
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue("无解时应返回空列表", result.isEmpty());
    }
    
    /**
     * 测试目的：验证全零特殊情况
     * 测试用例：使用题目提供的示例3
     * - 输入: 全零数组
     * - 预期输出: 包含一个三元组[0,0,0]
     */
    @Test
    public void testExample3() {
        int[] nums = {0, 0, 0};
        List<List<Integer>> result = solution.threeSum(nums);
        
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0, 0, 0));
        
        assertEquals("全零数组应该返回一个三元组", 1, result.size());
        assertEquals(expected, result);
    }
    
    /**
     * 测试目的：验证边界条件处理
     * 测试用例：最小长度数组
     * - 输入: 长度小于3的数组
     * - 预期输出: 空列表
     */
    @Test
    public void testSmallArray() {
        int[] nums1 = {};
        int[] nums2 = {1};
        int[] nums3 = {1, 2};
        
        assertTrue("空数组应返回空列表", solution.threeSum(nums1).isEmpty());
        assertTrue("单元素数组应返回空列表", solution.threeSum(nums2).isEmpty());
        assertTrue("双元素数组应返回空列表", solution.threeSum(nums3).isEmpty());
    }
    
    /**
     * 测试目的：验证重复元素处理
     * 测试用例：包含多个重复元素的数组
     * - 输入: 有重复元素但能组成有效三元组
     * - 预期输出: 不重复的三元组
     */
    @Test
    public void testDuplicateElements() {
        int[] nums = {-2, 0, 1, 1, 2};
        List<List<Integer>> result = solution.threeSum(nums);
        
        // 应该找到 [-2,0,2] 和 [-2,1,1]
        assertEquals("应该找到2个不重复的三元组", 2, result.size());
        
        // 验证具体内容
        boolean found1 = false, found2 = false;
        for (List<Integer> triplet : result) {
            if (triplet.contains(-2) && triplet.contains(0) && triplet.contains(2)) {
                found1 = true;
            }
            if (triplet.contains(-2) && triplet.contains(1) && Collections.frequency(triplet, 1) == 2) {
                found2 = true;
            }
        }
        assertTrue("应包含[-2,0,2]", found1);
        assertTrue("应包含[-2,1,1]", found2);
    }
    
    /**
     * 测试目的：验证大数处理
     * 测试用例：包含较大数值的数组
     * - 输入: 数值范围较大的数组
     * - 预期输出: 正确的三元组
     */
    @Test
    public void testLargeNumbers() {
        int[] nums = {1000, -1000, 0, 500, -500};
        List<List<Integer>> result = solution.threeSum(nums);
        
        // 应该找到 [1000, -1000, 0] 和 [500, -500, 0]
        assertEquals("应该找到2个三元组", 2, result.size());
    }
    
    /**
     * 测试目的：验证正数数组
     * 测试用例：全部为正数的数组
     * - 输入: 所有元素都为正数
     * - 预期输出: 空列表
     */
    @Test
    public void testAllPositive() {
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue("全正数数组应返回空列表", result.isEmpty());
    }
    
    /**
     * 测试目的：验证负数数组
     * 测试用例：全部为负数的数组
     * - 输入: 所有元素都为负数
     * - 预期输出: 空列表
     */
    @Test
    public void testAllNegative() {
        int[] nums = {-1, -2, -3, -4, -5};
        List<List<Integer>> result = solution.threeSum(nums);
        assertTrue("全负数数组应返回空列表", result.isEmpty());
    }
    
    /**
     * 测试目的：验证包含零的数组
     * 测试用例：包含零和正负数的数组
     * - 输入: 包含0且有匹配对的数组
     * - 预期输出: 正确的三元组
     */
    @Test
    public void testWithZero() {
        int[] nums = {-1, 0, 1, 2, -1, -4, 0};
        List<List<Integer>> result = solution.threeSum(nums);
        
        // 应该找到 [-1,-1,2], [-1,0,1], [0,0,0]
        assertTrue("应该至少找到2个三元组", result.size() >= 2);
    }
    
    // 辅助方法：检查两个列表是否包含相同的元素（顺序无关）
    private boolean containsAll(List<List<Integer>> expected, List<List<Integer>> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        
        for (List<Integer> list : expected) {
            if (!containsList(actual, list)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean containsList(List<List<Integer>> listOfLists, List<Integer> target) {
        for (List<Integer> list : listOfLists) {
            if (list.size() == target.size() && list.containsAll(target) && target.containsAll(list)) {
                return true;
            }
        }
        return false;
    }
}
