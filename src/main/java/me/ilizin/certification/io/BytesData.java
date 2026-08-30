package me.ilizin.certification.io;

import java.io.*;

public class BytesData {

    /*  There are several specialized exception classes such as FileNotFoundException or EOFException but all of them
        are rooted under the checked exception class java.io.IOException
        FileInputStream/FileOutputStream throws file with the specified pathname does not exist. It will also be
        thrown by these constructors if the file does exist but for some reason is inaccessible, for example
        when an attempt is made to open a read-only file for writing

        For a long time, Java had only the classes in java.io package to perform I/O related tasks. However,
        these classes implement what is known as "blocking" I/O operations.
        If you try to read data from a remote file or from a network socket, the thread on which the read method
        is being executed may be blocked for a long time and will not be available to perform other tasks.
        Although this approach is fine for many applications, it doesn't scale well. To overcome this limitation,
        Java introduced a non-blocking I/O (aka NIO) library in Java 1.4 and updated it in Java 7 (aka NIO2).
        These classes are packaged in the java.nio package.*/
    public static void main(String[] args) throws IOException {

        /* The concept of raw input stream and raw output stream is represented by abstract classes InputStream and
            OutputStream respectively.
            Since the source and destination of our input and output streams are files, we are using FileInputStream
            and FileOutputStream classes provided by the Java I/O library to read from and write to the files

            InputStream is the root abstract class for all byte based input streams.
            Important methods: int available(); int read(); int read(byte[] b); int read(byte[] b, int off, int len);
                               byte[] readAllBytes(); void reset(); long skip(long n); void mark(int readlimit);
                               boolean markSupported(); void close()

            FileInputStream extends InputStream, it's used to read bytes from a file.
            Important constructors: FileInputStream(String name); FileInputStream(File f) */
        InputStream fis = new FileInputStream("C:\\ilio\\repos\\1z0-830\\target\\classes\\test.jpg");
        /* Although the read() method reads a byte from the input stream, the return type of this method is int.
           Only the lower order 8 bits of the returned integer contain the actual byte read from the input stream.
           To get that value, we need to cast the returned value to byte. */
        int bite = fis.read();
        /* We keep reading from the input stream until we get a -1. A -1 indicates that the stream has ended and
           there is no more data available to read from the input stream. */
        while (bite != -1) {
            byte data = (byte)bite;
            System.out.print(data);
            bite = fis.read();
        }
        /* Finally, we use the close() method to close the input and output streams. Closing I/O streams is important
           because it allows any system resources associated with the streams such as file locks to be reclaimed by
           the OS. */
        fis.close();
        /* The OutputStream is the root class for all byte based output streams.
           Important methods: void write(byte[] b); void write(byte[] b, int off, int len); void write(int b);
           void flush(); void close()

           FileOutputStream extends OutputStream is used to write bytes to a file. Higher level streams do not care about
                                                 where the data actually comes from or goes to. It is the job of the lower
                                                 level streams such as FileInputStream or FileOutputStream
           Important constructors: FileOutputStream(String name); FileOutputStream(File f);
                                   FileOutputStream(String name, boolean append); FileOutputStream(File f, boolean append) */
        /* Avoid closing the stream by using try-with-resources */
        try ( OutputStream fos = new FileOutputStream("test2.dat") ) {
            byte[] imageData = new byte[1000];
            for (byte b : imageData) {
                fos.write(b);
            }
        }

        /* InputStream and OutputStream have a few methods that help you perform I/O operations in bulk */
        transfer(new FileInputStream("C:\\ilio\\repos\\1z0-830\\target\\classes\\test.jpg"), new FileOutputStream("test2.jpg"));
    }

    private static void transfer(InputStream is, OutputStream os) throws IOException {
        byte[] chunk = new byte[1024];
        int bytesRead = -1;
        /* The read method populates the byte array with the bytes read from the input stream and returns the number
           of bytes that it actually read. It overwrites the byte array with the newly read bytes starting from
           index 0. */
        while( (bytesRead = is.read(chunk)) != -1 ) {
            /* The available() method of FileInputStream class is used to return the estimated number of remaining
               bytes that can be read from the input stream without blocking. This method returns the number of bytes
               remaining to read from the file. When a file is completely read, this function returns zero. */
            System.out.print(is.available());
            /* To write the bytes to the output stream, we are using OutputStream's
            write(byte[] b, int offset, int len) method, which writes len number of bytes from the given byte
            array starting from the index given by the offset parameter */
            os.write(chunk, 0, bytesRead);
        };
    }

    /* It is a lower level stream and it does not take an input or an output stream as an argument during instantiation.
       For example, FileInputStream, and ByteArrayInputStream reveal their data sources (a file and a byte array respectively)
       and are therefore, lower level streams. On the other hand, ObjectInputStream, BufferedInputStream, and
       DataInputStream reveal the type of the data that they deal with (Object, byte buffer, and primitive data, respectively)
       instead of the source or the sink of the data and are therefore, higher level streams.

       If the name of an I/O stream class reveals an actual data source or sink, it is a lower level stream and it does not
       take an input or an output stream as an argument during instantiation. For example, FileInputStream, and
       ByteArrayInputStream reveal their data sources (a file and a byte array respectively) and are therefore,
       lower level streams. On the other hand, ObjectInputStream, BufferedInputStream, and DataInputStream reveal the
       type of the data that they deal with (Object, byte buffer, and primitive data, respectively) instead of the
       source or the sink of the data and are therefore, higher level streams.
       */

    /*


        class BufferedInputStream : Adds functionality to another input stream-namely, the ability to buffer the input and
                                    to support the mark and reset methods.
              BufferedInputStream(InputStream is)
              BufferedInputStream(InputStream is, int size)

        class BufferedOutputStream : Used to write bytes to the underlying output stream without necessarily causing a call
                                     to the underlying system for each byte written. BufferedInputStream (and its corresponding BufferedOutputStream)
                                     do not interact with the data source. They take an existing input stream (or an existing output stream)
                                     and build additional functionality on top of those streams. That is why such streams are called "higher-level" streams.
              BufferedOutputStream(OutputStream os)
              BufferedOutputStream(OutputStream os, int size)
        */

}
