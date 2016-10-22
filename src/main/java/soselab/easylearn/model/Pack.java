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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pack pack = (Pack) o;

        if (createTime != pack.createTime) return false;
        if (isPublic != pack.isPublic) return false;
        if (viewCount != pack.viewCount) return false;
        if (!id.equals(pack.id)) return false;
        if (!name.equals(pack.name)) return false;
        if (description != null ? !description.equals(pack.description) : pack.description != null) return false;
        if (!creatorUserId.equals(pack.creatorUserId)) return false;
        if (!creatorUserName.equals(pack.creatorUserName)) return false;
        if (coverFilename != null ? !coverFilename.equals(pack.coverFilename) : pack.coverFilename != null)
            return false;
        return version.equals(pack.version);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (isPublic ? 1 : 0);
        result = 31 * result + creatorUserId.hashCode();
        result = 31 * result + creatorUserName.hashCode();
        result = 31 * result + (coverFilename != null ? coverFilename.hashCode() : 0);
        result = 31 * result + version.hashCode();
        result = 31 * result + (int) (viewCount ^ (viewCount >>> 32));
        return result;
    }
}
