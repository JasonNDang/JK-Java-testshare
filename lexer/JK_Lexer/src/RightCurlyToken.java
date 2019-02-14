package JK_Lexer; 
public class RightCurlyToken implements Token {
    public int hashCode() { return 7; }
    public boolean equals(final Object other) {
        return other instanceof RightCurlyToken;
    }
    public String toString() {
        return "}";
    }
}