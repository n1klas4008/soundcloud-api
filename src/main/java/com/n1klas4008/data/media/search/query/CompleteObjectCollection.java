package com.n1klas4008.data.media.search.query;


import com.n1klas4008.data.media.search.Explorer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompleteObjectCollection<T> extends ObjectCollection<T> {

    private final List<T> list = new ArrayList<>();

    public CompleteObjectCollection(Explorer<T> explorer) {
        super(explorer);
        while (explorer.hasNext()) {
            PartialCollection<T> partialCollection = explorer.next();
            list.addAll(partialCollection.getList());
        }
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
