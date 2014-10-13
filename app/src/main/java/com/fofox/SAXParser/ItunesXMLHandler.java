package com.fofox.SAXParser;

import com.fofox.model.Channel;
import com.fofox.model.Podcast;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by arthurveys on 13/10/14.
 */
public class ItunesXMLHandler extends DefaultHandler {
    private Channel       rssFeed;
    private StringBuilder text;
    private Podcast          item;
    private boolean       imgStatus;

    public void startElement(String uri, String localName, String qName,
                              Attributes attributes)
    {
        if (qName.equalsIgnoreCase("channel"))
            this.rssFeed = new Channel();
        else if (qName.equalsIgnoreCase("item") && (this.rssFeed != null))
        {
            this.item = new Podcast();
            this.rssFeed.addItem(this.item);
        }
        else if (qName.equalsIgnoreCase("image") && (this.rssFeed != null))
            this.imgStatus = true;
        else if (qName.equalsIgnoreCase("enclosure")){
            this.item.setLink(attributes.getValue("url"));
        }
    }

    public void endElement(String uri, String localName, String qName)
    {
        if (this.rssFeed == null)
            return;

        if (qName.equalsIgnoreCase("item"))
            this.item = null;

        else if (qName.equalsIgnoreCase("image"))
            this.imgStatus = false;

        else if (qName.equalsIgnoreCase("title"))
        {
            if (this.item != null) this.item.title = this.text.toString().trim();
            else this.rssFeed.title = this.text.toString().trim();
        }
        else if (qName.equalsIgnoreCase("enclosure")){
            System.out.println("test");
        }
        else if (qName.equalsIgnoreCase("link"))
        {
            if (this.item != null) this.item.link = this.text.toString().trim();
            else if (this.imgStatus) this.rssFeed.imageLink = this.text.toString().trim();
            else this.rssFeed.link = this.text.toString().trim();
        }

        else if (qName.equalsIgnoreCase("description"))
        {
            if (this.item != null) this.item.description = this.text.toString().trim();
            else this.rssFeed.description = this.text.toString().trim();
        }

        else if (qName.equalsIgnoreCase("url") && this.imgStatus)
            this.rssFeed.imageUrl = this.text.toString().trim();

        else if (qName.equalsIgnoreCase("language"))
            this.rssFeed.lang = this.text.toString().trim();


        else if (qName.equalsIgnoreCase("pubDate") && (this.item != null))
            this.item.pubDate = this.text.toString().trim();

        this.text.setLength(0);
    }

    public void characters(char[] ch, int start, int length)
    {
        this.text.append(ch, start, length);
    }
}
