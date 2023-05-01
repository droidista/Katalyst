package com.katalyst.site

import com.katalyst.environment.Environment
import com.katalyst.environment.clean
import com.katalyst.environment.copyStaticAssets
import com.katalyst.site.pages.buildExamplePage
import com.katalyst.site.pages.buildIndexPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.io.File

suspend fun main(): Unit = withContext(Dispatchers.Default) {
    val workingDirectory = System.getProperty("user.dir")
    val environment = Environment(
        baseDir = File(workingDirectory,"static"),
        outputDir = File(workingDirectory,"dist"),
    )
    clean(environment)
    val asyncTasks = listOf(
        async { copyStaticAssets(environment) },
        async { buildIndexPage(environment) },
        async { buildExamplePage(environment) }
    )
    asyncTasks.awaitAll()
}