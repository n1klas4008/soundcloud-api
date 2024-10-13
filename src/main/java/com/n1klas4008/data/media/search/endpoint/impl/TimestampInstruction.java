package com.n1klas4008.data.media.search.endpoint.impl;

import com.n1klas4008.data.media.search.endpoint.AbstractInstruction;

public class TimestampInstruction extends AbstractInstruction {

    @Override
    public String modify(String base, String[] args) {
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    protected int getArguments() {
        return 0;
    }

    @Override
    public String getName() {
        return "timestamp";
    }
}
