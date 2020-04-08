package com.avatarduel.util;

public class Pair<T, U> {
    private final T t;
    private final U u;

    public Pair(T t, U u) {
        this.t= t;
        this.u= u;
    }

    public T first(){
        return this.t;
    }

    public U second(){
        return this.u;
    }
}