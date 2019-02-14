package JK_Lexer;
public class ThisToken implements Token{
    public int hashCode(){ return 20; }
    public boolean equals(final Object other){
        return other instanceof ThisToken;
    }
    public String toString(){
        return "this";
    }
}