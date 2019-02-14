package JK_Lexer; 
public class IfToken implements Token {
    public int hashCode() { return 1; }
    public boolean equals(final Object other) {
        return other instanceof IfToken;
    }
    public String toString() {
        return "if";
    }
}