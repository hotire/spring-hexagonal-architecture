package com.github.hotire.spring.hexagonal.architecture.ex.account

import java.beans.PropertyChangeSupport

class CustomPropertyChangeSupport<T>(instance: T) : PropertyChangeSupport(instance)
