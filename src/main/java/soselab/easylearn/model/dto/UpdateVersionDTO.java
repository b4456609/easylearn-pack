package soselab.easylearn.model.dto;

/**
 * Created by bernie on 2016/11/13.
 */
public class UpdateVersionDTO {
    private String versionId;
    private String packId;
    private String newContent;

    public UpdateVersionDTO(String versionId, String packId, String newContent) {
        this.versionId = versionId;
        this.packId = packId;
        this.newContent = newContent;
    }

    public UpdateVersionDTO() {
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
