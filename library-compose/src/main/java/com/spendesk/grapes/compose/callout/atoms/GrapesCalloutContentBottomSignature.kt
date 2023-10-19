package com.spendesk.grapes.compose.callout.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.callout.GrapesCalloutDefaults
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun GrapesCalloutContentBottomSignature(
    fullName: String,
    profilePicture: Painter? = null,
    profilePicturePlaceHolder: Int = R.drawable.ic_people,
    contentDescription: String? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = profilePicture ?: painterResource(profilePicturePlaceHolder),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(GrapesCalloutDefaults.signatureImageSize)
                .clip(CircleShape)
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = fullName
        )
    }
}
