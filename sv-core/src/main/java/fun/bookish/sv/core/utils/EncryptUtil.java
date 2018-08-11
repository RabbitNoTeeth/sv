package fun.bookish.sv.core.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 加密工具类
 */
public class EncryptUtil {

    private EncryptUtil(){}

    private static MessageDigest MD5_DIGEST;

    static {
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            throw new RuntimeException("md5加密工具创建失败",e);
        }
    }

    /**
     * Md5加密字符串
     * @param source
     * @return
     */
    public static String encryptByMd5(String source){
        try {
            byte[] byteArray = source.getBytes("UTF-8");
            byte[] md5Bytes = MD5_DIGEST.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的字符集类型",e);
        }
    }

}
