package JK_Lexer; 
public class LeftCurlyToken implements Token {
    public int hashCode() { return 2; }
    public boolean equals(final Object other) {
        return other instanceof LeftCurlyToken;
    }
    public String toString() {
        return "{";
    }

}