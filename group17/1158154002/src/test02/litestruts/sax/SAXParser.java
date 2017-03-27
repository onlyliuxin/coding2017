package test02.litestruts.sax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import test02.litestruts.Action;

public class SAXParser {
    private static SAXParser parserInstance = new SAXParser();
    private static SAXParserHandler parserHandler;
    private SAXParser(){} // Singleton Pattern, a private constructor
    private static SAXParserState state = SAXParserState.OUT_OF_TAG; // initial state

    public static SAXParser getInstance() {
        return parserInstance;
    }

    public Action parse(String path){
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
	        int currentCharCode;
	        // callback start document
	        parserHandler.startDocument();
	        try {
				while((currentCharCode = br.read()) != -1){
				    char currentChar = (char)currentCharCode;
				    handleParser(currentChar);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       return parserHandler.endDocument();
    }

    public void setHandler(SAXParserHandler handler){
        parserHandler = handler;
    }

    private static void handleParser(char c) {
        // This SAX Parser will ignore any line wrap.
        if(c == '\n'){
            return;
        }
        switch (state){
            case OUT_OF_TAG:{
                if(c == '<'){
                    if(SAXParsedData.innerText.trim().length() != 0) {
                        parserHandler.innerText(SAXParsedData.innerText);
                    }
                    SAXParsedData.innerText = "";
                    SAXParsedData.tagName = "";
                    state = SAXParserState.BEGIN_START_OR_END_TAG;
                } else if (c == '>') {
                    state = SAXParserState.SYNTAX_ERROR;
                } else {
                    SAXParsedData.innerText += c;
                }
                break;
            }
            case BEGIN_START_OR_END_TAG:{
                if(c == '/') {
                    SAXParsedData.tagName = "";
                    state = SAXParserState.IN_END_TAG;
                }else if(c == '?' || c == '!'){
                    state = SAXParserState.METADATA;
                }else{
                    SAXParsedData.tagName += c;
                    state = SAXParserState.IN_START_TAG;
                }
                break;
            }
            case IN_START_TAG:{
                if(c == ' '){
                    state = SAXParserState.SPACE_IN_START_TAG;
                }else if(c == '>'){
                    // callback startElement event;
                    parserHandler.startElement(SAXParsedData.tagName, SAXParsedData.attributes);
                    SAXParsedData.clear();
                    state = SAXParserState.CLOSE_START_TAG;
                }else {
                    SAXParsedData.tagName += c;
                }
                break;
            }
            case SPACE_IN_START_TAG:{
                if(SAXParsedData.tagName.length() > 0){
                    if(c != ' '){
                        SAXParsedData.attribKey += c;
                        state = SAXParserState.IN_ATTRIB_KEY;
                    }
                }
                break;
            }
            case IN_ATTRIB_KEY:{
                if(c == '='){
                    state = SAXParserState.IN_ATTRIB_EQUAL;
                }else{
                    SAXParsedData.attribKey += c;
                }
                break;
            }
            case IN_ATTRIB_EQUAL:{
                if(c == '"'){
                    state = SAXParserState.IN_ATTRIB_VALUE;
                }
                break;
            }
            case IN_ATTRIB_VALUE:{
                if(c == '"'){
                    SAXParsedData.newAttribute();
                    state = SAXParserState.IN_START_TAG;
                }else{
                    SAXParsedData.attribValue += c;
                }
                break;
            }
            case CLOSE_START_TAG:{
                if(c == '<') {
                    state = SAXParserState.BEGIN_START_OR_END_TAG;
                }else{
                    SAXParsedData.innerText += c;
                    state = SAXParserState.OUT_OF_TAG;
                }
                break;
            }
            case IN_END_TAG:{
                if(c == '>'){
                    // callback endElement event
                    parserHandler.endElement(SAXParsedData.tagName);
                    state = SAXParserState.CLOSE_END_TAG;
                }else{
                    SAXParsedData.tagName += c;
                }
                break;
            }
            case CLOSE_END_TAG:{
                if(c == ' '){
                    state = SAXParserState.OUT_OF_TAG;
                }else if(c == '<'){
                    SAXParsedData.tagName = "";
                    state = SAXParserState.BEGIN_START_OR_END_TAG;
                }
                break;
            }
            case METADATA:{
                if(c == '>'){
                    state = SAXParserState.CLOSE_END_TAG;
                }
                break;
            }
            case SYNTAX_ERROR:{
                try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }

    }

    private enum SAXParserState {
        // The state when parser meets "<". This is a pending state.
        // If the next char is "/", the state will be IN_END_TAG,
        // Otherwise, the state will be IN_START_TAG
        BEGIN_START_OR_END_TAG,

        // The state when parser is reading between start tag(<...>).
        // When parser meets ">", callback "startElement" event
        IN_START_TAG,

        // The state when parser is reading between end tag(</...>).
        // When parser meets "<", callback "endElement" event
        IN_END_TAG,

        // The state when parser meets " ", and is in IN_START_TAG state.
        // If the length of tag_name is non-zero, finish parsing tag_name.
        // Otherwise, finish parsing a key/value attribute.
        SPACE_IN_START_TAG,
        IN_ATTRIB_KEY,IN_ATTRIB_EQUAL,IN_ATTRIB_VALUE,
        CLOSE_START_TAG,CLOSE_END_TAG,

        // The state when parser is reading any char at the outside of <XXX>, or between two <XXX>. This is a pending state.
        // Contents between <XXX> will be recorded, but if the contents consist only spaces, the content will be discarded.
        // Otherwise, callback "innerText" event.
        OUT_OF_TAG,

        METADATA, SYNTAX_ERROR
    }

    private static class SAXParsedData{
        private static String tagName = "";
        private static String attribKey = "";
        private static String attribValue = "";
        private static String innerText = "";
        private static HashMap<String,String> attributes = new HashMap<>();

        private static void clear(){
            tagName = "";
            attribKey = "";
            attribValue = "";
            innerText = "";
            attributes.clear();
        }

        private static void newAttribute(){
            attributes.put(attribKey, attribValue);
            attribKey = "";
            attribValue = "";
        }
    }
}