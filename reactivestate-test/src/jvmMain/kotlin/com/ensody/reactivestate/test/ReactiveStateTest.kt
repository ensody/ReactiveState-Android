package com.ensody.reactivestate.test

import com.ensody.reactivestate.ErrorEvents
import com.ensody.reactivestate.EventNotifier
import com.ensody.reactivestate.ReactiveState

/**
 * Base class for unit testing an [EventNotifier].
 *
 * You have to override the [eventNotifier] and [events] attributes. Usually, [events] will be a mock.
 *
 * By default this handles events in [runBlockingTest]. You can disable this by overriding
 * [handleEventsInRunBlockingTest] to `false`. In that case you have to explicitly call [handleEvents] in each test.
 */
public abstract class ReactiveStateTest<E : ErrorEvents> : EventNotifierTest<E>() {
    public abstract val reactiveState: ReactiveState<E>
    override val eventNotifier: EventNotifier<E> get() = reactiveState.eventNotifier
}
