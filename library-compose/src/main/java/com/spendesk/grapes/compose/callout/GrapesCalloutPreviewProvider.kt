package com.spendesk.grapes.compose.callout

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/

internal class CalloutProvider : PreviewParameterProvider<Callouts> {
    override val values: Sequence<Callouts> = sequenceOf(
        // Errors
        Callouts.Error(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action2CTA
        ),
        Callouts.Error(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1PrimaryCTA
        ),
        Callouts.Error(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1SecondaryCTA
        ),
        Callouts.Error(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Signature
        ),
        Callouts.Error(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Simple
        ),
        Callouts.Error(
            title = "Message Title",
            description = null,
            contentType = null
        ),

        // Warnings
        Callouts.Warning(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action2CTA
        ),
        Callouts.Warning(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1PrimaryCTA
        ),
        Callouts.Warning(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1SecondaryCTA
        ),
        Callouts.Warning(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Signature
        ),
        Callouts.Warning(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Simple
        ),
        Callouts.Warning(
            title = "Message Title",
            description = null,
            contentType = null
        ),

        // Info
        Callouts.Info(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action2CTA
        ),
        Callouts.Info(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1PrimaryCTA
        ),
        Callouts.Info(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1SecondaryCTA
        ),
        Callouts.Info(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Signature
        ),
        Callouts.Info(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Simple
        ),
        Callouts.Info(
            title = "Message Title",
            description = null,
            contentType = null
        ),

        // Success
        Callouts.Success(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action2CTA
        ),
        Callouts.Success(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1PrimaryCTA
        ),
        Callouts.Success(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1SecondaryCTA
        ),
        Callouts.Success(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Signature
        ),
        Callouts.Success(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Simple
        ),
        Callouts.Success(
            title = "Message Title",
            description = null,
            contentType = null
        ),

        // Neutral
        Callouts.Neutral(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action2CTA
        ),
        Callouts.Neutral(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1PrimaryCTA
        ),
        Callouts.Neutral(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Action1SecondaryCTA
        ),
        Callouts.Neutral(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Signature
        ),
        Callouts.Neutral(
            title = "Message Title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec lectus sed sem porttitor viverra. Vestibulum magna leo.",
            contentType = CalloutContentType.Simple
        ),
        Callouts.Neutral(
            title = "Message Title",
            description = null,
            contentType = null
        )
    )
}

internal sealed class Callouts {
    abstract val title: String
    abstract val description: String?
    abstract val contentType: CalloutContentType?

    data class Error(override val title: String, override val description: String?, override val contentType: CalloutContentType?) : Callouts()
    data class Warning(override val title: String, override val description: String?, override val contentType: CalloutContentType?) : Callouts()
    data class Info(override val title: String, override val description: String?, override val contentType: CalloutContentType?) : Callouts()
    data class Success(override val title: String, override val description: String?, override val contentType: CalloutContentType?) : Callouts()
    data class Neutral(override val title: String, override val description: String?, override val contentType: CalloutContentType?) : Callouts()
}

internal enum class CalloutContentType { Simple, Action2CTA, Action1PrimaryCTA, Action1SecondaryCTA, Signature }
