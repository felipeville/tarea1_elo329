JFLAGS = -g
JC = javac
JVM = java
FILEIN = entrada.csv
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	AcrobaticDrones.java \
	Operator.java \
	Joystick.java \
	Joysticks.java \
	Drone.java \
	SkyController.java \
	State.java \
	Actionable.java \
	InputDevice.java \
	Keyboard.java

MAIN = AcrobaticDrones

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) $(FILEIN)

clean:
	$(RM) *.class
