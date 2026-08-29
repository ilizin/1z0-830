package me.ilizin.certification.io;

import java.io.File;
import java.io.IOException;

public class FileIo {

    /* In the Java world, however, there is a single abstraction called "file" for files as well as directories. A file
       could be a regular file or it could be a directory (aka a folder).

       Another important class of the java.io package is the File class. It is an immutable class whose objects represent
       a file or a directory path name. The purpose of this class is not to operate on the data inside the file but to
       work with the file itself in a platform independent manner.

       An operating system uses a pathname to identify a file, while Java uses an abstract pathname for the same. The
       JVM converts an abstract pathname to the real pathname and vice-versa as and when required. The conversion logic
       depends on the operating system. An abstract pathname is a series of zero or more file names separated by a system
       specific separator character. On Windows the separator character is back slash (\), while on Linux and MacOS, it
       is forward slash (/). It is not necessary for an abstract pathname to refer to an actual file in the file system,
       but if you want to be able to convert it to a real pathname, every component except the last one of an abstract
       pathname must correspond to a directory. */
    public static void main(String[] args) throws IOException {
        /* The following code illustrates how to create, rename, and delete the actual file in the file system using
           methods of the File class */

        /* Observe that a File object representing any file can be created irrespective of whether the file actually
           exists or not in the file system.
           The File class does not have methods to copy and movea file. The renameTo method can be used to move the file but
           it may fail if the destination is on a different file system or if the destination already exists.
           The java.nio.file.Files class provides a more comprehensive way to do these operations.
           The file class has been in existence since Java 1.0 */
        File f1 = new File("c:\\temp\\test1.txt");
        /* Return true if it was able to create an empty file in the file system. It could fail (and return false) if the file already
           exists or if there is a security restriction. */
        boolean successful = f1.createNewFile();
        System.out.println(successful);
        File f2 = new File("c:\\temp\\test2.txt");
        System.out.println(f1.renameTo(f2)); // prints true if test1.txt was renamed to test2.txt.
        System.out.println(f2.delete()); // prints true if test2.txt was deleted.

        /* You may use a File object to list the contents of a directory if the File object corresponds to a directory in
           the file system.

           To access a file in a hierarchical file system, we need to know not just the name of the file but also the
           names of its containing folders, all the way up to the root. So, if a pathname includes all the names starting
           from the root down to the file that you want to access, it is an absolute path. For example, c:\a\b\c\test.txt
           is an absolute path because we can start from the root c:\ and step inside the folder named a, then in b, and c
           to reach the test.txt file. Similarly, on Linux, since the root is denoted by /, /a/b/c/test.txt would be an
           absolute path.

           A relative path, on the other hand, is not sufficient in itself to identify a file in a file system. It can
           only identify a file when used in relation to another path whose location is already known. Any path that
           does not start with a root is a relative path. */
        File f = new File("c:\\temp");
        if(f.isDirectory()){
            String[] fileNames = f.list();
            for(String fn : fileNames){
                System.out.println(fn);
            }
        }

        /* A canonical path is an absolute path that doesn't contain any redundant path fragments. For example, c:\temp\a\..\test.txt
           is an absolute path but if you follow this path, you will go from c:to temp, then from temp to a, then back
           to temp, and finally to test.txt. Some operating systems allow you to create symbolic links to other files in
           the file system. A canonical path resolves such links as well, meaning, it shows the real path instead of the
           symbolic link. */
        /* Uses a relative path while creating the File object */
        f = new File(".");
        /* Get the absolute path */
        String ap = f.getAbsolutePath();
        /* Get the canonical path */
        String cp = f.getCanonicalPath();
        /* It prints C:\temp\. C:\temp
           The canonical path does not contain a dot at the end because the dot is redundant */
        System.out.println(ap + " " + cp);

        /* The File class lets you enquire about its properties of the file through methods such as isDirectory(),
           isFile(), isHidden(), and lastModified(). */
        f = new File(".");
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        System.out.println(f.isHidden());
        System.out.println(f.lastModified());

        /* In the previous code examples, I used Windows specific paths to files such as "c:\\temp\\test1.txt"
            and "c:\\temp". These path strings won't work on a Linux machine because Linux uses forward slash (/) as
            the separator character. Furthermore, the root of a file system in Linux is just /.
            The File class has two public static fields for this purpose:

            public static char separatorChar: It is the system-dependent default name-separator character.
                                              On Windows it is back slash ('\\') and on Linux, it is forward slash ('/').
            public static String separator: A String version of separatorChar for convenience. It contains the same character
                                            as separatorChar.

            So, if I write the path string "c:\\temp\\test.txt" as File.separator + "temp" + File.separator + "test.txt",
            it will work on Windows as well as on Linux because it will be translated to \temp\test.txt if the program
            is run on a Windows machine and to /temp/test.txt if it is run on a Linux machine. Yes, the translation on
            Windows is missing the drive letter c:, but unless you want to target a file on a different drive than the
            one from which your program is executed, a drive letter is not required

            Thus, on a Windows machine, if your path starts with a \ and your current directory is on the C drive, then
            \temp\test.txt will refer to c:\temp\test.txt.

            The File class has two more public static fields for separating paths in a platform independent manner.
            These are:

            public static char pathSeparatorChar: It is the system-dependent path-separator character. On windows, it is ;
                                                  and on Linux, it is :.
            public static String pathSeparator: A String version of pathSeparatorChar for convenience. It contains the
                                                same character as pathSeparatorChar.
            */
    }
}
