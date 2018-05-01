package ru.mirea;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dictionary {
    private final List<Tag> tagList;

    public Dictionary() {
        tagList = new ArrayList<>();
    }

    public List<Tag> getTag() {
        return tagList;
    }

    public void putTag(String mnemo, String name) throws Exception {
        if (mnemo == null || mnemo.length() < 2) {
            throw new InternalException(InternalException.MNEMO_LENGTH);
        } else if (name == null || name.isEmpty()) {
            throw new InternalException(InternalException.NAME_LENGTH);
        }
        synchronized (tagList) {
            for (Tag tag : tagList) {
                if (tag.getMnemo().equals(mnemo)) {
                    tag.setName(name);
                    return;
                }
            }
            tagList.add(new Tag(mnemo, name));
        }
    }

    public void deleteTag(String mnemo) throws Exception {
        if (mnemo == null || mnemo.length() < 2) {
            throw new InternalException(InternalException.MNEMO_LENGTH);
        }
        synchronized (tagList) {
            Iterator<Tag> iterator = tagList.iterator();
            while (iterator.hasNext()) {
                Tag tag = iterator.next();
                if (tag.getMnemo().equals(mnemo)) {
                    iterator.remove();
                    return;
                }
            }
            throw new InternalException(InternalException.TAG_NOT_FOUND);
        }
    }

    public List<Tag> findTag(String search) throws Exception {
        if (search.length() < 2) {
            throw new InternalException(InternalException.SEARCH_LENGTH);
        }
        List<Tag> result = new ArrayList<>();
        synchronized (tagList) {
            for (Tag tag : tagList) {
                if (tag.getMnemo().startsWith(search)) {
                    result.add(tag);
                }
            }
        }
        return result;
    }
}