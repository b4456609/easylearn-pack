package soselab.easylearn.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class VersionBuilder {
    private String id = "versionId";
    private String content = "content";
    private long createTime = new Date().getTime();
    private boolean isPublic = false;
    private String creatorUserId = "userid";
    private String creatorUserName = "username";
    private Set<String> file = new HashSet<String>();
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

    public VersionBuilder setFile(Set<String> file) {
        this.file = file;
        return this;
    }

    public VersionBuilder setViewCount(long viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Version createVersion() {
        return new Version(id, content, createTime, isPublic, creatorUserId, creatorUserName, file, viewCount);
    }
}