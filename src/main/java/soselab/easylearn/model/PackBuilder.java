package soselab.easylearn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackBuilder {
    private String name;
    private String id;
    private String description = "";
    private long createTime = new Date().getTime();
    private boolean isPublic = false;
    private String creatorUserId = "id";
    private String creatorUserName = "name";
    private String coverFilename = "";
    private List<Version> version = new ArrayList<>();
    private long viewCount = 0;

    public PackBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PackBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PackBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PackBuilder setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public PackBuilder setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public PackBuilder setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
        return this;
    }

    public PackBuilder setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
        return this;
    }

    public PackBuilder setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
        return this;
    }

    public PackBuilder setVersion(List<Version> version) {
        this.version = version;
        return this;
    }

    public PackBuilder setViewCount(long viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Pack createPack() {
        return new Pack(name, id, description, createTime, isPublic, creatorUserId, creatorUserName, coverFilename, version, viewCount);
    }
}