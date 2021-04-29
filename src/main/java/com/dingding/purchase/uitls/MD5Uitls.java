package com.dingding.purchase.uitls;

import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5Uitls {

    @SneakyThrows
    public static String genMD5Str(String stringValue) {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String string = Base64.getEncoder().encodeToString(md5.digest(stringValue.getBytes()));
        return string;
    }
}
