package backups;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Backups {

    /**
     * Создание списка путей ко всем дочерним элементам исходной директории,
     * включая исходную директорию.
     * @param sourceDir исходная директория
     * @return List<Path>
     */
    private static List<Path> createListOfFiles(String sourceDir) {
        List<Path> list = new ArrayList<>();
        try(Stream<Path> stream = Files.walk(Path.of(sourceDir))){
            list = stream.collect(Collectors.toList());
        }
        catch (IOException ex){
            System.out.printf("Директории %s не существует.\n", sourceDir);
        }
        return list;
    }

    /**
     * Создание списка путей для директории назначения.
     * @param sourceDir исходная директория
     * @param destDir директория назначения
     * @return List<Path>
     */
    private static List<Path> replaceParentPath(String sourceDir, String destDir) {
        List<Path> destList = new ArrayList<>();
        List<Path> sourceList = createListOfFiles(sourceDir);
        sourceList.forEach(p -> destList.add(Path.of(destDir).resolve(Path.of(sourceDir).relativize(p))));
        return destList;
    }

    /**
     * Создание копии исходной директории
     * @param sourceDir исходная директория
     * @param destDir директория назначения
     */
    public static void createBackups(String sourceDir, String destDir) {
        try {
            List<Path> sourceList = createListOfFiles(sourceDir);
            List<Path> destList = replaceParentPath(sourceDir, destDir);
            for(int i = 0; i < sourceList.size(); i++){
                Files.copy(sourceList.get(i), destList.get(i));
            }
            System.out.printf("Директория %s успешно скопирована в %s\n", sourceDir, destDir);
        }
        catch (IOException ex){
            System.out.printf("Директории %s не существует.\n", sourceDir);
        }
    }
}
