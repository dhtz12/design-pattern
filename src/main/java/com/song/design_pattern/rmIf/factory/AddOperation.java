package com.song.rmIf.factory;

/**
 * packageName:com.song.rmIf.factory
 *
 * @author: lss
 * @Date: 2023/5/2 19:46
 * desc:
 */
public class AddOperation implements Operation {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
