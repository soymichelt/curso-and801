package com.soymichel.applistadoapi.views

import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

fun ImageView.fromUrl(url: String) {
    Picasso.get().load(url).transform(RoundedCornersTransformation(20,0)).into(this)
}