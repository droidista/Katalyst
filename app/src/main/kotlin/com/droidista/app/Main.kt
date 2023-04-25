package com.droidista.app

import com.droidista.katalyst.environment.Environment
import com.droidista.katalyst.environment.clean
import com.droidista.katalyst.environment.copyStaticAssets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File

suspend fun main(args: Array<String>) = withContext(Dispatchers.Default) {
    val workingDirectory = System.getProperty("user.dir")
    println("Working Dir: $workingDirectory")
    val env = Environment(
        baseDir = File(workingDirectory,"static"),
        outputDir = File(workingDirectory,"dist"),
    )
    clean(env)
    val copyTask = async {
        copyStaticAssets(env)
    }
    val indexPageGenerationTask = async {
        buildIndexPage(env)
    }
    copyTask.await()
    indexPageGenerationTask.await()
}