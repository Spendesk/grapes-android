package com.spendesk.grapes.samples.core.di

import android.content.Context
import com.spendesk.grapes.samples.core.components.translator.ContextTranslator
import com.spendesk.grapes.samples.core.components.translator.Translator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author : danyboucanova
 * @since : 12/04/2022, Tue
 **/
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTranslator(@ApplicationContext context: Context): Translator = ContextTranslator(context)
}