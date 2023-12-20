package com.clementbayard.clement_ws.photo;

public class PhotoDto {
    private Long id;

    private String type;
    private String filename;
       private long photographeId;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getPhotographeId() {
        return photographeId;
    }

    public void setPhotographeId(long photographeId) {
        this.photographeId = photographeId;
    }

    public PhotoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
