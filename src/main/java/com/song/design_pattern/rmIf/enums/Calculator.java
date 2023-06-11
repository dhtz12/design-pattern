package com.song.rmIf.enums;

/**
 * packageName:com.song.rmIf.enums
 *
 * @author: lss
 * @Date: 2023/5/2 20:03
 * desc:
 */
public class Calculator {

    public int calculate(int a, int b, OperatorEnum operatorEnum) {
        return operatorEnum.apply( a, b );
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println( calculator.calculate( 1, 2, OperatorEnum.ADD ) );
        System.out.println( calculator.calculate( 1, 2, OperatorEnum.MULTIPLY ) );
    }
}
