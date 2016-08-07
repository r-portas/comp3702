JC = javac

all: BFSSearch.class DFSSearch.class

DFSSearch.class: DFSSearch.java build/Node.class
	javac -d build -classpath build DFSSearch.java

BFSSearch.class: BFSSearch.java build/Node.class
	javac -d build -classpath build BFSSearch.java

build/Node.class: Node.java
	javac -d build -classpath build Node.java

clean:
	rm -f build/*.class

