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
           mapping between raw byte data ("code unit") and the characters. */
        try(var fw = new FileWriter("c:\\temp\\test.txt", Charset.forName("UTF-8")) ) {
            char[] charData2 = "hello".toCharArray();
            for (char c : charData2) {
                fw.write(c);
            }
        }
        /* Character data always requires an encoding and decoding mechanism to convert characters into raw bytes and
           vice-versa. The rules for this interpretation are defined by a character encoding. Although it is usually possible
           to read and write character data without specifying a character encoding explicitly, it is a bad idea to do so
           because not specifying a character encoding explicitly just means that some default character encoding is applied by
           the underlying API implicitly. Any change in the default encoding may render the existing character data completely
           incomprehensible. Prior to Java 11, FileReader and FileWriter did not take a Charset as an argument during
           instantiation. They used the default encoding as determined from the environment in which the program is run.
           In Java 18, FileReader and FileWriter and several other classes were updated to use UTF-8 as the default character
           encoding instead of relying on the default character encoding of the environment. Although this change does make
           the code more portable, it is still better to specify the character encoding explicitly. */
        try(var fr = new FileReader("c:\\temp\\test.txt", Charset.forName("UTF-8")) ) {
            int data2 = fr.read();
            while(data2 != -1) {
                char letter = (char) data2;//cast data to char
                //do something with the char just read
                System.out.print(letter);
                data2 = fr.read();
            }
        }
    }

    /* Generally, data from an input stream can only be read once. You cannot "rewind" a stream and reread the same
       data again. However, some input streams that use internal data buffers may be able to provide this functionality.
       This functionality comes in handy while implementing parsers that need to rewind to a previous location if they
       encounter a specific control character.

       Note that the above example uses a Reader(which means, it is a character stream) but this functionality works the
       same way for an InputStream(which means, it is a byte stream) as well. The only difference is that in case of
       a byte stream, the arguments to mark and skip methods are interpreted as the number of bytes (instead of number
       of characters).*/
    public static void rewind() {
        try (BufferedReader bfr = new BufferedReader(new FileReader("test.txt"))) {
            /* The markSupported method tells you whether the underlying stream supports mark and reset. */
            if(bfr.markSupported()) {
                int letter = -1;
                int noOfChars = 0;
                String tag = "";
                while ((letter = bfr.read()) != -1) {
                    noOfChars++;
                    if (((char) letter) == '<') {
                        noOfChars = 0; //start counting chars till we hit '>'
                        /* The mark method takes an int parameter named markAheadLimit. It sets the maximum number of
                           characters you can read safely after which the stream may invalidate the mark. In the above
                           code, we have passed 256, which means, if we read more than 256 characters after setting
                           the mark, we may not be able to reset the stream back to that mark. The stream will throw
                           an IOException if we reset the stream after the stream has already invalidated the mark. */
                        bfr.mark(256); //mark this location (just after '<')
                    }
                    if (letter == '>') {
                        bfr.reset(); //rewind to the marked point
                        char[] buf = new char[noOfChars - 1];
                        bfr.read(buf);
                        System.out.println(new String(buf)+" ");
                        /* The skip method skips the stream forward by the specified number of characters. */
                        bfr.skip(1); //skip the '>' character
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* The following image shows the important input streams that work with characters:
       abstract class Reader : Root class for all character based streams
         int read()
         int read(char[] b, int off, int len)
         void skip(long n)
         boolean transferTo(Writer w)
         void close()
         int read(char[] b)
         int read(CharBuffer buf)
         boolean ready()
         boolean markSupported()
         void mark(int readAheadLimit)
         reset()

       class InputStreamReader extends Reader : A bridge from byte streams to character streams: It reads bytes and
                                                decodes them into characters using a specified charset or  using the
                                                default charset.
            InputStreamReader(InputStream in)
            InputStreamReader(InputStream in, String charsetName)
            InputStreamReader(InputStream in, Charset cs)
            String getEncoding()

       class FileReader : Reads text from character files using a default buffer size.
            FileReader(File file)
            FileReader(File file, Charset charset)
            FileReader(String fileName)
            FileReader(String fileName, Charset charset)

       class BufferedReader : Reads text from a character-input stream, buffering characters so as to provide for the
                              efficient reading of characters, arrays and lines.
             BufferedReader(Reader r)
             BufferedReader(Reader r, int bfrSize)
             String readLine()
             Stream<String> lines()
      */

      /* The following image shows the important output streams that work with characters.
         abstract class Writer : Root class for all character based output streams. Reader and Writer are abstract and
                                 they contain most of the methods that we normally use while dealing with low level character
                                 based input and output.
            void write(char[] cbuf)
            void write(char[] cbuf, int off, int len)
            void write(String str)
            void write(String str, int off, int len)
            Writer append(char c)
            Writer append(CharSequence csq)
            Writer append(CharSequence csq, int start, int end)
            void flush()
            void close()
            void write(int c)

         class OutputStreamWriter extends Writer : A bridge from character streams to byte streams: Characters written
                                                   to it are encoded into bytes using a specified (or the default) charset.
            OutputStreamWriter(OutputStream out)
            OutputStreamWriter(OutputStream out, String charsetName)
            OutputStreamWriter(OutputStream out, Charset cs)

         class FileWriter : Writes text to character files using a default buffer size. Uses a specified (or the default)
                            charset to encode bytes to characters. FileWriter and FileReader can be created without bridge
                            classes.
            FileWriter(File file)
            FileWriter(File file, boolean append)
            FileWriter(File file, Charset cs)
            FileWriter(File file, Charset cs, boolean append)

         class BufferedWriter : Writes text to a character-output stream, buffering characters so as to provide for the
                                efficient writing of single characters, arrays and strings. BufferedReader and BufferedWriter
                                requires an instance of a bridge class (InputStreamReader, OutputStreamWriter)
             BufferedWriter(Writer w)
             BufferedWriter(Writer w, int bfrSize)
             newLine()
         */
}
