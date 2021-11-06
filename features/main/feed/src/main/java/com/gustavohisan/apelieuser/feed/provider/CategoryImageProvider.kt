package com.gustavohisan.apelieuser.feed.provider

import com.gustavohisan.apelieuser.feed.R

internal class CategoryImageProvider {

    fun getImageFromCategory(category: String) =
        when (category) {
            "Bebidas" -> R.mipmap.bebidas
            "Cervejas" -> R.mipmap.cervejas
            "Argila" -> R.mipmap.argila
            "Comidas" -> R.mipmap.comidas
            "Roupas" -> R.mipmap.roupas
            "Madeira" -> R.mipmap.madeira
            "Jardinagem" -> R.mipmap.jardinagem
            "Enfeites" -> R.mipmap.enfeites
            "Trico" -> R.mipmap.trico
            "Costura" -> R.mipmap.costura
            else -> R.mipmap.artesanato
        }
}
