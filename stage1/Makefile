JFLAGS = -g
JC = javac
JVM = java
FILEIN = entrada.csv
FILEOUT = salida.csv
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Stage1Test.java \
	Operator.java \
	Joystick.java

MAIN = Stage1Test

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILEIN) > $(FILEOUT)

clean:
	$(RM) *.class
