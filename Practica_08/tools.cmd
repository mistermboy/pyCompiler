java -cp tools\jflex\JFlex.jar JFlex.Main -d src\scanner src\scanner\scanner.jflex
cd tools\byaccj
yacc.exe -J -v -Jpackage=parser -Jsemantic=Object "../../src/parser/parser.y"
move Parser.java ../../src/parser
move y.output ../../src/parser