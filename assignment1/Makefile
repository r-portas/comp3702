JC = javac -d build -classpath build -source 1.6 -target 1.6

all: EnvironmentMap.class NavigationAgent.class Main.class

# Runs the classes
run:
	java -cp build/ -Xmx101m Main example1/exEnv1.txt example1/exQueries1.txt example1/output.txt

# Builds a jar file
jar: a1-comp3702-43560846.jar

a1-comp3702-43560846.jar: Main.class
	jar cvmf build/Manifest.txt a1-comp3702-43560846.jar -C build/ .

Main.class: code/Main.java
	$(JC) code/Main.java

EnvironmentMap.class: code/EnvironmentMap.java
	$(JC) code/EnvironmentMap.java

NavigationAgent.class: code/NavigationAgent.java
	$(JC) code/NavigationAgent.java

clean:
	rm -f build/*.class

