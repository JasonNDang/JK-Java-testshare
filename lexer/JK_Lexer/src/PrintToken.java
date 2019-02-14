package JK_Lexer; 
public class PrintToken implements Token{
    public int hashCode(){ return 21; }
    public boolean equals(final Object other){
        return other instanceof PrintToken;
    }
    public String toString(){
        return "println";
    }
}