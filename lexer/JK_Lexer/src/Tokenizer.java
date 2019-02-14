package JK_Lexer; 
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    // begin static variables
    private static Map<String, Token> TOKEN_MAPPING =
        new HashMap<String, Token>() {{
            put("+", new PlusToken());
            put("-", new MinusToken());
            put("*", new MultToken());
            put("/", new DivToken());
            put("(", new LeftParenToken());
            put(")", new RightParenToken());
            put("{", new LeftCurlyToken());
            put("}", new RightCurlyToken());
            put("if", new IfToken());
            put("else", new ElseToken());
            put("class", new ClassToken());
            put(";", new ColonToken());
            put("extends", new ExtendsToken());
            put("new", new NewToken()); 
            put("Object", new ObjectToken());
            put(".", new PeriodToken());
            put("private", new PrivateToken()); 
            put("public", new PublicToken()); 
            put("\"", new QuoteToken()); 
            put(";", new SemicolonToken()); 
            put("String", new StringToken());
            put("this", new ThisToken()); 
            put("void", new VoidToken()); 
            put("int", new IntToken()); 
            put("println", new PrintToken()); 
            put("=", new AssignmentToken());
            put(",", new CommaToken());
            put("return", new ReturnToken());
        }};
    // end static variables
    
    // begin instance variables
    private final char[] input;
    private int inputPos; // position in the input
    // end instance variables
    
    public Tokenizer(final char[] input) {
        this.input = input;
        inputPos = 0;
    }

    public static boolean isTokenString(final String input) {
        return TOKEN_MAPPING.containsKey(input);
    }
    
    // assumes there is at least one character left
    // returns null if it couldn't parse a number
    private NumberToken tryTokenizeNumber() {
        final int initialInputPos = inputPos;
        String digits = "";

        while (inputPos < input.length &&
               Character.isDigit(input[inputPos])) {
            digits += input[inputPos];
            inputPos++;
        }

        if (digits.length() > 0) {
            return new NumberToken(Integer.parseInt(digits));
        } else {
            // reset position
            inputPos = initialInputPos;
            return null;
        }
    }

    // assumes there is at least one character left
    // returns null if it couldn't parse a variable
    private NameToken tryTokenizeName() {
        final int initialInputPos = inputPos;
        String name = "";

        if (Character.isLetter(input[inputPos])) {
            name += input[inputPos];
            inputPos++;            
            while (inputPos < input.length &&
                   Character.isLetterOrDigit(input[inputPos])) {
                name += input[inputPos];
                inputPos++;
            }
        } else {
            // reset position
            inputPos = initialInputPos;
            return null;
        }

        if (isTokenString(name)) {
            // reset position
            inputPos = initialInputPos;
            return null;
        } else {
            return new NameToken(name);
        }
    }

    private boolean prefixCharsEqual(final String probe) {
        int targetPos = inputPos;
        int probePos = 0;

        while (targetPos < input.length &&
               probePos < probe.length() &&
               probe.charAt(probePos) == input[targetPos]) {
            probePos++;
            targetPos++;
        }

        return probePos == probe.length();
    }
            
    // returns null if it couldn't parse a token
    private Token tryTokenizeOther() {
        for (final Map.Entry<String, Token> entry : TOKEN_MAPPING.entrySet()) {
            final String key = entry.getKey();
            if (prefixCharsEqual(key)) {
                inputPos += key.length();
                return entry.getValue();
            }
        }
        return null;
    }
    
    private void skipWhitespace() {
        while (inputPos < input.length &&
               Character.isWhitespace(input[inputPos])) {
            inputPos++;
        }
    }

    // returns null if there are no more tokens
    public Token tokenizeSingle() throws TokenizerException {
        NameToken var = null;
        NumberToken num = null;
        Token otherToken = null;

        skipWhitespace();

        if (inputPos >= input.length) {
            return null;
        } else if ((var = tryTokenizeName()) != null) {
            return var;
        } else if ((num = tryTokenizeNumber()) != null) {
            return num;
        } else if ((otherToken = tryTokenizeOther()) != null) {
            return otherToken;
        } else {
            throw new TokenizerException("Invalid character " +
                                         input[inputPos] +
                                         " at position " +
                                         inputPos);
        }
    }
    
    public List<Token> tokenize() throws TokenizerException {
        List<Token> list = new ArrayList<Token>();
        Token current = null;
            
        while ((current = tokenizeSingle()) != null) {
            list.add(current);
        }

        return list;
    }
}