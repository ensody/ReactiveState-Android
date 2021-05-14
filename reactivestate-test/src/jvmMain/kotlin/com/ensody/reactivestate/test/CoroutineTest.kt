package com.ensody.reactivestate.test

import com.ensody.reactivestate.AttachedDisposables
import com.ensody.reactivestate.DisposableGroup
import com.ensody.reactivestate.derived
import com.ensody.reactivestate.dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope

/**
 * Base class for unit testing coroutine based code.
 *
 * On every test run this class automatically sets `MainScope` and [dispatchers] to use a `TestCoroutineDispatcher`.
 *
 * Internally, this uses a [CoroutineTestRule] which does the actual [dispatchers] and `Dispatchers.setMain()` setup.
 * This allows accessing e.g. the [testCoroutineScope] in your `@Before` setup method.
 *
 * Moreover, this provides an [attachedDisposables] attribute and a [collectFlow] helper, so you can activate
 * [SharingStarted.WhileSubscribed] based flows created with [derived], for example.
 */
public open class CoroutineTest : CoroutineTestRule(), AttachedDisposables {
    override val attachedDisposables: DisposableGroup = DisposableGroup()

    /** Collects a flow in the background. */
    public fun <T> TestCoroutineScope.collectFlow(flow: Flow<T>, collector: suspend (T) -> Unit = {}) {
        attachedDisposables.add(launch { flow.collect(collector) })
    }

    /** In addition to running the test this also disposes the [attachedDisposables] (useful with [collectFlow]). */
    public override fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        super.runBlockingTest {
            try {
                block()
            } finally {
                dispose()
            }
        }
    }
}
