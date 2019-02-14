package JK_Lexer; 
public class SemicolonToken implements Token{
    public int hashCode(){ return 11;}
    public boolean equals(final Object other){
        return other instanceof SemicolonToken;
    }
    public String toString(){
        return ";";
    }
}
