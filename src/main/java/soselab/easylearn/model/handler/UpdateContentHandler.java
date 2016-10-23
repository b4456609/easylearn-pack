package soselab.easylearn.model.handler;


import soselab.easylearn.model.Version;

public class UpdateContentHandler {
    public Version execute(String content, Version dbVersion) {
        String dbContent = dbVersion.getContent();
        StringBuffer dbContentBuffer = new StringBuffer(dbContent);
        StringBuffer contentBuffer = new StringBuffer(content);


        int index = 0;
        int clientIndex = contentBuffer
                .indexOf("<span class=\"note", index);
        int dbIndex = dbContentBuffer.indexOf("<span class=\"note", index);

        if (clientIndex == -1 && dbIndex == -1)
            return dbVersion;
        else if (clientIndex >= 0 && dbIndex == -1) {
            // client has newer versin
            dbVersion.setContent(content);
            return dbVersion;
        } else if (clientIndex == -1 && dbIndex >= 0) {
            // return do nothing
            // db has new
            return dbVersion;
        }

        while (true) {
            clientIndex = contentBuffer
                    .indexOf("<span class=\"note", index);
            dbIndex = dbContentBuffer.indexOf("<span class=\"note", index);
            if (clientIndex == -1 && dbIndex == -1) {
                // result
                dbVersion.setContent(dbContentBuffer.toString());
                return dbVersion;
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
}
