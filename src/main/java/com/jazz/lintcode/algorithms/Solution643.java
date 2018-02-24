package com.jazz.lintcode.algorithms;

import java.util.HashMap;
import java.util.Map;

/**27
 * Created by XUJING592 on 2018/2/23.
 */
public class Solution643 {
    public static int lengthLongestPath(String input) {
        int res = 0;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        for (String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            int len = s.substring(level).length();
            if (s.contains(".")) {
                res = Math.max(res, m.get(level) + len);
                System.out.println( "res " + res);
            } else {
                m.put(level + 1, m.get(level) + len + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1xxxxxxxxxxxxxx\n\t\ttest.ext\n\tsubdir2\n\t\tfile.ext"));
    }
}