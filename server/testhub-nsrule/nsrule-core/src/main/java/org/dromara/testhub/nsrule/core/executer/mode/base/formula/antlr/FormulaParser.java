// Generated from /Users/vinc/Documents/github/TestHub/server/testhub-nsrule/nsrule-core/antlr4/Formula.g4 by ANTLR 4.13.1
package org.dromara.testhub.nsrule.core.executer.mode.base.formula.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FormulaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, SingleQuoteAnyText=6, IN=7, NOT=8, 
		LIKE=9, RLIKE=10, EQ=11, NEQ=12, GT=13, LT=14, GE=15, LE=16, ADD=17, SUB=18, 
		MUL=19, DIV=20, PERCENTAGE=21, SPACE=22, SingleQuote=23, QUE_MARK=24, 
		SIGH=25, COMMA=26, DOT=27, DOLLAR=28, LCURLY=29, RCURLY=30, LBRACKET=31, 
		RBRACKET=32, INT=33, FLOAT=34, IDENTIFIER=35, CHINESE=36, WELL=37, AND=38, 
		SEMICOLON=39, AT=40, COLON=41, Whitespace=42;
	public static final int
		RULE_formula = 0, RULE_otherNode = 1, RULE_dataNode = 2, RULE_keyVal = 3, 
		RULE_keyFormula = 4, RULE_pathNode = 5, RULE_funcNode = 6, RULE_params = 7, 
		RULE_param = 8, RULE_jsonpath = 9, RULE_subscript = 10, RULE_dotIdentifier = 11, 
		RULE_all = 12, RULE_dotAll = 13, RULE_arraySubscript = 14, RULE_condition = 15, 
		RULE_notNull = 16, RULE_expression = 17, RULE_op = 18, RULE_strOp = 19, 
		RULE_arrayIndex = 20, RULE_listIndex = 21, RULE_numIndex = 22, RULE_interval = 23, 
		RULE_arrayInterval = 24, RULE_toInterval = 25, RULE_firstInterval = 26, 
		RULE_recursion = 27, RULE_function = 28, RULE_decimal = 29, RULE_arithmetic = 30, 
		RULE_binArithmetic = 31, RULE_addsub = 32, RULE_muldiv = 33, RULE_factor = 34, 
		RULE_space = 35, RULE_chineseString = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"formula", "otherNode", "dataNode", "keyVal", "keyFormula", "pathNode", 
			"funcNode", "params", "param", "jsonpath", "subscript", "dotIdentifier", 
			"all", "dotAll", "arraySubscript", "condition", "notNull", "expression", 
			"op", "strOp", "arrayIndex", "listIndex", "numIndex", "interval", "arrayInterval", 
			"toInterval", "firstInterval", "recursion", "function", "decimal", "arithmetic", 
			"binArithmetic", "addsub", "muldiv", "factor", "space", "chineseString"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'${'", "'%{'", "'()'", null, "'in'", "'not'", "'like'", 
			"'rlike'", "'='", "'!='", "'>'", "'<'", "'>='", "'<='", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "' '", "'''", "'?'", "'!'", "','", "'.'", "'$'", 
			"'{'", "'}'", "'['", "']'", null, null, null, null, "'#'", "'&'", "';'", 
			"'@'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "SingleQuoteAnyText", "IN", "NOT", 
			"LIKE", "RLIKE", "EQ", "NEQ", "GT", "LT", "GE", "LE", "ADD", "SUB", "MUL", 
			"DIV", "PERCENTAGE", "SPACE", "SingleQuote", "QUE_MARK", "SIGH", "COMMA", 
			"DOT", "DOLLAR", "LCURLY", "RCURLY", "LBRACKET", "RBRACKET", "INT", "FLOAT", 
			"IDENTIFIER", "CHINESE", "WELL", "AND", "SEMICOLON", "AT", "COLON", "Whitespace"
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

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormulaContext extends ParserRuleContext {
		public Token dataType;
		public FuncNodeContext funcNode() {
			return getRuleContext(FuncNodeContext.class,0);
		}
		public PathNodeContext pathNode() {
			return getRuleContext(PathNodeContext.class,0);
		}
		public DataNodeContext dataNode() {
			return getRuleContext(DataNodeContext.class,0);
		}
		public ArithmeticContext arithmetic() {
			return getRuleContext(ArithmeticContext.class,0);
		}
		public OtherNodeContext otherNode() {
			return getRuleContext(OtherNodeContext.class,0);
		}
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(74);
				match(T__0);
				setState(75);
				((FormulaContext)_localctx).dataType = match(IDENTIFIER);
				setState(76);
				match(T__1);
				}
				break;
			}
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(79);
				funcNode();
				}
				break;
			case 2:
				{
				setState(80);
				pathNode();
				}
				break;
			case 3:
				{
				setState(81);
				dataNode();
				}
				break;
			case 4:
				{
				setState(82);
				arithmetic(0);
				}
				break;
			case 5:
				{
				setState(83);
				otherNode();
				}
				break;
			}
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(86);
					formula();
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OtherNodeContext extends ParserRuleContext {
		public List<TerminalNode> DIV() { return getTokens(FormulaParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FormulaParser.DIV, i);
		}
		public List<TerminalNode> WELL() { return getTokens(FormulaParser.WELL); }
		public TerminalNode WELL(int i) {
			return getToken(FormulaParser.WELL, i);
		}
		public List<TerminalNode> AND() { return getTokens(FormulaParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(FormulaParser.AND, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(FormulaParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(FormulaParser.SEMICOLON, i);
		}
		public List<TerminalNode> PERCENTAGE() { return getTokens(FormulaParser.PERCENTAGE); }
		public TerminalNode PERCENTAGE(int i) {
			return getToken(FormulaParser.PERCENTAGE, i);
		}
		public List<TerminalNode> AT() { return getTokens(FormulaParser.AT); }
		public TerminalNode AT(int i) {
			return getToken(FormulaParser.AT, i);
		}
		public List<TerminalNode> DOT() { return getTokens(FormulaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(FormulaParser.DOT, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FormulaParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FormulaParser.COLON, i);
		}
		public List<TerminalNode> MUL() { return getTokens(FormulaParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(FormulaParser.MUL, i);
		}
		public List<TerminalNode> QUE_MARK() { return getTokens(FormulaParser.QUE_MARK); }
		public TerminalNode QUE_MARK(int i) {
			return getToken(FormulaParser.QUE_MARK, i);
		}
		public List<TerminalNode> SIGH() { return getTokens(FormulaParser.SIGH); }
		public TerminalNode SIGH(int i) {
			return getToken(FormulaParser.SIGH, i);
		}
		public List<TerminalNode> SUB() { return getTokens(FormulaParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(FormulaParser.SUB, i);
		}
		public List<TerminalNode> DOLLAR() { return getTokens(FormulaParser.DOLLAR); }
		public TerminalNode DOLLAR(int i) {
			return getToken(FormulaParser.DOLLAR, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(FormulaParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(FormulaParser.SPACE, i);
		}
		public OtherNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterOtherNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitOtherNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitOtherNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherNodeContext otherNode() throws RecognitionException {
		OtherNodeContext _localctx = new OtherNodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_otherNode);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(92);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4261068668928L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(95); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataNodeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public TerminalNode SingleQuoteAnyText() { return getToken(FormulaParser.SingleQuoteAnyText, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public ChineseStringContext chineseString() {
			return getRuleContext(ChineseStringContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(FormulaParser.LBRACKET, 0); }
		public List<DataNodeContext> dataNode() {
			return getRuleContexts(DataNodeContext.class);
		}
		public DataNodeContext dataNode(int i) {
			return getRuleContext(DataNodeContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(FormulaParser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FormulaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode LCURLY() { return getToken(FormulaParser.LCURLY, 0); }
		public List<KeyValContext> keyVal() {
			return getRuleContexts(KeyValContext.class);
		}
		public KeyValContext keyVal(int i) {
			return getRuleContext(KeyValContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(FormulaParser.RCURLY, 0); }
		public List<KeyFormulaContext> keyFormula() {
			return getRuleContexts(KeyFormulaContext.class);
		}
		public KeyFormulaContext keyFormula(int i) {
			return getRuleContext(KeyFormulaContext.class,i);
		}
		public DataNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterDataNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitDataNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitDataNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataNodeContext dataNode() throws RecognitionException {
		DataNodeContext _localctx = new DataNodeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dataNode);
		int _la;
		try {
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(SingleQuoteAnyText);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				decimal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				chineseString();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				match(LBRACKET);
				setState(102);
				dataNode();
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(103);
					match(COMMA);
					setState(104);
					dataNode();
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110);
				match(RBRACKET);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				match(LBRACKET);
				setState(113);
				formula();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(114);
					match(COMMA);
					setState(115);
					formula();
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(RBRACKET);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(123);
				match(LCURLY);
				setState(124);
				keyVal();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(125);
					match(COMMA);
					setState(126);
					keyVal();
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				match(RCURLY);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(134);
				match(LCURLY);
				setState(135);
				keyFormula();
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(136);
					match(COMMA);
					setState(137);
					keyFormula();
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				match(RCURLY);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyValContext extends ParserRuleContext {
		public Token key;
		public Token val;
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(FormulaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(FormulaParser.IDENTIFIER, i);
		}
		public TerminalNode SingleQuoteAnyText() { return getToken(FormulaParser.SingleQuoteAnyText, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public KeyValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterKeyVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitKeyVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitKeyVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyValContext keyVal() throws RecognitionException {
		KeyValContext _localctx = new KeyValContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_keyVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			((KeyValContext)_localctx).key = match(IDENTIFIER);
			setState(148);
			match(COLON);
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(149);
				((KeyValContext)_localctx).val = match(IDENTIFIER);
				}
				break;
			case SingleQuoteAnyText:
				{
				setState(150);
				match(SingleQuoteAnyText);
				}
				break;
			case SUB:
			case INT:
			case FLOAT:
				{
				setState(151);
				decimal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyFormulaContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public KeyFormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyFormula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterKeyFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitKeyFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitKeyFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyFormulaContext keyFormula() throws RecognitionException {
		KeyFormulaContext _localctx = new KeyFormulaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_keyFormula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(IDENTIFIER);
			setState(155);
			match(COLON);
			setState(156);
			formula();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PathNodeContext extends ParserRuleContext {
		public JsonpathContext jsonpath() {
			return getRuleContext(JsonpathContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(FormulaParser.RCURLY, 0); }
		public PathNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterPathNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitPathNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitPathNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathNodeContext pathNode() throws RecognitionException {
		PathNodeContext _localctx = new PathNodeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_pathNode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__2);
			setState(159);
			jsonpath();
			setState(160);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncNodeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(FormulaParser.RCURLY, 0); }
		public TerminalNode DOT() { return getToken(FormulaParser.DOT, 0); }
		public JsonpathContext jsonpath() {
			return getRuleContext(JsonpathContext.class,0);
		}
		public FuncNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFuncNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFuncNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitFuncNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNodeContext funcNode() throws RecognitionException {
		FuncNodeContext _localctx = new FuncNodeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__3);
			setState(163);
			match(IDENTIFIER);
			setState(164);
			params();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(165);
				match(DOT);
				setState(166);
				jsonpath();
				}
			}

			setState(169);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FormulaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_params);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(T__4);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				match(T__0);
				setState(173);
				param();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(174);
					match(COMMA);
					setState(175);
					param();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_param);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(IDENTIFIER);
				setState(186);
				match(COLON);
				setState(187);
				formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
				formula();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonpathContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public RecursionContext recursion() {
			return getRuleContext(RecursionContext.class,0);
		}
		public List<SubscriptContext> subscript() {
			return getRuleContexts(SubscriptContext.class);
		}
		public SubscriptContext subscript(int i) {
			return getRuleContext(SubscriptContext.class,i);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public JsonpathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonpath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterJsonpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitJsonpath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitJsonpath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonpathContext jsonpath() throws RecognitionException {
		JsonpathContext _localctx = new JsonpathContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jsonpath);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(191);
				recursion();
				}
			}

			setState(194);
			match(IDENTIFIER);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(195);
					subscript();
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(201);
				function();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubscriptContext extends ParserRuleContext {
		public DotIdentifierContext dotIdentifier() {
			return getRuleContext(DotIdentifierContext.class,0);
		}
		public DotAllContext dotAll() {
			return getRuleContext(DotAllContext.class,0);
		}
		public ArraySubscriptContext arraySubscript() {
			return getRuleContext(ArraySubscriptContext.class,0);
		}
		public RecursionContext recursion() {
			return getRuleContext(RecursionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public SubscriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subscript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterSubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitSubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitSubscript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubscriptContext subscript() throws RecognitionException {
		SubscriptContext _localctx = new SubscriptContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_subscript);
		try {
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				dotIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				dotAll();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(206);
				arraySubscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(207);
				recursion();
				setState(208);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DotIdentifierContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(FormulaParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public DotIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterDotIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitDotIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitDotIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DotIdentifierContext dotIdentifier() throws RecognitionException {
		DotIdentifierContext _localctx = new DotIdentifierContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dotIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(DOT);
			setState(213);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(FormulaParser.MUL, 0); }
		public AllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllContext all() throws RecognitionException {
		AllContext _localctx = new AllContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_all);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(MUL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DotAllContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(FormulaParser.DOT, 0); }
		public TerminalNode MUL() { return getToken(FormulaParser.MUL, 0); }
		public DotAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterDotAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitDotAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitDotAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DotAllContext dotAll() throws RecognitionException {
		DotAllContext _localctx = new DotAllContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dotAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(DOT);
			setState(218);
			match(MUL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArraySubscriptContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(FormulaParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(FormulaParser.RBRACKET, 0); }
		public AllContext all() {
			return getRuleContext(AllContext.class,0);
		}
		public ArrayIndexContext arrayIndex() {
			return getRuleContext(ArrayIndexContext.class,0);
		}
		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ArraySubscriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraySubscript; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterArraySubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitArraySubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitArraySubscript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraySubscriptContext arraySubscript() throws RecognitionException {
		ArraySubscriptContext _localctx = new ArraySubscriptContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arraySubscript);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(LBRACKET);
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(221);
				all();
				}
				break;
			case 2:
				{
				setState(222);
				arrayIndex();
				}
				break;
			case 3:
				{
				setState(223);
				interval();
				}
				break;
			case 4:
				{
				setState(224);
				condition();
				}
				break;
			}
			setState(227);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public NotNullContext notNull() {
			return getRuleContext(NotNullContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_condition);
		try {
			setState(231);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUE_MARK:
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				notNull();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotNullContext extends ParserRuleContext {
		public TerminalNode QUE_MARK() { return getToken(FormulaParser.QUE_MARK, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public NotNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notNull; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterNotNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitNotNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitNotNull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotNullContext notNull() throws RecognitionException {
		NotNullContext _localctx = new NotNullContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_notNull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(QUE_MARK);
			setState(234);
			match(T__0);
			setState(235);
			match(IDENTIFIER);
			setState(236);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public FormulaContext data;
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public List<TerminalNode> SPACE() { return getTokens(FormulaParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(FormulaParser.SPACE, i);
		}
		public List<SpaceContext> space() {
			return getRuleContexts(SpaceContext.class);
		}
		public SpaceContext space(int i) {
			return getRuleContext(SpaceContext.class,i);
		}
		public StrOpContext strOp() {
			return getRuleContext(StrOpContext.class,0);
		}
		public TerminalNode IN() { return getToken(FormulaParser.IN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FormulaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}
		public TerminalNode NOT() { return getToken(FormulaParser.NOT, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		int _la;
		try {
			int _alt;
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				match(IDENTIFIER);
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(239);
					match(SPACE);
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(245);
				op();
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(246);
						match(SPACE);
						}
						} 
					}
					setState(251);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				setState(252);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(IDENTIFIER);
				setState(255);
				space();
				setState(256);
				strOp();
				setState(257);
				space();
				setState(258);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				match(IDENTIFIER);
				setState(261);
				space();
				setState(262);
				match(IN);
				setState(263);
				space();
				setState(264);
				match(T__0);
				setState(265);
				formula();
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(266);
					match(COMMA);
					setState(267);
					formula();
					}
					}
					setState(272);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(273);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(275);
				match(IDENTIFIER);
				setState(276);
				space();
				setState(277);
				match(NOT);
				setState(278);
				match(IN);
				setState(279);
				space();
				setState(280);
				match(T__0);
				setState(281);
				formula();
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(282);
					match(COMMA);
					setState(283);
					formula();
					}
					}
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(289);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(FormulaParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(FormulaParser.NEQ, 0); }
		public TerminalNode GE() { return getToken(FormulaParser.GE, 0); }
		public TerminalNode GT() { return getToken(FormulaParser.GT, 0); }
		public TerminalNode LT() { return getToken(FormulaParser.LT, 0); }
		public TerminalNode LE() { return getToken(FormulaParser.LE, 0); }
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 129024L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StrOpContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(FormulaParser.NOT, 0); }
		public TerminalNode SPACE() { return getToken(FormulaParser.SPACE, 0); }
		public TerminalNode RLIKE() { return getToken(FormulaParser.RLIKE, 0); }
		public TerminalNode LIKE() { return getToken(FormulaParser.LIKE, 0); }
		public StrOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterStrOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitStrOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitStrOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrOpContext strOp() throws RecognitionException {
		StrOpContext _localctx = new StrOpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_strOp);
		try {
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(295);
				match(NOT);
				setState(296);
				match(SPACE);
				setState(297);
				match(RLIKE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(RLIKE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(299);
				match(NOT);
				setState(300);
				match(SPACE);
				setState(301);
				match(LIKE);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(302);
				match(LIKE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayIndexContext extends ParserRuleContext {
		public NumIndexContext numIndex() {
			return getRuleContext(NumIndexContext.class,0);
		}
		public List<ListIndexContext> listIndex() {
			return getRuleContexts(ListIndexContext.class);
		}
		public ListIndexContext listIndex(int i) {
			return getRuleContext(ListIndexContext.class,i);
		}
		public ArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterArrayIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitArrayIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitArrayIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arrayIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			numIndex();
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(306);
				listIndex();
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListIndexContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(FormulaParser.COMMA, 0); }
		public NumIndexContext numIndex() {
			return getRuleContext(NumIndexContext.class,0);
		}
		public ListIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterListIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitListIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitListIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListIndexContext listIndex() throws RecognitionException {
		ListIndexContext _localctx = new ListIndexContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_listIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(COMMA);
			setState(313);
			numIndex();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumIndexContext extends ParserRuleContext {
		public PathNodeContext pathNode() {
			return getRuleContext(PathNodeContext.class,0);
		}
		public FuncNodeContext funcNode() {
			return getRuleContext(FuncNodeContext.class,0);
		}
		public TerminalNode INT() { return getToken(FormulaParser.INT, 0); }
		public TerminalNode SUB() { return getToken(FormulaParser.SUB, 0); }
		public NumIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterNumIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitNumIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitNumIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumIndexContext numIndex() throws RecognitionException {
		NumIndexContext _localctx = new NumIndexContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_numIndex);
		int _la;
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				pathNode();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				funcNode();
				}
				break;
			case SUB:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(317);
					match(SUB);
					}
				}

				setState(320);
				match(INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntervalContext extends ParserRuleContext {
		public ArrayIntervalContext arrayInterval() {
			return getRuleContext(ArrayIntervalContext.class,0);
		}
		public FirstIntervalContext firstInterval() {
			return getRuleContext(FirstIntervalContext.class,0);
		}
		public ToIntervalContext toInterval() {
			return getRuleContext(ToIntervalContext.class,0);
		}
		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_interval);
		try {
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				arrayInterval();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				firstInterval();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				toInterval();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayIntervalContext extends ParserRuleContext {
		public NumIndexContext start;
		public NumIndexContext end;
		public NumIndexContext step;
		public List<TerminalNode> COLON() { return getTokens(FormulaParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FormulaParser.COLON, i);
		}
		public List<NumIndexContext> numIndex() {
			return getRuleContexts(NumIndexContext.class);
		}
		public NumIndexContext numIndex(int i) {
			return getRuleContext(NumIndexContext.class,i);
		}
		public ArrayIntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInterval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterArrayInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitArrayInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitArrayInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIntervalContext arrayInterval() throws RecognitionException {
		ArrayIntervalContext _localctx = new ArrayIntervalContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arrayInterval);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			((ArrayIntervalContext)_localctx).start = numIndex();
			setState(329);
			match(COLON);
			setState(330);
			((ArrayIntervalContext)_localctx).end = numIndex();
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(331);
				match(COLON);
				setState(332);
				((ArrayIntervalContext)_localctx).step = numIndex();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ToIntervalContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public NumIndexContext numIndex() {
			return getRuleContext(NumIndexContext.class,0);
		}
		public ToIntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_toInterval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterToInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitToInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitToInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ToIntervalContext toInterval() throws RecognitionException {
		ToIntervalContext _localctx = new ToIntervalContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_toInterval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			match(COLON);
			setState(336);
			numIndex();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FirstIntervalContext extends ParserRuleContext {
		public NumIndexContext numIndex() {
			return getRuleContext(NumIndexContext.class,0);
		}
		public TerminalNode COLON() { return getToken(FormulaParser.COLON, 0); }
		public FirstIntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_firstInterval; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFirstInterval(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFirstInterval(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitFirstInterval(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FirstIntervalContext firstInterval() throws RecognitionException {
		FirstIntervalContext _localctx = new FirstIntervalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_firstInterval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			numIndex();
			setState(339);
			match(COLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecursionContext extends ParserRuleContext {
		public List<TerminalNode> DOT() { return getTokens(FormulaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(FormulaParser.DOT, i);
		}
		public RecursionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recursion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterRecursion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitRecursion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitRecursion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecursionContext recursion() throws RecognitionException {
		RecursionContext _localctx = new RecursionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_recursion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(DOT);
			setState(342);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(FormulaParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			match(DOT);
			setState(345);
			match(IDENTIFIER);
			setState(346);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecimalContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FormulaParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(FormulaParser.FLOAT, 0); }
		public TerminalNode SUB() { return getToken(FormulaParser.SUB, 0); }
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		DecimalContext _localctx = new DecimalContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB) {
				{
				setState(348);
				match(SUB);
				}
			}

			setState(351);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticContext extends ParserRuleContext {
		public ArithmeticContext left;
		public ArithmeticContext right;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public MuldivContext muldiv() {
			return getRuleContext(MuldivContext.class,0);
		}
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public AddsubContext addsub() {
			return getRuleContext(AddsubContext.class,0);
		}
		public ArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticContext arithmetic() throws RecognitionException {
		return arithmetic(0);
	}

	private ArithmeticContext arithmetic(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticContext _localctx = new ArithmeticContext(_ctx, _parentState);
		ArithmeticContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_arithmetic, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(354);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(366);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(364);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(356);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(357);
						muldiv();
						setState(358);
						((ArithmeticContext)_localctx).right = arithmetic(3);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(360);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(361);
						addsub();
						setState(362);
						((ArithmeticContext)_localctx).right = arithmetic(2);
						}
						break;
					}
					} 
				}
				setState(368);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinArithmeticContext extends ParserRuleContext {
		public ArithmeticContext left;
		public ArithmeticContext right;
		public MuldivContext muldiv() {
			return getRuleContext(MuldivContext.class,0);
		}
		public List<ArithmeticContext> arithmetic() {
			return getRuleContexts(ArithmeticContext.class);
		}
		public ArithmeticContext arithmetic(int i) {
			return getRuleContext(ArithmeticContext.class,i);
		}
		public AddsubContext addsub() {
			return getRuleContext(AddsubContext.class,0);
		}
		public BinArithmeticContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binArithmetic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterBinArithmetic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitBinArithmetic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitBinArithmetic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinArithmeticContext binArithmetic() throws RecognitionException {
		BinArithmeticContext _localctx = new BinArithmeticContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_binArithmetic);
		try {
			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(370);
				muldiv();
				setState(371);
				((BinArithmeticContext)_localctx).right = arithmetic(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(374);
				addsub();
				setState(375);
				((BinArithmeticContext)_localctx).right = arithmetic(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddsubContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(FormulaParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(FormulaParser.SUB, 0); }
		public AddsubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addsub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterAddsub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitAddsub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitAddsub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddsubContext addsub() throws RecognitionException {
		AddsubContext _localctx = new AddsubContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_addsub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MuldivContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(FormulaParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(FormulaParser.DIV, 0); }
		public TerminalNode PERCENTAGE() { return getToken(FormulaParser.PERCENTAGE, 0); }
		public MuldivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_muldiv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterMuldiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitMuldiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitMuldiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MuldivContext muldiv() throws RecognitionException {
		MuldivContext _localctx = new MuldivContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_muldiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public BinArithmeticContext binArithmetic() {
			return getRuleContext(BinArithmeticContext.class,0);
		}
		public PathNodeContext pathNode() {
			return getRuleContext(PathNodeContext.class,0);
		}
		public FuncNodeContext funcNode() {
			return getRuleContext(FuncNodeContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_factor);
		try {
			setState(390);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				decimal();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				match(T__0);
				setState(385);
				binArithmetic();
				setState(386);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(388);
				pathNode();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(389);
				funcNode();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SpaceContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(FormulaParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(FormulaParser.SPACE, i);
		}
		public SpaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_space; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterSpace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitSpace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitSpace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpaceContext space() throws RecognitionException {
		SpaceContext _localctx = new SpaceContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_space);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(393); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(392);
					match(SPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(395); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ChineseStringContext extends ParserRuleContext {
		public List<TerminalNode> CHINESE() { return getTokens(FormulaParser.CHINESE); }
		public TerminalNode CHINESE(int i) {
			return getToken(FormulaParser.CHINESE, i);
		}
		public ChineseStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chineseString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).enterChineseString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormulaListener ) ((FormulaListener)listener).exitChineseString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormulaVisitor ) return ((FormulaVisitor<? extends T>)visitor).visitChineseString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChineseStringContext chineseString() throws RecognitionException {
		ChineseStringContext _localctx = new ChineseStringContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_chineseString);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(398); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(397);
					match(CHINESE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(400); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 30:
			return arithmetic_sempred((ArithmeticContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetic_sempred(ArithmeticContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u0193\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"N\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u0000U\b\u0000\u0001\u0000\u0005\u0000X\b\u0000\n\u0000\f\u0000"+
		"[\t\u0000\u0001\u0001\u0004\u0001^\b\u0001\u000b\u0001\f\u0001_\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002j\b\u0002\n\u0002\f\u0002m\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002u\b\u0002\n\u0002\f\u0002x\t\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u0080\b\u0002\n"+
		"\u0002\f\u0002\u0083\t\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u008b\b\u0002\n\u0002\f\u0002"+
		"\u008e\t\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0092\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0099"+
		"\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00a8\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00b1"+
		"\b\u0007\n\u0007\f\u0007\u00b4\t\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00b8\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00be\b\b\u0001"+
		"\t\u0003\t\u00c1\b\t\u0001\t\u0001\t\u0005\t\u00c5\b\t\n\t\f\t\u00c8\t"+
		"\t\u0001\t\u0003\t\u00cb\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00d3\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00e2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00e8\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0005\u0011\u00f1\b\u0011"+
		"\n\u0011\f\u0011\u00f4\t\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00f8"+
		"\b\u0011\n\u0011\f\u0011\u00fb\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u010d\b\u0011\n\u0011\f\u0011\u0110\t\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u011d"+
		"\b\u0011\n\u0011\f\u0011\u0120\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u0124\b\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u0130\b\u0013\u0001\u0014\u0001\u0014\u0005\u0014\u0134\b\u0014\n\u0014"+
		"\f\u0014\u0137\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u013f\b\u0016\u0001\u0016\u0003\u0016"+
		"\u0142\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0147\b"+
		"\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u014e\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0003\u001d\u015e\b\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0005\u001e\u016d\b\u001e\n\u001e\f\u001e\u0170\t\u001e\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0003\u001f\u017a\b\u001f\u0001 \u0001 \u0001!\u0001!\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0187\b\"\u0001"+
		"#\u0004#\u018a\b#\u000b#\f#\u018b\u0001$\u0004$\u018f\b$\u000b$\f$\u0190"+
		"\u0001$\u0000\u0001<%\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFH\u0000\u0005"+
		"\u0004\u0000\u0012\u0016\u0018\u0019\u001b\u001c%)\u0001\u0000\u000b\u0010"+
		"\u0001\u0000!\"\u0001\u0000\u0011\u0012\u0001\u0000\u0013\u0015\u01a9"+
		"\u0000M\u0001\u0000\u0000\u0000\u0002]\u0001\u0000\u0000\u0000\u0004\u0091"+
		"\u0001\u0000\u0000\u0000\u0006\u0093\u0001\u0000\u0000\u0000\b\u009a\u0001"+
		"\u0000\u0000\u0000\n\u009e\u0001\u0000\u0000\u0000\f\u00a2\u0001\u0000"+
		"\u0000\u0000\u000e\u00b7\u0001\u0000\u0000\u0000\u0010\u00bd\u0001\u0000"+
		"\u0000\u0000\u0012\u00c0\u0001\u0000\u0000\u0000\u0014\u00d2\u0001\u0000"+
		"\u0000\u0000\u0016\u00d4\u0001\u0000\u0000\u0000\u0018\u00d7\u0001\u0000"+
		"\u0000\u0000\u001a\u00d9\u0001\u0000\u0000\u0000\u001c\u00dc\u0001\u0000"+
		"\u0000\u0000\u001e\u00e7\u0001\u0000\u0000\u0000 \u00e9\u0001\u0000\u0000"+
		"\u0000\"\u0123\u0001\u0000\u0000\u0000$\u0125\u0001\u0000\u0000\u0000"+
		"&\u012f\u0001\u0000\u0000\u0000(\u0131\u0001\u0000\u0000\u0000*\u0138"+
		"\u0001\u0000\u0000\u0000,\u0141\u0001\u0000\u0000\u0000.\u0146\u0001\u0000"+
		"\u0000\u00000\u0148\u0001\u0000\u0000\u00002\u014f\u0001\u0000\u0000\u0000"+
		"4\u0152\u0001\u0000\u0000\u00006\u0155\u0001\u0000\u0000\u00008\u0158"+
		"\u0001\u0000\u0000\u0000:\u015d\u0001\u0000\u0000\u0000<\u0161\u0001\u0000"+
		"\u0000\u0000>\u0179\u0001\u0000\u0000\u0000@\u017b\u0001\u0000\u0000\u0000"+
		"B\u017d\u0001\u0000\u0000\u0000D\u0186\u0001\u0000\u0000\u0000F\u0189"+
		"\u0001\u0000\u0000\u0000H\u018e\u0001\u0000\u0000\u0000JK\u0005\u0001"+
		"\u0000\u0000KL\u0005#\u0000\u0000LN\u0005\u0002\u0000\u0000MJ\u0001\u0000"+
		"\u0000\u0000MN\u0001\u0000\u0000\u0000NT\u0001\u0000\u0000\u0000OU\u0003"+
		"\f\u0006\u0000PU\u0003\n\u0005\u0000QU\u0003\u0004\u0002\u0000RU\u0003"+
		"<\u001e\u0000SU\u0003\u0002\u0001\u0000TO\u0001\u0000\u0000\u0000TP\u0001"+
		"\u0000\u0000\u0000TQ\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000"+
		"TS\u0001\u0000\u0000\u0000UY\u0001\u0000\u0000\u0000VX\u0003\u0000\u0000"+
		"\u0000WV\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0001\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000\\^\u0007\u0000\u0000\u0000]\\\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`\u0003\u0001\u0000\u0000\u0000a\u0092\u0005#\u0000\u0000"+
		"b\u0092\u0005\u0006\u0000\u0000c\u0092\u0003:\u001d\u0000d\u0092\u0003"+
		"H$\u0000ef\u0005\u001f\u0000\u0000fk\u0003\u0004\u0002\u0000gh\u0005\u001a"+
		"\u0000\u0000hj\u0003\u0004\u0002\u0000ig\u0001\u0000\u0000\u0000jm\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"ln\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005 \u0000\u0000"+
		"o\u0092\u0001\u0000\u0000\u0000pq\u0005\u001f\u0000\u0000qv\u0003\u0000"+
		"\u0000\u0000rs\u0005\u001a\u0000\u0000su\u0003\u0000\u0000\u0000tr\u0001"+
		"\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000"+
		"vw\u0001\u0000\u0000\u0000wy\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000"+
		"\u0000yz\u0005 \u0000\u0000z\u0092\u0001\u0000\u0000\u0000{|\u0005\u001d"+
		"\u0000\u0000|\u0081\u0003\u0006\u0003\u0000}~\u0005\u001a\u0000\u0000"+
		"~\u0080\u0003\u0006\u0003\u0000\u007f}\u0001\u0000\u0000\u0000\u0080\u0083"+
		"\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083\u0081"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u001e\u0000\u0000\u0085\u0092"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u001d\u0000\u0000\u0087\u008c"+
		"\u0003\b\u0004\u0000\u0088\u0089\u0005\u001a\u0000\u0000\u0089\u008b\u0003"+
		"\b\u0004\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008e\u0001\u0000"+
		"\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000"+
		"\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0005\u001e\u0000\u0000\u0090\u0092\u0001\u0000"+
		"\u0000\u0000\u0091a\u0001\u0000\u0000\u0000\u0091b\u0001\u0000\u0000\u0000"+
		"\u0091c\u0001\u0000\u0000\u0000\u0091d\u0001\u0000\u0000\u0000\u0091e"+
		"\u0001\u0000\u0000\u0000\u0091p\u0001\u0000\u0000\u0000\u0091{\u0001\u0000"+
		"\u0000\u0000\u0091\u0086\u0001\u0000\u0000\u0000\u0092\u0005\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0005#\u0000\u0000\u0094\u0098\u0005)\u0000\u0000"+
		"\u0095\u0099\u0005#\u0000\u0000\u0096\u0099\u0005\u0006\u0000\u0000\u0097"+
		"\u0099\u0003:\u001d\u0000\u0098\u0095\u0001\u0000\u0000\u0000\u0098\u0096"+
		"\u0001\u0000\u0000\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u0007"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0005#\u0000\u0000\u009b\u009c\u0005"+
		")\u0000\u0000\u009c\u009d\u0003\u0000\u0000\u0000\u009d\t\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0005\u0003\u0000\u0000\u009f\u00a0\u0003\u0012"+
		"\t\u0000\u00a0\u00a1\u0005\u001e\u0000\u0000\u00a1\u000b\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0005\u0004\u0000\u0000\u00a3\u00a4\u0005#\u0000\u0000"+
		"\u00a4\u00a7\u0003\u000e\u0007\u0000\u00a5\u00a6\u0005\u001b\u0000\u0000"+
		"\u00a6\u00a8\u0003\u0012\t\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0005\u001e\u0000\u0000\u00aa\r\u0001\u0000\u0000\u0000\u00ab\u00b8"+
		"\u0005\u0005\u0000\u0000\u00ac\u00ad\u0005\u0001\u0000\u0000\u00ad\u00b2"+
		"\u0003\u0010\b\u0000\u00ae\u00af\u0005\u001a\u0000\u0000\u00af\u00b1\u0003"+
		"\u0010\b\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0005\u0002\u0000\u0000\u00b6\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b7\u00ab\u0001\u0000\u0000\u0000\u00b7\u00ac\u0001\u0000"+
		"\u0000\u0000\u00b8\u000f\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005#\u0000"+
		"\u0000\u00ba\u00bb\u0005)\u0000\u0000\u00bb\u00be\u0003\u0000\u0000\u0000"+
		"\u00bc\u00be\u0003\u0000\u0000\u0000\u00bd\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bd\u00bc\u0001\u0000\u0000\u0000\u00be\u0011\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c1\u00036\u001b\u0000\u00c0\u00bf\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c6\u0005#\u0000\u0000\u00c3\u00c5\u0003\u0014\n\u0000\u00c4\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00ca"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00cb"+
		"\u00038\u001c\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001"+
		"\u0000\u0000\u0000\u00cb\u0013\u0001\u0000\u0000\u0000\u00cc\u00d3\u0003"+
		"\u0016\u000b\u0000\u00cd\u00d3\u0003\u001a\r\u0000\u00ce\u00d3\u0003\u001c"+
		"\u000e\u0000\u00cf\u00d0\u00036\u001b\u0000\u00d0\u00d1\u0005#\u0000\u0000"+
		"\u00d1\u00d3\u0001\u0000\u0000\u0000\u00d2\u00cc\u0001\u0000\u0000\u0000"+
		"\u00d2\u00cd\u0001\u0000\u0000\u0000\u00d2\u00ce\u0001\u0000\u0000\u0000"+
		"\u00d2\u00cf\u0001\u0000\u0000\u0000\u00d3\u0015\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0005\u001b\u0000\u0000\u00d5\u00d6\u0005#\u0000\u0000\u00d6"+
		"\u0017\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005\u0013\u0000\u0000\u00d8"+
		"\u0019\u0001\u0000\u0000\u0000\u00d9\u00da\u0005\u001b\u0000\u0000\u00da"+
		"\u00db\u0005\u0013\u0000\u0000\u00db\u001b\u0001\u0000\u0000\u0000\u00dc"+
		"\u00e1\u0005\u001f\u0000\u0000\u00dd\u00e2\u0003\u0018\f\u0000\u00de\u00e2"+
		"\u0003(\u0014\u0000\u00df\u00e2\u0003.\u0017\u0000\u00e0\u00e2\u0003\u001e"+
		"\u000f\u0000\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e1\u00de\u0001\u0000"+
		"\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005 \u0000"+
		"\u0000\u00e4\u001d\u0001\u0000\u0000\u0000\u00e5\u00e8\u0003 \u0010\u0000"+
		"\u00e6\u00e8\u0003\"\u0011\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e7"+
		"\u00e6\u0001\u0000\u0000\u0000\u00e8\u001f\u0001\u0000\u0000\u0000\u00e9"+
		"\u00ea\u0005\u0018\u0000\u0000\u00ea\u00eb\u0005\u0001\u0000\u0000\u00eb"+
		"\u00ec\u0005#\u0000\u0000\u00ec\u00ed\u0005\u0002\u0000\u0000\u00ed!\u0001"+
		"\u0000\u0000\u0000\u00ee\u00f2\u0005#\u0000\u0000\u00ef\u00f1\u0005\u0016"+
		"\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f9\u0003$\u0012\u0000\u00f6\u00f8\u0005\u0016\u0000"+
		"\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u00fb\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00fa\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000"+
		"\u0000\u00fc\u00fd\u0003\u0000\u0000\u0000\u00fd\u0124\u0001\u0000\u0000"+
		"\u0000\u00fe\u00ff\u0005#\u0000\u0000\u00ff\u0100\u0003F#\u0000\u0100"+
		"\u0101\u0003&\u0013\u0000\u0101\u0102\u0003F#\u0000\u0102\u0103\u0003"+
		"\u0000\u0000\u0000\u0103\u0124\u0001\u0000\u0000\u0000\u0104\u0105\u0005"+
		"#\u0000\u0000\u0105\u0106\u0003F#\u0000\u0106\u0107\u0005\u0007\u0000"+
		"\u0000\u0107\u0108\u0003F#\u0000\u0108\u0109\u0005\u0001\u0000\u0000\u0109"+
		"\u010e\u0003\u0000\u0000\u0000\u010a\u010b\u0005\u001a\u0000\u0000\u010b"+
		"\u010d\u0003\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010d"+
		"\u0110\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000\u0000\u0000\u010e"+
		"\u010f\u0001\u0000\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110"+
		"\u010e\u0001\u0000\u0000\u0000\u0111\u0112\u0005\u0002\u0000\u0000\u0112"+
		"\u0124\u0001\u0000\u0000\u0000\u0113\u0114\u0005#\u0000\u0000\u0114\u0115"+
		"\u0003F#\u0000\u0115\u0116\u0005\b\u0000\u0000\u0116\u0117\u0005\u0007"+
		"\u0000\u0000\u0117\u0118\u0003F#\u0000\u0118\u0119\u0005\u0001\u0000\u0000"+
		"\u0119\u011e\u0003\u0000\u0000\u0000\u011a\u011b\u0005\u001a\u0000\u0000"+
		"\u011b\u011d\u0003\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000"+
		"\u011d\u0120\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000"+
		"\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0121\u0001\u0000\u0000\u0000"+
		"\u0120\u011e\u0001\u0000\u0000\u0000\u0121\u0122\u0005\u0002\u0000\u0000"+
		"\u0122\u0124\u0001\u0000\u0000\u0000\u0123\u00ee\u0001\u0000\u0000\u0000"+
		"\u0123\u00fe\u0001\u0000\u0000\u0000\u0123\u0104\u0001\u0000\u0000\u0000"+
		"\u0123\u0113\u0001\u0000\u0000\u0000\u0124#\u0001\u0000\u0000\u0000\u0125"+
		"\u0126\u0007\u0001\u0000\u0000\u0126%\u0001\u0000\u0000\u0000\u0127\u0128"+
		"\u0005\b\u0000\u0000\u0128\u0129\u0005\u0016\u0000\u0000\u0129\u0130\u0005"+
		"\n\u0000\u0000\u012a\u0130\u0005\n\u0000\u0000\u012b\u012c\u0005\b\u0000"+
		"\u0000\u012c\u012d\u0005\u0016\u0000\u0000\u012d\u0130\u0005\t\u0000\u0000"+
		"\u012e\u0130\u0005\t\u0000\u0000\u012f\u0127\u0001\u0000\u0000\u0000\u012f"+
		"\u012a\u0001\u0000\u0000\u0000\u012f\u012b\u0001\u0000\u0000\u0000\u012f"+
		"\u012e\u0001\u0000\u0000\u0000\u0130\'\u0001\u0000\u0000\u0000\u0131\u0135"+
		"\u0003,\u0016\u0000\u0132\u0134\u0003*\u0015\u0000\u0133\u0132\u0001\u0000"+
		"\u0000\u0000\u0134\u0137\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136)\u0001\u0000\u0000"+
		"\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0138\u0139\u0005\u001a\u0000"+
		"\u0000\u0139\u013a\u0003,\u0016\u0000\u013a+\u0001\u0000\u0000\u0000\u013b"+
		"\u0142\u0003\n\u0005\u0000\u013c\u0142\u0003\f\u0006\u0000\u013d\u013f"+
		"\u0005\u0012\u0000\u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u0001\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0142"+
		"\u0005!\u0000\u0000\u0141\u013b\u0001\u0000\u0000\u0000\u0141\u013c\u0001"+
		"\u0000\u0000\u0000\u0141\u013e\u0001\u0000\u0000\u0000\u0142-\u0001\u0000"+
		"\u0000\u0000\u0143\u0147\u00030\u0018\u0000\u0144\u0147\u00034\u001a\u0000"+
		"\u0145\u0147\u00032\u0019\u0000\u0146\u0143\u0001\u0000\u0000\u0000\u0146"+
		"\u0144\u0001\u0000\u0000\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0147"+
		"/\u0001\u0000\u0000\u0000\u0148\u0149\u0003,\u0016\u0000\u0149\u014a\u0005"+
		")\u0000\u0000\u014a\u014d\u0003,\u0016\u0000\u014b\u014c\u0005)\u0000"+
		"\u0000\u014c\u014e\u0003,\u0016\u0000\u014d\u014b\u0001\u0000\u0000\u0000"+
		"\u014d\u014e\u0001\u0000\u0000\u0000\u014e1\u0001\u0000\u0000\u0000\u014f"+
		"\u0150\u0005)\u0000\u0000\u0150\u0151\u0003,\u0016\u0000\u01513\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u0003,\u0016\u0000\u0153\u0154\u0005)\u0000"+
		"\u0000\u01545\u0001\u0000\u0000\u0000\u0155\u0156\u0005\u001b\u0000\u0000"+
		"\u0156\u0157\u0005\u001b\u0000\u0000\u01577\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0005\u001b\u0000\u0000\u0159\u015a\u0005#\u0000\u0000\u015a\u015b"+
		"\u0005\u0005\u0000\u0000\u015b9\u0001\u0000\u0000\u0000\u015c\u015e\u0005"+
		"\u0012\u0000\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015d\u015e\u0001"+
		"\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0160\u0007"+
		"\u0002\u0000\u0000\u0160;\u0001\u0000\u0000\u0000\u0161\u0162\u0006\u001e"+
		"\uffff\uffff\u0000\u0162\u0163\u0003D\"\u0000\u0163\u016e\u0001\u0000"+
		"\u0000\u0000\u0164\u0165\n\u0002\u0000\u0000\u0165\u0166\u0003B!\u0000"+
		"\u0166\u0167\u0003<\u001e\u0003\u0167\u016d\u0001\u0000\u0000\u0000\u0168"+
		"\u0169\n\u0001\u0000\u0000\u0169\u016a\u0003@ \u0000\u016a\u016b\u0003"+
		"<\u001e\u0002\u016b\u016d\u0001\u0000\u0000\u0000\u016c\u0164\u0001\u0000"+
		"\u0000\u0000\u016c\u0168\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000"+
		"\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000"+
		"\u0000\u0000\u016f=\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000"+
		"\u0000\u0171\u0172\u0003<\u001e\u0000\u0172\u0173\u0003B!\u0000\u0173"+
		"\u0174\u0003<\u001e\u0000\u0174\u017a\u0001\u0000\u0000\u0000\u0175\u0176"+
		"\u0003<\u001e\u0000\u0176\u0177\u0003@ \u0000\u0177\u0178\u0003<\u001e"+
		"\u0000\u0178\u017a\u0001\u0000\u0000\u0000\u0179\u0171\u0001\u0000\u0000"+
		"\u0000\u0179\u0175\u0001\u0000\u0000\u0000\u017a?\u0001\u0000\u0000\u0000"+
		"\u017b\u017c\u0007\u0003\u0000\u0000\u017cA\u0001\u0000\u0000\u0000\u017d"+
		"\u017e\u0007\u0004\u0000\u0000\u017eC\u0001\u0000\u0000\u0000\u017f\u0187"+
		"\u0003:\u001d\u0000\u0180\u0181\u0005\u0001\u0000\u0000\u0181\u0182\u0003"+
		">\u001f\u0000\u0182\u0183\u0005\u0002\u0000\u0000\u0183\u0187\u0001\u0000"+
		"\u0000\u0000\u0184\u0187\u0003\n\u0005\u0000\u0185\u0187\u0003\f\u0006"+
		"\u0000\u0186\u017f\u0001\u0000\u0000\u0000\u0186\u0180\u0001\u0000\u0000"+
		"\u0000\u0186\u0184\u0001\u0000\u0000\u0000\u0186\u0185\u0001\u0000\u0000"+
		"\u0000\u0187E\u0001\u0000\u0000\u0000\u0188\u018a\u0005\u0016\u0000\u0000"+
		"\u0189\u0188\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000"+
		"\u018b\u0189\u0001\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000\u0000"+
		"\u018cG\u0001\u0000\u0000\u0000\u018d\u018f\u0005$\u0000\u0000\u018e\u018d"+
		"\u0001\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u018e"+
		"\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000\u0000\u0191I\u0001"+
		"\u0000\u0000\u0000&MTY_kv\u0081\u008c\u0091\u0098\u00a7\u00b2\u00b7\u00bd"+
		"\u00c0\u00c6\u00ca\u00d2\u00e1\u00e7\u00f2\u00f9\u010e\u011e\u0123\u012f"+
		"\u0135\u013e\u0141\u0146\u014d\u015d\u016c\u016e\u0179\u0186\u018b\u0190";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}