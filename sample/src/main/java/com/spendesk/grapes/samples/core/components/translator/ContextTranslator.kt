package com.spendesk.grapes.samples.core.components.translator

import android.content.Context

/**
 * @author : danyboucanova
 * @since : 12/04/2022, Thu
 **/

class ContextTranslator(
    private val context: Context
) : Translator {

    override fun getString(id: Int): String =
        context.getString(id)

    override fun getStringFormat(id: Int, vararg args: Any?): String =
        String.format(getString(id), *args)

    override fun getQuantityString(id: Int, quantity: Int, vararg args: Any?): String =
        context.resources.getQuantityString(id, quantity, *args)
}