package com.avinnovz.sss.models;

/**
 * Created by jayan on 8/30/2016.
 */
public class Directory {

    String name;
    String position;
    String office;
    String directLine;
    String trunkLine;
    String local;
    String emailAddress;

    public Directory() {
    }

    /*Constructor for SSC Directory**/
    public Directory(String name, String position, String directLine, String trunkLine, String local, String emailAddress) {
        this.name = name;
        this.position = position;
        this.directLine = directLine;
        this.trunkLine = trunkLine;
        this.local = local;
        this.emailAddress = emailAddress;
    }

    /*Constructor for SSS Directory**/
    public Directory(String name, String position, String office, String directLine, String trunkLine, String local, String emailAddress) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.directLine = directLine;
        this.trunkLine = trunkLine;
        this.local = local;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDirectLine() {
        return directLine;
    }

    public void setDirectLine(String directLine) {
        this.directLine = directLine;
    }

    public String getTrunkLine() {
        return trunkLine;
    }

    public void setTrunkLine(String trunkLine) {
        this.trunkLine = trunkLine;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", directLine='" + directLine + '\'' +
                ", trunkLine='" + trunkLine + '\'' +
                ", local='" + local + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
