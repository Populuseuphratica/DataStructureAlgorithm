package com.killstan.stringMatch;

/**
 * @Auther: Kill_Stan
 * @Date: 2022/1/21 22:37
 * @Description:
 */
public class ViolentMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    public static int violenceMatch(String source, String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();

        int i = 0;
        int j = 0;
        int index = -1;
        while (i < s.length) {
            if (s[i] == t[j]) {
                i++;
                j++;
                if (j == t.length) {
                    index = i - j;
                    break;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        return index;
    }
}
