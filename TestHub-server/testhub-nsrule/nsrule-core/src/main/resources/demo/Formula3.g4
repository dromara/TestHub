grammar Formula;

formula: fixNode | pathNode | dataNode;


fixNode:
    (dataNode | pathNode)*;

dataNode:
    Identifier;

pathNode:
    '${' jsonpath '}';


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
