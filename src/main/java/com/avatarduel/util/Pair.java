package com.avatarduel.util;

public class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T t, U u) {
        this.first = t;
        this.second = u;
    }

    public T first(){
        return this.first;
    }

    public U second(){
        return this.second;
    }
}