package ru.mirea;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private int offset;
    private int tagPerPage;
    private int tagListSize;
    private List tagList;

    public Response(int offset, int tagPerPage, List tagList) {
        this.offset = offset;
        this.tagPerPage = tagPerPage;
        this.tagListSize = tagList.size();
        this.tagList = tagList;
    }

    public int getOffset() {
        return offset;
    }

    public int getTagPerPage() {
        return tagPerPage;
    }

    public int getTagListSize() {
        return tagListSize;
    }

    public List getTagList() {
        if (offset > tagListSize) {
            return new ArrayList();
        }
        int end = tagPerPage + offset;
        if (end > tagListSize) {
            end = tagListSize;
        }
        return tagList.subList(offset, end);
    }
}