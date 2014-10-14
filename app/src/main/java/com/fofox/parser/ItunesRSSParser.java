package com.fofox.parser;

import com.fofox.model.Channel;
import com.fofox.model.Podcast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

/**
 * Created by arthurveys on 14/10/14.
 */
public class ItunesRSSParser {
    public static Channel parse(InputStream is) {
        Channel chan = null;
        Podcast pod = null;
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        chan = new Channel();
                        break;
                    case XmlPullParser.START_TAG:
                        String tagName = parser.getName();
                        if (tagName.equalsIgnoreCase("item")){
                            pod = new Podcast();
                        }
                        else if (tagName.equalsIgnoreCase("title"))

                        break;
                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if (tagName.equalsIgnoreCase("item")){
                            if (pod!=null)
                                chan.addItem(pod);
                        }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return chan;
    }
}
