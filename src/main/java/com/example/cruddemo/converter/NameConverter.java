package com.example.cruddemo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("nameConverter")
public class NameConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return capitalizeName(value.trim());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    private String capitalizeName(String name) {
        String[] words = name.split("\\s+"); //
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedName.append(word.substring(0, 1).toUpperCase()) // Chữ cái đầu viết hoa
                        .append(word.substring(1).toLowerCase())   // Phần còn lại viết thường
                        .append(" ");
            }
        }
        return formattedName.toString().trim();
    }
}
