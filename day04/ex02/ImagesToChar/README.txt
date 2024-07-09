# Download external libraries

mkdir lib
cd lib
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar --output jcommander-1.78.jar
curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar --output JCDP-4.0.2.jar
cd ..


# Command to compile

javac -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.78.jar -d target src/java/edu/school21/printer/app/Program.java


# Copy resources folder

cp -R src/resources target/


# Extract libraries jar archive to target folder

cd target
jar xf ../lib/JCDP-4.0.2.jar
jar xf ../lib/jcommander-1.78.jar
rm -rf META-INF
cd ..


# Create JAR archive

jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target .


# Command to launch
# The white parameter specifies the color to replace the white pixel with.
# The black parameter specifies the color to replace the black pixel with.
# Allowed colors in parameters: BLACK, BLUE, CYAN, GREEN, MAGENTA, NONE, RED, WHITE, YELLOW

java -jar target/images-to-chars-printer.jar --white=<Character> --black=<Character>
