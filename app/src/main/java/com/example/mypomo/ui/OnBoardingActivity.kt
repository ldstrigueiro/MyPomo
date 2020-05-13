package com.example.mypomo.ui

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.mypomo.IntroSliderAdapter
import com.example.mypomo.IntroSliderData
import com.example.mypomo.R
import com.example.mypomo.databinding.ActivityOnboardingBinding
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : BaseActivity() {

    companion object{
        const val INDICATOR_LAYOUT_MARGIN_LEFT = 8
        const val INDICATOR_LAYOUT_MARGIN_TOP = 0
        const val INDICATOR_LAYOUT_MARGIN_RIGHT = 8
        const val INDICATOR_LAYOUT_MARGIN_BOTTOM = 0
    }

    private val introSliderAdapter: IntroSliderAdapter by lazy{
        IntroSliderAdapter(getOnBoardList())
    }

    lateinit var binding : ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_onboarding
        )
        initSlider()
        initIndicators()
        setCurrentIndicator(0)
        initIndicatorTransition()
        initButtons()
    }

    private fun initButtons(){
        binding.btnNext.setOnClickListener{
            if(binding.introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                binding.introSliderViewPager.currentItem += 1
            }else{
                goToMainActivity()
            }
        }

        binding.btnSkip.setOnClickListener{
            goToMainActivity()
        }

    }

    private fun goToMainActivity() {
        Intent(applicationContext, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    private fun initSlider(){
        binding.introSliderViewPager.adapter = introSliderAdapter
    }

    private fun initIndicators(){
        val indicators = arrayOfNulls<AppCompatImageView>(introSliderAdapter.itemCount)
        val layoutParams: ConstraintLayout.LayoutParams =
            ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        layoutParams.setMargins(
            INDICATOR_LAYOUT_MARGIN_LEFT,
            INDICATOR_LAYOUT_MARGIN_TOP,
            INDICATOR_LAYOUT_MARGIN_RIGHT,
            INDICATOR_LAYOUT_MARGIN_BOTTOM
        )

        for(i in indicators.indices){
            indicators[i] = AppCompatImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainter.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainter.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainter[i] as AppCompatImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
    private fun initIndicatorTransition() {
        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun getOnBoardList(): List<IntroSliderData> {
        return listOf(
            IntroSliderData(
                this.getString(R.string.onboard_title_one),
                this.getString(R.string.onboard_description_one),
                R.drawable.ic_tomato
            ),
            IntroSliderData(
                this.getString(R.string.onboard_title_two),
                this.getString(R.string.onboard_description_two),
                R.drawable.ic_hourglass
            ),
            IntroSliderData(
                this.getString(R.string.onboard_title_three),
                this.getString(R.string.onboard_description_three),
                R.drawable.ic_coffee
            )
        )
    }

}