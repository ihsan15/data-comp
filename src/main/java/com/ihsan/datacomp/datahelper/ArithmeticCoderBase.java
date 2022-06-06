package com.ihsan.datacomp.datahelper;

import com.ihsan.datacomp.service.FrequencyTable;

import java.io.IOException;

public abstract class ArithmeticCoderBase {

    final int numStateBits;

    private final long fullRange;

    final long halfRange;

    private final long quarterRange;

    final long stateMask;

    long low;

    long high;

    ArithmeticCoderBase(int numBits) {

        numStateBits = numBits;
        fullRange = 1L << numStateBits;
        halfRange = fullRange >>> 1; // Non-zero
        quarterRange = halfRange >>> 1; // Can be zero
        stateMask = fullRange - 1;

        low = 0;
        high = stateMask;
    }

    void update(FrequencyTable freqs, int symbol) throws IOException {
        long range = high - low + 1;

        // Frequency table values check
        long total = freqs.getTotal();
        long symLow = freqs.getLow(symbol);
        long symHigh = freqs.getHigh(symbol);

        // Update range
        long newLow = low + symLow * range / total;
        long newHigh = low + symHigh * range / total - 1;
        low = newLow;
        high = newHigh;

        // While low and high have the same top bit value, shift them out
        while (((low ^ high) & halfRange) == 0) {
            shift();
            low = ((low << 1) & stateMask);
            high = ((high << 1) & stateMask) | 1;
        }
        // Now low's top bit must be 0 and high's top bit must be 1

        while ((low & ~high & quarterRange) != 0) {
            underflow();
            low = (low << 1) ^ halfRange;
            high = ((high ^ halfRange) << 1) | halfRange | 1;
        }
    }

    protected abstract void shift() throws IOException;

    protected abstract void underflow() throws IOException;
}
