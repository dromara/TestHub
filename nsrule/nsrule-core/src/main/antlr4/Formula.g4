grammar Formula;

// 语法规则
formula: '%{' VARNAME '(' params*')''}'                # methodFormula
       | '${' pathFormula '}'                 # variableFormula
       | STRING                           # stringFormula
       | NUMBER                           # numberFormula
       ;

pathFormula: (VARNAME | NUMBER) (array | property)* ; // 解析路径表达式
range: (NUMBER | formula) COMMA (NUMBER | formula);
array: LBRACKET (NUMBER | '*' | range | formula)* RBRACKET  ; // 数组访问规则，例如 [2] 或 [*] 或 [][][]
property: DOT VARNAME ; // 属性访问规则

params: param (';' param)*;

param: VARNAME ':' formula        # paramFormula
     ;

// 词法规则
DOT: '.' ;
LBRACKET: '[' ;
RBRACKET: ']' ;
COMMA: ',' ;

VARNAME: [a-zA-Z_][a-zA-Z0-9_]*;//参数名称
STRING: '\'' ( '\\'. | '\'\'' | ~('\''| '\\') )* '\''; // 字符串面量
NUMBER: [0-9]+; // 整数字面量

WS: [ \t\r\n]+ -> skip;
