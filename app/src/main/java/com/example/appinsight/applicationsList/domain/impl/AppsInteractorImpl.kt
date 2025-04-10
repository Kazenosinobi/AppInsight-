package com.example.appinsight.applicationsList.domain.impl

import com.example.appinsight.applicationsList.domain.api.AppsInteractor
import com.example.appinsight.applicationsList.domain.api.AppsRepository
import com.example.appinsight.applicationsList.domain.models.AppItem

class AppsInteractorImpl(private val repository: AppsRepository) : AppsInteractor {
    override fun getInstallApps(): List<AppItem> {
        return repository.getInstallApps()
    }
}