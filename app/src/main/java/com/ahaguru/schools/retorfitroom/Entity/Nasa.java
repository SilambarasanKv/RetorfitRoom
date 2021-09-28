package com.ahaguru.schools.retorfitroom.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "nasa_table")
public class Nasa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("date")
    @ColumnInfo(name = "date")
    private String date;

    @SerializedName("explanation")
    @ColumnInfo(name = "explanation")
    private String explanation;

    @SerializedName("hdurl")
    @ColumnInfo(name = "hdurl")
    private String hdurl;

    @SerializedName("media_type")
    @ColumnInfo(name = "media_type")
    private String media_type;

    @SerializedName("service_version")
    @ColumnInfo(name = "service_version")
    private String service_version;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    public Nasa(int id, String date, String explanation, String hdurl, String media_type, String service_version, String title, String url) {
        this.id = id;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Nasa{" +
                "title" + title +
                ", explanation" + explanation +
                ", url'" + url  +

                '}';
    }
}
