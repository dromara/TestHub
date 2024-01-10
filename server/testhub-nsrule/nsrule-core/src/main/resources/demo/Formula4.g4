grammar Formula;

formula: (dataNode | pathNode | funcNode)*;

funcNode:
   '%{' Identifier '(' (param (',' param)*)? ')' '}';


dataNode:
    Identifier;

pathNode:
    '${' jsonpath '}';

param:
    (Identifier ':' formula) | formula; // 支持键值对和值列表形式


jsonpath:
    identifier (subscript)*;

subscript:
    dotIdentifier | arraySubscript;

dotIdentifier:
    DOT identifier;

arraySubscript:
    LBRACKET (asterisk | arrayIndex | pathNode) RBRACKET;

arrayIndex:
    INT;

asterisk:
    ASTERISK (dotIdentifier)?;

identifier:
    Identifier;


DOT:
    '.';

DOLLAR:
    '$';

LCURLY:
    '{';

RCURLY:
    '}';

LBRACKET:
    '[';

RBRACKET:
    ']';

ASTERISK:
    '*';

INT:
    [0-9]+;

Identifier:
    [a-zA-Z_] [a-zA-Z_0-9]*;

Whitespace:
    [ \t\r\n]+ -> skip;
