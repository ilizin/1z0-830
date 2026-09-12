package me.ilizin.certification.nio;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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
    }
}
