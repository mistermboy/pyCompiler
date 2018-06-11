# LEXICAL PATTERNS
```
Rubbish = [ \t\n\r]

CommentV1 = #.*

CommentV2 = \"""~\"""

Letter = [a-zA-Z]

Digit  = [0-9]

Ident = [_a-zA-Z][a-zA-Z_0-9]*

IntConstant = [0-9]*


RealType = [0-9]+[.][0-9]* | [.][0-9]+

RealConstant = {RealType}|{RealType}E[+|-][0-9]+|[0-9]+e[+|-][0-9]+

Character = \'.\'

CharacterASCII = [']\\[0-9]*[']
```

																																																																	
