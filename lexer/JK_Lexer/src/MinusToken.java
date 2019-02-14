package JK_Lexer;
public class MinusToken implements Token {
    public int hashCode() { return 4; }
    public boolean equals(final Object other) {
        return other instanceof MinusToken;
    }
    public String toString() {
        return "-";
    }
}