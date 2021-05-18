package com.ensody.reactivestate.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.ensody.reactivestate.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

/**
 * Creates a [StateFlow] that computes its value based on other [StateFlow]s via an [autoRun] block.
 *
 * This behaves like [SharingStarted.Eagerly] and computes the initial value by executing the [observer] function
 * immediately.
 */
public fun <T> ViewModel.derived(
    launcher: CoroutineLauncher = if (this is CoroutineLauncher) this else SimpleCoroutineLauncher(viewModelScope),
    observer: AutoRunCallback<T>,
): StateFlow<T> =
    launcher.derived(observer = observer)

/**
 * Creates a [StateFlow] that computes its value based on other [StateFlow]s via a suspendable [coAutoRun] block.
 *
 * You can use this to compute values on-demand only via [SharingStarted.WhileSubscribed].
 *
 * @param initial The initial value (until the first computation finishes).
 * @param started When the value should be updated. Pass [SharingStarted.WhileSubscribed] to compute only on demand.
 *                Defaults to [SharingStarted.Eagerly].
 * @param launcher The [CoroutineLauncher] to use.
 * @param flowTransformer How changes should be executed/collected. Defaults to `{ conflatedWorker() }`.
 * @param dispatcher The [CoroutineDispatcher] to use. Defaults to `dispatchers.default`.
 * @param withLoading Tracks loading state for the (re-)computation. Defaults to [CoroutineLauncher.generalLoading] if
 *                    this is a [CoroutineLauncher] or `null` otherwise.
 * @param observer The callback which is used to track the observables.
 */
public fun <T> ViewModel.derived(
    initial: T,
    started: SharingStarted = SharingStarted.Eagerly,
    launcher: CoroutineLauncher = if (this is CoroutineLauncher) this else SimpleCoroutineLauncher(viewModelScope),
    flowTransformer: AutoRunFlowTransformer = defaultAutoRunFlowTransformer,
    dispatcher: CoroutineDispatcher = dispatchers.default,
    withLoading: MutableValueFlow<Int>? = if (this is CoroutineLauncher) launcher.generalLoading else null,
    observer: CoAutoRunCallback<T>,
): StateFlow<T> =
    launcher.derived(
        initial = initial,
        started = started,
        flowTransformer = flowTransformer,
        dispatcher = dispatcher,
        withLoading = withLoading,
        observer = observer,
    )

/**
 * Creates a [StateFlow] that computes its value based on other [StateFlow]s via an [autoRun] block.
 *
 * This behaves like [SharingStarted.Eagerly] and computes the initial value by executing the [observer] function
 * immediately.
 */
public fun <T> LifecycleOwner.derived(
    launcher: CoroutineLauncher = if (this is CoroutineLauncher) this else LifecycleCoroutineLauncher(this),
    observer: AutoRunCallback<T>,
): StateFlow<T> =
    launcher.derived(observer = observer)

/**
 * Creates a [StateFlow] that computes its value based on other [StateFlow]s via a suspendable [coAutoRun] block.
 *
 * You can use this to compute values on-demand only via [SharingStarted.WhileSubscribed].
 *
 * @param initial The initial value (until the first computation finishes).
 * @param started When the value should be updated. Pass [SharingStarted.WhileSubscribed] to compute only on demand.
 *                Defaults to [SharingStarted.Eagerly].
 * @param launcher The [CoroutineLauncher] to use.
 * @param flowTransformer How changes should be executed/collected. Defaults to `{ conflatedWorker() }`.
 * @param dispatcher The [CoroutineDispatcher] to use. Defaults to `dispatchers.default`.
 * @param withLoading Tracks loading state for the (re-)computation. Defaults to [CoroutineLauncher.generalLoading] if
 *                    this is a [CoroutineLauncher] or `null` otherwise.
 * @param observer The callback which is used to track the observables.
 */
public fun <T> LifecycleOwner.derived(
    initial: T,
    started: SharingStarted = SharingStarted.Eagerly,
    launcher: CoroutineLauncher = if (this is CoroutineLauncher) this else LifecycleCoroutineLauncher(this),
    flowTransformer: AutoRunFlowTransformer = defaultAutoRunFlowTransformer,
    dispatcher: CoroutineDispatcher = dispatchers.default,
    withLoading: MutableValueFlow<Int>? = if (this is CoroutineLauncher) launcher.generalLoading else null,
    observer: CoAutoRunCallback<T>,
): StateFlow<T> =
    launcher.derived(
        initial = initial,
        started = started,
        flowTransformer = flowTransformer,
        dispatcher = dispatcher,
        withLoading = withLoading,
        observer = observer,
    )
