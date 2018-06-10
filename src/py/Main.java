package py;
import java.io.FileReader;
import java.io.IOException;

import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import py.codegeneration.ExecuteCodeGeneratorVisitor;
import py.errorHandler.EH;
import py.parser.Parser;
import py.scanner.Scanner;
import py.visitor.IdentificationVisitor;
import py.visitor.OffSetVisitor;
import py.visitor.TypeCheckingVisitor;
import py.visitor.Visitor;

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Pass me the name of the input file.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("The file " + args[0] + " could not be opened.");
			return;
		}

		// * Scanner and parser creation
		Scanner lexico = new Scanner(fr);
		Parser parser = new Parser(lexico);
		// * Parsing
		parser.run();

		Visitor i = new IdentificationVisitor();
		Visitor v = new TypeCheckingVisitor();
		Visitor o = new OffSetVisitor();
		Visitor e = new ExecuteCodeGeneratorVisitor(args[0], args[1]);

		parser.getAST().accept(i, null);

		if (EH.getEH().hasErrors()) {
			// * Show errors
			EH.getEH().showErrors(System.err);
		} else {
			parser.getAST().accept(v, null);

			if (EH.getEH().hasErrors()) {
				// * Show errors
				EH.getEH().showErrors(System.err);
			} else {
				parser.getAST().accept(o, null);

				if (EH.getEH().hasErrors()) {
					// * Show errors
					EH.getEH().showErrors(System.err);
				} else {
					parser.getAST().accept(e, null);

					// * Show AST
					IntrospectorModel model = new IntrospectorModel("Program", parser.getAST());
					new IntrospectorTree("Introspector", model);

				}

			}

		}
		
	}

}