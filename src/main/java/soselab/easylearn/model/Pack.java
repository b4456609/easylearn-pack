package soselab.easylearn.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Pack {
    @Id
    private String id;
    private String name;
    private String description;
    private long createTime;
    private boolean isPublic;
    private String creatorUserId;
    private String creatorUserName;
    private String coverFilename;
    private List<Version> version;
    private long viewCount;

    public Pack() {
    }

    public Pack(String name, String id, String description, long createTime, boolean isPublic, String creatorUserId,
                String creatorUserName, String coverFilename, List<Version> version, long viewCount) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.createTime = createTime;
        this.isPublic = isPublic;
        this.creatorUserId = creatorUserId;
        this.creatorUserName = creatorUserName;
        this.coverFilename = coverFilename;
        this.version = version;
        this.viewCount = viewCount;
    }

    public String getCoverFilename() {
        return coverFilename;
    }

    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public List<Version> getVersion() {
        return version;
    }

    public void setVersion(List<Version> version) {
        this.version = version;
    }
}
