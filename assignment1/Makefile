JC = javac -d build -classpath build -source 1.6 -target 1.6

all: EnvironmentMap.class Main.class

run:
	java -cp build/ -Xmx101m Main example1/exEnv1.txt example1/exQueries1.txt example1/output.txt

Main.class: code/Main.java
	$(JC) code/Main.java

EnvironmentMap.class: code/EnvironmentMap.java
	$(JC) code/EnvironmentMap.java

clean:
	rm -f build/*.class

