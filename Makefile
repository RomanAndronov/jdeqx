MF = /tmp/deqManifest

DEQX = deqx.jar
SRCDIR = deqx

JFLAGS = -g
JAVAC = javac -cp ./$(SRCDIR):${CLASSPATH}

.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAGS) $<

_DEQX_SRC = DeqXApplet.java \
	DeqXFrame.java \
	DeqXPanel.java \
	DeqXGui.java \
	Square.java \
	SquareView.java \
	SquareViewMouseHandler.java \
	Move.java

DEQX_SRC = $(_DEQX_SRC:%=$(SRCDIR)/%)

DEQX_CLASSES = $(DEQX_SRC:.java=.class)

$(DEQX):$(DEQX_SRC) $(DEQX_CLASSES)
	rm -f $(MF)
	echo "Main-Class: $(SRCDIR)/DeqXFrame" > $(MF)
	jar cmf $(MF) $@ $(SRCDIR)/*.class
	rm -f $(MF)

clean:
	rm -f $(DEQX) $(SRCDIR)/*.class
