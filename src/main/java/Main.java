import encrypt.EncryptionUtils;
import encrypt.FileEncrypterDecrypter;
import input.InputParameterReader;
import manager.BackupManager;
import zip.Zipper;

import javax.crypto.KeyGenerator;
import java.security.Key;

public class Main {
    public static void main(String[] args) throws Exception {
        var inputReader = new InputParameterReader();
        InputParameterReader.loadProperties();
        var secretKey = EncryptionUtils.getKeyFromPassword(inputReader.getValue("encryptionPassword"));
        var fileEncrypter = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        var zipper = new Zipper();
        new BackupManager(inputReader, zipper, fileEncrypter).start();
    }
}
