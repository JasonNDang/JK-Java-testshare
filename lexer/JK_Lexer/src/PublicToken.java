package JK_Lexer;
public class PublicToken implements Token{
    public int hashCode(){ return 18; }
    public boolean equals(final Object other){
        return other instanceof PublicToken;
    }
    public String toString(){
        return "public";
    }
}