package soselab.easylearn.model;

public class Version {
    private String id;
    private String content;
    private long createTime;
    private boolean isPublic;
    private String creatorUserId;
    private String creatorUserName;
    private long viewCount;

    public Version() {
    }

    public Version(String id, String content, long createTime, boolean isPublic, String creatorUserId,
                   String creatorUserName, long viewCount) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.isPublic = isPublic;
        this.creatorUserId = creatorUserId;
        this.creatorUserName = creatorUserName;
        this.viewCount = viewCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
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

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isPublic=" + isPublic +
                ", creatorUserId='" + creatorUserId + '\'' +
                ", creatorUserName='" + creatorUserName + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        if (createTime != version.createTime) return false;
        if (isPublic != version.isPublic) return false;
        if (!id.equals(version.id)) return false;
        if (!content.equals(version.content)) return false;
        return creatorUserId.equals(version.creatorUserId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (isPublic ? 1 : 0);
        result = 31 * result + creatorUserId.hashCode();
        return result;
    }
}
