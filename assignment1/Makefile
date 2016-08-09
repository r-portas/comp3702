JC = javac

all: Main.class

run:
	java -cp build/ -Xmx100m Main

Main.class: code/Main.java
	javac -d build -classpath build -source 1.6 -target 1.6 code/Main.java

clean:
	rm -f build/*.class

