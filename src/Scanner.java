import java.io.*;

public class Scanner {

    // global state and methods
    private Token lastToken;
    private char nextChar;
    private String code;
    private int counter;
    FileInputStream filePointer;// next unprocessed input character

    public Scanner(String location) throws IOException {
        filePointer = openFile(location);
        code = skipWhiteSpace(filePointer);
        counter = 0;

    }

    // advance to next input char
    void getChar() {
        if (counter < code.length()) {
            nextChar = code.charAt(counter);
            counter++;
        }
    }

    FileInputStream openFile(String location) throws FileNotFoundException {
        File file = new File(location);
        return new FileInputStream(file);
    }

    // skip whitespace and comments
    String skipWhiteSpace(FileInputStream filePointer) throws IOException {
        int r = 0;
        StringBuilder code = new StringBuilder();
        while ((r = filePointer.read()) != -1) {
            if (r == '/') {
                char temp = (char) filePointer.read();
                if (temp == '/') {
                    while (temp != '\n') {
                        temp = (char) filePointer.read();
                    }
                } else if (temp == '*') {
                    temp = (char) filePointer.read();
                    while (temp != '/') {
                        temp = (char) filePointer.read();
                    }
                }
            } else {
                code.append((char) r);
            }
        }
        return code.toString();
    }

    // return next input token
    public Token getToken() {
        Token result;
        if (counter == code.length()) {
            result = new Token(Token.EOF);
            lastToken = result;
            return result;
        } else {
            getChar();
            if (nextChar == ' ' || nextChar == '=') {
                getChar();
            }
            switch (nextChar) {
                case '(':
                    result = new Token(Token.LPAREN);
                    lastToken = result;
                    return result;
                case ')':
                    result = new Token(Token.RPAREN);
                    lastToken = result;
                    return result;
                case ';':
                    result = new Token(Token.SCOLN);
                    lastToken = result;
                    return result;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    StringBuilder num = new StringBuilder();
                    num.append(nextChar);
                    getChar();
                    while (((int) nextChar >= 65 && (int) nextChar <= 90)) {
                        num.append(nextChar);
                        getChar();
                    }
                    result = new Token(Token.INT, Integer.parseInt(num.toString()));
                    lastToken = result;
                    return result;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    StringBuilder str = new StringBuilder();
                    str.append(nextChar);
                    getChar();
                    while (((int) nextChar >= 65 && (int) nextChar <= 90) || ((int) nextChar >= 97 && (int) nextChar <= 122) || nextChar == '_') {
                        str.append(nextChar);
                        getChar();
                    }
                    if (str.toString().equals("if")) {
                        result = new Token(Token.IF);
                        lastToken = result;
                        return result;
                    } else if (str.toString().equals("while")) {
                        result = new Token(Token.WHILE);
                        lastToken = result;
                        return result;
                    } else if (str.toString().equals("int")) {
                        result = new Token(Token.INT);
                        lastToken = result;
                        return result;
                    } else {
                        if (lastToken.getKind() == Token.INT) {
                            result = new Token(Token.ID, str.toString());
                            lastToken = result;
                            return result;
                        }
                    }
                default:
                    return new Token(Token.OTHERS);
            }
        }
    }
}
