package com.ihsan.datacomp.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ArithmeticCompService {

    String compress(MultipartFile file) throws IOException;

    String deCompress(MultipartFile file) throws IOException;

}
