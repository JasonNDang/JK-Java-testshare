package JK_Lexer; 
public class MultToken implements Token {
    public int hashCode() { return 5; }
    public boolean equals(final Object other) {
        return other instanceof MultToken;
    }
    public String toString() {
        return "*";
    }
}