package manager;

import input.InputReader;
import org.apache.commons.lang3.StringUtils;
import zip.Zipper;

import java.io.File;

public class BackupManager {
    private InputReader inputReader;
    private Zipper zipper;

    public BackupManager(InputReader inputReader, Zipper zipper) {
        this.inputReader = inputReader;
        this.zipper = zipper;
    }

    public void start(){
        var sourceDir = inputReader.getValue("inputDirectories");
        var outputDir = inputReader.getValue("outputDirectory");
        var pwd = inputReader.getValue("encryptionPassword");

        sourceDir = StringUtils.stripEnd(sourceDir, "\\");
        outputDir = StringUtils.stripEnd(outputDir, "\\");

        try {
            zipper.pack(sourceDir, outputDir+"\\"+new File(sourceDir).getName()+".zip");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
