package JK_Lexer;
public class VoidToken implements Token{
    public int hashCode(){ return 15;}
    public boolean equals(final Object other){
        return other instanceof VoidToken;
    }
    public String toString(){
        return "void";
    }
}