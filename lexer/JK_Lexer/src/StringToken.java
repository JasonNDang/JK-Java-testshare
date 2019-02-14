package JK_Lexer;
public class StringToken implements Token{
    public int hashCode(){ return 17;}
    public boolean equals(final Object other){
        return other instanceof StringToken;
    }
    public String toString(){
        return "String";
    }
}