package soselab.easylearn.model;

import java.util.Date;

public class VersionBuilder {
    private String id = "versionId";
    private String content = "content";
    private long createTime = new Date().getTime();
    private boolean isPublic = false;
    private String creatorUserId = "userid";
    private String creatorUserName = "username";
    private long viewCount = 0;

    public VersionBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public VersionBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public VersionBuilder setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public VersionBuilder setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public VersionBuilder setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
        return this;
    }

    public VersionBuilder setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
        return this;
    }


    public VersionBuilder setViewCount(long viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Version createVersion() {
        return new Version(id, content, createTime, isPublic, creatorUserId, creatorUserName, viewCount);
    }
}