package com.example.mypomo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSliders: List<IntroSliderData>)
    : RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {


    inner class IntroSliderViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val textTitle = view.findViewById<AppCompatTextView>(R.id.textTitle)
        private val textDescription = view.findViewById<AppCompatTextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<AppCompatImageView>(R.id.onboardImage)

        fun bind(introSlide: IntroSliderData){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
      return IntroSliderViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.intro_slide_item_container,
              parent,
              false))
    }

    override fun getItemCount(): Int {
        return introSliders.size
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSliders[position])
    }

}