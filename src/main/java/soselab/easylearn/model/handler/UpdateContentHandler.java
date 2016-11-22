package soselab.easylearn.model.handler;


public class UpdateContentHandler {
    public String execute(String content, String dbContent) {
        StringBuffer dbContentBuffer = new StringBuffer(dbContent);
        StringBuffer contentBuffer = new StringBuffer(content);


        int index = 0;
        int clientIndex = contentBuffer
                .indexOf("<span class=\"note", index);
        int dbIndex = dbContentBuffer.indexOf("<span class=\"note", index);

        if (clientIndex == -1 && dbIndex == -1)
            return dbContent;
        else if (clientIndex >= 0 && dbIndex == -1) {
            // client has newer version
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
                return dbContentBuffer.toString();
            } else if (clientIndex != -1 && dbIndex == -1) {
                versionInsert(clientIndex, contentBuffer,
                        dbContentBuffer);
            } else if (clientIndex == -1 && dbIndex != -1) {
                versionInsert(dbIndex, dbContentBuffer,
                        contentBuffer);
            } else if (clientIndex == dbIndex) {
                index = clientIndex + 1;
            } else if (clientIndex < dbIndex) {
                versionInsert(clientIndex, contentBuffer,
                        dbContentBuffer);
            } else if (clientIndex > dbIndex) {
                versionInsert(dbIndex, dbContentBuffer,
                        contentBuffer);
            } else {
                System.out.println("error");
            }
        }
    }

    private int versionInsert(int index,
                              StringBuffer contentBuffer, StringBuffer dbContentBuffer) {
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
