package com.avinnovz.sss.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jayan on 8/29/2016.
 */
public class DateHelper {
    public static String formatDate(Date d, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }
}
