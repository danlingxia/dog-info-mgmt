package com.doggiehome.doginfomgmt.util;

import com.google.common.base.Charsets;

import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;

@Component
public class MD5Util {
     public enum EncodePair{
         BEFORE_ENCODE,
         SALT,
         ENCODED_MSG
    }

    public static String encode(SecretKey secretKey, String msg) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        mac.update(msg.getBytes("UTF-8"));
        byte[] result =mac.doFinal();
        String after = new BigInteger(1, result).toString(16);
        System.out.println("加密后 ：" + after);
        return after;

    }


    /**
     * 后端随机盐加密
     * @param msg
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static EnumMap<EncodePair, String> randomSaltMD5(String msg) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        EnumMap<EncodePair, String> enumMap = new EnumMap<EncodePair, String>(EncodePair.class);
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // 随机生成的key:
        byte[] skey = key.getEncoded();
//        String randomSalt = new BigInteger(1, skey).toString(16);
        String randomSalt = new BigInteger( skey).toString(16);




        System.out.println("随机盐 : "+ randomSalt);
//        随机盐加密
        String encodedMsg = encode(key, msg);
//        随机盐加密后结果
        System.out.println("随机盐加密后结果 ： " + encodedMsg);
        enumMap.put(EncodePair.BEFORE_ENCODE, msg);
        enumMap.put(EncodePair.SALT, randomSalt);
        enumMap.put(EncodePair.ENCODED_MSG, encodedMsg);

        return enumMap;

    }

    /**
     * 固定盐加密
     * @param fixedSalt
     * @param msg
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static EnumMap<EncodePair, String> fixedSaltMD5(String fixedSalt, String msg) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        System.out.println("固定盐 ： " + fixedSalt);
        EnumMap<EncodePair, String> enumMap = new EnumMap<EncodePair, String>(EncodePair.class);
//        SecretKey MD5_KEY = new SecretKeySpec(fixedSalt.getBytes(Charsets.UTF_8), "HmacMD5");
        byte[] keys =  new BigInteger(fixedSalt, 16).toByteArray();
        SecretKey MD5_KEY = new SecretKeySpec(keys, "HmacMD5");
        //固定盐加密后结果
        String encodedMsg = encode(MD5_KEY, msg);
        System.out.println("固定盐加密后结果 ：" + encodedMsg);
        enumMap.put(EncodePair.BEFORE_ENCODE, msg);
        enumMap.put(EncodePair.SALT, fixedSalt);
        enumMap.put(EncodePair.ENCODED_MSG, encodedMsg);
        return enumMap;

    }


    /**
     * 输入明文密码，两次加密后结果（第一次固定盐，第二次随机盐）
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
//    public static EnumMap<EncodePair, String> fixedAndRandomSaltMD5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
//        String frontSalt = "afeu$%#SDCV*YG)87ygv87403pgh-t%^(&)U_{$^DSXC*";
////        System.out.println("固定盐 ： " + frontSalt);
////        SecretKey MD5_KEY = new SecretKeySpec(frontSalt.getBytes(Charsets.UTF_8), "HmacMD5");
////        //固定盐加密后结果
////        String beforeBackendMsg = encode(MD5_KEY, password);
//        EnumMap<EncodePair, String> enumMapFixed = fixedSaltMD5(frontSalt, password);
////        随机盐加密后结果
//        EnumMap<EncodePair, String> enumMapRandom = randomSaltMD5(enumMapFixed.get(EncodePair.ENCODED_MSG));
//        return enumMapRandom;
//    }



//    public static void main(String[] args) {
//        String password = "123456";
//        try {
//            front(password);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//
//    }
}
