import input.InputReader;
import manager.BackupManager;
import zip.Zipper;

public class Main {
    public static void main(String[] args) {
        var inputReader = new InputReader();
        InputReader.loadProperties();
        var zipper = new Zipper();
        new BackupManager(inputReader, zipper).start();
    }
}
