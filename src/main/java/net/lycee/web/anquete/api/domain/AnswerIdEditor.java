package net.lycee.web.anquete.api.domain;

import java.beans.PropertyEditorSupport;

public class AnswerIdEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        Object o = getValue();
        if (o == null) {
            return null;
        }
        return ((AnswerId) o).value();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new AnswerId(text));
    }
}
