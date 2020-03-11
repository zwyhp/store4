package com.exercise.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    public static String uploadFile1(MultipartFile file) throws IOException{
        String filename = null;
        String suffix = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        filename = DigestUtils.md5Hex(inputStream);
        String name = file.getOriginalFilename();
        suffix = name.substring(name.lastIndexOf("."));
        File writeFile = new File("F:\\imgs\\" + filename + suffix);
        file.transferTo(writeFile);
        }finally {
           if (inputStream != null )
               inputStream.close();
        }
        return "F:\\imgs\\" + filename + suffix;
    }



    public static String GetFileMD5name(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return DigestUtils.md5Hex(inputStream);
    }

}