grammar Demo;

parse : prog+ ;

prog : 'Hello' ID
    | 'hello' ID
    ;

ID : [a-z]+ ;

WS : [ \t\n\r]+ -> skip ;