package com.spendesk.grapes.samples.core.components.translator

/**
 * @author : danyboucanova
 * @since : 12/04/2022, Thu
 **/

interface Translator {

    fun getString(id: Int): String
    fun getStringFormat(id: Int, vararg args: Any?): String
    fun getQuantityString(id: Int, quantity: Int, vararg args: Any?): String
}