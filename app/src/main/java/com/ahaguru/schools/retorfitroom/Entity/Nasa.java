package com.ahaguru.schools.retorfitroom.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "nasa_table")
public class Nasa {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("explanation")
    @ColumnInfo(name = "explanation")
    private String explanation;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    public Nasa(String explanation, String title, String url) {
        this.explanation = explanation;
        this.title = title;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
