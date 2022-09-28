package a.jinkim.bemily.ui

import a.jinkim.bemily.R
import a.jinkim.bemily.adapter.userListAdapter
import a.jinkim.bemily.databinding.ActivityMainBinding
import a.jinkim.bemily.viewmodel.bemilyViewModel
import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ActivityMainBinding 선언
    private lateinit var binding: ActivityMainBinding
    // NavController 선언
    private lateinit var navController: NavController
    // 뷰모델 생성
    private val viewModel by viewModels<bemilyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        binding.apply {
            
            //BottomNavigation 연결
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
            navController = navHostFragment.findNavController()
            bottomNavigationView.setupWithNavController(navController)
        }




    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView: View? = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm != null) imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}