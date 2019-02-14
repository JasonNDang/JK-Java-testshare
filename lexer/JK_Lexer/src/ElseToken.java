package JK_Lexer; 
public class ElseToken implements Token {
    public int hashCode() { return 9; }
    public boolean equals(final Object other) {
        return other instanceof ElseToken;
    }
    public String toString() {
        return "else";
    }
}
