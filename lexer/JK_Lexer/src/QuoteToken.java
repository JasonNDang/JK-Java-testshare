package JK_Lexer; 
public class QuoteToken implements Token{
    public int hashCode(){ return 13;}
    public boolean equals(final Object other){
        return other instanceof QuoteToken;
    }
    public String toString(){
        return "\"";
    }
}