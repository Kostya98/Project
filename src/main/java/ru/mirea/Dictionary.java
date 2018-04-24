package ru.mirea;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dictionary {
    private final CopyOnWriteArrayList<Tag> tagList;

    public Dictionary() {
        tagList = new CopyOnWriteArrayList<>();
    }

    public List getTag() {
        return tagList;
    }

    public void putTag(String mnemo, String name) throws Exception {
        if (mnemo == null || name == null || mnemo.length() < 2 || name.isEmpty()) {
            throw new Exception("Неправильные параметры запроса");
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
            throw new Exception("Неправильные параметры запроса");
        }
        synchronized (tagList) {
            for (Tag tag : tagList) {
                if (tag.getMnemo().equals(mnemo)) {
                    tagList.remove(tag);
                    return;
                }
            }
            throw new Exception("Тег не найден");
        }
    }

    public List findTag(String mnemo) throws Exception {
        if (mnemo == null || mnemo.length() < 2) {
            throw new Exception("Неправильные параметры запроса");
        }
        List<Tag> result = new ArrayList<>();
        synchronized (tagList) {
            for (Tag tag : tagList) {
                if (tag.getMnemo().startsWith(mnemo)) {
                    result.add(tag);
                }
            }
        }
        return result;
    }
}