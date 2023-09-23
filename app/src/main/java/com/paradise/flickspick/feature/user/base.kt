package com.paradise.flickspick.feature.user

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFrameLayout<B : ViewBinding>(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) :
    FrameLayout(context, attrs, defStyleAttr) {

    protected lateinit var vb: B

    init {
        bindCustomView()
    }

    private fun bindCustomView() {
        val type = javaClass.genericSuperclass as ParameterizedType
        val bindingClass = type.actualTypeArguments[0] as Class<*>

        val bindMethod =
            bindingClass.getDeclaredMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.javaPrimitiveType
            )
        vb = bindMethod.invoke(null, LayoutInflater.from(context), this, true) as B

    }
}