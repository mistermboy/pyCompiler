import java.io.FileReader;
import java.io.IOException;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import scanner.Scanner;
import parser.Parser;
import errorHandler.*;

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

		// * Check errors
		if (EH.getEH().hasErrors()) {
			// * Show errors
			EH.getEH().showErrors(System.err);
		} else {
			// * Show AST
			IntrospectorModel model = new IntrospectorModel("Program", parser.getAST());
			new IntrospectorTree("Introspector", model);
		}
		
	}

} 