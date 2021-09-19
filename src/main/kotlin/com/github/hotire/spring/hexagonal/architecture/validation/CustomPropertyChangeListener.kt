package com.github.hotire.spring.hexagonal.architecture.validation

import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

interface CustomPropertyChangeListener<T, V> : PropertyChangeListener {
    val propertyName: String
    val propertyProvider: T.() -> V

    override fun propertyChange(evt: PropertyChangeEvent) {
        if (propertyName == evt.propertyName) {
            propertyChange(evt.oldValue?.let { it as V }, evt.newValue?.let { it as V })
        }
    }

    fun propertyChange(oldValue: V?, newValue: V?)
}
