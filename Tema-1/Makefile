# Exemplu de Makefile pentru soluții scrise în Java.

.PHONY: build clean

build: P1.class P2.class P3.class P4.class

run-p1:
	java Feribot
run-p2:
	java Nostory
run-p3:
	java Sushi
run-p4:
	java Semnale

# Nu uitați să modificați numele surselor.
P1.class: Feribot.java
			javac $^
P2.class: Nostory.java
			javac $^
P3.class: Sushi.java
			javac $^
P4.class: Semnale.java
			javac $^

# Vom șterge fișierele bytecode compilate.
clean:
	rm -f *.class
