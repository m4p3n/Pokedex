package it.mmilani.pokedex.utils

import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import it.mmilani.pokedex.BuildConfig
import it.mmilani.pokedex.R
import it.mmilani.pokedex.api.models.Pokemon
import java.lang.Exception

@BindingAdapter("loadPortrait", "skeleton", requireAll = false)
fun ImageView.loadImage(url : String?, skeleton : View){
    if(url.isNullOrBlank()) return
    val shimmerSkeleton = skeleton as ShimmerFrameLayout
    shimmerSkeleton.startShimmer()

    Handler().postDelayed({
        Picasso.get()
            .load(Uri.parse(url))
            .placeholder(this.context.resources.getDrawable(R.drawable.placeholder))
            .into(this, object : Callback{
                override fun onSuccess() {
                    Log.i("Picasso", "onSuccess")
                    shimmerSkeleton.stopShimmer()
                    shimmerSkeleton.setShimmer(null)
                }

                override fun onError(e: Exception?) {
                    Log.i("Picasso", "onError${e?.message}")
                    shimmerSkeleton.stopShimmer()
                    shimmerSkeleton.setShimmer(null)
                }
            })
    }, BuildConfig.DELAY)
    

}