package me.ilizin.certification.io;

import java.io.*;
import java.nio.charset.Charset;

public class CharacterData {

    public static void main(String[] args) throws IOException {

        /* Java provides InputStreamReader and OutputStreamWriter concrete classes that perform the conversion
           as per the given character encoding. An InputStreamReader reads raw bytes from an InputStream and converts
           them into characters. Similarly, an OutputStreamWriter converts a stream of characters to a stream
           of bytes and pipes the resulting stream of bytes to an Output Stream as shown in the following code: */

        /* We are using FileInputStream and FileOutputStream to get raw input and output streams from and to a file.
           We are then using them to create InputStreamReader nd OutputStreamWriter instances so that we can read/write
           characters instead of bytes from/to the file. */
        FileOutputStream fos = new FileOutputStream("c:\\temp\\test.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        char[] charData = "hello".toCharArray();
        for (char c : charData) {
            osw.write(c);
        }
        osw.close();
        FileInputStream fis = new FileInputStream("c:\\temp\\test.txt");
        /* We are using UTF-8as the character encoding while writing and reading bytes to and from the file. */
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        int data = isr.read();
        while (data != -1) {
            char letter = (char) data;
            System.out.print(letter);
            data = isr.read();
        }
        /* Calling close() on InputStreamReader or OutputStreamWriter also closes the underlying FileInputStream and
           FileOutputStream instances. */
        isr.close();

        /* we can read and write lines of text instead of single characters by wrapping an InputStreamReader into a
           BufferedReader and an OutputStreamWriter into a BufferedWriter as follows:*/

        try( var fos2 = new FileOutputStream("c:\\temp\\test.txt");
             var osw2 = new OutputStreamWriter(fos2, "ISO-8859-1");
             var bw = new BufferedWriter(osw2);){
            String str = "hello";
            bw.write(str+"\r\n"+str+"\n\r");//writing three lines to the file
        }

        /* A total of three lines are read from the file. The third line is an empty string (it is not null).
           This is because a line is considered to be terminated by any one of a line feed (\n), a carriage return ( \r),
           a carriage return followed immediately by a line feed (\r\n), or by reaching the end of the file. */
        try(var fis2 = new FileInputStream("c:\\temp\\test.txt");
            var isr2 = new InputStreamReader(fis, "ISO-8859-1");
            var br = new BufferedReader(isr2);){
            String str = null;
            /* We keep on reading the lines until the BufferedReader returns null. */
            while( (str = br.readLine()) != null){
                System.out.println("|"+str+"|");
            }
        }

        /* Java also provides concrete classes named FileReader and FileWriter that extend InputStreamReader and
           OutputStreamWriter respectively for reading character data. Here is a sample program that shows how to use these
           classes:

           The program is similar to the previous ones except that it uses an instance of the java.nio.charset.Charset
           class instead of a String to specify the character encoding. Charset is a utility class that contains the
           mapping between raw byte data and the characters. */
        try(var fw = new FileWriter("c:\\temp\\test.txt", Charset.forName("UTF-8")) ){
            char[] charData2 = "hello".toCharArray();
            for (char c : charData2) {
                fw.write(c);
            }
        }
        try(var fr = new FileReader("c:\\temp\\test.txt", Charset.forName("UTF-8")) ){
            int data2 = fr.read();
            while(data2 != -1) {
                char letter = (char) data2;//cast data to char
                //do something with the char just read
                System.out.print(letter);
                data2 = fr.read();
            }
        }
    }
}
