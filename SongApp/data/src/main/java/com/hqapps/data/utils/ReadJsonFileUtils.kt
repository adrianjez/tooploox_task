package com.hqapps.data.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

object ReadJsonFileUtils {
    fun loadJsonFileFromResources(path: String): String{
        val inn = BufferedReader(InputStreamReader(
                javaClass.classLoader.getResourceAsStream(path), "UTF-8"
        ))
        return inn.lines()
                .parallel()
                .collect(Collectors.joining())
    }
}