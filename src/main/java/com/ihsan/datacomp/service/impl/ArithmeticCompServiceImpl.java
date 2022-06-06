package com.ihsan.datacomp.service.impl;

import java.io.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ihsan.datacomp.datahelper.ArithmeticDecoder;
import com.ihsan.datacomp.datahelper.ArithmeticEncoder;
import com.ihsan.datacomp.datahelper.BitInputStream;
import com.ihsan.datacomp.datahelper.BitOutputStream;
import com.ihsan.datacomp.service.ArithmeticCompService;
import com.ihsan.datacomp.service.FrequencyTable;

@Service
@Transactional
public class ArithmeticCompServiceImpl implements ArithmeticCompService {

    private static final int NUM_BIT = 32;

    private static final String FILE_PATH = "src/main/resources/files/";
    private static final String TEMP_FILE_PATH = FILE_PATH + "temp/targetFile.tmp";

    @Override
    public String compress(MultipartFile multipartFile) throws IOException {
        File file = new File(TEMP_FILE_PATH);
        File outputFile = new File(FILE_PATH + "C" + multipartFile.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }

        FrequencyTable freqs = getFrequencies(file);
        freqs.increment(256); // EOF symbol gets a frequency of 1

        // Read input file again, compress with arithmetic coding, and write output file
        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
                BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            for (int i = 0; i < 256; i++)
                writeInt(out, NUM_BIT, freqs.get(i));

            compress(freqs, in, out);
        }

        return "OK";
    }

    @Override
    public String deCompress(MultipartFile multipartFile) throws IOException {
        File file = new File(TEMP_FILE_PATH);
        File outputFile = new File(FILE_PATH + "D" + multipartFile.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }

        try (BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(file)));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            int[] freq = new int[257];
            for (int i = 0; i < 256; i++)
                freq[i] = readInt(in, NUM_BIT);
            freq[256] = 1; // EOF symbol

            deCompress(new SimpleFrequencyTable(freq), in, out);
        }
        return "OK";
    }

    private static void deCompress(FrequencyTable freqs, BitInputStream in, OutputStream out) throws IOException {
        ArithmeticDecoder dec = new ArithmeticDecoder(NUM_BIT, in);
        while (true) {
            int symbol = dec.read(freqs);
            if (symbol == 256) // EOF symbol
                break;
            out.write(symbol);
        }
    }

    private static FrequencyTable getFrequencies(File file) throws IOException {
        FrequencyTable freqs = new SimpleFrequencyTable(new int[257]);
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            while (true) {
                int b = input.read();
                if (b == -1)
                    break;
                freqs.increment(b);
            }
        }
        return freqs;
    }

    private static void compress(FrequencyTable freqs, InputStream in, BitOutputStream out) throws IOException {
        ArithmeticEncoder enc = new ArithmeticEncoder(NUM_BIT, out);
        while (true) {
            int symbol = in.read();
            if (symbol == -1)
                break;
            enc.write(freqs, symbol);
        }
        enc.write(freqs, 256); // EOF
        enc.finish(); // Flush remaining code bits
    }

    private static void writeInt(BitOutputStream out, int numBits, int value) throws IOException {
        for (int i = numBits - 1; i >= 0; i--) {
            out.write((value >>> i) & 1); // Big endian
        }
    }

    private static int readInt(BitInputStream in, int numBits) throws IOException {
        if (numBits < 0 || numBits > NUM_BIT)
            throw new IllegalArgumentException();

        int result = 0;
        for (int i = 0; i < numBits; i++)
            result = (result << 1) | in.readNoEof(); // Big endian
        return result;
    }

}
