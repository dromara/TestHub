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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, SingleQuoteAnyText=7, 
		IN=8, NOT=9, LIKE=10, RLIKE=11, EQ=12, NEQ=13, GT=14, LT=15, GE=16, LE=17, 
		ADD=18, SUB=19, MUL=20, DIV=21, PERCENTAGE=22, SPACE=23, SingleQuote=24, 
		QUE_MARK=25, COMMA=26, DOT=27, DOLLAR=28, LCURLY=29, RCURLY=30, LBRACKET=31, 
		RBRACKET=32, INT=33, FLOAT=34, IDENTIFIER=35, Whitespace=36;
	public static final int
		RULE_formula = 0, RULE_dataNode = 1, RULE_keyVal = 2, RULE_keyFormula = 3, 
		RULE_pathNode = 4, RULE_funcNode = 5, RULE_params = 6, RULE_param = 7, 
		RULE_jsonpath = 8, RULE_subscript = 9, RULE_dotIdentifier = 10, RULE_all = 11, 
		RULE_dotAll = 12, RULE_arraySubscript = 13, RULE_condition = 14, RULE_notNull = 15, 
		RULE_expression = 16, RULE_op = 17, RULE_strOp = 18, RULE_arrayIndex = 19, 
		RULE_listIndex = 20, RULE_numIndex = 21, RULE_interval = 22, RULE_arrayInterval = 23, 
		RULE_toInterval = 24, RULE_firstInterval = 25, RULE_recursion = 26, RULE_function = 27, 
		RULE_decimal = 28, RULE_arithmetic = 29, RULE_binArithmetic = 30, RULE_addsub = 31, 
		RULE_muldiv = 32, RULE_factor = 33, RULE_space = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"formula", "dataNode", "keyVal", "keyFormula", "pathNode", "funcNode", 
			"params", "param", "jsonpath", "subscript", "dotIdentifier", "all", "dotAll", 
			"arraySubscript", "condition", "notNull", "expression", "op", "strOp", 
			"arrayIndex", "listIndex", "numIndex", "interval", "arrayInterval", "toInterval", 
			"firstInterval", "recursion", "function", "decimal", "arithmetic", "binArithmetic", 
			"addsub", "muldiv", "factor", "space"
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
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(70);
				match(T__0);
				setState(71);
				((FormulaContext)_localctx).dataType = match(IDENTIFIER);
				setState(72);
				match(T__1);
				}
				break;
			}
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(75);
				funcNode();
				}
				break;
			case 2:
				{
				setState(76);
				pathNode();
				}
				break;
			case 3:
				{
				setState(77);
				dataNode();
				}
				break;
			case 4:
				{
				setState(78);
				arithmetic(0);
				}
				break;
			}
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					formula();
					}
					} 
				}
				setState(86);
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
	public static class DataNodeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FormulaParser.IDENTIFIER, 0); }
		public TerminalNode SingleQuoteAnyText() { return getToken(FormulaParser.SingleQuoteAnyText, 0); }
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
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
		enterRule(_localctx, 2, RULE_dataNode);
		int _la;
		try {
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(SingleQuoteAnyText);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				decimal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(90);
				match(LBRACKET);
				setState(91);
				dataNode();
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(92);
					match(COMMA);
					setState(93);
					dataNode();
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(99);
				match(RBRACKET);
				}
				break;
			case 5:
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
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				match(LCURLY);
				setState(113);
				keyVal();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(114);
					match(COMMA);
					setState(115);
					keyVal();
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
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(123);
				match(LCURLY);
				setState(124);
				keyFormula();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(125);
					match(COMMA);
					setState(126);
					keyFormula();
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
		enterRule(_localctx, 4, RULE_keyVal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			((KeyValContext)_localctx).key = match(IDENTIFIER);
			setState(137);
			match(T__2);
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(138);
				((KeyValContext)_localctx).val = match(IDENTIFIER);
				}
				break;
			case SingleQuoteAnyText:
				{
				setState(139);
				match(SingleQuoteAnyText);
				}
				break;
			case SUB:
			case INT:
			case FLOAT:
				{
				setState(140);
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
		enterRule(_localctx, 6, RULE_keyFormula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(IDENTIFIER);
			setState(144);
			match(T__2);
			setState(145);
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
		enterRule(_localctx, 8, RULE_pathNode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(T__3);
			setState(148);
			jsonpath();
			setState(149);
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
		enterRule(_localctx, 10, RULE_funcNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__4);
			setState(152);
			match(IDENTIFIER);
			setState(153);
			params();
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(154);
				match(DOT);
				setState(155);
				jsonpath();
				}
			}

			setState(158);
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
		enterRule(_localctx, 12, RULE_params);
		int _la;
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(T__5);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(T__0);
				setState(162);
				param();
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(163);
					match(COMMA);
					setState(164);
					param();
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(170);
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
		enterRule(_localctx, 14, RULE_param);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(IDENTIFIER);
				setState(175);
				match(T__2);
				setState(176);
				formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
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
		enterRule(_localctx, 16, RULE_jsonpath);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(180);
				recursion();
				}
			}

			setState(183);
			match(IDENTIFIER);
			setState(187);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(184);
					subscript();
					}
					} 
				}
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(190);
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
		enterRule(_localctx, 18, RULE_subscript);
		try {
			setState(199);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				dotIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				dotAll();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				arraySubscript();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				recursion();
				setState(197);
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
		enterRule(_localctx, 20, RULE_dotIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(DOT);
			setState(202);
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
		enterRule(_localctx, 22, RULE_all);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 24, RULE_dotAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(DOT);
			setState(207);
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
		enterRule(_localctx, 26, RULE_arraySubscript);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(LBRACKET);
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(210);
				all();
				}
				break;
			case 2:
				{
				setState(211);
				arrayIndex();
				}
				break;
			case 3:
				{
				setState(212);
				interval();
				}
				break;
			case 4:
				{
				setState(213);
				condition();
				}
				break;
			}
			setState(216);
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
		enterRule(_localctx, 28, RULE_condition);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUE_MARK:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				notNull();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
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
		enterRule(_localctx, 30, RULE_notNull);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(QUE_MARK);
			setState(223);
			match(T__0);
			setState(224);
			match(IDENTIFIER);
			setState(225);
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
		enterRule(_localctx, 32, RULE_expression);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(IDENTIFIER);
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(228);
					match(SPACE);
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(234);
				op();
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(235);
					match(SPACE);
					}
					}
					setState(240);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(241);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(IDENTIFIER);
				setState(244);
				space();
				setState(245);
				strOp();
				setState(246);
				space();
				setState(247);
				((ExpressionContext)_localctx).data = formula();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				match(IDENTIFIER);
				setState(250);
				space();
				setState(251);
				match(IN);
				setState(252);
				space();
				setState(253);
				match(T__0);
				setState(254);
				formula();
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(255);
					match(COMMA);
					setState(256);
					formula();
					}
					}
					setState(261);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(262);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(264);
				match(IDENTIFIER);
				setState(265);
				space();
				setState(266);
				match(NOT);
				setState(267);
				match(IN);
				setState(268);
				space();
				setState(269);
				match(T__0);
				setState(270);
				formula();
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(271);
					match(COMMA);
					setState(272);
					formula();
					}
					}
					setState(277);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(278);
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
		enterRule(_localctx, 34, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 258048L) != 0)) ) {
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
		enterRule(_localctx, 36, RULE_strOp);
		try {
			setState(292);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(284);
				match(NOT);
				setState(285);
				match(SPACE);
				setState(286);
				match(RLIKE);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				match(RLIKE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(288);
				match(NOT);
				setState(289);
				match(SPACE);
				setState(290);
				match(LIKE);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(291);
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
		enterRule(_localctx, 38, RULE_arrayIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			numIndex();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(295);
				listIndex();
				}
				}
				setState(300);
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
		enterRule(_localctx, 40, RULE_listIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(COMMA);
			setState(302);
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
		enterRule(_localctx, 42, RULE_numIndex);
		int _la;
		try {
			setState(310);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				pathNode();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				funcNode();
				}
				break;
			case SUB:
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB) {
					{
					setState(306);
					match(SUB);
					}
				}

				setState(309);
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
		enterRule(_localctx, 44, RULE_interval);
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(312);
				arrayInterval();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				firstInterval();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
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
		enterRule(_localctx, 46, RULE_arrayInterval);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			((ArrayIntervalContext)_localctx).start = numIndex();
			setState(318);
			match(T__2);
			setState(319);
			((ArrayIntervalContext)_localctx).end = numIndex();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(320);
				match(T__2);
				setState(321);
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
		enterRule(_localctx, 48, RULE_toInterval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(T__2);
			setState(325);
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
		enterRule(_localctx, 50, RULE_firstInterval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			numIndex();
			setState(328);
			match(T__2);
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
		enterRule(_localctx, 52, RULE_recursion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(DOT);
			setState(331);
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
		enterRule(_localctx, 54, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(DOT);
			setState(334);
			match(IDENTIFIER);
			setState(335);
			match(T__5);
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
		enterRule(_localctx, 56, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB) {
				{
				setState(337);
				match(SUB);
				}
			}

			setState(340);
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
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_arithmetic, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(343);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(355);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(353);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(345);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(346);
						muldiv();
						setState(347);
						((ArithmeticContext)_localctx).right = arithmetic(3);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_arithmetic);
						setState(349);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(350);
						addsub();
						setState(351);
						((ArithmeticContext)_localctx).right = arithmetic(2);
						}
						break;
					}
					} 
				}
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
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
		enterRule(_localctx, 60, RULE_binArithmetic);
		try {
			setState(366);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(359);
				muldiv();
				setState(360);
				((BinArithmeticContext)_localctx).right = arithmetic(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				((BinArithmeticContext)_localctx).left = arithmetic(0);
				setState(363);
				addsub();
				setState(364);
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
		enterRule(_localctx, 62, RULE_addsub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
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
		enterRule(_localctx, 64, RULE_muldiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7340032L) != 0)) ) {
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
		enterRule(_localctx, 66, RULE_factor);
		try {
			setState(379);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB:
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				decimal();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				match(T__0);
				setState(374);
				binArithmetic();
				setState(375);
				match(T__1);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(377);
				pathNode();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
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
		enterRule(_localctx, 68, RULE_space);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(381);
				match(SPACE);
				}
				}
				setState(384); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPACE );
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
		case 29:
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
		"\u0004\u0001$\u0183\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000J\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0003\u0000P\b\u0000\u0001\u0000\u0005"+
		"\u0000S\b\u0000\n\u0000\f\u0000V\t\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001_\b"+
		"\u0001\n\u0001\f\u0001b\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001j\b\u0001\n\u0001\f\u0001m\t"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001u\b\u0001\n\u0001\f\u0001x\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0080"+
		"\b\u0001\n\u0001\f\u0001\u0083\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u0087\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002\u008e\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u009d\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006\u00a6\b\u0006\n\u0006\f\u0006\u00a9\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u00ad\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u00b3\b\u0007\u0001\b\u0003\b\u00b6\b\b\u0001\b\u0001"+
		"\b\u0005\b\u00ba\b\b\n\b\f\b\u00bd\t\b\u0001\b\u0003\b\u00c0\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00c8\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d7\b\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0003\u000e\u00dd\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0005\u0010\u00e6\b\u0010"+
		"\n\u0010\f\u0010\u00e9\t\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00ed"+
		"\b\u0010\n\u0010\f\u0010\u00f0\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u0102\b\u0010\n\u0010\f\u0010\u0105\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0112"+
		"\b\u0010\n\u0010\f\u0010\u0115\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u0119\b\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0125\b\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u0129\b\u0013\n\u0013"+
		"\f\u0013\u012c\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0134\b\u0015\u0001\u0015\u0003\u0015"+
		"\u0137\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u013c\b"+
		"\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003"+
		"\u0017\u0143\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0003\u001c\u0153\b\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0005\u001d\u0162\b\u001d\n\u001d\f\u001d\u0165\t\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0003\u001e\u016f\b\u001e\u0001\u001f\u0001\u001f\u0001 "+
		"\u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u017c"+
		"\b!\u0001\"\u0004\"\u017f\b\"\u000b\"\f\"\u0180\u0001\"\u0000\u0001:#"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BD\u0000\u0004\u0001\u0000\f\u0011\u0001"+
		"\u0000!\"\u0001\u0000\u0012\u0013\u0001\u0000\u0014\u0016\u0197\u0000"+
		"I\u0001\u0000\u0000\u0000\u0002\u0086\u0001\u0000\u0000\u0000\u0004\u0088"+
		"\u0001\u0000\u0000\u0000\u0006\u008f\u0001\u0000\u0000\u0000\b\u0093\u0001"+
		"\u0000\u0000\u0000\n\u0097\u0001\u0000\u0000\u0000\f\u00ac\u0001\u0000"+
		"\u0000\u0000\u000e\u00b2\u0001\u0000\u0000\u0000\u0010\u00b5\u0001\u0000"+
		"\u0000\u0000\u0012\u00c7\u0001\u0000\u0000\u0000\u0014\u00c9\u0001\u0000"+
		"\u0000\u0000\u0016\u00cc\u0001\u0000\u0000\u0000\u0018\u00ce\u0001\u0000"+
		"\u0000\u0000\u001a\u00d1\u0001\u0000\u0000\u0000\u001c\u00dc\u0001\u0000"+
		"\u0000\u0000\u001e\u00de\u0001\u0000\u0000\u0000 \u0118\u0001\u0000\u0000"+
		"\u0000\"\u011a\u0001\u0000\u0000\u0000$\u0124\u0001\u0000\u0000\u0000"+
		"&\u0126\u0001\u0000\u0000\u0000(\u012d\u0001\u0000\u0000\u0000*\u0136"+
		"\u0001\u0000\u0000\u0000,\u013b\u0001\u0000\u0000\u0000.\u013d\u0001\u0000"+
		"\u0000\u00000\u0144\u0001\u0000\u0000\u00002\u0147\u0001\u0000\u0000\u0000"+
		"4\u014a\u0001\u0000\u0000\u00006\u014d\u0001\u0000\u0000\u00008\u0152"+
		"\u0001\u0000\u0000\u0000:\u0156\u0001\u0000\u0000\u0000<\u016e\u0001\u0000"+
		"\u0000\u0000>\u0170\u0001\u0000\u0000\u0000@\u0172\u0001\u0000\u0000\u0000"+
		"B\u017b\u0001\u0000\u0000\u0000D\u017e\u0001\u0000\u0000\u0000FG\u0005"+
		"\u0001\u0000\u0000GH\u0005#\u0000\u0000HJ\u0005\u0002\u0000\u0000IF\u0001"+
		"\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JO\u0001\u0000\u0000\u0000"+
		"KP\u0003\n\u0005\u0000LP\u0003\b\u0004\u0000MP\u0003\u0002\u0001\u0000"+
		"NP\u0003:\u001d\u0000OK\u0001\u0000\u0000\u0000OL\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000ON\u0001\u0000\u0000\u0000PT\u0001\u0000\u0000"+
		"\u0000QS\u0003\u0000\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000U\u0001"+
		"\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000W\u0087\u0005#\u0000"+
		"\u0000X\u0087\u0005\u0007\u0000\u0000Y\u0087\u00038\u001c\u0000Z[\u0005"+
		"\u001f\u0000\u0000[`\u0003\u0002\u0001\u0000\\]\u0005\u001a\u0000\u0000"+
		"]_\u0003\u0002\u0001\u0000^\\\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000"+
		"\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ac\u0001\u0000"+
		"\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005 \u0000\u0000d\u0087\u0001"+
		"\u0000\u0000\u0000ef\u0005\u001f\u0000\u0000fk\u0003\u0000\u0000\u0000"+
		"gh\u0005\u001a\u0000\u0000hj\u0003\u0000\u0000\u0000ig\u0001\u0000\u0000"+
		"\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000"+
		"\u0000\u0000ln\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005"+
		" \u0000\u0000o\u0087\u0001\u0000\u0000\u0000pq\u0005\u001d\u0000\u0000"+
		"qv\u0003\u0004\u0002\u0000rs\u0005\u001a\u0000\u0000su\u0003\u0004\u0002"+
		"\u0000tr\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000"+
		"\u0000\u0000vw\u0001\u0000\u0000\u0000wy\u0001\u0000\u0000\u0000xv\u0001"+
		"\u0000\u0000\u0000yz\u0005\u001e\u0000\u0000z\u0087\u0001\u0000\u0000"+
		"\u0000{|\u0005\u001d\u0000\u0000|\u0081\u0003\u0006\u0003\u0000}~\u0005"+
		"\u001a\u0000\u0000~\u0080\u0003\u0006\u0003\u0000\u007f}\u0001\u0000\u0000"+
		"\u0000\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000"+
		"\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0084\u0001\u0000\u0000"+
		"\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u001e\u0000"+
		"\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086W\u0001\u0000\u0000\u0000"+
		"\u0086X\u0001\u0000\u0000\u0000\u0086Y\u0001\u0000\u0000\u0000\u0086Z"+
		"\u0001\u0000\u0000\u0000\u0086e\u0001\u0000\u0000\u0000\u0086p\u0001\u0000"+
		"\u0000\u0000\u0086{\u0001\u0000\u0000\u0000\u0087\u0003\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0005#\u0000\u0000\u0089\u008d\u0005\u0003\u0000\u0000"+
		"\u008a\u008e\u0005#\u0000\u0000\u008b\u008e\u0005\u0007\u0000\u0000\u008c"+
		"\u008e\u00038\u001c\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008d\u008b"+
		"\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0005"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0005#\u0000\u0000\u0090\u0091\u0005"+
		"\u0003\u0000\u0000\u0091\u0092\u0003\u0000\u0000\u0000\u0092\u0007\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0005\u0004\u0000\u0000\u0094\u0095\u0003"+
		"\u0010\b\u0000\u0095\u0096\u0005\u001e\u0000\u0000\u0096\t\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005\u0005\u0000\u0000\u0098\u0099\u0005#\u0000"+
		"\u0000\u0099\u009c\u0003\f\u0006\u0000\u009a\u009b\u0005\u001b\u0000\u0000"+
		"\u009b\u009d\u0003\u0010\b\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\u001e\u0000\u0000\u009f\u000b\u0001\u0000\u0000\u0000\u00a0"+
		"\u00ad\u0005\u0006\u0000\u0000\u00a1\u00a2\u0005\u0001\u0000\u0000\u00a2"+
		"\u00a7\u0003\u000e\u0007\u0000\u00a3\u00a4\u0005\u001a\u0000\u0000\u00a4"+
		"\u00a6\u0003\u000e\u0007\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9"+
		"\u00a7\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005\u0002\u0000\u0000\u00ab"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ac\u00a0\u0001\u0000\u0000\u0000\u00ac"+
		"\u00a1\u0001\u0000\u0000\u0000\u00ad\r\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005#\u0000\u0000\u00af\u00b0\u0005\u0003\u0000\u0000\u00b0\u00b3\u0003"+
		"\u0000\u0000\u0000\u00b1\u00b3\u0003\u0000\u0000\u0000\u00b2\u00ae\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u000f\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b6\u00034\u001a\u0000\u00b5\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\u00bb\u0005#\u0000\u0000\u00b8\u00ba\u0003\u0012\t"+
		"\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00c0\u00036\u001b\u0000\u00bf\u00be\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u0011\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c8\u0003\u0014\n\u0000\u00c2\u00c8\u0003\u0018\f\u0000\u00c3"+
		"\u00c8\u0003\u001a\r\u0000\u00c4\u00c5\u00034\u001a\u0000\u00c5\u00c6"+
		"\u0005#\u0000\u0000\u00c6\u00c8\u0001\u0000\u0000\u0000\u00c7\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c2\u0001\u0000\u0000\u0000\u00c7\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c7\u00c4\u0001\u0000\u0000\u0000\u00c8\u0013\u0001"+
		"\u0000\u0000\u0000\u00c9\u00ca\u0005\u001b\u0000\u0000\u00ca\u00cb\u0005"+
		"#\u0000\u0000\u00cb\u0015\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005\u0014"+
		"\u0000\u0000\u00cd\u0017\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005\u001b"+
		"\u0000\u0000\u00cf\u00d0\u0005\u0014\u0000\u0000\u00d0\u0019\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d6\u0005\u001f\u0000\u0000\u00d2\u00d7\u0003\u0016"+
		"\u000b\u0000\u00d3\u00d7\u0003&\u0013\u0000\u00d4\u00d7\u0003,\u0016\u0000"+
		"\u00d5\u00d7\u0003\u001c\u000e\u0000\u00d6\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d3\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0005 \u0000\u0000\u00d9\u001b\u0001\u0000\u0000\u0000\u00da"+
		"\u00dd\u0003\u001e\u000f\u0000\u00db\u00dd\u0003 \u0010\u0000\u00dc\u00da"+
		"\u0001\u0000\u0000\u0000\u00dc\u00db\u0001\u0000\u0000\u0000\u00dd\u001d"+
		"\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0019\u0000\u0000\u00df\u00e0"+
		"\u0005\u0001\u0000\u0000\u00e0\u00e1\u0005#\u0000\u0000\u00e1\u00e2\u0005"+
		"\u0002\u0000\u0000\u00e2\u001f\u0001\u0000\u0000\u0000\u00e3\u00e7\u0005"+
		"#\u0000\u0000\u00e4\u00e6\u0005\u0017\u0000\u0000\u00e5\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea\u0001\u0000"+
		"\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00ee\u0003\"\u0011"+
		"\u0000\u00eb\u00ed\u0005\u0017\u0000\u0000\u00ec\u00eb\u0001\u0000\u0000"+
		"\u0000\u00ed\u00f0\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1\u00f2\u0003\u0000\u0000"+
		"\u0000\u00f2\u0119\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005#\u0000\u0000"+
		"\u00f4\u00f5\u0003D\"\u0000\u00f5\u00f6\u0003$\u0012\u0000\u00f6\u00f7"+
		"\u0003D\"\u0000\u00f7\u00f8\u0003\u0000\u0000\u0000\u00f8\u0119\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fa\u0005#\u0000\u0000\u00fa\u00fb\u0003D\""+
		"\u0000\u00fb\u00fc\u0005\b\u0000\u0000\u00fc\u00fd\u0003D\"\u0000\u00fd"+
		"\u00fe\u0005\u0001\u0000\u0000\u00fe\u0103\u0003\u0000\u0000\u0000\u00ff"+
		"\u0100\u0005\u001a\u0000\u0000\u0100\u0102\u0003\u0000\u0000\u0000\u0101"+
		"\u00ff\u0001\u0000\u0000\u0000\u0102\u0105\u0001\u0000\u0000\u0000\u0103"+
		"\u0101\u0001\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104"+
		"\u0106\u0001\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0106"+
		"\u0107\u0005\u0002\u0000\u0000\u0107\u0119\u0001\u0000\u0000\u0000\u0108"+
		"\u0109\u0005#\u0000\u0000\u0109\u010a\u0003D\"\u0000\u010a\u010b\u0005"+
		"\t\u0000\u0000\u010b\u010c\u0005\b\u0000\u0000\u010c\u010d\u0003D\"\u0000"+
		"\u010d\u010e\u0005\u0001\u0000\u0000\u010e\u0113\u0003\u0000\u0000\u0000"+
		"\u010f\u0110\u0005\u001a\u0000\u0000\u0110\u0112\u0003\u0000\u0000\u0000"+
		"\u0111\u010f\u0001\u0000\u0000\u0000\u0112\u0115\u0001\u0000\u0000\u0000"+
		"\u0113\u0111\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000"+
		"\u0114\u0116\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0005\u0002\u0000\u0000\u0117\u0119\u0001\u0000\u0000\u0000"+
		"\u0118\u00e3\u0001\u0000\u0000\u0000\u0118\u00f3\u0001\u0000\u0000\u0000"+
		"\u0118\u00f9\u0001\u0000\u0000\u0000\u0118\u0108\u0001\u0000\u0000\u0000"+
		"\u0119!\u0001\u0000\u0000\u0000\u011a\u011b\u0007\u0000\u0000\u0000\u011b"+
		"#\u0001\u0000\u0000\u0000\u011c\u011d\u0005\t\u0000\u0000\u011d\u011e"+
		"\u0005\u0017\u0000\u0000\u011e\u0125\u0005\u000b\u0000\u0000\u011f\u0125"+
		"\u0005\u000b\u0000\u0000\u0120\u0121\u0005\t\u0000\u0000\u0121\u0122\u0005"+
		"\u0017\u0000\u0000\u0122\u0125\u0005\n\u0000\u0000\u0123\u0125\u0005\n"+
		"\u0000\u0000\u0124\u011c\u0001\u0000\u0000\u0000\u0124\u011f\u0001\u0000"+
		"\u0000\u0000\u0124\u0120\u0001\u0000\u0000\u0000\u0124\u0123\u0001\u0000"+
		"\u0000\u0000\u0125%\u0001\u0000\u0000\u0000\u0126\u012a\u0003*\u0015\u0000"+
		"\u0127\u0129\u0003(\u0014\u0000\u0128\u0127\u0001\u0000\u0000\u0000\u0129"+
		"\u012c\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a"+
		"\u012b\u0001\u0000\u0000\u0000\u012b\'\u0001\u0000\u0000\u0000\u012c\u012a"+
		"\u0001\u0000\u0000\u0000\u012d\u012e\u0005\u001a\u0000\u0000\u012e\u012f"+
		"\u0003*\u0015\u0000\u012f)\u0001\u0000\u0000\u0000\u0130\u0137\u0003\b"+
		"\u0004\u0000\u0131\u0137\u0003\n\u0005\u0000\u0132\u0134\u0005\u0013\u0000"+
		"\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000"+
		"\u0000\u0134\u0135\u0001\u0000\u0000\u0000\u0135\u0137\u0005!\u0000\u0000"+
		"\u0136\u0130\u0001\u0000\u0000\u0000\u0136\u0131\u0001\u0000\u0000\u0000"+
		"\u0136\u0133\u0001\u0000\u0000\u0000\u0137+\u0001\u0000\u0000\u0000\u0138"+
		"\u013c\u0003.\u0017\u0000\u0139\u013c\u00032\u0019\u0000\u013a\u013c\u0003"+
		"0\u0018\u0000\u013b\u0138\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000"+
		"\u0000\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013c-\u0001\u0000\u0000"+
		"\u0000\u013d\u013e\u0003*\u0015\u0000\u013e\u013f\u0005\u0003\u0000\u0000"+
		"\u013f\u0142\u0003*\u0015\u0000\u0140\u0141\u0005\u0003\u0000\u0000\u0141"+
		"\u0143\u0003*\u0015\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143"+
		"\u0001\u0000\u0000\u0000\u0143/\u0001\u0000\u0000\u0000\u0144\u0145\u0005"+
		"\u0003\u0000\u0000\u0145\u0146\u0003*\u0015\u0000\u01461\u0001\u0000\u0000"+
		"\u0000\u0147\u0148\u0003*\u0015\u0000\u0148\u0149\u0005\u0003\u0000\u0000"+
		"\u01493\u0001\u0000\u0000\u0000\u014a\u014b\u0005\u001b\u0000\u0000\u014b"+
		"\u014c\u0005\u001b\u0000\u0000\u014c5\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0005\u001b\u0000\u0000\u014e\u014f\u0005#\u0000\u0000\u014f\u0150\u0005"+
		"\u0006\u0000\u0000\u01507\u0001\u0000\u0000\u0000\u0151\u0153\u0005\u0013"+
		"\u0000\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000"+
		"\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0155\u0007\u0001"+
		"\u0000\u0000\u01559\u0001\u0000\u0000\u0000\u0156\u0157\u0006\u001d\uffff"+
		"\uffff\u0000\u0157\u0158\u0003B!\u0000\u0158\u0163\u0001\u0000\u0000\u0000"+
		"\u0159\u015a\n\u0002\u0000\u0000\u015a\u015b\u0003@ \u0000\u015b\u015c"+
		"\u0003:\u001d\u0003\u015c\u0162\u0001\u0000\u0000\u0000\u015d\u015e\n"+
		"\u0001\u0000\u0000\u015e\u015f\u0003>\u001f\u0000\u015f\u0160\u0003:\u001d"+
		"\u0002\u0160\u0162\u0001\u0000\u0000\u0000\u0161\u0159\u0001\u0000\u0000"+
		"\u0000\u0161\u015d\u0001\u0000\u0000\u0000\u0162\u0165\u0001\u0000\u0000"+
		"\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000"+
		"\u0000\u0164;\u0001\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000"+
		"\u0166\u0167\u0003:\u001d\u0000\u0167\u0168\u0003@ \u0000\u0168\u0169"+
		"\u0003:\u001d\u0000\u0169\u016f\u0001\u0000\u0000\u0000\u016a\u016b\u0003"+
		":\u001d\u0000\u016b\u016c\u0003>\u001f\u0000\u016c\u016d\u0003:\u001d"+
		"\u0000\u016d\u016f\u0001\u0000\u0000\u0000\u016e\u0166\u0001\u0000\u0000"+
		"\u0000\u016e\u016a\u0001\u0000\u0000\u0000\u016f=\u0001\u0000\u0000\u0000"+
		"\u0170\u0171\u0007\u0002\u0000\u0000\u0171?\u0001\u0000\u0000\u0000\u0172"+
		"\u0173\u0007\u0003\u0000\u0000\u0173A\u0001\u0000\u0000\u0000\u0174\u017c"+
		"\u00038\u001c\u0000\u0175\u0176\u0005\u0001\u0000\u0000\u0176\u0177\u0003"+
		"<\u001e\u0000\u0177\u0178\u0005\u0002\u0000\u0000\u0178\u017c\u0001\u0000"+
		"\u0000\u0000\u0179\u017c\u0003\b\u0004\u0000\u017a\u017c\u0003\n\u0005"+
		"\u0000\u017b\u0174\u0001\u0000\u0000\u0000\u017b\u0175\u0001\u0000\u0000"+
		"\u0000\u017b\u0179\u0001\u0000\u0000\u0000\u017b\u017a\u0001\u0000\u0000"+
		"\u0000\u017cC\u0001\u0000\u0000\u0000\u017d\u017f\u0005\u0017\u0000\u0000"+
		"\u017e\u017d\u0001\u0000\u0000\u0000\u017f\u0180\u0001\u0000\u0000\u0000"+
		"\u0180\u017e\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000"+
		"\u0181E\u0001\u0000\u0000\u0000$IOT`kv\u0081\u0086\u008d\u009c\u00a7\u00ac"+
		"\u00b2\u00b5\u00bb\u00bf\u00c7\u00d6\u00dc\u00e7\u00ee\u0103\u0113\u0118"+
		"\u0124\u012a\u0133\u0136\u013b\u0142\u0152\u0161\u0163\u016e\u017b\u0180";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}