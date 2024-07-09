# Command to compile

javac -sourcepath src/java -d target src/java/edu/school21/printer/app/Program.java


# Copy resources folder

cp -R src/resources target/


# Create JAR archive

jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target .


# Command to launch
# The whiteCharacter parameter will replace white color pixel with that character in the output
# The blackCharacter parameter will replace black color pixel with that character in the output

java -jar target/images-to-chars-printer.jar --whiteCharacter=<Character> --blackCharacter=<Character>
