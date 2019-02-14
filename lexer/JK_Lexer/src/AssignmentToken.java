package JK_Lexer;

public class AssignmentToken implements Token {
    public int hashCode() { return 25; }
    public boolean equals(final Object other) {
        return other instanceof AssignmentToken;
    }
    public String toString() {
        return "=";
    }
}