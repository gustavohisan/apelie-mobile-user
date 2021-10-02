package com.gustavohisan.apelieuser.product.provider


import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.gustavohisan.apelieuser.main.provider.ProductProvider
import com.gustavohisan.apelieuser.product.presentation.Product

internal class ProductProviderImpl : ProductProvider {

    @ExperimentalPagerApi
    @Composable
    override fun ProductComposable(
        productId: Int,
        onAddToCardSuccess: () -> Unit,
        onBackClicked: () -> Unit
    ) = Product(
        productId = productId,
        onAddToCardSuccess = onAddToCardSuccess,
        onBackClicked = onBackClicked
    )
}
