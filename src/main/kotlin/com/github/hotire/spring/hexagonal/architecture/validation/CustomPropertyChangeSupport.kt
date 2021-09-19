package com.github.hotire.spring.hexagonal.architecture.validation

import java.beans.PropertyChangeSupport

class CustomPropertyChangeSupport<T>(instance: T) : PropertyChangeSupport(instance)
