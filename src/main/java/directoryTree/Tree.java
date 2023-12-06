package directoryTree;

import java.io.File;

public class Tree {

    /**
     * Псевдографическое отображение директории и её дочерних элементов,
     * (рекурсивный обход директории)
     * @param file исходная директория
     * @param indent строка - вставка
     * @param isLast флаг (является ли элемент последним на данном уровне)
     */
    public static void printTree(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print(ConsoleColors.YELLOW + "└─");
            indent += "  ";
        }
        else {
            System.out.print(ConsoleColors.YELLOW + "├─");
            indent += ConsoleColors.YELLOW + "│ ";
        }
        // печать директорий голубым цветом
        if(file.isDirectory()) System.out.println(ConsoleColors.BLUE + file.getName());
        // печать файлов зелёным цветом
        if(file.isFile() && !file.isHidden()) System.out.println(ConsoleColors.GREEN + file.getName());
        // печать скрытых файлов красным цветом
        if(file.isFile() && file.isHidden()) System.out.println(ConsoleColors.RED + file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            subDirTotal++;
        }

        int subDirCounter = 0;
        for (File newFile : files) {
            printTree(newFile, indent, subDirTotal == ++subDirCounter);
        }
        // восстановление цвета
        System.out.print(ConsoleColors.RESET);
    }
}
