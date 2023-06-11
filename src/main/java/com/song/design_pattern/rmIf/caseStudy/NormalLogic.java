package com.song.rmIf.caseStudy;

/**
 * packageName:com.song.rmIf.factory
 *
 * @author: lss
 * @Date: 2023/5/2 19:40
 * desc:  初学者写判断逻辑.
 */
public class NormalLogic {

    public int calculate(int a, int b, String operator) {
        int result = Integer.MIN_VALUE;

        if ("add".equals(operator)) {
            result = a + b;
        } else if ("multiply".equals(operator)) {
            result = a * b;
        } else if ("divide".equals(operator)) {
            result = a / b;
        } else if ("subtract".equals(operator)) {
            result = a - b;
        }
        return result;
    }
}
