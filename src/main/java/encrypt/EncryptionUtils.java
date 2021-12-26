package encrypt;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

public class EncryptionUtils {
    public static Key getKeyFromPassword(String password)
            throws Exception {

        var bytes = password.getBytes(StandardCharsets.UTF_8);

        if(bytes.length != 16) {
            throw new RuntimeException("pwd byte length not 16");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(bytes, "AES");
        return skeySpec;
    }
}
