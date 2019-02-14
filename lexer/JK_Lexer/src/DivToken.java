package JK_Lexer; 
public class DivToken implements Token{
    public int hashCode() { return 0; }
    public boolean equals(final Object other) {
        return other instanceof DivToken;
    }
    public String toString() {
        return "/";
}
}
