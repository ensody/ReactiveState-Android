package com.ensody.reactivestate

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

// TODO: Define a more I/O optimized thread pool
internal actual fun getDispatchersIO(): CoroutineDispatcher =
    Dispatchers.Default
