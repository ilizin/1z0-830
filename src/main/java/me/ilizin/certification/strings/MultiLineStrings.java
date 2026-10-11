package me.ilizin.certification.strings;

import java.io.IOException;

public class MultiLineStrings {

    public static void main(String[] args) {

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
        // All of the trailing white spaces at the end of each line is replaced by a single new line character.
        String commonHeader4 =
                """
      <head>
       <meta charset="utf-8" />             
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />    
         </head>    """; // This white spaces are also removed
        System.out.println(commonHeader4);
        /* \t inserts a tab character between Hello and Tab and \n inserts a new line character after Tab. Note that escape
           sequences are not whitespace characters themselves and are therefore, never a part of incidental or trailing
           whitespaces. The compiler translates escape sequences to whitespace characters in the resulting string after removing
           incidental and trailing whitespaces.
           Their presence at the beginning or at the end of a line terminates the incidental or trailing whitespace of that line.
           In other words, if there is a whitespace present immediately after an escape sequence at the beginning of a line
           or immediately before an escape sequence at the end of a line, then that whitespace will be preserved in the
           resulting string.
           For example, if you want to have two space characters at the beginning as as well as at end of the first line
           (i.e., immediately before Hello and immediately after Tab), then you can write the line as \s Hello\tTab \s.
            (There is only one space character between \s and Hand between band \s.) Incidental white space ends at the
            first \s and starts after the last \s. Therefore, the space character after the first \s and the space character
            before the last \swill be preserved. The two \s will also be replaced by space characters in the final string,
            resulting in two spaces at the beginning and two spaces at the end of the line.*/
        String s = """
                Hello\tTab\n""";
        System.out.println(s);

        String s2 = """
                \s Hello\tTab \s\n""";
        System.out.println(s2);
        /* If you want to break a long string into multiple lines in the code without actually inserting a new line character
           in the resulting string, you can do so, using a backslash (\) at the end of a line, like this:
           Observe that there is no new line between Hello and World in the output even though there is a new line between
           them in the code and the single space character between Hello and \is preserved while all of the whitespace
           after the \ is removed because the \ marks the beginning of the trailing whitespace as explained above.
           */
        String s3 = """
               Hello \
               World\t""";
        System.out.println("|"+s3+"|");//prints |Hello World    |

        System.out.println();
        System.out.println("*** PART 3 ***");
        // String str = """Hello"""; // Invalid. New line after the opening """ is required to mark the beginning of a text block.
        //String str = """  // Invalid. A text block must be closed with """.
          //      Hello";
        //String str = """ // Invalid. Since """ is a delimiter, if you want to have """ as a part of the resulting string, you have to escape at least one of the " characters.
          //      """
            //    """;
        String str = """
                \"
                " \""" """;
        System.out.println(str);
    }
}
