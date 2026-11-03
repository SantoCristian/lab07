package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import it.unibo.inner.api.Predicate;
import it.unibo.inner.api.IterableWithPolicy;
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] array;
    private final Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] array){
        IterableWithPolicyImpl(array, true);
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter){

         this.array = Arrays.copyOf(array, array.length);
         this.predicate = filter;
    }
    /**
     * {@inheritDoc}
     */
    
    @Override
    public void setIterationPolicy(final Predicate<T> filter){
    }

    public inner iterator(){
        return new inner();
    }

    public class inner implements Iterator<T>{
        int currentIndex = 0;

        @Override 
        public boolean hasNext(){
            return currentIndex < array.length; // Array.getLength(IterableWithPolicyImpl.this.array);
        }

        @Override
        public T next(){
            return IterableWithPolicyImpl.this.array[currentIndex++];
        }

        @Override 
        public void remove(){
            throw new UnsupportedOperationException("cannot do remove");
        }

    }
}