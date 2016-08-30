package com.avinnovz.sss.models.events;

import com.avinnovz.sss.models.Directory;

/**
 * Created by jayan on 8/31/2016.
 */
public class DirectoryItemClickEvent {

    Directory directory;

    public DirectoryItemClickEvent() {
    }

    public DirectoryItemClickEvent(Directory directory) {
        this.directory = directory;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
}
