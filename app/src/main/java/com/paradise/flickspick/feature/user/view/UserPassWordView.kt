package com.paradise.flickspick.feature.user.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.paradise.flickspick.databinding.LayoutUserPassWordBinding
import com.paradise.flickspick.feature.user.BaseFrameLayout
import com.paradise.flickspick.feature.user.UserDataViewModel

class UserPassWordView (context: Context, attrs: AttributeSet? = null) : BaseFrameLayout<LayoutUserPassWordBinding>(context, attrs) {
    private var userViewModel: UserDataViewModel
    var passWord = ""

    init {
        userViewModel = ViewModelProvider(context as ViewModelStoreOwner)[UserDataViewModel::class.java]

        vb.userPasswordField.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //값 변경 시 실행되는 함수
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                //입력값 담기
                passWord = vb.userPasswordField.text.toString()

                if(passWord.isNotEmpty()){
                    userViewModel.setPassWordState(UserDataViewModel.State.FULL)
                }else{
                    userViewModel.setPassWordState(UserDataViewModel.State.EMPTY)
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}