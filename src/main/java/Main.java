import backups.Backups;
import directoryTree.Tree;

import java.io.File;

public class Main {
    public static void main(String[] args){
        String sourceDir = ".\\src\\main\\resources\\test";
        String destDir = ".\\src\\main\\resources\\backups";
        Backups.createBackups(sourceDir, destDir);
        File file = new File(sourceDir);
        Tree.printTree(file, "", true);
        System.out.println("***********");
    }
}
