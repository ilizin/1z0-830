package me.ilizin.certification.nio;

import java.io.IOException;
import java.nio.file.Path;

public class PathClass {

    /* The java.nio.file package provides a more comprehensive and platform independent access to the file system than
       the java.io.File class. It does so, by formally abstracting the concepts of file system, path, and file attributes
       into a large set of interfaces and classes. Some of the things that you can do using this package that you cannot
       do using the File class are - open files in ten different read/write modes, copy files and directories, refer to
       files in a different file systems, watch directories for changes at run time, */
    public static void main(String[] args) throws IOException {

        System.out.println();
        /* The of method provides a way to convert a String into a Path object. Thus, ./files is a relative path because
           it doesn't start with a root component. It will be interpreted as being relative to the current directory,
           which is C:\temp in this case. */
        Path currentDirPath = Path.of("./files");
        System.out.println(currentDirPath.getFileName()); // files
        System.out.println(currentDirPath.subpath(1, 2)); // files
        System.out.println(currentDirPath.getRoot()); // null
        Path absPath = currentDirPath.toAbsolutePath();
        System.out.println(absPath); // C:\temp\.\files
        Path normalizedCurrentDirPath = absPath.normalize();
        System.out.println(normalizedCurrentDirPath); // C:\temp\files
        System.out.print(normalizedCurrentDirPath.toRealPath()); // java.nio.file.NoSuchFileException
    }
}
