package com.ihsan.datacomp.service;

public interface FrequencyTable {

    int getSymbolLimit();

    int get(int symbol);

    void increment(int symbol);

    int getTotal();

    int getLow(int symbol);

    int getHigh(int symbol);
}
