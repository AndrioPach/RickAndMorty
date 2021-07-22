package com.exemplo.RickAndMorty.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exemplo.RickAndMorty.api.Repository

class SharedViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     return SharedViewModel(repository) as T
    }
}