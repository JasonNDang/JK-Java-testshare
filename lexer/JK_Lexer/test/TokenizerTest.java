package JK_Lexer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class TokenizerTest {
    // specify null for expected if it's not supposed to tokenize
    public void assertTokenizes(final String input,
                                final Token[] expected) {
        final Tokenizer tokenizer = new Tokenizer(input.toCharArray());
        try {
            final List<Token> received = tokenizer.tokenize();
            assertTrue("Expected tokenization failure, got: " + received,
                       expected != null);
            assertArrayEquals(expected,
                              received.toArray(new Token[received.size()]));
        } catch (final TokenizerException e) {
            assertTrue(("Unexpected tokenization failure for \"" +
                        input + "\": " + e.getMessage()),
                       expected == null);
        }
    }

    @Test
    public void testTokenizeSingleDigitInteger() {
        assertTokenizes("0",
                        new Token[]{ new NumberToken(0) });
    }
    
    @Test
    public void testTokenizeInteger() {
        assertTokenizes("123",
                        new Token[]{ new NumberToken(123) });
    }

    @Test
    public void testTokenizeIntegerLeadingWhitespace() {
        assertTokenizes("  123",
                        new Token[]{ new NumberToken(123) });
    }

    @Test
    public void testTokenizeIntegerTrailingWhitespace() {
        assertTokenizes("123   ",
                        new Token[]{ new NumberToken(123) });
    }

    @Test
    public void testTokenizeIntegerLeadingAndTrailingWhitespace() {
        assertTokenizes("  123  ",
                        new Token[]{ new NumberToken(123) });
    }

    @Test
    public void testTokenizeVariableSingleLetter() {
        assertTokenizes("x",
                        new Token[]{ new NameToken("x") });
    }

    @Test
    public void testTokenizeVariableMultiLetter() {
        assertTokenizes("foo",
                        new Token[]{ new NameToken("foo") });
    }

    @Test
    public void testTokenizeVariableStartsWithIf() {
        assertTokenizes("ifx",
                        new Token[]{ new NameToken("ifx") });
    }

    @Test
    public void testTokenizeIf() {
        assertTokenizes("if",
                        new Token[]{ new IfToken() });
    }

    @Test
    public void testTokenizeSingleChars() {
        assertTokenizes("+-*/(){}",
                        new Token[]{ new PlusToken(),
                                     new MinusToken(),
                                     new MultToken(),
                                     new DivToken(),
                                     new LeftParenToken(),
                                     new RightParenToken(),
                                     new LeftCurlyToken(),
                                     new RightCurlyToken() });
    }

    @Test
    public void testTokenizeIntermixed() {
        assertTokenizes("*if+foo-",
                        new Token[]{ new MultToken(),
                                     new IfToken(),
                                     new PlusToken(),
                                     new NameToken("foo"),
                                     new MinusToken() });
    }

    @Test
    public void testTokenizeElse() {
        assertTokenizes("else",
                        new Token[]{ new ElseToken() });
    }

    @Test
    public void testTokenizeIfExpression() {
        assertTokenizes("if (1) { x } else { y }",
                        new Token[]{ new IfToken(),
                                     new LeftParenToken(),
                                     new NumberToken(1),
                                     new RightParenToken(),
                                     new LeftCurlyToken(),
                                     new NameToken("x"),
                                     new RightCurlyToken(),
                                     new ElseToken(),
                                     new LeftCurlyToken(),
                                     new NameToken("y"),
                                     new RightCurlyToken() });
    }
    
    //Types/variable declarations
    
    @Test
    public void testTokenizeIntDeclaration() {
    	assertTokenizes("int foo;",
    					new Token[] { new IntToken(),
    								  new NameToken("foo"),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeIntDeclarationWithAssignment() {
    	assertTokenizes("int foo = 1;",
    					new Token[] { new IntToken(),
    								  new NameToken("foo"),
    								  new AssignmentToken(),
    								  new NumberToken(1),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeStringDeclaration() {
    	assertTokenizes("String foo;",
    					new Token[] { new StringToken(),
    								  new NameToken("foo"),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeIntAssignment() {
    	assertTokenizes("foo = 1;",
    					new Token[] { new NameToken("foo"),
    								  new AssignmentToken(),
    								  new NumberToken(1),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeVariableAssignment() {
    	assertTokenizes("foo = bar;",
    					new Token[] { new NameToken("foo"),
    							      new AssignmentToken(),
    							      new NameToken("bar"),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeNewClass() {
    	assertTokenizes("new Foo();",
    					new Token[] { new NewToken(),
    								  new NameToken("Foo"),
    								  new LeftParenToken(),
    								  new RightParenToken(),
    								  new SemicolonToken() });
    }
    
    @Test
    public void testTokenizePlus() {
    	assertTokenizes("2+2",
    					new Token[] { new NumberToken(2),
    								  new PlusToken(),
    								  new NumberToken(2)});
    }
    
    @Test
    public void testTokenizeMinus() {
    	assertTokenizes("2-2",
    					new Token[] { new NumberToken(2),
    								  new MinusToken(),
    								  new NumberToken(2)});
    }
    
    @Test
    public void testTokenizeMult() {
    	assertTokenizes("2*2",
    					new Token[] { new NumberToken(2),
    								  new MultToken(),
    								  new NumberToken(2)});
    }
    
    @Test
    public void testTokenizeDiv() {
    	assertTokenizes("2/2",
    					new Token[] { new NumberToken(2),
    								  new DivToken(),
    								  new NumberToken(2)});
    }
    
    @Test
    public void testTokenizeThisVar() {
    	assertTokenizes("this.foo",
    					new Token[] { new ThisToken(),
    								  new PeriodToken(),
    								  new NameToken("foo")});
    }
    
    @Test
    public void testTokenizeVarFunc() {
    	assertTokenizes("foo.func(x,y);",
    					new Token[] { new NameToken("foo"),
    								  new PeriodToken(),
    								  new NameToken("func"),
    								  new LeftParenToken(),
    								  new NameToken("x"),
    								  new CommaToken(),
    								  new NameToken("y"),
    								  new RightParenToken(),
    								  new SemicolonToken()});
    }
    
    @Test
    public void testTokenizeFunctionDeclaration() {
    	assertTokenizes("public int addFoo(int x) { this.x+x; return x; }",
    					new Token[] { new PublicToken(),
    								  new IntToken(),
    								  new NameToken("addFoo"),
    								  new LeftParenToken(),
    								  new IntToken(),
    								  new NameToken("x"),
    								  new RightParenToken(),
    								  new LeftCurlyToken(),
    								  new ThisToken(),
    								  new PeriodToken(),
    								  new NameToken("x"),
    								  new PlusToken(),
    								  new NameToken("x"),
    								  new SemicolonToken(),
    								  new ReturnToken(),
    								  new NameToken("x"),
    								  new SemicolonToken(),
    								  new RightCurlyToken()});
    }
    
    @Test
    public void testTokenizeClassDeclaration() {
    	assertTokenizes("public class Student extends Person{" + 
    			"	private int id;" + 
    			"	public Student(int id){" + 
    			"		this.id=id;" + 
    			"	}" + 
    			"	public int getID(){" + 
    			"		return this.id;" + 
    			"}" + 
    			"}",
    					new Token[] { new PublicToken(),
    								  new ClassToken(),
    								  new NameToken("Student"),
    								  new ExtendsToken(),
    								  new NameToken("Person"),
    								  new LeftCurlyToken(),
    								  new PrivateToken(),
    								  new IntToken(),
    								  new NameToken("id"),
    								  new SemicolonToken(),
    								  new PublicToken(),
    								  new NameToken("Student"),
    								  new LeftParenToken(),
    								  new IntToken(),
    								  new NameToken("id"),
    								  new RightParenToken(),
    								  new LeftCurlyToken(),
    								  new ThisToken(),
    								  new PeriodToken(),
    								  new NameToken("id"),
    								  new AssignmentToken(),
    								  new NameToken("id"),
    								  new SemicolonToken(),
    								  new RightCurlyToken(),
    								  new PublicToken(),
    								  new IntToken(),
    								  new NameToken("getID"),
    								  new LeftParenToken(),
    								  new RightParenToken(),
    								  new LeftCurlyToken(),
    								  new ReturnToken(),
    								  new ThisToken(),
    								  new PeriodToken(),
    								  new NameToken("id"),
    								  new SemicolonToken(),
    								  new RightCurlyToken(),
    								  new RightCurlyToken()});
    }
}
