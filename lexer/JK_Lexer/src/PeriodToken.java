package JK_Lexer; 
public class PeriodToken implements Token{
    public int hashCode(){ return 10; }
    public boolean equals(final Object other){
        return other instanceof PeriodToken; 
    }
    public String toString(){
        return ".";
    }
}