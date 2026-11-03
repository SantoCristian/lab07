package it.unibo.inner.impl;

import it.unibo.inner.api.Predicate;

public class AlwaysTruePredicate<T> implements Predicate<T>{
    T given;

    public AlwaysTruePredicate (final T token){
        this.given = token;
    }

    public boolean test(T elem){
        return true;
    }
}