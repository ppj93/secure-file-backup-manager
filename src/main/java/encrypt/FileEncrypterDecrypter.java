package encrypt;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.Key;

public class FileEncrypterDecrypter {

    private final Key secretKey;
    private String transformation;

    public FileEncrypterDecrypter(Key secretKey, String transformation) {
            this.secretKey = secretKey;
        this.transformation = transformation;
    }

    public void encryptFileAndWriteToAnotherFile(String inputFileName, String outputFileName) throws Exception {
      var fileAsBytes = getFileAsBytes(inputFileName);
      var encryptedBytes = encryptBytes(fileAsBytes);
      saveFile(encryptedBytes, outputFileName);
    }


    public void decryptAndWriteToFile(String encryptedFilePath, String decryptedFilePath) throws Exception {
        var encryptedBytes = getFileAsBytes(encryptedFilePath);
        var decryptedBytes = decryptBytes(encryptedBytes);
        saveFile(decryptedBytes, decryptedFilePath);
    }

    private byte[] getFileAsBytes(String path) throws Exception{
        File f = new File(path);
        InputStream is = new FileInputStream(f);
        byte[] content = new byte[is.available()];
        is.read(content);
        return content;
    }

    private byte[] encryptBytes(byte[] content) throws Exception{
        byte[] encrypted = getNewCipher(Cipher.ENCRYPT_MODE).doFinal(content);
        return encrypted;
    }

    private Cipher getNewCipher(int mode) throws Exception {
        var cipher = Cipher.getInstance(transformation);
        cipher.init(mode, secretKey, new IvParameterSpec(new byte[16]));
        return cipher;
    }

    private byte[] decryptBytes(byte[] textCryp) throws Exception{
        byte[] decrypted = getNewCipher(Cipher.DECRYPT_MODE).doFinal(textCryp);
        return decrypted;
    }

    private void saveFile(byte[] bytes, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.close();
    }


}
