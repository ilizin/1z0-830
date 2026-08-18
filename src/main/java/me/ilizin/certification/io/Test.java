package me.ilizin.certification.io;

import java.io.*;

public class Test {

    /* There are several specialized exception classes such as FileNotFoundException or EOFException but all of them
        are rooted under the checked exception class java.io.IOException
        FileInputStream/FileOutputStream throws file with the specified pathname does not exist. It will also be
        thrown by these constructors if the file does exist but for some reason is inaccessible, for example
        when an attempt is made to open a read-only file for writing */
    public static void main(String[] args) throws IOException {

        /* The concept of raw input stream and raw output stream is represented by abstract classes InputStream and
            OutputStream respectively.
            Since the source and destination of our input and output streams are files, we are using FileInputStream
            and FileOutputStream classes provided by the Java I/O library to read from and write to the files */
        InputStream fis = new FileInputStream("c:\\temp\\test1.png");
        /* Although the read() method reads a byte from the input stream, the return type of this method is int.
           Only the lower order 8 bits of the returned integer contain the actual byte read from the input stream.
           To get that value, we need to cast the returned value to byte. */
        int bite = fis.read();
        /* We keep reading from the input stream until we get a -1. A -1 indicates that the stream has ended and
           there is no more data available to read from the input stream. */
        while (bite != -1) {
            byte data = (byte) bite;
            //do something with the byte just read
            System.out.print(data);
            bite = fis.read();
        }
        /* Finally, we use the close() method to close the input and output streams. Closing I/O streams is important
           because it allows any system resources associated with the streams such as file locks to be reclaimed by
           the OS. */
        fis.close(); //can be avoided by using try-with-resources

        try ( OutputStream fos = new FileOutputStream("c:\\temp\\test2.png") ) {
            byte[] imageData = new byte[1000];
            for (byte b : imageData) {
                fos.write(b);
            }
        }
    }

    /* InputStream and OutputStream have a few methods that help you perform I/O operations in bulk */
    public static void transfer(InputStream is, OutputStream os) throws IOException {
        byte[] chunk = new byte[1024];
        int bytesRead = -1;
        /* The read method populates the byte array with the bytes read from the input stream and returns the number
           of bytes that it actually read. It overwrites the byte array with the newly read bytes starting from
           index 0. */
        while( (bytesRead = is.read(chunk)) != -1 ) {
            /* To write the bytes to the output stream, we are using OutputStream's
            write(byte[] b, int offset, int len) method, which writes len number of bytes from the given byte
            array starting from the index given by the offset parameter */
            os.write(chunk, 0, bytesRead);
        };
    }
}
