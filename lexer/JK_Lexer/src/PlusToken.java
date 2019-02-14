package JK_Lexer; 

public class PlusToken implements Token {
    public int hashCode() { return 6; }
    public boolean equals(final Object other) {
        return other instanceof PlusToken;
    }
    public String toString() {
        return "+";
    }
}