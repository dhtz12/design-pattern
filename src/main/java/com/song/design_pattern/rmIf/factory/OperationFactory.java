package com.song.rmIf.factory;

import java.util.Optional;

/**
 * packageName:com.song.rmIf.factory
 *
 * @author: lss
 * @Date: 2023/5/2 19:48
 * desc:  工厂类
 */
public class OperationFactory {

    public static Operation getOperation(String operator) {
        Operation operation = null;
        switch (operator) {
            case "add":
                operation = new AddOperation();
                break;
            case "subtract":
                operation = new SubOperation();
                break;
            default:
                break;
        }
        return operation;
    }

    public int calculate(int a, int b, String operator) {
        Operation operation = Optional.ofNullable( OperationFactory.getOperation( operator ) ).orElseThrow( () -> new IllegalArgumentException( "operator not found" ) );
        return operation.calculate( a, b );
    }

    public static void main(String[] args) {
        OperationFactory operationFactory = new OperationFactory();
        int calculate = operationFactory.calculate( 1, 2, "subtract" );
        System.out.println( calculate );
    }
}
