package com.androidhuman.example.simplegithub.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 생명주기에 의존하여 disposable 을 자동으로 해제해주는 클래스
 */
class AutoClearedDisposable(
        private val lifecycleOwner: AppCompatActivity, // 생명주기를 참조할 액티비티
        private val alwaysClearOnStop: Boolean = true, // onStop() 이 호출되었을때, 관리하고있던 디스포져블을 해제할지 여부 (default-true)
        private val compositeDisposable: CompositeDisposable = CompositeDisposable()
): LifecycleObserver {
    /**
     * Disposable 추가
     */
    fun add(disposable: Disposable) {
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))

        compositeDisposable.add(disposable)
    }

    /**
     * Lifecycle 이 onStop 일때 동작할 디스포져블 해제 함수
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clean() {
        // onStop() 일때 무조건 디스포져블을 해제하지 않는 경우.
        //  액티비티의 isFinishing() 메서드를 사용하여 액티비티가 종료되지 않는 시점 (ex: 다른 액티비티 호출)에만 디스포져블을 해제하지 않도록
        if (!alwaysClearOnStop && !lifecycleOwner.isFinishing) {
            return
        }

        compositeDisposable.clear()
    }

    /**
     * onDestroy() 함수가 호출되면 모든것을 파괴
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        compositeDisposable.clear()

        // 더 이상 액티비티의 생명주기 이벤트를 받지 않는다.
        lifecycleOwner.lifecycle.removeObserver(this)
    }
}
