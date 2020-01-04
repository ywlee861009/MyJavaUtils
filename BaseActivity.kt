package com.example.lifeclean.base_components

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VB: ViewDataBinding, VM:BaseViewModel>: AppCompatActivity() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM
    protected val mDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    /**
     * 액티비티의 R.layout 리소스 리턴
     * setContentView 에서 사용
     *
     * @return 해당 액티비티가 사용할 Layout resource
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     * 액티비티에서 사용할 ViewModel Class 리턴
     *
     * @return 액티비티에서 사용할 viewmodel class::java
     */
    protected abstract fun getViewModel(): Class<VM>

    /**
     * Data Binding 변수 세팅
     * onCreate 시점에 호출
     */
    protected abstract fun initBindingVariables()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initBindingVariables()
    }

    override fun onStop() {
        mDisposable.clear()

        super.onStop()
    }

    /**
     * Data binding 초기 설정
     */
    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = ViewModelProviders.of(this).get(getViewModel())
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }
}
