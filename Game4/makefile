 # Author: Alexander Oey (NetID: aoey2) 
 .PHONY: all build jar cbuild cjar clean run
 FLAGS = -g -deprecation
 JC = javac
 
 SRC_DIR=./src
 CLS_DIR=./build/classes
 JDOC_DIR=./docs/
 SOURCE:= $(wildcard ./src/*/*.java)
 MANIFEST:= MANIFEST.txt
 JAR_FILE:= Game.jar
 
 #LIST:=
 #BUILD_CLASS:= $(SOURCE:.java=.class)
 #BUILD_CLASS=$(patsubst $(SRC_DIR)/%,$(CLS_DIR)/%,$(patsubst %.java,%.class,$(SOURCE)))
 BUILD_CLASS=$(wildcard ./build/classes/*/*.class)
 
 all: build jar
 
 build: $(SOURCE) $(CLS_DIR)
 		$(JC) $(FLAGS) -d $(CLS_DIR) -sourcepath $(SRC_DIR) $(SOURCE) ;
 
 $(CLS_DIR) :
 	mkdir -p $(CLS_DIR)
 
 $(JDOC_DIR):
 	mkdir -p $(JDOC_DIR)
 	
 #$(BUILD_CLASS): $(CLS_DIR)/%.class : $(SRC_DIR)/%.java
 #	$(eval LIST+=$$<)
 
jar: $(JAR_FILE) 
 $(JAR_FILE): $(MANIFEST) $(BUILD_CLASS)
	jar cvfm $@ $< -C ./build/classes game
	jar cvfm $@ $< -C ./build/classes .
 cbuild:
	rm -rf ./build
 
 cjar:
 	rm Game.jar
 	
 cdoc:
 	rm -rf ./docs/
 
 clean: cbuild cjar
 
 javadoc: $(SOURCE)
 	javadoc -sourcepath $(SRC_DIR) $(SOURCE) -d $(JDOC_DIR)
 	
 #run: $(JAR_FILE)
 #	java -jar $(JAR_FILE)
