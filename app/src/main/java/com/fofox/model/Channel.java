package com.fofox.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by arthurveys on 13/10/14.
 */
public class Channel {
    private String title;
    private String link;
    private String lang;
    private String description;
    private Date lstBuild;
    private String imageUrl;
    private String imageTitle;

    ArrayList<Podcast> listPodcast;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
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

    public Date getLstBuild() {
        return lstBuild;
    }

    public void setLstBuild(Date lstBuild) {
        this.lstBuild = lstBuild;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Channel(String title, String link, String lang, Date lstBuild, String imageUrl) {
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

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", lang='" + lang + '\'' +
                ", description='" + description + '\'' +
                ", lstBuild='" + lstBuild + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageTitle='" + imageTitle + '\'' +
                ", listPodcast=" + listPodcastToString(listPodcast) +
                '}';
    }

    private String listPodcastToString(ArrayList<Podcast> listPodcast) {
        StringBuilder text = new StringBuilder();
        for(Podcast pod : listPodcast){
            text.append(pod.toString()+"\n");
        }
        return text.toString();
    }
}
