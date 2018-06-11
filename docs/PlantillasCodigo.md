# CODE TEMPLATES

### EXECUTE [[Program: Program -> Definition*]]
	
	for(Definition d:Definition)
		if(d instanceof VarDefinition)
			EXECUTE[[d]]()
			
	<CALL MAIN>
	<HALT>

	for(Definition d:Definition)
		if(d instanceof FunDefinition)
			EXECUTE[[d]]()

	
	

### EXECUTE[[FunDefinition: Definition -> Type Statement*]]

	Definition.Name <:>
	<ENTER> Definition.LocalBytes
	for(Statement s:Statement*)
		if(!s instanceof VarDefinition)
			EXECUTE[[s]]()
	if(Type.ReturnType instanceof VoidType)
		<RET> 0 <,> Definition.LocalBytes <,> Definition.ParamBytes

### EXECUTE[[Write: Statement -> Exp]]
	
	VALUE[[Exp]]()
	<OUT> Exp.Type.Suffix()
	
### EXECUTE[[Read: Statement -> Exp]]
	
	ADDRESS[[Exp]]()
	<IN> Exp.Type.Suffix()
	<STORE> Exp.Type.Suffix()

### EXECUTE[[Assigment: Statement -> Exp1 Exp2]
	
	ADDRESS[[Exp1]]()
	VALUE[[Exp2]]()
	cg.convert(Exp2.Type,Exp1.Type)
	<STORE> Exp1.Type.Suffix()
	
### EXECUTE[[IfStatement: Statement	-> Exp if:Statement* else:Statement*]]
	
	int label =  cg.getLabels(2);
	VALUE[[Exp]]()
	<JZ><LABEL> label
	
	for(Statement s:if)
		EXECUTE[[s]]()
		
	<JMP><LABEL> label+1
	<LABEL> label <:>	
	
	for(Statement s:else)
		EXECUTE[[s]]()
		
	<LABEL> label+1 <:>	

### EXECUTE[[WhileStatement: Statement -> Exp Statement*]]
	
	int label =  cg.getLabels(2);
	<LABEL> label <:>
	VALUE[[Exp]]
	<JZ><LABEL> label+1
	
	for(Statement s:Statement*)
		EXECUTE[[s]]()
	
	<JMP><LABEL> label
	<LABEL> label+1 <:>
	
### EXECUTE[[ Invocation: Statement -> Variable Exp*]]

	VALUE[[ (Expression) Statement]]()
	if(Variable.Type.ReturnType != IO.VoidType)
		<POP> Variable.Type.ReturnType.Suffix();
		
### EXECUTE[[Return: Statement -> Exp]] Param -> (FunDefinition)
	
	VALUE[[Exp]]()
	cg.convert(Exp.Type,FunDefinition.Type.ReturnType);
	<RET> FunDefinition.ReturnType.NumberBytes
	<,> FunDefinition.LocalBytes
	<,> FunDefinition.ParamBytes
	
 
### VALUE[[IntLiteral: Exp -> IntConstant]]
	
	<PUSHI> Exp.VALUE
	
### VALUE[[ChaLiteral: Exp -> CharConstant]]
	
	<PUSHB> Exp.VALUE

### VALUE[[RealLiteral: Exp -> RealConstant]]
	
	<PUSHF> Exp.VALUE
	
### VALUE[[Variable: Exp -> ID]]
	
	ADDRESS[[EXP]]()
	<LOAD> Exp.Type.Suffix() 
	
### VALUE[[Arithmetic: Exp1 -> Exp2 Exp3 ]]

	VALUE[[Exp2]]()
	cg.convert(Exp2.Type,Exp1.Type)
	VALUE[[Exp3]]()
	cg.convert(Exp3.Type,Exp1.Type)
	cg.arithmetic(Exp1.operator,Exp1.Type)
	
### VALUE[[Comparison: Exp1 -> Exp2 Exp3 ]]

	supertype = Exp2.Type.SuperType(Exp3.Type)
	VALUE[[Exp2]]()
	cg.convert(Exp2.Type,supertype)
	VALUE[[Exp3]]()
	cg.convert(Exp3.Type,supertype)
	cg.comparison(Exp1.operator,supertype)

### VALUE[[Cast: Exp1 -> CastType Exp2]]

	VALUE[[Exp2]]()
	cg.cast(Exp2.Type, CastType)
	
### VALUE[[Logical: Exp1 -> Exp2 Exp3 ]]

	VALUE[[Exp2]]()
	VALUE[[Exp3]]()
	cg.logig(Exp1.operator)
	
### VALUE[[UnaryNot: Exp1 -> Exp2]]

	VALUE[[Exp2]]()
	<NOT>
	
### VALUE[[FieldAcces: Exp1 -> Exp2 ID]]	
	
	ADDRESS[[Exp1]]()
	<LOAD>Exp1.Type.Suffix()
	
### VALUE[[Indexing: Exp1 -> Exp2 Exp3 ]]	

	ADDRESS[[EXP1]]()
	<LOAD>Exp1.Type.Suffix()
	
### VALUE[[Invocation: Exp -> Variable Exp*]]

	int i=0;
	for(Expression e:Exp*)
		VALUE[[e]]()
		cg.convert(e.Type,Variable.Type.parameters[i++].Type)
	<CALL> Variable.Name
	

### ADDRESS[[Variable: Exp -> ID]]

	if(Exp.Definition.scope == 0)
		<PUSHA> Exp.Definition.Offset
	else
		<PUSH BP>
		<PUSHI> Exp.Definition.Offset
		<ADDI>
		
### ADDRESS[[ Indexing: Exp1 -> Exp2 Exp3 ]]

	ADDRESS[[Exp2]]()
	VALUE[[Exp3]]()
	<PUSH> Exp1.Type.NumberBytes
	<MUL>
	<ADD>
	
### ADDRESS[[FieldAcces: Exp1 -> Exp2 ID]]

	ADDRESS[[Exp2]]
	<PUSH>Exp2.Type.get(ID).Offset
	<ADD>
	
