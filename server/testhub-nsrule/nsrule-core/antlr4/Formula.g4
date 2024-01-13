grammar Formula;

//https://github.com/alibaba/fastjson/wiki/JSONPath

formula: ('(' dataType = IDENTIFIER ')')? (funcNode | pathNode  | dataNode | arithmetic |otherNode) (formula)*;


otherNode : (DIV | WELL | AND | SEMICOLON | PERCENTAGE |AT | DOT| COLON | MUL | QUE_MARK
    | SIGH | SUB | DOLLAR | SPACE)+;


dataNode:
    IDENTIFIER | SingleQuoteAnyText | decimal | chineseString
//    | LBRACKET dataNode ( COMMA dataNode)* RBRACKET
    | LBRACKET formula ( COMMA formula)* RBRACKET
//    | LCURLY keyVal (COMMA keyVal)* RCURLY
    | LCURLY keyFormula (COMMA keyFormula)* RCURLY;

keyVal:
     key=IDENTIFIER  ':' ( val=IDENTIFIER | SingleQuoteAnyText | decimal);

keyFormula:
     IDENTIFIER  ':' formula;

pathNode:
    '${' jsonpath '}';

funcNode:
   '%{' IDENTIFIER params  (DOT jsonpath )?'}';

params:
    '()' | '(' param (',' param)* ')';

param:
    IDENTIFIER ':' formula | formula;

jsonpath:
    recursion? IDENTIFIER (subscript)* function?;

subscript:
    dotIdentifier | dotAll | arraySubscript | recursion IDENTIFIER;

dotIdentifier:
    DOT IDENTIFIER;
all:
    MUL;
dotAll:
    DOT MUL;

arraySubscript:
    LBRACKET ( all | arrayIndex | interval | condition) RBRACKET;

condition:
    notNull | expression;


notNull:
    QUE_MARK '(' IDENTIFIER ')';

expression:
    IDENTIFIER (SPACE)* op (SPACE)* data = formula
    | IDENTIFIER space strOp space data = formula
    | IDENTIFIER space IN space '(' formula (COMMA formula)* ')'
    | IDENTIFIER space NOT IN space '(' formula (COMMA formula)* ')'
    ;
op:
    EQ | NEQ | GE | GT | LT | LE ;

strOp:
    (NOT SPACE RLIKE) | RLIKE | (NOT SPACE LIKE) | LIKE;

arrayIndex:
    numIndex (listIndex)*;

listIndex:
    COMMA numIndex;

numIndex:
     pathNode | funcNode | SUB? INT;

interval:
   arrayInterval | firstInterval | toInterval;

arrayInterval:
    start=numIndex ':' end=numIndex (':' step=numIndex)?;

toInterval:
    ':' numIndex;

firstInterval:
    numIndex ':' ;

recursion: DOT DOT;

function:
    DOT IDENTIFIER '()';


decimal: SUB? (INT | FLOAT);


arithmetic: factor
            | left=arithmetic muldiv right=arithmetic
            | left=arithmetic addsub right=arithmetic ;
binArithmetic:
    left=arithmetic muldiv right=arithmetic
    | left=arithmetic addsub right=arithmetic ;

addsub: ADD | SUB ;

muldiv: MUL | DIV | PERCENTAGE;

factor: decimal | '(' binArithmetic ')' | pathNode | funcNode;

space :
    SPACE+;

chineseString: CHINESE+;

SingleQuoteAnyText: '\'' ~'\''* '\'';

IN: 'in';
NOT: 'not';
LIKE: 'like';
RLIKE: 'rlike';
EQ: '=';
NEQ: '!=';
GT: '>';
LT: '<';
GE: '>=';
LE: '<=';


ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
PERCENTAGE:'%';

SPACE:' ';
SingleQuote: '\'';
QUE_MARK :'?';
SIGH :'!';
COMMA :',';
DOT: '.';
DOLLAR: '$';
LCURLY: '{';
RCURLY: '}';
LBRACKET: '[';
RBRACKET: ']';
INT: [0-9]+;
FLOAT: [0-9]+'.'[0-9]+;

IDENTIFIER:
    [a-zA-Z_] [a-zA-Z_0-9]*;

CHINESE: [\u4e00-\u9fa5];

WELL:'#';
AND:'&';
SEMICOLON:';';
AT:'@';
COLON:':';


Whitespace:
    [ \t\r\n]+ -> skip;
