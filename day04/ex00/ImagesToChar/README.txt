# Command to compile

javac -sourcepath src/java -d target src/java/edu/school21/printer/app/Program.java


# Command to launch
# The whiteCharacter parameter will replace white color pixel with that character in the output
# The blackCharacter parameter will replace black color pixel with that character in the output
# The pathToImage parameter is the full path to the image file

java -classpath target edu.school21.printer.app.Program --whiteCharacter=<Character> --blackCharacter=<Character> --pathToImage=<path/to/image>
