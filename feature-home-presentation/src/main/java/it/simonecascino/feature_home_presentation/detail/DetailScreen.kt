package it.simonecascino.feature_home_presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.simonecascino.core.ui.theme.contentSpacing
import it.simonecascino.core.ui.theme.innerSpacing
import it.simonecascino.destinationbuilder.annotation.Destination
import it.simonecascino.feature_home_presentation.R
import it.simonecascino.feature_home_presentation.detail.models.BeerDetailUI

@Destination(
    dynamicTitle = true,
    paths = ["beerId"]
)
@Composable
fun DetailScreen(
    beer: BeerDetailUI
) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(contentSpacing)) {

        Text(
            text = stringResource(id = R.string.brewer_tips_label),
            style = MaterialTheme.typography.h6
        )

        Text(text = beer.brewerTips)

        Text(
            modifier = Modifier.padding(top = innerSpacing),
            text = stringResource(id = R.string.food_pairing_label),
            style = MaterialTheme.typography.h6
        )

        Text(text = beer.foodPairing)

        Text(
            modifier = Modifier.padding(top = innerSpacing),
            text = stringResource(id = R.string.first_brewed_label),
            style = MaterialTheme.typography.h6
        )

        Text(text = beer.firstBrewed)

    }

}