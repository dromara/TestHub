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
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(FormulaParser.RBRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FormulaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FormulaParser.COMMA, i);
		}
		public TerminalNode LCURLY() { return getToken(FormulaParser.LCURLY, 0); }
		public List<KeyFormulaContext> keyFormula() {
			return getRuleContexts(KeyFormulaContext.class);
		}
		public KeyFormulaContext keyFormula(int i) {
			return getRuleContext(KeyFormulaContext.class,i);
		}
		public TerminalNode RCURLY() { return getToken(FormulaParser.RCURLY, 0); }
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
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(IDENTIFIER);
				}
				break;
			case SingleQuoteAnyText:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(SingleQuoteAnyText);
				}
				break;
			case SUB:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				decimal();
				}
				break;
			case CHINESE:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				chineseString();
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				match(LBRACKET);
				setState(102);
				formula();
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(103);
					match(COMMA);
					setState(104);
					formula();
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
			case LCURLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				match(LCURLY);
				setState(113);
				keyFormula();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(114);
					match(COMMA);
					setState(115);
					keyFormula();
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(RCURLY);
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
			setState(125);
			((KeyValContext)_localctx).key = match(IDENTIFIER);
			setState(126);
			match(COLON);
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(127);
				((KeyValContext)_localctx).val = match(IDENTIFIER);
				}
				break;
			case SingleQuoteAnyText:
				{
				setState(128);
				match(SingleQuoteAnyText);
				}
				break;
			case SUB:
			case INT:
			case FLOAT:
				{
				setState(129);
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
			setState(132);
			match(IDENTIFIER);
			setState(133);
			match(COLON);
			setState(134);
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
			setState(136);
			match(T__2);
			setState(137);
			jsonpath();
			setState(138);
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
			setState(140);
			match(T__3);
			setState(141);
			match(IDENTIFIER);
			setState(142);
			params();
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(143);
				match(DOT);
				setState(144);
				jsonpath();
				}
			}

			setState(147);
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
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(T__4);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(150);
				match(T__0);
				setState(151);
				param();
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(152);
					match(COMMA);
					setState(153);
					param();
					}
					}
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(159);
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
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				match(IDENTIFIER);
				setState(164);
				match(COLON);
				setState(165);
				formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
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
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(169);
				recursion();
				}
			}

			setState(172);
			match(IDENTIFIER);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(173);
					subscript();
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(179);
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
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				dotIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				dotAll();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				arraySubscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(185);
				recursion();
				setState(186);
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
			setState(190);
			match(DOT);
			setState(191);
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
			setState(193);
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
			setState(195);
			match(DOT);
			setState(196);
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
			setState(198);
			match(LBRACKET);
			setState(203);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(199);
				all();
				}
				break;
			case 2:
				{
				setState(200);
				arrayIndex();
				}
				break;
			case 3:
				{
				setState(201);
				interval();
				}
				break;
			case 4:
				{
				setState(202);
				condition();
				}
				break;
			}
			setState(205);
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
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUE_MARK:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				notNull();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
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
			setState(211);
			match(QUE_MARK);
			setState(212);
			match(T__0);
			setState(213);
			match(IDENTIFIER);
			setState(214);
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
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				match(IDENTIFIER);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(217);
					match(SPACE);
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(223);
				op();
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(224);
						match(SPACE);
						}
						} 
					}
					setState(229);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				setState(230);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				match(IDENTIFIER);
				setState(233);
				space();
				setState(234);
				strOp();
				setState(235);
				space();
				setState(236);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				match(IDENTIFIER);
				setState(239);
				space();
				setState(240);
				match(IN);
				setState(241);
				space();
				setState(242);
				match(T__0);
				setState(243);
				formula();
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(244);
					match(COMMA);
					setState(245);
					formula();
					}
					}
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(251);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(253);
				match(IDENTIFIER);
				setState(254);
				space();
				setState(255);
				match(NOT);
				setState(256);
				match(IN);
				setState(257);
				space();
				setState(258);
				match(T__0);
				setState(259);
				formula();
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(260);
					match(COMMA);
					setState(261);
					formula();
					}
					}
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(267);
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
			setState(271);
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
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(273);
				match(NOT);
				setState(274);
				match(SPACE);
				setState(275);
				match(RLIKE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(RLIKE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(277);
				match(NOT);
				setState(278);
				match(SPACE);
				setState(279);
				match(LIKE);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(280);
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
			setState(283);
			numIndex();
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(284);
				listIndex();
				}
				}
				setState(289);
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
			setState(290);
			match(COMMA);
			setState(291);
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
			setState(299);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				pathNode();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				funcNode();
				}
				break;
			case SUB:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(295);
					match(SUB);
					}
				}

				setState(298);
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
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				arrayInterval();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				firstInterval();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(303);
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
			setState(306);
			((ArrayIntervalContext)_localctx).start = numIndex();
			setState(307);
			match(COLON);
			setState(308);
			((ArrayIntervalContext)_localctx).end = numIndex();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(309);
				match(COLON);
				setState(310);
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
			setState(313);
			match(COLON);
			setState(314);
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
			setState(316);
			numIndex();
			setState(317);
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
			setState(319);
			match(DOT);
			setState(320);
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
			setState(322);
			match(DOT);
			setState(323);
			match(IDENTIFIER);
			setState(324);
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
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB) {
				{
				setState(326);
				match(SUB);
				}
			}

			setState(329);
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
			setState(332);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(344);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(342);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(334);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(335);
						muldiv();
						setState(336);
						((ArithmeticContext)_localctx).right = arithmetic(3);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(338);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(339);
						addsub();
						setState(340);
						((ArithmeticContext)_localctx).right = arithmetic(2);
						}
						break;
					}
					} 
				}
				setState(346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
			setState(355);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(348);
				muldiv();
				setState(349);
				((BinArithmeticContext)_localctx).right = arithmetic(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(352);
				addsub();
				setState(353);
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
			setState(357);
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
			setState(359);
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
			setState(368);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(361);
				decimal();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				match(T__0);
				setState(363);
				binArithmetic();
				setState(364);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(366);
				pathNode();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(367);
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
			setState(371); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(370);
					match(SPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(373); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
			setState(376); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(375);
					match(CHINESE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(378); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
		"\u0004\u0001*\u017d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\u0002u\b\u0002\n\u0002\f\u0002x\t\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002|\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u0083\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0092\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u009b\b\u0007\n\u0007\f\u0007\u009e\t\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00a2\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0003\b\u00a8\b\b\u0001\t\u0003\t\u00ab\b\t\u0001\t\u0001\t\u0005\t\u00af"+
		"\b\t\n\t\f\t\u00b2\t\t\u0001\t\u0003\t\u00b5\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00bd\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00cc\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00d2\b\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u00db\b\u0011\n\u0011\f\u0011\u00de\t\u0011\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u00e2\b\u0011\n\u0011\f\u0011\u00e5\t\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00f7\b\u0011\n\u0011"+
		"\f\u0011\u00fa\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u0107\b\u0011\n\u0011\f\u0011\u010a\t\u0011\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u010e\b\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u011a\b\u0013\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u011e\b\u0014\n\u0014\f\u0014\u0121\t\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0129\b\u0016"+
		"\u0001\u0016\u0003\u0016\u012c\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u0131\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u0138\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0003\u001d"+
		"\u0148\b\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0005\u001e\u0157\b\u001e\n\u001e\f\u001e\u015a"+
		"\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0164\b\u001f\u0001 \u0001"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0003\"\u0171\b\"\u0001#\u0004#\u0174\b#\u000b#\f#\u0175\u0001$\u0004"+
		"$\u0179\b$\u000b$\f$\u017a\u0001$\u0000\u0001<%\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BDFH\u0000\u0005\u0004\u0000\u0012\u0016\u0018\u0019\u001b\u001c"+
		"%)\u0001\u0000\u000b\u0010\u0001\u0000!\"\u0001\u0000\u0011\u0012\u0001"+
		"\u0000\u0013\u0015\u018f\u0000M\u0001\u0000\u0000\u0000\u0002]\u0001\u0000"+
		"\u0000\u0000\u0004{\u0001\u0000\u0000\u0000\u0006}\u0001\u0000\u0000\u0000"+
		"\b\u0084\u0001\u0000\u0000\u0000\n\u0088\u0001\u0000\u0000\u0000\f\u008c"+
		"\u0001\u0000\u0000\u0000\u000e\u00a1\u0001\u0000\u0000\u0000\u0010\u00a7"+
		"\u0001\u0000\u0000\u0000\u0012\u00aa\u0001\u0000\u0000\u0000\u0014\u00bc"+
		"\u0001\u0000\u0000\u0000\u0016\u00be\u0001\u0000\u0000\u0000\u0018\u00c1"+
		"\u0001\u0000\u0000\u0000\u001a\u00c3\u0001\u0000\u0000\u0000\u001c\u00c6"+
		"\u0001\u0000\u0000\u0000\u001e\u00d1\u0001\u0000\u0000\u0000 \u00d3\u0001"+
		"\u0000\u0000\u0000\"\u010d\u0001\u0000\u0000\u0000$\u010f\u0001\u0000"+
		"\u0000\u0000&\u0119\u0001\u0000\u0000\u0000(\u011b\u0001\u0000\u0000\u0000"+
		"*\u0122\u0001\u0000\u0000\u0000,\u012b\u0001\u0000\u0000\u0000.\u0130"+
		"\u0001\u0000\u0000\u00000\u0132\u0001\u0000\u0000\u00002\u0139\u0001\u0000"+
		"\u0000\u00004\u013c\u0001\u0000\u0000\u00006\u013f\u0001\u0000\u0000\u0000"+
		"8\u0142\u0001\u0000\u0000\u0000:\u0147\u0001\u0000\u0000\u0000<\u014b"+
		"\u0001\u0000\u0000\u0000>\u0163\u0001\u0000\u0000\u0000@\u0165\u0001\u0000"+
		"\u0000\u0000B\u0167\u0001\u0000\u0000\u0000D\u0170\u0001\u0000\u0000\u0000"+
		"F\u0173\u0001\u0000\u0000\u0000H\u0178\u0001\u0000\u0000\u0000JK\u0005"+
		"\u0001\u0000\u0000KL\u0005#\u0000\u0000LN\u0005\u0002\u0000\u0000MJ\u0001"+
		"\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NT\u0001\u0000\u0000\u0000"+
		"OU\u0003\f\u0006\u0000PU\u0003\n\u0005\u0000QU\u0003\u0004\u0002\u0000"+
		"RU\u0003<\u001e\u0000SU\u0003\u0002\u0001\u0000TO\u0001\u0000\u0000\u0000"+
		"TP\u0001\u0000\u0000\u0000TQ\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000"+
		"\u0000TS\u0001\u0000\u0000\u0000UY\u0001\u0000\u0000\u0000VX\u0003\u0000"+
		"\u0000\u0000WV\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001"+
		"\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0001\u0001\u0000\u0000"+
		"\u0000[Y\u0001\u0000\u0000\u0000\\^\u0007\u0000\u0000\u0000]\\\u0001\u0000"+
		"\u0000\u0000^_\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`\u0003\u0001\u0000\u0000\u0000a|\u0005#\u0000\u0000"+
		"b|\u0005\u0006\u0000\u0000c|\u0003:\u001d\u0000d|\u0003H$\u0000ef\u0005"+
		"\u001f\u0000\u0000fk\u0003\u0000\u0000\u0000gh\u0005\u001a\u0000\u0000"+
		"hj\u0003\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000"+
		"\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000ln\u0001\u0000"+
		"\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005 \u0000\u0000o|\u0001\u0000"+
		"\u0000\u0000pq\u0005\u001d\u0000\u0000qv\u0003\b\u0004\u0000rs\u0005\u001a"+
		"\u0000\u0000su\u0003\b\u0004\u0000tr\u0001\u0000\u0000\u0000ux\u0001\u0000"+
		"\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wy\u0001"+
		"\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000yz\u0005\u001e\u0000\u0000"+
		"z|\u0001\u0000\u0000\u0000{a\u0001\u0000\u0000\u0000{b\u0001\u0000\u0000"+
		"\u0000{c\u0001\u0000\u0000\u0000{d\u0001\u0000\u0000\u0000{e\u0001\u0000"+
		"\u0000\u0000{p\u0001\u0000\u0000\u0000|\u0005\u0001\u0000\u0000\u0000"+
		"}~\u0005#\u0000\u0000~\u0082\u0005)\u0000\u0000\u007f\u0083\u0005#\u0000"+
		"\u0000\u0080\u0083\u0005\u0006\u0000\u0000\u0081\u0083\u0003:\u001d\u0000"+
		"\u0082\u007f\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0007\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0005#\u0000\u0000\u0085\u0086\u0005)\u0000\u0000\u0086\u0087"+
		"\u0003\u0000\u0000\u0000\u0087\t\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"\u0003\u0000\u0000\u0089\u008a\u0003\u0012\t\u0000\u008a\u008b\u0005\u001e"+
		"\u0000\u0000\u008b\u000b\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0004"+
		"\u0000\u0000\u008d\u008e\u0005#\u0000\u0000\u008e\u0091\u0003\u000e\u0007"+
		"\u0000\u008f\u0090\u0005\u001b\u0000\u0000\u0090\u0092\u0003\u0012\t\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0005\u001e\u0000\u0000"+
		"\u0094\r\u0001\u0000\u0000\u0000\u0095\u00a2\u0005\u0005\u0000\u0000\u0096"+
		"\u0097\u0005\u0001\u0000\u0000\u0097\u009c\u0003\u0010\b\u0000\u0098\u0099"+
		"\u0005\u001a\u0000\u0000\u0099\u009b\u0003\u0010\b\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009f\u0001"+
		"\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0005"+
		"\u0002\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000\u00a1\u0095\u0001"+
		"\u0000\u0000\u0000\u00a1\u0096\u0001\u0000\u0000\u0000\u00a2\u000f\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0005#\u0000\u0000\u00a4\u00a5\u0005)\u0000"+
		"\u0000\u00a5\u00a8\u0003\u0000\u0000\u0000\u00a6\u00a8\u0003\u0000\u0000"+
		"\u0000\u00a7\u00a3\u0001\u0000\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a8\u0011\u0001\u0000\u0000\u0000\u00a9\u00ab\u00036\u001b\u0000"+
		"\u00aa\u00a9\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00b0\u0005#\u0000\u0000\u00ad"+
		"\u00af\u0003\u0014\n\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b5\u00038\u001c\u0000\u00b4\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u0013\u0001"+
		"\u0000\u0000\u0000\u00b6\u00bd\u0003\u0016\u000b\u0000\u00b7\u00bd\u0003"+
		"\u001a\r\u0000\u00b8\u00bd\u0003\u001c\u000e\u0000\u00b9\u00ba\u00036"+
		"\u001b\u0000\u00ba\u00bb\u0005#\u0000\u0000\u00bb\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bc\u00b6\u0001\u0000\u0000\u0000\u00bc\u00b7\u0001\u0000\u0000"+
		"\u0000\u00bc\u00b8\u0001\u0000\u0000\u0000\u00bc\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bd\u0015\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u001b\u0000"+
		"\u0000\u00bf\u00c0\u0005#\u0000\u0000\u00c0\u0017\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0005\u0013\u0000\u0000\u00c2\u0019\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c4\u0005\u001b\u0000\u0000\u00c4\u00c5\u0005\u0013\u0000\u0000"+
		"\u00c5\u001b\u0001\u0000\u0000\u0000\u00c6\u00cb\u0005\u001f\u0000\u0000"+
		"\u00c7\u00cc\u0003\u0018\f\u0000\u00c8\u00cc\u0003(\u0014\u0000\u00c9"+
		"\u00cc\u0003.\u0017\u0000\u00ca\u00cc\u0003\u001e\u000f\u0000\u00cb\u00c7"+
		"\u0001\u0000\u0000\u0000\u00cb\u00c8\u0001\u0000\u0000\u0000\u00cb\u00c9"+
		"\u0001\u0000\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005 \u0000\u0000\u00ce\u001d\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d2\u0003 \u0010\u0000\u00d0\u00d2\u0003\""+
		"\u0011\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d2\u001f\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\u0018"+
		"\u0000\u0000\u00d4\u00d5\u0005\u0001\u0000\u0000\u00d5\u00d6\u0005#\u0000"+
		"\u0000\u00d6\u00d7\u0005\u0002\u0000\u0000\u00d7!\u0001\u0000\u0000\u0000"+
		"\u00d8\u00dc\u0005#\u0000\u0000\u00d9\u00db\u0005\u0016\u0000\u0000\u00da"+
		"\u00d9\u0001\u0000\u0000\u0000\u00db\u00de\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd"+
		"\u00df\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00df"+
		"\u00e3\u0003$\u0012\u0000\u00e0\u00e2\u0005\u0016\u0000\u0000\u00e1\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\u0003\u0000\u0000\u0000\u00e7\u010e\u0001\u0000\u0000\u0000\u00e8\u00e9"+
		"\u0005#\u0000\u0000\u00e9\u00ea\u0003F#\u0000\u00ea\u00eb\u0003&\u0013"+
		"\u0000\u00eb\u00ec\u0003F#\u0000\u00ec\u00ed\u0003\u0000\u0000\u0000\u00ed"+
		"\u010e\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005#\u0000\u0000\u00ef\u00f0"+
		"\u0003F#\u0000\u00f0\u00f1\u0005\u0007\u0000\u0000\u00f1\u00f2\u0003F"+
		"#\u0000\u00f2\u00f3\u0005\u0001\u0000\u0000\u00f3\u00f8\u0003\u0000\u0000"+
		"\u0000\u00f4\u00f5\u0005\u001a\u0000\u0000\u00f5\u00f7\u0003\u0000\u0000"+
		"\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00fa\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000"+
		"\u0000\u00fb\u00fc\u0005\u0002\u0000\u0000\u00fc\u010e\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fe\u0005#\u0000\u0000\u00fe\u00ff\u0003F#\u0000\u00ff"+
		"\u0100\u0005\b\u0000\u0000\u0100\u0101\u0005\u0007\u0000\u0000\u0101\u0102"+
		"\u0003F#\u0000\u0102\u0103\u0005\u0001\u0000\u0000\u0103\u0108\u0003\u0000"+
		"\u0000\u0000\u0104\u0105\u0005\u001a\u0000\u0000\u0105\u0107\u0003\u0000"+
		"\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107\u010a\u0001\u0000"+
		"\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000"+
		"\u0000\u0000\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000"+
		"\u0000\u0000\u010b\u010c\u0005\u0002\u0000\u0000\u010c\u010e\u0001\u0000"+
		"\u0000\u0000\u010d\u00d8\u0001\u0000\u0000\u0000\u010d\u00e8\u0001\u0000"+
		"\u0000\u0000\u010d\u00ee\u0001\u0000\u0000\u0000\u010d\u00fd\u0001\u0000"+
		"\u0000\u0000\u010e#\u0001\u0000\u0000\u0000\u010f\u0110\u0007\u0001\u0000"+
		"\u0000\u0110%\u0001\u0000\u0000\u0000\u0111\u0112\u0005\b\u0000\u0000"+
		"\u0112\u0113\u0005\u0016\u0000\u0000\u0113\u011a\u0005\n\u0000\u0000\u0114"+
		"\u011a\u0005\n\u0000\u0000\u0115\u0116\u0005\b\u0000\u0000\u0116\u0117"+
		"\u0005\u0016\u0000\u0000\u0117\u011a\u0005\t\u0000\u0000\u0118\u011a\u0005"+
		"\t\u0000\u0000\u0119\u0111\u0001\u0000\u0000\u0000\u0119\u0114\u0001\u0000"+
		"\u0000\u0000\u0119\u0115\u0001\u0000\u0000\u0000\u0119\u0118\u0001\u0000"+
		"\u0000\u0000\u011a\'\u0001\u0000\u0000\u0000\u011b\u011f\u0003,\u0016"+
		"\u0000\u011c\u011e\u0003*\u0015\u0000\u011d\u011c\u0001\u0000\u0000\u0000"+
		"\u011e\u0121\u0001\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0001\u0000\u0000\u0000\u0120)\u0001\u0000\u0000\u0000\u0121"+
		"\u011f\u0001\u0000\u0000\u0000\u0122\u0123\u0005\u001a\u0000\u0000\u0123"+
		"\u0124\u0003,\u0016\u0000\u0124+\u0001\u0000\u0000\u0000\u0125\u012c\u0003"+
		"\n\u0005\u0000\u0126\u012c\u0003\f\u0006\u0000\u0127\u0129\u0005\u0012"+
		"\u0000\u0000\u0128\u0127\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012c\u0005!\u0000"+
		"\u0000\u012b\u0125\u0001\u0000\u0000\u0000\u012b\u0126\u0001\u0000\u0000"+
		"\u0000\u012b\u0128\u0001\u0000\u0000\u0000\u012c-\u0001\u0000\u0000\u0000"+
		"\u012d\u0131\u00030\u0018\u0000\u012e\u0131\u00034\u001a\u0000\u012f\u0131"+
		"\u00032\u0019\u0000\u0130\u012d\u0001\u0000\u0000\u0000\u0130\u012e\u0001"+
		"\u0000\u0000\u0000\u0130\u012f\u0001\u0000\u0000\u0000\u0131/\u0001\u0000"+
		"\u0000\u0000\u0132\u0133\u0003,\u0016\u0000\u0133\u0134\u0005)\u0000\u0000"+
		"\u0134\u0137\u0003,\u0016\u0000\u0135\u0136\u0005)\u0000\u0000\u0136\u0138"+
		"\u0003,\u0016\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001"+
		"\u0000\u0000\u0000\u01381\u0001\u0000\u0000\u0000\u0139\u013a\u0005)\u0000"+
		"\u0000\u013a\u013b\u0003,\u0016\u0000\u013b3\u0001\u0000\u0000\u0000\u013c"+
		"\u013d\u0003,\u0016\u0000\u013d\u013e\u0005)\u0000\u0000\u013e5\u0001"+
		"\u0000\u0000\u0000\u013f\u0140\u0005\u001b\u0000\u0000\u0140\u0141\u0005"+
		"\u001b\u0000\u0000\u01417\u0001\u0000\u0000\u0000\u0142\u0143\u0005\u001b"+
		"\u0000\u0000\u0143\u0144\u0005#\u0000\u0000\u0144\u0145\u0005\u0005\u0000"+
		"\u0000\u01459\u0001\u0000\u0000\u0000\u0146\u0148\u0005\u0012\u0000\u0000"+
		"\u0147\u0146\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000\u0000"+
		"\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014a\u0007\u0002\u0000\u0000"+
		"\u014a;\u0001\u0000\u0000\u0000\u014b\u014c\u0006\u001e\uffff\uffff\u0000"+
		"\u014c\u014d\u0003D\"\u0000\u014d\u0158\u0001\u0000\u0000\u0000\u014e"+
		"\u014f\n\u0002\u0000\u0000\u014f\u0150\u0003B!\u0000\u0150\u0151\u0003"+
		"<\u001e\u0003\u0151\u0157\u0001\u0000\u0000\u0000\u0152\u0153\n\u0001"+
		"\u0000\u0000\u0153\u0154\u0003@ \u0000\u0154\u0155\u0003<\u001e\u0002"+
		"\u0155\u0157\u0001\u0000\u0000\u0000\u0156\u014e\u0001\u0000\u0000\u0000"+
		"\u0156\u0152\u0001\u0000\u0000\u0000\u0157\u015a\u0001\u0000\u0000\u0000"+
		"\u0158\u0156\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000"+
		"\u0159=\u0001\u0000\u0000\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015b"+
		"\u015c\u0003<\u001e\u0000\u015c\u015d\u0003B!\u0000\u015d\u015e\u0003"+
		"<\u001e\u0000\u015e\u0164\u0001\u0000\u0000\u0000\u015f\u0160\u0003<\u001e"+
		"\u0000\u0160\u0161\u0003@ \u0000\u0161\u0162\u0003<\u001e\u0000\u0162"+
		"\u0164\u0001\u0000\u0000\u0000\u0163\u015b\u0001\u0000\u0000\u0000\u0163"+
		"\u015f\u0001\u0000\u0000\u0000\u0164?\u0001\u0000\u0000\u0000\u0165\u0166"+
		"\u0007\u0003\u0000\u0000\u0166A\u0001\u0000\u0000\u0000\u0167\u0168\u0007"+
		"\u0004\u0000\u0000\u0168C\u0001\u0000\u0000\u0000\u0169\u0171\u0003:\u001d"+
		"\u0000\u016a\u016b\u0005\u0001\u0000\u0000\u016b\u016c\u0003>\u001f\u0000"+
		"\u016c\u016d\u0005\u0002\u0000\u0000\u016d\u0171\u0001\u0000\u0000\u0000"+
		"\u016e\u0171\u0003\n\u0005\u0000\u016f\u0171\u0003\f\u0006\u0000\u0170"+
		"\u0169\u0001\u0000\u0000\u0000\u0170\u016a\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0170\u016f\u0001\u0000\u0000\u0000\u0171"+
		"E\u0001\u0000\u0000\u0000\u0172\u0174\u0005\u0016\u0000\u0000\u0173\u0172"+
		"\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u0173"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176G\u0001"+
		"\u0000\u0000\u0000\u0177\u0179\u0005$\u0000\u0000\u0178\u0177\u0001\u0000"+
		"\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u0178\u0001\u0000"+
		"\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017bI\u0001\u0000\u0000"+
		"\u0000$MTY_kv{\u0082\u0091\u009c\u00a1\u00a7\u00aa\u00b0\u00b4\u00bc\u00cb"+
		"\u00d1\u00dc\u00e3\u00f8\u0108\u010d\u0119\u011f\u0128\u012b\u0130\u0137"+
		"\u0147\u0156\u0158\u0163\u0170\u0175\u017a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}