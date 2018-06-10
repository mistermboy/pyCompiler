# PYCompiler :eyes:
PYCompiler is a compiler for a simple version of python that was develop in the subject "Diseño de Lenguajes de Programación"

## Getting Started :rocket:
To begin with the project, clone the repository in your computer. To do so, you can use this command:

```
git clone https://github.com/mistermboy/PYCompiler.git;
```

Alternative, you can download the repository as a zip file from the [repository main page](https://github.com/mistermboy/PYCompiler).

Once you have the repository, import the project in eclipse

```
-> File 
  -> Import
    -> General 
      -> Existing Projects into Workspace...
```

### Using PYCompyler
To use PYCompyler there are two alternatives, you can write your own program (PONER AQUÍ ENLACE A LA SINTAXIS) or use an example program.
I recommend you the last option if it´s your first time. There are a few example programs in the folder called "program4Test".

Once you have chosen an option, you only have to run the main class, that is in the package "py", passing the name of the input and the output file. Here is a example passing the program "big.input.txt"

```
-> Run as 
  -> Run configurations
    -> Java Application  
      -> Arguments 
        -> programs4Test/big.input.txt output.txt
```
Now you will see the instrospector window, that shows a general vist of the AST (Abstract Syntax Tree) built by the compiler. You can take a look if you want but it´s not necesary.

Close the introspector window and check the output file. You should see the instructions of the program you passed written in a language based in assembler.

#### MAPL :wrench:
At this point we need to use some tool that is able to interpret the instructions. That tool is MAPL. 

Execute "MAPL.cmd".
```
Location -> PYCompiler\MAPL.cmd
```
By default, MAPL charge the "output.txt" file but you can chose other in "File -> Open..."

Using MAPL is very simple. At the left of the window we have the instructions of the program loaded and the buttons to run, debugg, reload...In the middle the static memory and finally in the rigth, the stack memory. Also there is a small terminal bottom left.
