package com.blog.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format;

    public DateJsonValueProcessor(String format) {
     this.format=format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value == null) {
            return "";
        }
        if (value instanceof Timestamp) {
            String str = new SimpleDateFormat(this.format).format((Timestamp) value);
            return str;
        }
        if (value instanceof Date){
            String str = new SimpleDateFormat(this.format).format((Date) value);
            return str;
        }
        return value.toString();
    }
}
