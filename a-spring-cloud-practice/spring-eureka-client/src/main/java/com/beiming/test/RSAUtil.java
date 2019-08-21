package com.beiming.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {

//  private static final String PUBLICKEY =
//      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChEYW/azRjq2Y0Q2QGxXAWklv/\n" +
//          "WLIs2mCL5KVtrGKDV6VUhcRhYk1Z/GXH2KkYAzQ7oRNaDKRjeVu6dd1PdTZOMlP8\n" +
//          "75W4eN4ZR4IjMhnAkCaRZs08c8SGUK/XEUvm94x09IRPJSsQMOaC3uxNfFilARp9\n" +
//          "ly1UwIKPIf40sKIB0QIDAQAB";
//
//  private static final String PRIVATEKEY =
//      "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKERhb9rNGOrZjRD\n" +
//          "ZAbFcBaSW/9YsizaYIvkpW2sYoNXpVSFxGFiTVn8ZcfYqRgDNDuhE1oMpGN5W7p1\n" +
//          "3U91Nk4yU/zvlbh43hlHgiMyGcCQJpFmzTxzxIZQr9cRS+b3jHT0hE8lKxAw5oLe\n" +
//          "7E18WKUBGn2XLVTAgo8h/jSwogHRAgMBAAECgYB0WJ/wQZQ6Wtkc74EVkxEAfQCP\n" +
//          "VpZ959DNvH/y1Q7kKS93+ph8b/HICpXK4ipi+g2E9TlxVqh4YnVsHy+Z8eUjOVgG\n" +
//          "aEZcRnnsJOjQPLV3GSvfjz5CBB+nbWEe1S5lIs995Ms9OZepUEQYOzhUexTugrdy\n" +
//          "MycIlLQwcqpRzrfHQQJBANYwBdsIwScoQaEJib2C8stdbzVuedsITVNwuVJ+ARAz\n" +
//          "G+Xfc6+hSYT+rbgcdHc5M7iOisEk49HYzD4mnZhfEr0CQQDAguHb37juemiVKUt9\n" +
//          "bUYWGeulFbpXqTCiWeTYD/ete7yqUHyEalSzDslz/MbETzdbredmpkg/CfJwy3l0\n" +
//          "fIalAkARqHXjH+6kpsn6V8n2SMRxhat0cT40k5OkbGeq6F54Em6bXfewpBBmmYSC\n" +
//          "6D2l0p5z+bRwxfTOSRxVh2N4u2XVAkB1cqQ2z3v0ymtprSLoJ7WdoDy6n9Hqz0bj\n" +
//          "lbJe+wbhKV+GDMqawoBgKYJQKARgGfAtF6DYaFQlWX02wXycy6gdAkEAi0XCiVyp\n" +
//          "P5Og0K6kzvaKaayqFnHAda9ISfKbPt5qAcIkoQuZhwAtVAXut9gtgO2yKLUbrHtN\n" +
//          "zdMACKLmHYCGyw==";

  private static final String PRIVATEKEY =
      "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPEi3xagTWth1/q1s70Dzka+dU\n"
          + "kN12j5jqEp4fOC/qY7icZ3aE9iwqHXCkw46UeVwwwyn2ltN1oBR1r0Jleb62T5MPp5zo+Y+mJECo\n"
          + "q/bKGyqi/+iUcnp8xfQ6p4SDoUfVUtQgzyQ5IvnHavWxC27OQQpZe2RPL4oy6Ou/Eg21YL8/YL3S\n"
          + "U8W+w8HL+ZaOsDr8gMIM3gpJ4CtbOTuiPhZV+AEqpuRGHIl7nRZf+/g4Ke3T4FyS32kX6qfNoTwS\n"
          + "G4i1o8hOOBs0W380o/LjytF4E2ykvps1ioXf8eusoYPoZ/tYMj7pXqlbrAvEe5Z4XuKCQIX79EZV\n"
          + "5wwaYnZEGlRdAgMBAAECggEALc4OM3pT7kIEE7nSzcWPmToKqAJxROIYfOhVA33DqXEfy8M8KLl/\n"
          + "zkP6qmWJonNwVpRFPRNww/nqUvs3cLGXo1q6pIpvLA+qM/DrWpUjv/UOo3z3A3/bQ1NFK/ox7MqX\n"
          + "6u5Hx/FJkZlnCrdtza7xXMtp+tgMd2WrAz2bLGMlTGoftmCejI0a2UD8uq1kIiawvohInbkHjeG8\n"
          + "WVydaANIrc5xY4Jimz7l0TryG8JNVSNoPiNRpKH77HlBkfLPK25MgHeb8F5rduQmcmzfKAT17mer\n"
          + "dJ/gr++CnKKQ2ICKcPnPOSAjIipzP42hC1fw15TQwN8gusR2/IWe7qM7k2Wm6QKBgQDWqilOio5h\n"
          + "XpThu5I0Xxs+lBVG4yNKXjQ48LUJJVC/DvCL1fd65/QAjJvb1B3xRWCijvJcpkQxmOjlO7v7C7p5\n"
          + "veoH0exVVlb4+aPecBdnv2JG2tOnvX1xXZzTWuIREjJuTKpUwXUlZ7mbKuX+GXbawxe7jafLJEQO\n"
          + "K5KzigdhtwKBgQCqntOrJfRMwBWemRPF2UM0UnIUeth64GgbX5jU8q6eo74Xe1csS1/8xIlG9VPA\n"
          + "ugS+mRjaIFfDYu4qMKADI4u899JkkF/VUnGVmLx3qLmDmF1HtCjRdHxrTgZbQ9ik6lAyA8EohfyI\n"
          + "GefXlp6lYfw03p1jQtMPfxSv1/5qF3HqiwKBgQC3/j/Lhy1EvhnkZa6KXx5uA+U8zkEYZChkycSS\n"
          + "Iz7TlE2PvgUSfrAArISG5ogHngyXKOkJiqWW0VaF7S5Rnprl8Gcg7sif2JJRc0AGz5LwJPVqf/O8\n"
          + "qclUmwVpj1OHiE5bhjDU1sC/H+nR2vIpWtAp7+BurJsunlYKw0RJyTxMfwKBgEHIguvV8l81ICDJ\n"
          + "/Gmn3tVphDqJMrYNl5YWQaK4/sKDd2MuVunpFdUrIZKOPABuuC2UcZSIK85VcmYp4UfUiKgPFLtZ\n"
          + "PtnMs6ZuARxRnVUzhaODvskjFGV669B8VCchgFIISI0LO9ZtxonEtN4m7UqlGiGPPjkXUi6KJLla\n"
          + "2L0JAoGBAJU0/6+1YetYBKRrhYRCvgnCp9heHODIIelgHHOx7542BRN3rjspmeoV+n9rXC1o16OI\n"
          + "vWwsdVSzFIDSPZrhM2CqKyBbwe5oiEosLRXo91ci6zWdKkutB3S6ygihoZ9HsGH/4Z8xeX+MkgMl\n"
          + "8jYiMBIGjxBiQjxbv2guBDOedH9R";

  private static final String PUBLICKEY =
      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjxIt8WoE1rYdf6tbO9A85GvnVJDddo+Y\n"
          + "6hKeHzgv6mO4nGd2hPYsKh1wpMOOlHlcMMMp9pbTdaAUda9CZXm+tk+TD6ec6PmPpiRAqKv2yhsq\n"
          + "ov/olHJ6fMX0OqeEg6FH1VLUIM8kOSL5x2r1sQtuzkEKWXtkTy+KMujrvxINtWC/P2C90lPFvsPB\n"
          + "y/mWjrA6/IDCDN4KSeArWzk7oj4WVfgBKqbkRhyJe50WX/v4OCnt0+Bckt9pF+qnzaE8EhuItaPI\n"
          + "TjgbNFt/NKPy48rReBNspL6bNYqF3/HrrKGD6Gf7WDI+6V6pW6wLxHuWeF7igkCF+/RGVecMGmJ2\n"
          + "RBpUXQIDAQAB";

//  private static final String PUBLICKEY =
//      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv8detVcIzDWEmAz9ELpL\n"
//          + "Oy2GZtyxV+n0Y8DgeDIJy1yQ9SggFbs78pBJ4Jzaf39BKdyW1tmU6nJf39D2dcBE\n"
//          + "bmBmPRCw4IAzf+WiDZj3XJhgLah26U7F9M8+uH1FZzYEkvTRbMTpy2LQyz+L+Wp9\n"
//          + "YAfOnP5YG+hijDNGQgjGT4TFnxQ6dvCs75kE5W8w/cdLpFMzgEoXGbGcU3rJXb0B\n"
//          + "z/EmX1dXkYw9zhUc2crjKuSedn2ARspeOlep/el5hYEWLagMa5YFdBDGRTU4H+cB\n"
//          + "dXAen9Acydu4MY3z5X47IdCogCVuOCqWHCLkxzqUQFAQbgLFI0lD2y6ECW8MsdmX\n"
//          + "7QIDAQAB";
//
//  private static final String PRIVATEKEY =
//      "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIEAtEiFz8JG0CAggA\n"
//          + "MBQGCCqGSIb3DQMHBAgr1d7lIDXWTQSCBMiveurCeOTBlKP9MiP/3/8sUBnWFeYy\n"
//          + "Pnrc5c9UoRWRfH3k9ylxNR3zmvTe3lfeTAxt48mylIqBunpxxyrjog/z9GsuPeQQ\n"
//          + "bcU/HKGU7yzyPhX4mBwjLWY0gfdCrZy7aA09QZZRRf2oteS9UP9tC0t8R6ouV8Rf\n"
//          + "o6QjEDcYkrRUs79ONtOglF1FvHpB2OtI7cPm+lXifTNhpzrr4p4Sqi3rLMCUIMdc\n"
//          + "vbGGFauXI8791I+flQz5P59qTIKV7yoFaFU4+la1vW31YQpZZ0V2W/7PeuXazjyr\n"
//          + "PhwlV4lS62e8mwGJmUFqKKda+uCaP/nzPdLhXkfDtGXbHx47GzBEsQqcMgrulXlg\n"
//          + "GzzePekEA68baDduDA3k0sNkjdEfqw6/ZpvCDUJXNgd9UFUCMDtruclPrUR6hivM\n"
//          + "4Pdd4awmi4r6Yxbnh4KYXkGxgntnU1P0ecXQcRUHdgxy7Y/umReLnQd5vs62mJZd\n"
//          + "YpyD8LbshCdAGRY1XLTFpLSsDUznS0laVpZ5nIr7ROqnAfPKVt3Pkag3bHPTOwnv\n"
//          + "TlE0KoPceQpXeHJs/PYGytukO9Z1XxU+IlsnqyG0Ba+8adr6fJSEu8vxcokWRCix\n"
//          + "klxCqx53XCkHB1XYLgF8fhXIhrDZOrO2M901PCBvgoUYq1bjVZdSds6n5L0tkuy6\n"
//          + "oCbnFYZNVD7ok5+BQuxg2qh0oj0MYJOaD4bAdHIfMJAmQBl/WNYVJxMxPHsLqcUI\n"
//          + "OWTIx3bfG3BKeVbOT0T+6ePvkcgLiOg5dpx3xw7TsrOLsEARRGB3qSIUPn8+Hj27\n"
//          + "1HizRkCRUU/E8+dKhQtyKNW00oSEn0vq+yDOdY8odtkzutjdUJk5l89IHSqPkB7b\n"
//          + "UPS8t0T/kOxj0I1+vfYpqmg8SBx5MTwHWLUQQOmRVu4g6zUcF4Qw3qMt782tyffv\n"
//          + "IV6iM9tHocLkxd9I40xefC7HTuNaFg9eVaGRvNAsCtbB3ncrSplDwekfZnOkH0pJ\n"
//          + "f4V9tKIozy7p6ChxqQ1VHtQLrSZ9S9+XB5la54/47YxIE2LPwva6NvWLkjTZ3mv5\n"
//          + "KU8zGaVcPnhKE6oDmuQBBiL80kH4ebVqCd7tTMeKgnKtf8MYQu649ang6VxQ6k44\n"
//          + "T54upPNsaFlkJs/kA8lX6oHgXIKROiETwetYg9DUgwEVVgAndQ41xJ7Kx8KfiNpR\n"
//          + "saHf4B0KaaPQRatfcTaZEJBEj3AcQ0DUURnkvd2dRIKR+v4peaihNfW9R1B5Pexl\n"
//          + "BsckUTTHSl2KrcSFmS5Wet7BryNs/PLmhVhE0mEfSWa5VbMtv5Jb49g78aQbqLge\n"
//          + "iymztD/2q08nNmgq1Ksm1jkU30aIGvltnobajRNtkNS4C4TJDn9uGWc+fM01vWYE\n"
//          + "7898mPo5dYg+l0CzQwO05RrTmV2pXvRAmNkbt+SK6P/h9YnfF8uJbNBTVblsneOR\n"
//          + "wBBEZiHExjTPXHHVnSoI62Cziqg83o6q+76x9TpGUZfkew3FYecJElZrPJy8113P\n"
//          + "o6zyJkWiqbAywuVEJXL8J/SNfqm47iDwOHA062lknTI6KWmRCcF1+/6GBl9DB6al\n"
//          + "t25MDEnVAFWIqM2RKxUZEVRAXcwITZAb4sh2BLh4nAgaJdnDtkM+3qjtYJK4AuHE\n"
//          + "Ry4=";

  //生成秘钥对
  public static KeyPair getKeyPair() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    return keyPair;
  }

  //获取公钥(Base64编码)
  public static String getPublicKey(KeyPair keyPair) {
    PublicKey publicKey = keyPair.getPublic();
    byte[] bytes = publicKey.getEncoded();
    return byte2Base64(bytes);
  }

  //获取私钥(Base64编码)
  public static String getPrivateKey(KeyPair keyPair) {
    PrivateKey privateKey = keyPair.getPrivate();
    byte[] bytes = privateKey.getEncoded();
    return byte2Base64(bytes);
  }

  //将Base64编码后的公钥转换成PublicKey对象
  public static PublicKey string2PublicKey(String pubStr) throws Exception {
    byte[] keyBytes = base642Byte(pubStr);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(keySpec);
    return publicKey;
  }

  //将Base64编码后的私钥转换成PrivateKey对象
  public static PrivateKey string2PrivateKey(String priStr) throws Exception {
    byte[] keyBytes = base642Byte(priStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return privateKey;
  }

  //公钥加密
  public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] bytes = cipher.doFinal(content);
    return bytes;
  }

  //公钥加密
  public static byte[] encrypt(byte[] content) throws Exception {
    PublicKey publicKey = RSAUtil.string2PublicKey(PUBLICKEY);
    return publicEncrypt(content, publicKey);
  }

  //公钥加密
  public static String encrypt(String content) throws Exception {
    byte[] encrypt = encrypt(content.getBytes(StandardCharsets.UTF_8));
    return byte2Base64(encrypt);
  }

  //私钥解密
  public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] bytes = cipher.doFinal(content);
    return bytes;
  }

  //私钥解密
  public static byte[] decrypt(byte[] content) throws Exception {

    PrivateKey privateKey = RSAUtil.string2PrivateKey(PRIVATEKEY);
    return privateDecrypt(content, privateKey);
  }

  //私钥解密
  public static String decrypt(String content) throws Exception {
    byte[] decrypt = decrypt(base642Byte(content));
    return new String(decrypt, StandardCharsets.UTF_8);
  }

  //字节数组转Base64编码
  public static String byte2Base64(byte[] bytes) {
	BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(bytes);
  }

  //Base64编码转字节数组
  public static byte[] base642Byte(String base64Key) throws IOException {
    BASE64Decoder decoder = new BASE64Decoder();
    return decoder.decodeBuffer(base64Key);
  }

  public static void main(String[] args) throws Exception {
    KeyPair keyPair = getKeyPair();
    System.out.println(getPrivateKey(keyPair));
    System.out.println("--------------------");
    System.out.println(getPublicKey(keyPair));
    //System.out.println(generateAes());
  }

  /**
   * 生成128位 AES 密钥
   */
  public static String generateAes() {
    try {
      KeyGenerator kg = KeyGenerator.getInstance("AES");
      kg.init(128);
      SecretKey sk = kg.generateKey();
      byte[] b = sk.getEncoded();
      String s = byteToHexString(b);
      return s;
      //return b;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String byteToHexString(byte[] bytes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      String strHex = Integer.toHexString(bytes[i]);
      if (strHex.length() > 3) {
        sb.append(strHex.substring(6));
      } else {
        if (strHex.length() < 2) {
          sb.append("0" + strHex);
        } else {
          sb.append(strHex);
        }
      }
    }
    return sb.toString();
  }

  /**
   * AES 加密 传入加密的值以及规则 生成128位 AES 密钥
   */
  public static String AESEncode(String encodeRules, String content) {
    try {
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(encodeRules.getBytes());
      keygen.init(128, secureRandom);
      //keygen.init(128, new SecureRandom(encodeRules.getBytes()));
      SecretKey original_key = keygen.generateKey();
      byte[] raw = original_key.getEncoded();
      SecretKey key = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] byte_encode = content.getBytes("utf-8");
      byte[] byte_AES = cipher.doFinal(byte_encode);
      String AES_encode = new String(new BASE64Encoder().encode(byte_AES));
      return AES_encode;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * AES 解密 传入需要解密的值以及规则
   */
  public static String AESDncode(String encodeRules, String content) {
    try {
      KeyGenerator keygen = KeyGenerator.getInstance("AES");
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
      secureRandom.setSeed(encodeRules.getBytes());
      keygen.init(128, secureRandom);
      //keygen.init(128, new SecureRandom(encodeRules.getBytes()));
      SecretKey original_key = keygen.generateKey();
      byte[] raw = original_key.getEncoded();
      SecretKey key = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
      byte[] byte_decode = cipher.doFinal(byte_content);
      String AES_decode = new String(byte_decode, "utf-8");
      return AES_decode;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    return null;
  }

}
