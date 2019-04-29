package com.kalangirajeev.ebooks.apgovtebooks;

/**
 * Created by dell on 12/15/2016.
 */

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLPullParserHandler {

    List<String> list;

    private Ebook ebook;
    private String text;

    public XMLPullParserHandler() {
        list = new ArrayList<String>();
    }

    public List<String> getEbook() {
        return list;
    }

    public List<String> parse(InputStream is) {

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            parser = factory.newPullParser();
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        if(tagname.equalsIgnoreCase("para")) {
                            ebook = new Ebook();
                        }
                        break;

                    case XmlPullParser.TEXT:

                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(tagname.equalsIgnoreCase("para")) {
                            list.add(ebook.toString());
                        }else if(tagname.equalsIgnoreCase("para_name")) {
                            ebook.setPara_name(text);
                        }else if(tagname.equalsIgnoreCase("para_desc")) {
                            ebook.setPara_desc(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
