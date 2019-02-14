package JK_Lexer; 
public class LeftParenToken implements Token {
    public int hashCode() { return 3; }
    public boolean equals(final Object other) {
        return other instanceof LeftParenToken;
    }
    public String toString() {
        return "(";
    }
}
