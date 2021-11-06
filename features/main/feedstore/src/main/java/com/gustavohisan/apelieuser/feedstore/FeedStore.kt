package com.gustavohisan.apelieuser.feedstore

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.gustavohisan.apelieuser.design.darkGrey
import com.gustavohisan.apelieuser.design.ratingYellow
import timber.log.Timber

@ExperimentalCoilApi
@Composable
fun FeedStore(
    onStoreClicked: (Int) -> Unit,
    storeId: Int,
    bannerUrl: String,
    storeUrl: String,
    name: String,
    categories: List<String>,
    city: String,
    state: String,
    rating: Float,
    productImages: List<String>
) {
    val listImages = productImages.map { rememberImagePainter(data = it) }
    val bannerImage = rememberImagePainter(data = bannerUrl)
    val storeImage = rememberImagePainter(data = storeUrl)
    Box(
        Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(0.dp, Color.White, shape = RoundedCornerShape(10.dp))
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = { onStoreClicked(storeId) })
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .placeholder(
                    visible = (bannerImage.state is ImagePainter.State.Success).not(),
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.fade(Color.White)
                )
        )
        Column {
            Row(modifier = Modifier.padding(top = 50.dp, start = 10.dp, bottom = 10.dp)) {
                Image(
                    painter = storeImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(4.dp)
                        .size(100.dp)
                        .placeholder(
                            visible = (storeImage.state is ImagePainter.State.Success).not(),
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.fade(Color.White)
                        )
                )
                Row(
                    modifier = Modifier.padding(top = 34.dp, start = 6.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1F)) {
                        Text(
                            text = name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = categories.joinToString(separator = ", ", limit = 3),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = darkGrey,
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "${city}, ${state}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 12.sp,
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = ratingYellow,
                        )
                        Text(
                            text = rating.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            color = ratingYellow,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            LazyRow(modifier = Modifier.padding(vertical = 15.dp)) {
                items(listImages) { image ->
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .size(80.dp),
                        contentScale = ContentScale.Crop,
                        painter = image,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
