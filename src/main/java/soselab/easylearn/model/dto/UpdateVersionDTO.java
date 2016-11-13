package soselab.easylearn.model.dto;

/**
 * Created by bernie on 2016/11/13.
 */
public class UpdateVersionDTO {
    private String versionId;
    private String packId;
    private String content;

    public UpdateVersionDTO(String versionId, String packId, String content) {
        this.versionId = versionId;
        this.packId = packId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
