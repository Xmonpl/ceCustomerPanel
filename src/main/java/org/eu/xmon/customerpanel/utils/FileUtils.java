package org.eu.xmon.customerpanel.utils;

import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static byte[] getByteImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        final byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return bytes;
    }
}
