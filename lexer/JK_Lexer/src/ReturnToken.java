package JK_Lexer;

public class ReturnToken implements Token {
    public int hashCode() { return 27; }
    public boolean equals(final Object other) {
        return other instanceof ReturnToken;
    }
    public String toString() {
        return "return";
    }
}