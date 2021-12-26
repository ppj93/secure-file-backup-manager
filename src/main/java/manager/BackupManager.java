package manager;

import encrypt.FileEncrypterDecrypter;
import input.InputParameterReader;
import org.apache.commons.lang3.StringUtils;
import zip.Zipper;

import java.io.File;

public class BackupManager {
    private final InputParameterReader inputParameterReader;
    private final Zipper zipper;
    private final FileEncrypterDecrypter fileEncrypterDecrypter;

    public BackupManager(InputParameterReader inputParameterReader, Zipper zipper, FileEncrypterDecrypter fileEncrypterDecrypter) {
        this.inputParameterReader = inputParameterReader;
        this.zipper = zipper;
        this.fileEncrypterDecrypter = fileEncrypterDecrypter;
    }

    public void start(){
        var sourceDir = inputParameterReader.getValue("inputDirectories");
        var outputDir = inputParameterReader.getValue("outputDirectory");
        var pwd = inputParameterReader.getValue("encryptionPassword");

        sourceDir = StringUtils.stripEnd(sourceDir, "\\");
        outputDir = StringUtils.stripEnd(outputDir, "\\");

        var zipFilePath = outputDir+"\\"+new File(sourceDir).getName()+".zip";
        try {
            //zipper.pack(sourceDir, zipFilePath);
            var encryptedFilePath = outputDir+"\\encrypted\\"+new File(sourceDir).getName()+".enc";
            var decryptedFilePath = outputDir+"\\decrypted\\"+new File(sourceDir).getName()+".zip";

            //Files.createFile(Paths.get(encryptedFilePath));
            fileEncrypterDecrypter.encryptFileAndWriteToAnotherFile(zipFilePath, encryptedFilePath);
            fileEncrypterDecrypter.decryptAndWriteToFile(encryptedFilePath, decryptedFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
