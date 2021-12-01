package org.eu.xmon.customerpanel.controllers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import org.eu.xmon.customerpanel.utils.MvcEnum;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class MvnController {
    private static Cache<MvcEnum, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES).build();
    @SneakyThrows
    public static String getMvc(final MvcEnum mvcEnum){
        String language = cache.getIfPresent(mvcEnum);
        if (language != null){
            return language;
        }else{
            language = String.join("", Files.readAllLines(Path.of("mvc",mvcEnum.name() + ".txt"), StandardCharsets.UTF_8));
            cache.put(mvcEnum, language);
            return language;
        }
    }
}
