public class Token {
    private int kind; // token’s lexical class
    private int intVal; // integer value if class = INT
    private String id;// actual identifier if class = ID
    private boolean isInt;
    // lexical classes
    public static final int EOF = 0; // “end of file” token
    public static final int ID = 1; // identifier, not keyword
    public static final int INT = 2; // integer
    public static final int LPAREN = 4;
    public static final int SCOLN = 5;
    public static final int WHILE = 6;
    public static final int RPAREN = 7;
    public static final int IF = 8;
    public static final int OTHERS = 9;

    public Token(int kind, int intVal) {
        this.kind = kind;
        this.intVal = intVal;
        this.isInt = true;
    }

    public Token(int kind, String id) {
        this.kind = kind;
        this.id = id;
    }

    public Token(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return kind;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("TOKEN: { ");
        str.append("kind: ");
        switch (kind){
            case 0:
                str.append("EOF");
                break;
            case 1:
                str.append("ID");
                break;
            case 2:
                str.append("INT");
                break;
            case 4:
                str.append("LPAREN");
                break;
            case 5:
                str.append("SCOLN");
                break;
            case 6:
                str.append("WHILE");
                break;
            case 7:
                str.append("RPAREN");
                break;
            case 8:
                str.append("IF");
                break;
            case 9:
                str.append("OTHER");
                break;
        }
        str.append(' ');
        if(id != null){
            str.append("id: ").append(id).append(" }").append('\n');
        }else if(isInt){
            str.append("value: ").append(intVal).append(" }").append('\n');
        }else{
            str.append(" }").append('\n');
        }
       return str.toString();
    }
}