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

    public void sync(Version dbVersion) {
        //update view count
        this.content = syncNote(dbVersion.content);
    }


    public String syncNote(String dbContent) {
        if (content.equals(dbContent)) {
            return dbContent;
        }


        StringBuffer dbContentBuffer = new StringBuffer(dbContent);
        StringBuffer contentBuffer = new StringBuffer(content);


        int index = 0;
        int clientIndex = contentBuffer
                .indexOf("<span class=\"note", index);
        int dbIndex = dbContentBuffer.indexOf("<span class=\"note", index);

        if (clientIndex == -1 && dbIndex == -1)
            return dbContent;
        else if (clientIndex >= 0 && dbIndex == -1) {
            // client has newer versin
            return content;
        } else if (clientIndex == -1 && dbIndex >= 0) {
            // return do nothing
            // db has new
            return dbContent;
        }

        while (true) {
            clientIndex = contentBuffer
                    .indexOf("<span class=\"note", index);
            dbIndex = dbContentBuffer.indexOf("<span class=\"note", index);
            if (clientIndex == -1 && dbIndex == -1) {
                // result
                //db.updateVersion(id, contentBuffer.toString());
                return dbContentBuffer.toString();
            } else if (clientIndex != -1 && dbIndex == -1) {
                versionInsert(index, clientIndex, contentBuffer,
                        dbContentBuffer);
            } else if (clientIndex == -1 && dbIndex != -1) {
                versionInsert(index, dbIndex, dbContentBuffer,
                        contentBuffer);
            } else if (clientIndex == dbIndex) {
                index = clientIndex + 1;
            } else if (clientIndex < dbIndex) {
                versionInsert(index, clientIndex, contentBuffer,
                        dbContentBuffer);
            } else if (clientIndex > dbIndex) {
                versionInsert(index, dbIndex, dbContentBuffer,
                        contentBuffer);
            } else {
                System.out.println("error");
            }
        }


        /*Document parse = Jsoup.parse(content, "", Parser.xmlParser());
        Document dbparse = Jsoup.parse(dbContent, "", Parser.xmlParser());

        Elements notes = parse.getElementsByClass("note");
        Elements dbnotes = dbparse.getElementsByClass("note");


        Set<String> note = new HashSet<String>();
        Set<String> dbNoteId = new HashSet<String>();


        for (Element element : notes) {
            Attributes attributes = element.attributes();
            note.add(attributes.get("noteid"));
        }

        for (Element element : dbnotes) {
            Attributes attributes = element.attributes();
            dbNoteId.add(attributes.get("noteid"));

        }


        LOGGER.debug(note.toString());
        LOGGER.debug(dbNoteId.toString());

        Set<String> dbNoteIdAdd = new HashSet<String>();
        dbNoteIdAdd.addAll(dbNoteId);
        dbNoteIdAdd.addAll(note);
        dbNoteIdAdd.removeAll(dbNoteId);

        //no need to add note
        if (dbNoteIdAdd.size() == 0) {
            return dbContent;
        }

        LOGGER.debug(dbNoteIdAdd.toString());

        for (String id : dbNoteIdAdd) {
            LOGGER.debug(id);
            Element noteParent = parse.getElementsByAttributeValue("noteid", id).parents().get(0);
            LOGGER.debug(noteParent.html());
        }*/
    }

    private int versionInsert(int index, int clientIndex,
                              StringBuffer contentBuffer, StringBuffer dbContentBuffer) {
        index = clientIndex;
        int last = contentBuffer.indexOf(">", index);
        String newStr = contentBuffer.substring(index, last + 1);
        dbContentBuffer.insert(index, newStr);
        index = last;

        index = contentBuffer.indexOf("</span>", index);
        // deal with last index
        dbContentBuffer.insert(index, "</span>");
        index = index + 7;
        return index;
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
