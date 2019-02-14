package JK_Lexer;

public class NumberToken implements Token {
    public final int number;

    public NumberToken(int number) {
        this.number = number;
    }

    public int hashCode() { return number; }
    public boolean equals(final Object other) {
        return (other instanceof NumberToken &&
                ((NumberToken)other).number == number);
    }
    public String toString() {
        return Integer.toString(number);
    }
}