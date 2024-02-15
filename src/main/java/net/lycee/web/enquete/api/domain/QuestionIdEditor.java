package net.lycee.web.enquete.api.domain;

import java.beans.PropertyEditorSupport;

public class QuestionIdEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        Object o = getValue();
        if (o == null) {
            return null;
        }
        return ((QuestionId) o).value();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new QuestionId(text));
    }
}
