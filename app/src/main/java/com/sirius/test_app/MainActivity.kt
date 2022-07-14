package com.sirius.test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.wefika.flowlayout.FlowLayout

class MainActivity : AppCompatActivity() {

    private lateinit var ivImage: ImageView
    private lateinit var tvName: MaterialTextView
    private lateinit var tvGradeCnt: MaterialTextView
    private lateinit var tvDescription: MaterialTextView
    private lateinit var tvGradeReviews: MaterialTextView
    private lateinit var tvRatingReviews: MaterialTextView
    private lateinit var ratingHeader: RatingBar
    private lateinit var ratingReviews: RatingBar
    private lateinit var chipsBoxLayout: FlowLayout
    private lateinit var rvReviews: RecyclerView
    private lateinit var btnAction: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()

        val dataModel = DataModel()
        initHeaderImage(dataModel)

        tvName.text = dataModel.name
        tvGradeCnt.text = dataModel.gradeCnt
        ratingHeader.rating = dataModel.rating

        initTags(dataModel)

        tvDescription.text = dataModel.description

        tvRatingReviews.text = dataModel.rating.toString()
        ratingReviews.rating = dataModel.rating
        tvGradeReviews.text = getString(R.string.reviews, dataModel.gradeCnt)

        initReviews(dataModel)

        initButtonAction(dataModel)
    }

    private fun initReviews(dataModel: DataModel) {
        val reviewAdapter = ReviewAdapter(layoutInflater, dataModel.reviews)
        rvReviews.adapter = reviewAdapter
    }

    private fun findViews() {
        ivImage = findViewById(R.id.ivHeader)
        tvName = findViewById(R.id.tvName)
        ratingHeader = findViewById(R.id.ratingHeader)
        ratingReviews = findViewById(R.id.ratingReviews)
        tvGradeCnt = findViewById(R.id.tvGradeCnt)
        tvDescription = findViewById(R.id.tvDescription)
        tvGradeReviews = findViewById(R.id.tvGradeReviews)
        chipsBoxLayout = findViewById(R.id.chipsBoxLayout)
        tvRatingReviews = findViewById(R.id.tvRatingReviews)
        rvReviews = findViewById(R.id.rvReviews)
        btnAction = findViewById(R.id.btnAction)
    }

    private fun initHeaderImage(dataModel: DataModel) {
        Glide.with(this)
            .load(dataModel.image)
            .centerCrop()
            .into(ivImage)
    }

    private fun initTags(dataModel: DataModel) {
        val params = FlowLayout.LayoutParams(
            FlowLayout.LayoutParams.WRAP_CONTENT,
            FlowLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 10, 10, 10)
        dataModel.tags.forEach {
            val chip = layoutInflater.inflate(R.layout.view_chip_text, null, false)
            (chip as? MaterialTextView)?.text = it
            chip.layoutParams = params
            chipsBoxLayout.addView(chip)
        }
    }

    private fun initButtonAction(dataModel: DataModel) {
        btnAction.text = dataModel.action.name
        btnAction.setOnClickListener {
            // just for example
            if (dataModel.action.action == "action_install") {
                Toast.makeText(this, "Install", Toast.LENGTH_SHORT).show()
            }
        }
    }

}