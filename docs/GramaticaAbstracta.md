# Abstract grammar
```
Program: Program -> Definition*

VarDefinition: Definition -> Type ID
FunDefinition: Definition -> Type Statement*


Write: Statement -> Exp
Read: Statement -> Exp
Assigment: Statement -> Exp1 Exp2
IfStatement: Statement	-> Exp if:Statement* else:Statement*
WhileStatement: Statement -> Exp Statement*
Invocation: Statement -> Variable Exp*
Return: Statement -> Exp



IntLiteral: Exp -> IntConstant
ChaLiteral: Exp -> CharConstant
RealLiteral: Exp -> RealConstant
Variable: Exp -> ID
Arithmetic: Exp -> left:Exp right:Exp
Comparison: Exp -> left:Exp right:Exp
Cast: Exp -> CastType valor:Exp 
Logical: Exp -> left:Exp right:Exp
UnaryNot: Exp -> valor:Exp
FieldAcces: Exp -> valor:Exp ID
Indexing: Exp -> left:Exp right:Exp
Invocation: Exp -> Variable Exp*
```
