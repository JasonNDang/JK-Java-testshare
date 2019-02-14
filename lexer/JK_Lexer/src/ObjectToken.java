package JK_Lexer; 
public class ObjectToken implements Token{
    public int hashCoe(){ return 16;}
    public boolean equals(final Object other){
        return other instanceof ObjectToken;
    }
    public String toString(){
        return "Object";
    }
}