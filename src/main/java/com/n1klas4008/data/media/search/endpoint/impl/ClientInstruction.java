package com.n1klas4008.data.media.search.endpoint.impl;

import com.n1klas4008.data.VirtualClient;
import com.n1klas4008.data.media.search.endpoint.AbstractInstruction;

public class ClientInstruction extends AbstractInstruction {

    @Override
    public String modify(String base, String[] args) throws Exception {
        return VirtualClient.getID();
    }

    @Override
    protected int getArguments() {
        return 0;
    }

    @Override
    public String getName() {
        return "client";
    }
}
