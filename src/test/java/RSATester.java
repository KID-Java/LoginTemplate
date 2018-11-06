public class RSATester {

//    static String publicKey;
//    static String privateKey;
//
//    static {
//        try {
//            Map<String, Object> keyMap = RSAUtils.genKeyPair();
////            publicKey = RSAUtils.getPublicKey(keyMap);
//            publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI/762w8Dttofmsqylc+zrEoWqjpmf3ZnQA7jiVV5LlHV12qWk/HJJhzoebDCyxVSlNtxYKyT7gde96QpIt4QPihdR25hXkQxxAuWPkGtMnq9vsnaPO8H2OINujsIlHF0QOj7SWYzOfvO6PFHpJO0RkMs+Tqua4PhCzjwI9P+lzQIDAQAB";
////            privateKey = RSAUtils.getPrivateKey(keyMap);
//            privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIj/vrbDwO22h+ayrKVz7OsShaqOmZ/dmdADuOJVXkuUdXXapaT8ckmHOh5sMLLFVKU23FgrJPuB173pCki3hA+KF1HbmFeRDHEC5Y+Qa0yer2+ydo87wfY4g26OwiUcXRA6PtJZjM5+87o8Uekk7RGQyz5Oq5rg+ELOPAj0/6XNAgMBAAECgYAY1gxUA87xCVs/1np4EDkOMLBsyCdPlRPyYwpcdc9/ci2NLRNEgsORUuOValW1wxJiVTzC+ydcNSDQe/PyVNqq07JKlh5L6TD9MmStLyZCtukZyPbPuZYwwvBJVNk62CX4bD8TVnIAT6QkONQzeKOFT6Yo9bPdek6m595NwHpZnQJBAPraJiX9/W+VCpEBa3lAiHNz3zONTHR3FPEzoPBETRXmFyu5ZruJhoPI4f/qUwHQqNxHQbIBEIN0FCkoux7P7wsCQQCLz3gFIq3qoaU/BXu51l6dOGdRaZ+CG1yAzFFKOw4Dj1SKZ93psz1CJ64iPMmjUNsCI6iU5DfLH4gTQyWG5CWHAkBR8DXrzQYej4FvEhan+ZNYmu26ahoiJM+bZfDY6oZ/c7P33UyYT3i3zUM9E65fdmfh5AiqezyIo77+HsbYbrU3AkBQ59V3e1MTdUlwB9dKeoA0ESDQ3wOcQgMs0Y+1GimI69erZGHpzHyFnPrK+0xgQvkJ6uKyjsKuu+d95hyAS+RfAkATSmu5EJKbZEnBdlWSpPP2YTJZk0RW4vmECMd6eAmIsgiEVGimS5MIIDDhDv0GjXWLD9E+VqMPstVoF0oJaxzy";
//            System.err.println("公钥: \n\r" + publicKey);
//            System.err.println("私钥： \n\r" + privateKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        String pass = "QmlzrNS5r7yDpb4RhXJLQKPyIQRuvQoBzyNkdLywVd4rh+k4p/KsEjiiNvZmHPfrIhHqog3aIQ27A1QvuVlHEljNF2S/bG2MjlFRWjTwGLsq6cj+cYO89eHKW8vGJdUqWsgBpYkgBiafc0FEBZix39IZ2S3ySq/sJmo+O8ybYJ0=";
//        pass = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(pass), privateKey));
//        System.out.println(pass);
//
////        test();
//        //testSign();
//        //testHttpSign();
//    }
//
//    static void test() throws Exception {
//
//        System.err.println("公钥加密——私钥解密");
//        String source = "sslgo123456";
//        System.out.println("\r加密前文字：\r\n" + source);
//        byte[] data = source.getBytes();
//        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
////        System.out.println("加密后文字：\r\n" + new String(encodedData));
//        System.out.println("加密后文字：\r\n" + Base64Utils.encode(encodedData));
//        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
//        String target = new String(decodedData);
//        System.out.println("解密后文字: \r\n" + target);
//    }
//
//    static void testSign() throws Exception {
//        System.err.println("私钥加密——公钥解密");
//        String source = "这是一行测试RSA数字签名的无意义文字";
//        System.out.println("原文字：\r\n" + source);
//        byte[] data = source.getBytes();
//        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
//        System.out.println("加密后：\r\n" + new String(encodedData));
//        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
//        String target = new String(decodedData);
//        System.out.println("解密后: \r\n" + target);
//        System.err.println("私钥签名——公钥验证签名");
//        String sign = RSAUtils.sign(encodedData, privateKey);
//        System.err.println("签名:\r" + sign);
//        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
//        System.err.println("验证结果:\r" + status);
//    }
//
//    static void testHttpSign() throws Exception {
//        String param = "id=1&name=张三";
//        byte[] encodedData = RSAUtils.encryptByPrivateKey(param.getBytes(), privateKey);
//        System.out.println("加密后：" + encodedData);
//
//        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
//        System.out.println("解密后：" + new String(decodedData));
//
//        String sign = RSAUtils.sign(encodedData, privateKey);
//        System.err.println("签名：" + sign);
//
//        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
//        System.err.println("签名验证结果：" + status);
//    }


}