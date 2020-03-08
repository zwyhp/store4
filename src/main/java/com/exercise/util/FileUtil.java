package com.exercise.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class FileUtil {

    public static String uploadFile(MultipartFile file) throws IOException {
        OutputStream out = null;
        InputStream input = null;
        byte[] bs = new byte[1024];
        int len = -1;
        String filename = null;

        try {
            input = file.getInputStream();
            Image image = ImageIO.read(input);
            if (image == null) {
                throw new BussinessException("文件类型错误");
            }
            filename = DigestUtils.md5Hex(input);
            out = new FileOutputStream("F:\\imgs" + filename);
            while ((len = input.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
        } finally {
            if (out != null)
                out.close();
            if (input != null)
                input.close();

            return "F:\\imgs" + filename;
        }
    }
        public static String GetFileMD5name(MultipartFile file) throws IOException {

            return DigestUtils.md5Hex(file.getInputStream());

        }
    }