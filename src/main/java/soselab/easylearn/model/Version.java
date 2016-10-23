package soselab.easylearn.model;

import java.util.Set;

public class Version {
    private String id;
    private String content;
    private long createTime;
    private boolean isPublic;
    private String creatorUserId;
    private String creatorUserName;
    private Set<String> file;
    private long viewCount;

    public Version() {
    }

    public Version(String id, String content, long createTime, boolean isPublic, String creatorUserId,
                   String creatorUserName, Set<String> file, long viewCount) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.isPublic = isPublic;
        this.creatorUserId = creatorUserId;
        this.creatorUserName = creatorUserName;
        this.file = file;
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

    public Set<String> getFile() {
        return file;
    }

    public void setFile(Set<String> file) {
        this.file = file;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        if (createTime != version.createTime) return false;
        if (isPublic != version.isPublic) return false;
        if (viewCount != version.viewCount) return false;
        if (!id.equals(version.id)) return false;
        if (!content.equals(version.content)) return false;
        if (!creatorUserId.equals(version.creatorUserId)) return false;
        if (!creatorUserName.equals(version.creatorUserName)) return false;
        return file != null ? file.equals(version.file) : version.file == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (isPublic ? 1 : 0);
        result = 31 * result + creatorUserId.hashCode();
        result = 31 * result + creatorUserName.hashCode();
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (int) (viewCount ^ (viewCount >>> 32));
        return result;
    }
}
