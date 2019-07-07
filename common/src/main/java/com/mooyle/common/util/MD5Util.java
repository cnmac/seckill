package com.mooyle.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {


    private static final String salt = "1kwb3r4pt";

    public static String inputPassToFormPass (String inputPass){
        String str = salt.charAt(2) + salt.charAt(3) + inputPass + salt.charAt(5) + salt.charAt(7);
        return DigestUtils.md5Hex(str);
    }

    public static String formPassToDBPass (String inputPass, String salt){
        String str = salt.charAt(2) + salt.charAt(3) + inputPass + salt.charAt(5) + salt.charAt(7);
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToDbPass (String input, String saltDb) {
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass, saltDb);
    }

}
