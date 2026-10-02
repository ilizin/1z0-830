package me.ilizin.certification.strings;

import java.io.IOException;

public class MultiLineStrings {

    public static void main(String[] args) throws IOException {

        System.out.println();
        System.out.println("*** PART 1 ***");
        /* Many a times there is a need to specify multiple lines of hardcoded strings such an HTML fragments within Java
           code. For example, I may want to hardcode the following HTML header data: */
        String commonHeader =
                "<head>\n" +
                        "<meta charset=\"utf-8\" />\n" +
                        "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "</head>";
           /* We had to split one long string literal into multiple single-line string literals and then concatenate
           all of them using the + operator. The same code can now be written as follows using a text block.
            In the above code, the concatenations are gone, many of the double quotes are gone, and the escape characters
            for double quotes and the new line characters are gone as well. This has been made possible by a feature called
            text blocks introduced in Java 17.*/
        System.out.println(commonHeader);
        String commonHeader2 =
                """
                <head>
                   <meta charset="utf-8" />
                   <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                </head>
                """;
        System.out.println(commonHeader2);

        System.out.println();
        System.out.println("*** PART 2 ***");
        /* Delimiters:
           1.The starting delimiter is """ followed by any number of white spaces followed by the ASCII LF character.
           2.The ending delimiter is """.
           Pay attention to the fact that the starting and ending delimiters are different. The ending delimiter does not
           include a new line character. Thus, in the above example, the resulting string starts with <head>and ends with a
           new line character after </head>. The original string that was written using string literals did not have the
           new line character at the end.

           Indentation:
           1.White space characters that are used to indent the text block are removed from each line. These white spaces
           are collectively called "incidental whitespace". In the above example, <head> is indented 3 spaces from the left
           margin. Some other lines have more spaces at the beginning but the first three of those spaces are common to
           all the lines. These are all incidental whitespace and are removed.
           However, any space after the incidental whitespace and before the first non-whitespace character at each line
           is considered "essential whitespace" and is kept.
           2.All trailing whitespace is removed and each line is terminated with a single ASCII LF character
           (the new line character \u000A) immediately after the last non-whitespace character. Thus, even if you have
           multiple space characters (such as spaces and tabs) at the end of a line, those will not be a part of the
           resulting string. They will be replaced with a single new line character.
           */
        String commonHeader3 =
   """
   <head>
      <meta charset="utf-8" />            
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />    
   </head>
   """;
        System.out.println(commonHeader3);
        String commonHeader4 =
                """
      <head>
       <meta charset="utf-8" />            
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />    
         </head>    """;
        System.out.println(commonHeader4);
    }
}
