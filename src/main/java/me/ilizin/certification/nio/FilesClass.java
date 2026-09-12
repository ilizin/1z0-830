package me.ilizin.certification.nio;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesClass {

    /* the java.nio.file.Files class, you will notice that it is a convenience class that offers a ton of methods and they
       let you do pretty much everything that you may want to do with files. In fact, all of its methods are static and
       they delegate to the associated file system provider to perform file operations. */
    public static void main(String[] args) throws IOException {

        System.out.println();
        /* The Files class has several overloaded methods that allow you to acquire binary as well as character streams
           from a file. Depending on which overloaded method you are using, they have one or two more parameters - a
           Charset, in case you are trying to acquire a character stream, and a varargs array of OpenOptions that
           determine the mode in which the file is to be opened.
           OpenOption is an interface and it does not define any value. The values are defined in the StandardOpenOption
           class, which implements OpenOption.
           For newInputStream, the default is READ. For newOutputStream, the default is the combination of WRITE, CREATE,
           and TRUNCATE_EXISTING. The newBufferedReader method does not have this parameter because the file is opened only
           for reading. For newBufferedWriter, the default is the combination of WRITE, CREATE, and TRUNCATE_EXISTING.
           The combination of WRITE, CREATE, and TRUNCATE_EXISTING means that the file is opened for writing, it will be
           created if it does not already exist, and it will be truncated (i.e. all of its contents will be deleted) if it
           exists. Furthermore, depending on whether you are opening a file for reading or writing, some OptionOption
           combinations may be illegal to use while acquiring the stream. For example, if you pass StandardOpenOption.WRITE
           to newInputStream, or StandardOpenOption.READ to newOutputStream an IllegalArgumentException will be thrown.*/
        Path path1 = Path.of("./FilesClass/test1.txt");
        InputStream is = Files.newInputStream(path1);
        is = Files.newInputStream(path1, StandardOpenOption.READ);
        Path path2 = Path.of("./FilesClass/test2.txt");
        OutputStream os = Files.newOutputStream(path2);
        os = Files.newOutputStream(path2, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        BufferedReader bfr = Files.newBufferedReader(path1);
        bfr = Files.newBufferedReader(path1, Charset.forName("UTF-8"));
        BufferedWriter bfw = Files.newBufferedWriter(path2);
        bfw = Files.newBufferedWriter(path2, Charset.forName("UTF-8"));
        bfw = Files.newBufferedWriter(path2, StandardOpenOption.WRITE);
        bfw = Files.newBufferedWriter(path2, Charset.forName("UTF-8"), StandardOpenOption.CREATE);

        System.out.println();
        /* If you feel that using streams to read or write small files is too much work, you will love the following methods:
           Not only are the above methods convenient to use, they also close the underlying resource automatically after the
           reading/writing is complete without the need for wrapping them into a try-with-resource statement. The only catch is
           that if you try them on a large file, you may run out of memory.*/
        /* Reading and writing bytes */
        byte[] allBytes = Files.readAllBytes(path1);
        Files.write(path1, allBytes); // OpenOptions may also be passed as the third argument
        /* Reading and writing text */
        String data = Files.readString(path1);
        List<String> allLines = Files.readAllLines(path1);
        //Charset may also be passed as the second argument in both of the above
        Files.write(path2, data);
        Files.write(path2, allLines); //Charset and OpenOptions may also be passed as the second and the third arguments

        /* You can read the list of files in a directory using the list method: */
        try(Stream<Path> streamOfPaths = Files.list(path1) ){
            streamOfPaths.forEach(System.out::println);
        }

        /* If you want to recursively traverse through a directory tree in depth first manner, there are a couple of walk
           methods that return a lazily populated Stream of Paths. The above code lists all the directories that lie under c:\
           directory at any depth whose names start with a. */
        Stream<Path> sp = Files.walk(Path.of("c:\\"), FileVisitOption.FOLLOW_LINKS);
        List<Path> files  = sp.filter(p -> Files.isDirectory(p) && p.getName(p.getNameCount()-1)
                        .toString().startsWith("a"))
                        .collect(Collectors.toList());
        System.out.println(files);

        /* There are a few methods in Files that can be used to check the kind of file a Path refers to, namely,
           isRegularFile, isDirectory, isHidden, isReadable, isWritable, and isExecutable. All of them take a Path
           (and a vararg of type LinkOption in some cases) and return a boolean. You may also check the existence of a
           file using the exists method. Besides the above, there are readAttributes, getAttribute and getFileAttributeView
           methods to read file attributes. Different file systems support different file attributes but all of them
           support a few basic attributes that are common to all file systems. These are represented by the BasicFileAttributes
           interface. Others such as DosFileAttributes and PosixFileAttributes extend BasicFileAttributes.  */
        Path myFile = Path.of("c:\\temp\\test.txt");
        BasicFileAttributes attr = Files.readAttributes(myFile, BasicFileAttributes.class);
        FileTime fileTime = attr.creationTime();
        System.out.println("File creation time: " + fileTime);
    }
}
