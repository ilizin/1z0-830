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
        /* The getFileName method returns the farthest (i.e. the last) element of the path, which is files in this case. */
        System.out.println(currentDirPath.getFileName());
        /* The returned Path object has the name elements that begin at beginIndex and extend to the element at index endIndex-1.
           Note that it returns a relative path, which means, the returned path will never start with the root. */
        System.out.println(currentDirPath.subpath(1, 2));
        /* The getRoot method returns the root if it is invoked on an absolute path. Since ./files is not an absolute path,
           getRoot returns null here. */
        System.out.println(currentDirPath.getRoot());
        /* The toAbsolutePath() method returns the full path of the given relative path starting from the default root.
           In this case, the root is C:\ and so, it returns C:\temp\.\files. Observe that this path contains a ., which
           is redundant. This shows that the toAbsolutePath method does not return the canonical version of the path. */
        Path absPath = currentDirPath.toAbsolutePath();
        System.out.println(absPath); // C:\temp\.\files
        /* To get that, we need to call normalize(). The normalize() method is practically the same as the getCanonicalPath
           method of the java.io.File class (except the fact that normalize() returns a Path object instead of a String). */
        Path normalizedCurrentDirPath = absPath.normalize();
        System.out.println(normalizedCurrentDirPath); // C:\temp\files
        /* The toRealPath method returns the canonical path for the given path and also checks if the file (or the directory)
           referred to by the path actually exists. If the file does not exist, it throws an IOException. Thus, in this example,
           if the files directory exists, it will return C:\temp\file sand if it does not, it will throw an IOException. */
        System.out.print(normalizedCurrentDirPath.toRealPath()); // java.nio.file.NoSuchFileException

        /* A well formed paths do not end with a trailing separator character (unless it is referring to the root such as C:\ or /).
           So, even if you try to create a Path with a trailing slash, it will be ignored. For example:
           System.out.print(Path.of("/a/b/")); will print /a/band not /a/b/. */


        /* If the argument to the resolve method is a relative path, the resolve method assumes that the given path is
           relative to the path on which this method is called. Therefore, it just joins the two paths to create the actual
           path to the file. In this case, since base Path is c:\temp, basePath.resolve("props\values.properties");
           returns c:\temp\props\values.properties. If the argument is an absolute path, there is nothing to resolve and
           it returns the same path as the argument. */
        Path basePath = Path.of(System.getProperty("basepath"));
        Path propFilePath = basePath.resolve("props/values.properties");
        System.out.println(propFilePath);
        /* The resolveSibling method works similarly. The only difference is, it assumes that the path given as an
           argument is supposed to exist at the same level as the path on which this method is invoked. Therefore,
           propFilePath.resolveSibling("dbconnection.properties") returns c:\temp\props\dbconnection.properties.
           Had we called propFilePath.resolve("dbconnection.properties"), it would have returned
           c:\temp\props\values.properties\dbconnection.properties. */
        Path dbPath = propFilePath.resolveSibling("dbconnection.properties");
        System.out.println(dbPath);

        /* The relativize method is the inverse of resolve. It finds a path to the given file relative to the path on which
           it is invoked. In other words, it tells you the path that you need to take to reach the given path from the
           path on which it is called. Continuing the previous example, given that base Path is c:\temp and that
           propFilePath is c:\temp\props\values.properties, basePath.relativize(propFilePath) will return
           props/values.properties. */
        basePath = Path.of(System.getProperty("basepath"));
        propFilePath = basePath.resolve("props/values.properties");
        System.out.println(basePath.relativize(propFilePath));

    }
}
