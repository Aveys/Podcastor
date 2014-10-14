package com.fofox.parser;

import android.util.Log;

import com.fofox.model.Channel;
import com.fofox.model.Podcast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Android App : ${PROJECT_NAME}
 * Created by arthurveys on 14/10/14.
 */
public class ItunesRSSParser {
    private static final String TAG = "Podcastor";
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
                        else if (tagName.equalsIgnoreCase("title") && pod==null){
                            chan.setTitle(parser.nextText());
                        }
                        else if (tagName.equalsIgnoreCase("itunes:summary") && pod==null){
                            chan.setDescription(parser.nextText());
                        }
                        else if (tagName.equalsIgnoreCase("link")&& pod==null)
                            chan.setLink(parser.nextText());
                        else if (tagName.equalsIgnoreCase("language")&& pod==null)
                            chan.setLang(parser.nextText());
                        else if (tagName.equalsIgnoreCase("lastBuildDate")&& pod==null){
                            SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:MM:SS ZZZ");
                            try {
                                Date date = format.parse(parser.nextText());
                                chan.setLstBuild(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        else if(tagName.equalsIgnoreCase("image")&& pod==null){
                                parser.nextTag();
                                tagName = parser.getName();
                                if(tagName.equalsIgnoreCase("url"))
                                    chan.setImageUrl(parser.getText());

                        }
                        else if(tagName.equalsIgnoreCase("title") && pod!=null)
                            pod.setTitle(parser.nextText());
                        else if(tagName.equalsIgnoreCase("enclosure")&& pod!=null)
                            pod.setLink(parser.getAttributeValue(null,"url"));
                        else if (tagName.equalsIgnoreCase("itunes:summary") && pod!=null){
                            pod.setDescription(parser.nextText());
                        }
                        else if(tagName.equalsIgnoreCase("pubDate")&& pod!=null){
                            SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:MM:SS ZZZ");
                            try {
                                Date date = format.parse(parser.nextText());
                                pod.setPubDate(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        if (tagName.equalsIgnoreCase("item")){
                            if (pod!=null){
                                chan.addItem(pod);
                                pod=null;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.d(TAG,"TEXT PARSER : "+parser.getText());
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            chan = null;
        } catch (IOException e) {
            e.printStackTrace();
            chan=null;
        }
        return chan;
    }
}
