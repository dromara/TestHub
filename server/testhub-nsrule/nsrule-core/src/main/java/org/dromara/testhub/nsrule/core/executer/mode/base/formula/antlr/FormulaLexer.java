// Generated from /Users/vinc/Documents/github/TestHub/server/testhub-nsrule/nsrule-core/antlr4/Formula.g4 by ANTLR 4.13.1
package org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FormulaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, SingleQuoteAnyText=7, 
		IN=8, NOT=9, LIKE=10, RLIKE=11, EQ=12, NEQ=13, GT=14, LT=15, GE=16, LE=17, 
		ADD=18, SUB=19, MUL=20, DIV=21, PERCENTAGE=22, SPACE=23, SingleQuote=24, 
		QUE_MARK=25, COMMA=26, DOT=27, DOLLAR=28, LCURLY=29, RCURLY=30, LBRACKET=31, 
		RBRACKET=32, INT=33, FLOAT=34, IDENTIFIER=35, Whitespace=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "SingleQuoteAnyText", 
			"IN", "NOT", "LIKE", "RLIKE", "EQ", "NEQ", "GT", "LT", "GE", "LE", "ADD", 
			"SUB", "MUL", "DIV", "PERCENTAGE", "SPACE", "SingleQuote", "QUE_MARK", 
			"COMMA", "DOT", "DOLLAR", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", 
			"INT", "FLOAT", "IDENTIFIER", "Whitespace"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "':'", "'${'", "'%{'", "'()'", null, "'in'", "'not'", 
			"'like'", "'rlike'", "'='", "'!='", "'>'", "'<'", "'>='", "'<='", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "' '", "'''", "'?'", "','", "'.'", "'$'", 
			"'{'", "'}'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "SingleQuoteAnyText", "IN", 
			"NOT", "LIKE", "RLIKE", "EQ", "NEQ", "GT", "LT", "GE", "LE", "ADD", "SUB", 
			"MUL", "DIV", "PERCENTAGE", "SPACE", "SingleQuote", "QUE_MARK", "COMMA", 
			"DOT", "DOLLAR", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "INT", "FLOAT", 
			"IDENTIFIER", "Whitespace"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000$\u00be\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0005\u0006[\b\u0006\n\u0006\f\u0006^\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0004 \u00a2\b"+
		" \u000b \f \u00a3\u0001!\u0004!\u00a7\b!\u000b!\f!\u00a8\u0001!\u0001"+
		"!\u0004!\u00ad\b!\u000b!\f!\u00ae\u0001\"\u0001\"\u0005\"\u00b3\b\"\n"+
		"\"\f\"\u00b6\t\"\u0001#\u0004#\u00b9\b#\u000b#\f#\u00ba\u0001#\u0001#"+
		"\u0000\u0000$\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f"+
		"? A!C\"E#G$\u0001\u0000\u0005\u0001\u0000\'\'\u0001\u000009\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u00c3\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005"+
		"\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000"+
		"\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000"+
		"\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C"+
		"\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000"+
		"\u0000\u0000\u0001I\u0001\u0000\u0000\u0000\u0003K\u0001\u0000\u0000\u0000"+
		"\u0005M\u0001\u0000\u0000\u0000\u0007O\u0001\u0000\u0000\u0000\tR\u0001"+
		"\u0000\u0000\u0000\u000bU\u0001\u0000\u0000\u0000\rX\u0001\u0000\u0000"+
		"\u0000\u000fa\u0001\u0000\u0000\u0000\u0011d\u0001\u0000\u0000\u0000\u0013"+
		"h\u0001\u0000\u0000\u0000\u0015m\u0001\u0000\u0000\u0000\u0017s\u0001"+
		"\u0000\u0000\u0000\u0019u\u0001\u0000\u0000\u0000\u001bx\u0001\u0000\u0000"+
		"\u0000\u001dz\u0001\u0000\u0000\u0000\u001f|\u0001\u0000\u0000\u0000!"+
		"\u007f\u0001\u0000\u0000\u0000#\u0082\u0001\u0000\u0000\u0000%\u0084\u0001"+
		"\u0000\u0000\u0000\'\u0086\u0001\u0000\u0000\u0000)\u0088\u0001\u0000"+
		"\u0000\u0000+\u008a\u0001\u0000\u0000\u0000-\u008c\u0001\u0000\u0000\u0000"+
		"/\u008e\u0001\u0000\u0000\u00001\u0090\u0001\u0000\u0000\u00003\u0092"+
		"\u0001\u0000\u0000\u00005\u0094\u0001\u0000\u0000\u00007\u0096\u0001\u0000"+
		"\u0000\u00009\u0098\u0001\u0000\u0000\u0000;\u009a\u0001\u0000\u0000\u0000"+
		"=\u009c\u0001\u0000\u0000\u0000?\u009e\u0001\u0000\u0000\u0000A\u00a1"+
		"\u0001\u0000\u0000\u0000C\u00a6\u0001\u0000\u0000\u0000E\u00b0\u0001\u0000"+
		"\u0000\u0000G\u00b8\u0001\u0000\u0000\u0000IJ\u0005(\u0000\u0000J\u0002"+
		"\u0001\u0000\u0000\u0000KL\u0005)\u0000\u0000L\u0004\u0001\u0000\u0000"+
		"\u0000MN\u0005:\u0000\u0000N\u0006\u0001\u0000\u0000\u0000OP\u0005$\u0000"+
		"\u0000PQ\u0005{\u0000\u0000Q\b\u0001\u0000\u0000\u0000RS\u0005%\u0000"+
		"\u0000ST\u0005{\u0000\u0000T\n\u0001\u0000\u0000\u0000UV\u0005(\u0000"+
		"\u0000VW\u0005)\u0000\u0000W\f\u0001\u0000\u0000\u0000X\\\u0005\'\u0000"+
		"\u0000Y[\b\u0000\u0000\u0000ZY\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]_\u0001\u0000"+
		"\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0005\'\u0000\u0000`\u000e\u0001"+
		"\u0000\u0000\u0000ab\u0005i\u0000\u0000bc\u0005n\u0000\u0000c\u0010\u0001"+
		"\u0000\u0000\u0000de\u0005n\u0000\u0000ef\u0005o\u0000\u0000fg\u0005t"+
		"\u0000\u0000g\u0012\u0001\u0000\u0000\u0000hi\u0005l\u0000\u0000ij\u0005"+
		"i\u0000\u0000jk\u0005k\u0000\u0000kl\u0005e\u0000\u0000l\u0014\u0001\u0000"+
		"\u0000\u0000mn\u0005r\u0000\u0000no\u0005l\u0000\u0000op\u0005i\u0000"+
		"\u0000pq\u0005k\u0000\u0000qr\u0005e\u0000\u0000r\u0016\u0001\u0000\u0000"+
		"\u0000st\u0005=\u0000\u0000t\u0018\u0001\u0000\u0000\u0000uv\u0005!\u0000"+
		"\u0000vw\u0005=\u0000\u0000w\u001a\u0001\u0000\u0000\u0000xy\u0005>\u0000"+
		"\u0000y\u001c\u0001\u0000\u0000\u0000z{\u0005<\u0000\u0000{\u001e\u0001"+
		"\u0000\u0000\u0000|}\u0005>\u0000\u0000}~\u0005=\u0000\u0000~ \u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0005<\u0000\u0000\u0080\u0081\u0005=\u0000\u0000"+
		"\u0081\"\u0001\u0000\u0000\u0000\u0082\u0083\u0005+\u0000\u0000\u0083"+
		"$\u0001\u0000\u0000\u0000\u0084\u0085\u0005-\u0000\u0000\u0085&\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0005*\u0000\u0000\u0087(\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005/\u0000\u0000\u0089*\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0005%\u0000\u0000\u008b,\u0001\u0000\u0000\u0000\u008c\u008d\u0005"+
		" \u0000\u0000\u008d.\u0001\u0000\u0000\u0000\u008e\u008f\u0005\'\u0000"+
		"\u0000\u008f0\u0001\u0000\u0000\u0000\u0090\u0091\u0005?\u0000\u0000\u0091"+
		"2\u0001\u0000\u0000\u0000\u0092\u0093\u0005,\u0000\u0000\u00934\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0005.\u0000\u0000\u00956\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0005$\u0000\u0000\u00978\u0001\u0000\u0000\u0000\u0098"+
		"\u0099\u0005{\u0000\u0000\u0099:\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"}\u0000\u0000\u009b<\u0001\u0000\u0000\u0000\u009c\u009d\u0005[\u0000"+
		"\u0000\u009d>\u0001\u0000\u0000\u0000\u009e\u009f\u0005]\u0000\u0000\u009f"+
		"@\u0001\u0000\u0000\u0000\u00a0\u00a2\u0007\u0001\u0000\u0000\u00a1\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4B\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a7\u0007\u0001\u0000\u0000\u00a6\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ac\u0005.\u0000\u0000\u00ab\u00ad\u0007\u0001"+
		"\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000"+
		"\u0000\u0000\u00afD\u0001\u0000\u0000\u0000\u00b0\u00b4\u0007\u0002\u0000"+
		"\u0000\u00b1\u00b3\u0007\u0003\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b6\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5F\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b9\u0007\u0004\u0000\u0000"+
		"\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0006#\u0000\u0000\u00bd"+
		"H\u0001\u0000\u0000\u0000\u0007\u0000\\\u00a3\u00a8\u00ae\u00b4\u00ba"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}