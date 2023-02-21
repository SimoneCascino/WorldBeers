package it.simonecascino.feature_home_presentation.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import it.simonecascino.core.ui.components.SearchField
import it.simonecascino.core.ui.theme.contentSpacing
import it.simonecascino.core.ui.theme.innerSpacing
import it.simonecascino.destinationbuilder.annotation.Destination
import it.simonecascino.feature_home_presentation.R
import it.simonecascino.feature_home_presentation.home.models.BeerHomeUI
import kotlinx.coroutines.launch
import java.util.*

@Destination
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    beers: List<BeerHomeUI>,
    loading: Boolean,
    query: String,
    onValueChange: (String) -> Unit,
    onBeerClicked: (String, Int) -> Unit
){

    Crossfade(targetState = loading) {

        when(it){
            true -> Box(
                modifier = Modifier.fillMaxSize(),
            ){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            false -> {

                var boxHeight by remember {
                    mutableStateOf(0)
                }

                val coroutineScope = rememberCoroutineScope()

                Box(modifier = Modifier.fillMaxWidth()) {

                    SearchField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(contentSpacing)
                            .onGloballyPositioned {
                                boxHeight = it.size.height
                            }
                            .zIndex(1F),
                        text = query,
                        placeHolder = stringResource(id = R.string.search),
                        onValueChange = {

                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                            }

                            onValueChange(it)
                        }
                    )

                    BeersList(
                        modifier = modifier,
                        lazyListState = lazyListState,
                        contentPadding = PaddingValues(
                            start = contentSpacing,
                            end = contentSpacing,
                            bottom = contentSpacing,
                            top = contentSpacing + contentSpacing + with(LocalDensity.current){
                                boxHeight.toDp()
                            }
                        ),
                        beers = beers,
                        onBeerClicked = onBeerClicked
                    )

                }
            }
        }

    }

}

@Composable
private fun BeersList(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    contentPadding: PaddingValues,
    beers: List<BeerHomeUI>,
    onBeerClicked: (String, Int) -> Unit
){

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(contentSpacing),
        contentPadding = contentPadding
    ){

        items(
            items = beers,
            key = {
                it.id.toString()
            }
        ){
            BeerItem(
                modifier = Modifier.clickable {
                    onBeerClicked(it.name, it.id)
                },
                beer = it
            )
        }

    }

}

@Composable
private fun BeerItem(
    modifier: Modifier = Modifier,
    beer: BeerHomeUI
){

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerSpacing)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(beer.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = innerSpacing)
                ) {

                    Text(
                        text = beer.name,
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = beer.description
                    )

                }

            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = innerSpacing)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = innerSpacing)
            ){

                Text(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    text = String.format(Locale.getDefault(), stringResource(id = R.string.abv_label), beer.abv),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.fillMaxWidth(0.5F),
                    text = String.format(
                        Locale.getDefault(),
                        stringResource(id = R.string.ibu_label),
                        beer.ibu ?: stringResource(id = R.string.ibu_missing)
                    ),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )

            }

        }

    }

}