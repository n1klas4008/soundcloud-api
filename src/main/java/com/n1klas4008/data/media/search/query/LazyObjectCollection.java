package com.n1klas4008.data.media.search.query;

import com.n1klas4008.data.media.search.Explorer;

import java.util.Iterator;



public class LazyObjectCollection<T> extends ObjectCollection<T> {
    private final Iterator<T> iterator;
    private PartialCollection<T> collection;

    public LazyObjectCollection(Explorer<T> explorer) {
        super(explorer);
        this.iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (collection == null) {
                    LazyObjectCollection.this.collection = explorer.next();
                } else if (!collection.getList().isEmpty()) {
                    return true;
                } else {
                    if (!explorer.hasNext()) {
                        return false;
                    }
                    LazyObjectCollection.this.collection = explorer.next();
                }
                while (LazyObjectCollection.this.collection.getList().isEmpty()) {
                    if (explorer.hasNext()) {
                        LazyObjectCollection.this.collection = explorer.next();
                    } else {
                        break;
                    }
                }
                return !LazyObjectCollection.this.collection.getList().isEmpty();
            }

            @Override
            public T next() {
                return LazyObjectCollection.this.collection.getList().remove(0);
            }
        };
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }
}