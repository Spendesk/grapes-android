package com.spendesk.grapes.samples.core.di

import android.content.Context
import com.spendesk.grapes.samples.core.components.translator.ContextTranslator
import com.spendesk.grapes.samples.core.components.translator.Translator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * @author : danyboucanova
 * @since : 12/04/2022, Tue
 **/
//@Module
//@InstallIn(ActivityComponent::class)
//object ActivityModule {
//
//    @Provides
//    @ActivityScoped
//    fun provideTranslator(context: Context): Translator = ContextTranslator(context)
//}