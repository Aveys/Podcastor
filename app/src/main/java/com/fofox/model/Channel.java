package com.fofox.model;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by arthurveys on 13/10/14.
 */
public class Channel {
    public String title;
    public String link;
    public String lang;
    public String description;
    public String lstBuild;
    public  String imageUrl;
    public  String imageTitle;
    public  String imageLink;

    ArrayList<Podcast> listPodcast;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLstBuild() {
        return lstBuild;
    }

    public void setLstBuild(String lstBuild) {
        this.lstBuild = lstBuild;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Channel(String title, String link, String lang, String lstBuild, String imageUrl) {
        this.title = title;
        this.link = link;
        this.lang = lang;
        this.lstBuild = lstBuild;
        this.imageUrl = imageUrl;
    }

    public Channel() {
        this.title = null;
        this.link = null;
        this.lang = null;
        this.lstBuild = null;
        this.imageUrl = null;
        this.listPodcast = new ArrayList<Podcast>();
    }

    public void addItem(Podcast item) {
        listPodcast.add(item);
    }
}
