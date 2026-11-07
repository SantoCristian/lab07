package it.unibo.inner.impl;


import java.util.Iterator;
import it.unibo.inner.api.Predicate;
import it.unibo.inner.api.IterableWithPolicy;
import java.util.List;
import java.util.NoSuchElementException;
public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final List<T> array;
    private Predicate<T> predicate;

    

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter){

         this.array = List.of(array);
         this.predicate = filter;
    }

    public IterableWithPolicyImpl(final T[] array){
        this(array, new Predicate<T>(){//creo classe anonima
            @Override 
            public boolean test(T elem){
                return true;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    
    @Override
    public void setIterationPolicy(final Predicate<T> filter){
        this.predicate = filter;
    }

    public inner iterator(){
        return new inner();
    }

    public class inner implements Iterator<T>{
        int currentIndex = 0;

        @Override 
        public boolean hasNext(){
            while(currentIndex < IterableWithPolicyImpl.this.array.size()){
                if(IterableWithPolicyImpl.this.predicate.test(IterableWithPolicyImpl.this.array.get(currentIndex))){
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public T next(){
            if(hasNext()){
                return IterableWithPolicyImpl.this.array.get(currentIndex++);
            }
            throw new NoSuchElementException();
        }

        @Override 
        public void remove(){
            throw new UnsupportedOperationException("cannot do remove");
        }

    }
}