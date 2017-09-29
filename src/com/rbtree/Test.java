package com.rbtree;

/**
 * Created by zhiwei on 2017/9/29.
 */
public class Test<T> {

    public <T extends Number> double add(T t1, T t2){
        double sum = 0.0;
        sum = t1.doubleValue() + t2.doubleValue();
        return sum;
    }
}
