package JK_Lexer;
public class PrivateToken implements Token{
    public int hashCoe(){ return 19; }
    public boolean equals(final Object other){
        return other instanceof PrivateToken;
    }
    public String toString(){
        return "private";
    }
}