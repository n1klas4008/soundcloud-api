package com.n1klas4008.data.media.search.endpoint;


public interface Instruction {
    String getName();

    String manipulate(String base, String[] args) throws Exception;

}
