package com.ihsan.datacomp.service.impl;

import com.ihsan.datacomp.service.FrequencyTable;

public class SimpleFrequencyTable implements FrequencyTable {

    private int[] frequencies;

    private int[] cumulative;

    private int total;

    SimpleFrequencyTable(int[] freqs) {
        frequencies = freqs.clone(); // Make copy
        total = 0;
        for (int x : frequencies) {
            total = total + x;
        }
        cumulative = null;
    }

    public int getSymbolLimit() {
        return frequencies.length;
    }

    public int get(int symbol) {
        return frequencies[symbol];
    }

    public void increment(int symbol) {
        total++;
        frequencies[symbol]++;
        cumulative = null;
    }
    public int getTotal() {
        return total;
    }

    public int getLow(int symbol) {
        if (cumulative == null)
            initCumulative();
        return cumulative[symbol];
    }

    public int getHigh(int symbol) {
        if (cumulative == null)
            initCumulative();
        return cumulative[symbol + 1];
    }

    private void initCumulative() {
        cumulative = new int[frequencies.length + 1];
        int sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum = frequencies[i] + sum;
            cumulative[i + 1] = sum;
        }
    }
}
