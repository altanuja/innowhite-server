package com.innowhite.whiteboard.persistence.beans;

public class PlayBackPlayListVO {

    private String duration;
    private String filePath;
    private String server;
    private String size;

    public String getFilePath() {
	return filePath;
    }

    public void setFilePath(String filePath) {
	this.filePath = filePath;
    }

    /**
     * @param duration
     *            the duration to set
     */
    public void setDuration(String duration) {
	this.duration = duration;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
	return duration;
    }

    public String getSize() {
	return size;
    }

    public void setSize(String size) {
	this.size = size;
    }

    public String getServer() {
	return server;
    }

    public void setServer(String server) {
	this.server = server;
    }

}
