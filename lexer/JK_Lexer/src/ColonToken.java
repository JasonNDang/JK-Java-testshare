package JK_Lexer; 
public class ColonToken implements Token{
    public int hashCode(){ return 12;}
    public boolean equals(final Object other){
        return other instanceof ColonToken;
    }
}