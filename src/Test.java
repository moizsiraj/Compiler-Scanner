import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner("C:\\Users\\moizs\\OneDrive\\Documents\\NetBeansProjects\\scanner\\src\\code.txt");
        FileWriter firstWriter = new FileWriter("token.txt");
        Token token = scanner.getToken();
        System.out.println(token);
        while (token.getKind() != Token.EOF) {
            token = scanner.getToken();
            firstWriter.write(token.toString());
            System.out.println(token);
        }
        firstWriter.close();
    }
}
