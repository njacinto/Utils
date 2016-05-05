/*
 * The MIT License
 *
 * Copyright 2016 njacinto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.nfpj.utils.collections;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import org.nfpj.utils.PageIterator;
import org.nfpj.utils.predicates.TruePredicate;

/**
 *
 * @author njacinto
 * @param <T> the type of object being returned by this iterator
 */
public class ListPageFilterIteratorDescending<T> implements PageIterator<T> {
    
    protected static final Object END = new Object();
    protected final ListIterator<T> it;
    protected final Predicate<T> predicate;
    protected final int fromIndex;
    protected final int toIndex;
    protected T previous;
    protected int countElements = 0;

    // <editor-fold defaultstate="expanded" desc="Constructors">
    /**
     * Creates an instance of this class
     * 
     * @param it the list iterator from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param predicate the filter to be applied to the elements
     */
    public ListPageFilterIteratorDescending(ListIterator<T> it, int fromIndex, int toIndex, Predicate<T> predicate) {
        if(fromIndex>=toIndex){
            throw new IllegalArgumentException("fromIndex cannot be bigger or equal to toIndex");
        }
        this.it = it!=null ? it : Collections.emptyListIterator();
        this.predicate = predicate!=null ? predicate : TruePredicate.getInstance();
        this.fromIndex = fromIndex<0 ? 0 : fromIndex;
        this.toIndex = toIndex;
        this.previous = getPrevious();
    }

    /**
     * Creates an instance of this class
     * 
     * @param list the list from where this instance will extract the elements
     * @param fromIndex
     * @param toIndex
     * @param predicate the filter to be applied to the elements
     */
    public ListPageFilterIteratorDescending(List<T> list, int fromIndex, int toIndex, Predicate<T> predicate) {
        this(list==null ? null : list.listIterator(list.size()), fromIndex, toIndex, predicate);
    }
    // </editor-fold>
    // <editor-fold defaultstate="expanded" desc="Public methods">
    /**
     * {@inheritDoc} 
     */
    @Override
    public int getFromIndex() {
        return fromIndex;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getToIndex() {
        return toIndex;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getPage() {
        return (fromIndex/(toIndex-fromIndex))+1;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public int getPageSize() {
        return (previous!=END)? -1 : countElements-fromIndex;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return previous !=END;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        if(previous==END){
            throw new NoSuchElementException("The underline collection has no elements.");
        }
        T ret = previous;
        previous = getPrevious();
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("The iterator doesn't allow changes.");
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Protected methods">
    /**
     * Searches for the next element that matches the filtering conditions and
     * returns it.
     * 
     * @return the next element that matches the filtering conditions or null
     *          if no more elements are available
     */
    protected T getPrevious(){
        if(countElements<toIndex){
            T tmp;
            while(it.hasPrevious()){
                if(predicate.test(tmp=it.previous())){
                    countElements++;
                    if(countElements>fromIndex){
                        return tmp;
                    }
                }
            }
        }
        return (T)END;
    }
    // </editor-fold>
}
