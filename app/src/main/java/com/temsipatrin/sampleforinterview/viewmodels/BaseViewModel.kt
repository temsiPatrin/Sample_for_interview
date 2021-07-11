package com.temsipatrin.sampleforinterview.viewmodels

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.temsipatrin.sampleforinterview.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    protected open fun handlerException(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            val message = ExceptionHandler.parse(exception)
            handleError(message)
        }
    }

    abstract fun handleError(@StringRes message: Int)

}